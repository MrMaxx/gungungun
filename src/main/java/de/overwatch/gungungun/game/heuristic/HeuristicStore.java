package de.overwatch.gungungun.game.heuristic;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HeuristicStore {

    private static final Map<HeuristicName, AbstractHeuristic> HEURISTICS = new HashMap<>();
    static{
        HEURISTICS.put(HeuristicName.CLOSER_DISTANCE_TO_NEAREST_ENEMY, new CloserDistanceToNearestEnemyHeuristic());
        HEURISTICS.put(HeuristicName.GOOD_SHOOTING_POSITION, new GoodShootingPositionHeuristic());
        HEURISTICS.put(HeuristicName.NEAREST_ENEMY_CLOSE_TO_LONG_RANGE_WEAPON_RANGE, new NearestEnemyCloseToLonRangeWeaponRangeHeuristic());
        HEURISTICS.put(HeuristicName.SHOOTING_IS_GOOD, new ShootingIsGoodHeuristic());
    }


    public AbstractHeuristic getHeuristicByName(HeuristicName name){
        return HEURISTICS.get(name);
    }


}
