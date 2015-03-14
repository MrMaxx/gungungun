package de.overwatch.gungungun.game.gameevent;


import de.overwatch.gungungun.game.model.HeroToken;

public class TokenDiedEvent extends GameEvent{

    private Long elementId;

    public TokenDiedEvent(HeroToken victim) {
        super();
        this.elementId = victim.getId();
    }

    public Long getElementId() {
        return elementId;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.TOKEN_DIED;
    }

    @Override
    public String toString() {
        return "TokenDiedEvent{" +
                "elementId=" + elementId +
                "} " + super.toString();
    }
}
