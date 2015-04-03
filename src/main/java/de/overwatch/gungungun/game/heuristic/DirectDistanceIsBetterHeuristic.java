package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.NoPathExistsException;

import java.util.List;

public class DirectDistanceIsBetterHeuristic extends AbstractHeuristic {

    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        if(gameMove.getType() != GameMove.GameMoveType.MOVE_TO){
            return 0;
        }
        MoveToGameMove move = (MoveToGameMove)gameMove;
        if(move.getPath().size()<1){return 0;}
        double distance = heroToken.getCoordinate().getDistance(move.getPath().get(move.getPath().size()-1).getCoordinate());


        float score = 0.1f - (0.001f * ((float)distance));
        if(score < 0 ){
            return 0;
        }
        return score;

    }

}
