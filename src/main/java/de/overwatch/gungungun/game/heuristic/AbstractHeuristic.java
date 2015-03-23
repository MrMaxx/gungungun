package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.NoPathExistsException;

import java.util.Collection;
import java.util.List;

public abstract class AbstractHeuristic {

    public abstract float evaluate(GameState state, HeroToken token, GameMove gameMove);

    protected Coordinate getCoordinateToTest(HeroToken heroToken, GameMove gameMove){
        if( gameMove.getType() == GameMove.GameMoveType.MOVE_TO ){
            MoveToGameMove move = (MoveToGameMove)gameMove;
            return move.getPath().get(move.getPath().size()-1).getCoordinate();
        }else{
            return heroToken.getCoordinate();
        }
    }

    protected List<Coordinate> getPathToNearestHeroToken(GameState gameState, HeroToken heroToken, GameMove gameMove) throws NoPathExistsException {
        HeroToken nearest = getNearestHeroToken(gameState, heroToken, gameMove);
        if(nearest==null){return null;}
        return gameState.getArenaBoard().getShortestPathWithoutTargetCoordinate( getCoordinateToTest(heroToken, gameMove), nearest.getCoordinate());
    }

    protected HeroToken getNearestHeroToken(GameState gameState, HeroToken heroToken, GameMove gameMove){

        Coordinate coordinateToTest = getCoordinateToTest(heroToken, gameMove);

        Collection<HeroToken> enemyTokens = gameState.getEnemyHeroes(heroToken);

        HeroToken closest = null;
        int minDistance = 99999;
        for(HeroToken enemy: enemyTokens){
            try {
                List<Coordinate> path = gameState.getArenaBoard().getShortestPathWithoutTargetCoordinate(coordinateToTest, enemy.getCoordinate());
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
        return closest;
    }
}
