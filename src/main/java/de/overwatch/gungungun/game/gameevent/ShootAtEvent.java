package de.overwatch.gungungun.game.gameevent;

import de.overwatch.gungungun.game.model.HeroToken;

public class ShootAtEvent extends GameEvent{

    private Long elementId;
    private Long targetId;
    private int damage;

    public ShootAtEvent(HeroToken shooter, HeroToken target, int damage){
        super();
        this.elementId = shooter.getId();
        this.targetId = target.getId();
        this.damage = damage;
    }

    public Long getElementId() {
        return elementId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.SHOOT;
    }

    @Override
    public String toString() {
        return "ShootAtEvent{" +
                "elementId=" + elementId +
                ", targetId=" + targetId +
                ", damage=" + damage +
                "} " + super.toString();
    }
}
