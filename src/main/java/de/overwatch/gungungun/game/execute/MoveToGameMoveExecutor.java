package de.overwatch.gungungun.game.execute;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.gameevent.MoveToEvent;
import de.overwatch.gungungun.game.gameevent.TurnToEvent;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MoveToGameMoveExecutor {

    public List<GameEvent> execute(GameState gameState, HeroToken heroToken, MoveToGameMove moveToGameMove){
        List<GameEvent> result = new LinkedList<>();

        for(MoveToGameMove.MoveToSequence mts : moveToGameMove.getPath()){

            if(mts.getDirection() != null){
                result.add(new TurnToEvent(heroToken, mts.getDirection()));
                heroToken.setDirection(mts.getDirection());
            }
            result.add(new MoveToEvent(heroToken, mts.getCoordinate()));
            heroToken.setCoordinate(mts.getCoordinate());
        }

        gameState.heroMoved(heroToken);
        heroToken.consumeActionsPoints(moveToGameMove.getCost());

        return result;
    }

}
