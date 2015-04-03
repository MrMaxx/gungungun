package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.model.HeroToken;

public class AttackingIsGoodHeuristic extends AbstractHeuristic {


    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        if(gameMove.getType() == GameMove.GameMoveType.ATTACK){
            return 1;
        }

        return 0;
    }

}
