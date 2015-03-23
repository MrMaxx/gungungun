package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.NoPathExistsException;

import java.util.List;

public class CloserDistanceToNearestEnemyHeuristic extends AbstractHeuristic {

    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        try{
            List<Coordinate> path = getPathToNearestHeroToken(gameState, heroToken, gameMove);
            float score = 1 - (0.05f * (path.size()-1));
            if(score < 0 ){
                return 0;
            }
            return score;
        }catch(NoPathExistsException npee){
            return 0;
        }

    }

}
