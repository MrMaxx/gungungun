package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.gamemove.MoveToGameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;

public class GoodShootingPositionHeuristic extends AbstractHeuristic {


    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        Coordinate coordinateToTest = getCoordinateToTest(heroToken, gameMove);

        int enemiesInLosCount = 0;

        for(HeroToken enemyToken: gameState.getEnemyHeroes(heroToken)){
            if(gameState.getArenaBoard().isLOSFreeToTarget(coordinateToTest, enemyToken.getCoordinate())){
                enemiesInLosCount++;
            }
        }
        return ((float)enemiesInLosCount)/((float)gameState.getEnemyHeroes(heroToken).size());
    }

}
