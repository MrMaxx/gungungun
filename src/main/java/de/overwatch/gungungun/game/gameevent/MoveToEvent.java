package de.overwatch.gungungun.game.gameevent;

import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;


public class MoveToEvent extends GameEvent{

    private Long elementId;
    private Coordinate coordinate;

    public MoveToEvent(HeroToken hero, Coordinate coordinate){
        super();
        this.elementId = hero.getId();
        this.coordinate = coordinate;
    }

    public Long getElementId() {
        return elementId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.MOVE_TO;
    }

    @Override
    public String toString() {
        return "MoveToEvent{" +
                "elementId=" + elementId +
                ", coordinate=" + coordinate +
                "} " + super.toString();
    }
}
