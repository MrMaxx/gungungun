package de.overwatch.gungungun.game.gameevent;


import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.Direction;

public class TurnToEvent extends GameEvent{

    private Long elementId;
    private Direction direction;


    public TurnToEvent(HeroToken heroToken, Direction direction) {
        super();
        this.elementId = heroToken.getId();
        this.direction = direction;
    }

    public Long getElementId() {
        return elementId;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.TURN_TO;
    }

    @Override
    public String toString() {
        return "TurnToEvent{" +
                "elementId=" + elementId +
                ", direction=" + direction +
                "} " + super.toString();
    }
}
