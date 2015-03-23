package de.overwatch.gungungun.game.execute;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.gameevent.ShootAtEvent;
import de.overwatch.gungungun.game.gameevent.TokenDiedEvent;
import de.overwatch.gungungun.game.gameevent.TurnToEvent;
import de.overwatch.gungungun.game.gamemove.ShootAtGameMove;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class ShootAtGameMoveExecutor {

    public List<GameEvent> execute(GameState gameState, HeroToken heroToken, ShootAtGameMove shootAtGameMove){
        List<GameEvent> result = new LinkedList<>();

        if(shootAtGameMove.getTurnToDirection() != null){
            result.add(new TurnToEvent(heroToken, shootAtGameMove.getTurnToDirection()));
            heroToken.setDirection(shootAtGameMove.getTurnToDirection());
        }

        Random rand = new Random();

        int baseDamage = rand.nextInt(heroToken.getLongRangeWeaponDamageMaximum() - heroToken.getLongRangeWeaponDamageMinimum());
        int damage = baseDamage + heroToken.getShortRangeWeaponDamageMinimum();

        shootAtGameMove.getTarget().inflictDamage(damage);

        result.add(new ShootAtEvent(heroToken, shootAtGameMove.getTarget(), damage));

        if(shootAtGameMove.getTarget().isDead()){
            result.add(new TokenDiedEvent(shootAtGameMove.getTarget()));
            gameState.heroKilled(shootAtGameMove.getTarget());
        }

        heroToken.consumeActionsPoints(shootAtGameMove.getCost());

        return result;
    }

}
