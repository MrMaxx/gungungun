package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;

public class NearestEnemyCloseToLonRangeWeaponRangeHeuristic extends AbstractHeuristic {

    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {

        HeroToken nearestEnemy = getNearestHeroToken(gameState, heroToken, gameMove);
        if(nearestEnemy==null){ return 0; }

        Coordinate coordinateToTest = getCoordinateToTest(heroToken, gameMove);

        if( ! gameState.getArenaBoard().isLOSFreeToTarget(coordinateToTest, nearestEnemy.getCoordinate())){ return 0; }

        int distance = coordinateToTest.getDistance(nearestEnemy.getCoordinate());

        int deviation = Math.abs(heroToken.getLongRangeAttackRange() - distance);

        float score = 1 - (0.1f * deviation);
        if(score < 0 ){
            return 0;
        }
        return score;
    }

}
