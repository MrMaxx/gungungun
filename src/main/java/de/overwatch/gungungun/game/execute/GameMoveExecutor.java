package de.overwatch.gungungun.game.execute;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.gamemove.AttackGameMove;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.gamemove.ShootAtGameMove;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Component
public class GameMoveExecutor {

    @Inject private MoveToGameMoveExecutor moveToGameMoveExecutor;
    @Inject private ShootAtGameMoveExecutor shootAtGameMoveExecutor;
    @Inject private AttackGameMoveExecutor attackGameMoveExecutor;


    public List<GameEvent> execute(GameState gameState, HeroToken heroToken, GameMove gameMove){

        if(gameMove.getType() == GameMove.GameMoveType.MOVE_TO){
            return moveToGameMoveExecutor.execute(gameState, heroToken, (MoveToGameMove)gameMove);
        }
        if(gameMove.getType() == GameMove.GameMoveType.SHOOT){
            return shootAtGameMoveExecutor.execute(gameState, heroToken, (ShootAtGameMove)gameMove);
        }
        if(gameMove.getType() == GameMove.GameMoveType.ATTACK){
            return attackGameMoveExecutor.execute(gameState, heroToken, (AttackGameMove)gameMove);
        }
        // Else its GameMoveType.WAIT
        return Collections.emptyList();

    }

}
