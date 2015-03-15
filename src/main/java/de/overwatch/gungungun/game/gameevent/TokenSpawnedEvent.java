package de.overwatch.gungungun.game.gameevent;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.Direction;

public class TokenSpawnedEvent extends GameEvent{

    private Long elementId;
    private int actionsLeft;
    private int health;
    private Direction direction;
    private String name;
    private Long tokenBlueprintId;
    private String tokenKey;
    private Coordinate coordinate;

    public TokenSpawnedEvent(HeroToken spawn) {
        super();
        this.elementId = spawn.getId();
        this.actionsLeft = spawn.getActionsLeft();
        this.health = spawn.getHealth();
        this.direction = spawn.getDirection();
        this.name = spawn.getName();
        this.tokenBlueprintId = spawn.getTokenBlueprintId();
        this.tokenKey = spawn.getTokenKey();
        this.coordinate = spawn.getCoordinate();
    }

    public Long getElementId() {
        return elementId;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public int getActionsLeft() {
        return actionsLeft;
    }

    public Long getTokenBlueprintId() {
        return tokenBlueprintId;
    }

    public int getHealth() {
        return health;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.TOKEN_SPAWNED;
    }

    @Override
    public String toString() {
        return "TokenSpawnedEvent{" +
                "elementId=" + elementId +
                ", actionsLeft=" + actionsLeft +
                ", health=" + health +
                ", direction=" + direction +
                ", name='" + name + '\'' +
                ", tokenBlueprintId=" + tokenBlueprintId +
                ", tokenKey='" + tokenKey + '\'' +
                ", coordinate=" + coordinate +
                "} " + super.toString();
    }
}
