package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.NoPathExistsException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CloserDistanceToNearestEnemyHeuristic implements Heuristic{

    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        Coordinate coordinateToTest = getCoordinateToTest(heroToken, gameMove);


        Collection<HeroToken> enemyTokens = gameState.getEnemyHeroes(heroToken);

        HeroToken closest = null;
        int minDistance = 99999;
        for(HeroToken enemy: enemyTokens){
            try {
                List<Coordinate> path = gameState.getArenaBoard().getShortestPath(coordinateToTest, enemy.getCoordinate());
                if(closest == null){
                    closest = enemy;
                    minDistance = path.size();
                    continue;
                }
                if(path.size()<minDistance){
                    closest = enemy;
                    minDistance = path.size();
                }

            } catch (NoPathExistsException e) {
                // this can happen and is expected
            }
        }
        // this can happen if no token is reachable
        if(closest == null){
            // then we evaluate this move with a zero score
            return 0;
        }

        float score = 1 - (0.07f * (minDistance-1));
        if(score < 0 ){
            return 0;
        }
        return score;
    }

    private Coordinate getCoordinateToTest(HeroToken heroToken, GameMove gameMove){
        if( gameMove.getType() == GameMove.GameMoveType.MOVE_TO ){
            MoveToGameMove move = (MoveToGameMove)gameMove;
            return move.getPath().get(move.getPath().size()-1).getCoordinate();
        }else{
            return heroToken.getCoordinate();
        }
    }
}
