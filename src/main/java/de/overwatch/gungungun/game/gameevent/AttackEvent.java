package de.overwatch.gungungun.game.gameevent;

import de.overwatch.gungungun.game.model.HeroToken;

public class AttackEvent extends GameEvent{
    private Long elementId;
    private Long targetId;
    private int damage;

    public AttackEvent(HeroToken attacker, HeroToken target, int damage){
        super();
        this.elementId = attacker.getId();
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
    public String toString() {
        return "AttackEvent{" +
                "elementId=" + elementId +
                ", targetId=" + targetId +
                ", damage=" + damage +
                "} " + super.toString();
    }

    @Override
    public GameEvent.GameEventType getType() {
        return GameEventType.ATTACK;
    }

}
