package de.overwatch.gungungun.game.gamemove;


import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.Direction;

public class AttackGameMove extends GameMove{

    private HeroToken target;
    private Direction turnToDirection;

    public AttackGameMove(HeroToken target) {
        this.target = target;
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
    public String toString() {
        return "AttackGameMove{" +
                "targetId=" + target.getId() +
                ", turnToDirection=" + turnToDirection +
                "} " + super.toString();
    }

    @Override
    public int getCost() {
        if(turnToDirection != null){ return 2;}
        return 1;
    }

    @Override
    public GameMoveType getType() {
        return GameMoveType.ATTACK;
    }
}
