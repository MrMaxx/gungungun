package de.overwatch.gungungun.game.gamemove;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.Direction;

import java.util.List;

public class ShootAtGameMove extends GameMove{

    private HeroToken target;
    private Direction turnToDirection;

    public ShootAtGameMove(HeroToken target) {
        this.target = target;
    }

    @Override
    public int getCost() {
        if(turnToDirection != null){ return 2;}
        return 1;
    }

    public void setTurnToDirection(Direction turnToDirection) {
        this.turnToDirection = turnToDirection;
    }

    public Direction getTurnToDirection() {
        return turnToDirection;
    }

    public HeroToken getTarget() {
        return target;
    }

    @Override
    public GameMoveType getType() {
        return GameMoveType.SHOOT;
    }

    @Override
    public String toString() {
        return "ShootAtGameMove{" +
                "targetId=" + target.getId() +
                ", turnToDirection=" + turnToDirection +
                "} " + super.toString();
    }
}
