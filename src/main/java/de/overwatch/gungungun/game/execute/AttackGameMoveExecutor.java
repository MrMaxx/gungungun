package de.overwatch.gungungun.game.execute;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gameevent.AttackEvent;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.gameevent.TokenDiedEvent;
import de.overwatch.gungungun.game.gameevent.TurnToEvent;
import de.overwatch.gungungun.game.gamemove.AttackGameMove;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class AttackGameMoveExecutor {

    public List<GameEvent> execute(GameState gameState, HeroToken heroToken, AttackGameMove attackGameMove){
        List<GameEvent> result = new LinkedList<>();

        if(attackGameMove.getTurnToDirection() != null){
            result.add(new TurnToEvent(heroToken, attackGameMove.getTurnToDirection()));
            heroToken.setDirection(attackGameMove.getTurnToDirection());
        }

        Random rand = new Random();

        int baseDamage = rand.nextInt(heroToken.getShortRangeWeaponDamageMaximum() - heroToken.getShortRangeWeaponDamageMinimum());
        int damage = baseDamage + heroToken.getShortRangeWeaponDamageMinimum();

        attackGameMove.getTarget().inflictDamage(damage);

        result.add(new AttackEvent(heroToken, attackGameMove.getTarget(), damage));

        if(attackGameMove.getTarget().isDead()){
            result.add(new TokenDiedEvent(attackGameMove.getTarget()));
            gameState.heroKilled(attackGameMove.getTarget());
        }

        heroToken.consumeActionsPoints(attackGameMove.getCost());
        return result;
    }

}
