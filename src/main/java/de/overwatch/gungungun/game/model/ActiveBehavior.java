package de.overwatch.gungungun.game.model;

import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.game.heuristic.HeuristicName;


/** A Copy of the data froma domain Object Behavior
 */
public class ActiveBehavior {


    private HeuristicName heuristicName;
    private int priority;

    public ActiveBehavior(Behavior behavior) {
        this.heuristicName = behavior.getHeuristicName();

        this.priority = behavior.getPriority();
    }

    public ActiveBehavior(HeuristicName heuristicName, int priority) {
        this.heuristicName = heuristicName;

        this.priority = priority;
    }

    public HeuristicName getHeuristicName() {
        return heuristicName;
    }

    public double getPriority() {
        return priority;
    }
}
