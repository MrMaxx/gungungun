package de.overwatch.gungungun.game.model;


import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.game.heuristic.HeuristicName;
import de.overwatch.gungungun.game.heuristic.HeuristicStore;
import de.overwatch.gungungun.game.util.Direction;

import java.util.Collection;
import java.util.LinkedList;

public class HeroToken {

    private Long id;
    private String name;
    private Long partyId;
    private String tokenKey;
    private Long tokenBlueprintId;
    private Integer health;
    private Integer actionsPerTurn;
    private Integer longRangeAttackRange;
    private Integer longRangeWeaponDamageMinimum;
    private Integer longRangeWeaponDamageMaximum;
    private Integer shortRangeAttackRange;
    private Integer shortRangeWeaponDamageMinimum;
    private Integer shortRangeWeaponDamageMaximum;
    private Integer moveRange;

    private Coordinate coordinate;
    private Direction direction;
    private int actionsLeft;

    private Collection<ActiveBehavior> behaviors = new LinkedList<>();

    public HeroToken(Hero hero, Coordinate coordinate) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.partyId = hero.getParty().getId();
        this.tokenKey = hero.getTokenBlueprint().getTokenKey();
        this.tokenBlueprintId = hero.getTokenBlueprint().getId();
        this.health = hero.getTokenBlueprint().getHealth();
        this.actionsPerTurn = hero.getTokenBlueprint().getActionsPerTurn();
        this.longRangeAttackRange = hero.getTokenBlueprint().getLongRangeAttackRange();
        this.longRangeWeaponDamageMinimum = hero.getTokenBlueprint().getLongRangeWeaponDamageMinimum();
        this.longRangeWeaponDamageMaximum = hero.getTokenBlueprint().getLongRangeWeaponDamageMaximum();
        this.shortRangeAttackRange = hero.getTokenBlueprint().getShortRangeAttackRange();
        this.shortRangeWeaponDamageMinimum = hero.getTokenBlueprint().getShortRangeWeaponDamageMinimum();
        this.shortRangeWeaponDamageMaximum = hero.getTokenBlueprint().getShortRangeWeaponDamageMaximum();
        this.moveRange = hero.getTokenBlueprint().getMoveRange();

        this.coordinate = coordinate;
        this.direction = Direction.NORTH;
        this.actionsLeft = hero.getTokenBlueprint().getActionsPerTurn();

        if(hero.getBehaviors()!=null){
            for(Behavior behavior: hero.getBehaviors()){
                this.behaviors.add(new ActiveBehavior(behavior));
            }
        }
    }

    public boolean isDead(){
        return this.health <= 0;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void inflictDamage(Integer damage) {
        this.health = this.health - damage;
    }

    public int getActionsLeft() {
        return actionsLeft;
    }

    public void setActionsLeft(int actionsLeft) {
        this.actionsLeft = actionsLeft;
    }

    public void consumeActionsPoints(int actions) {
        this.actionsLeft -= actions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPartyId() {
        return partyId;
    }

    public Collection<ActiveBehavior> getBehaviors() {
        return behaviors;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getActionsPerTurn() {
        return actionsPerTurn;
    }

    public Integer getLongRangeAttackRange() {
        return longRangeAttackRange;
    }

    public Integer getLongRangeWeaponDamageMinimum() {
        return longRangeWeaponDamageMinimum;
    }

    public Integer getLongRangeWeaponDamageMaximum() {
        return longRangeWeaponDamageMaximum;
    }

    public Integer getShortRangeAttackRange() {
        return shortRangeAttackRange;
    }

    public Integer getShortRangeWeaponDamageMinimum() {
        return shortRangeWeaponDamageMinimum;
    }

    public Integer getShortRangeWeaponDamageMaximum() {
        return shortRangeWeaponDamageMaximum;
    }

    public Integer getMoveRange() {
        return moveRange;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Long getTokenBlueprintId() {
        return tokenBlueprintId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroToken)) return false;

        HeroToken heroToken = (HeroToken) o;

        if (actionsLeft != heroToken.actionsLeft) return false;
        if (actionsPerTurn != null ? !actionsPerTurn.equals(heroToken.actionsPerTurn) : heroToken.actionsPerTurn != null)
            return false;
        if (coordinate != null ? !coordinate.equals(heroToken.coordinate) : heroToken.coordinate != null) return false;
        if (direction != heroToken.direction) return false;
        if (health != null ? !health.equals(heroToken.health) : heroToken.health != null) return false;
        if (id != null ? !id.equals(heroToken.id) : heroToken.id != null) return false;
        if (longRangeAttackRange != null ? !longRangeAttackRange.equals(heroToken.longRangeAttackRange) : heroToken.longRangeAttackRange != null)
            return false;
        if (longRangeWeaponDamageMaximum != null ? !longRangeWeaponDamageMaximum.equals(heroToken.longRangeWeaponDamageMaximum) : heroToken.longRangeWeaponDamageMaximum != null)
            return false;
        if (longRangeWeaponDamageMinimum != null ? !longRangeWeaponDamageMinimum.equals(heroToken.longRangeWeaponDamageMinimum) : heroToken.longRangeWeaponDamageMinimum != null)
            return false;
        if (moveRange != null ? !moveRange.equals(heroToken.moveRange) : heroToken.moveRange != null) return false;
        if (name != null ? !name.equals(heroToken.name) : heroToken.name != null) return false;
        if (partyId != null ? !partyId.equals(heroToken.partyId) : heroToken.partyId != null) return false;
        if (shortRangeAttackRange != null ? !shortRangeAttackRange.equals(heroToken.shortRangeAttackRange) : heroToken.shortRangeAttackRange != null)
            return false;
        if (shortRangeWeaponDamageMaximum != null ? !shortRangeWeaponDamageMaximum.equals(heroToken.shortRangeWeaponDamageMaximum) : heroToken.shortRangeWeaponDamageMaximum != null)
            return false;
        if (shortRangeWeaponDamageMinimum != null ? !shortRangeWeaponDamageMinimum.equals(heroToken.shortRangeWeaponDamageMinimum) : heroToken.shortRangeWeaponDamageMinimum != null)
            return false;
        if (tokenBlueprintId != null ? !tokenBlueprintId.equals(heroToken.tokenBlueprintId) : heroToken.tokenBlueprintId != null)
            return false;
        if (tokenKey != null ? !tokenKey.equals(heroToken.tokenKey) : heroToken.tokenKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (partyId != null ? partyId.hashCode() : 0);
        result = 31 * result + (tokenKey != null ? tokenKey.hashCode() : 0);
        result = 31 * result + (tokenBlueprintId != null ? tokenBlueprintId.hashCode() : 0);
        result = 31 * result + (health != null ? health.hashCode() : 0);
        result = 31 * result + (actionsPerTurn != null ? actionsPerTurn.hashCode() : 0);
        result = 31 * result + (longRangeAttackRange != null ? longRangeAttackRange.hashCode() : 0);
        result = 31 * result + (longRangeWeaponDamageMinimum != null ? longRangeWeaponDamageMinimum.hashCode() : 0);
        result = 31 * result + (longRangeWeaponDamageMaximum != null ? longRangeWeaponDamageMaximum.hashCode() : 0);
        result = 31 * result + (shortRangeAttackRange != null ? shortRangeAttackRange.hashCode() : 0);
        result = 31 * result + (shortRangeWeaponDamageMinimum != null ? shortRangeWeaponDamageMinimum.hashCode() : 0);
        result = 31 * result + (shortRangeWeaponDamageMaximum != null ? shortRangeWeaponDamageMaximum.hashCode() : 0);
        result = 31 * result + (moveRange != null ? moveRange.hashCode() : 0);
        result = 31 * result + (coordinate != null ? coordinate.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + actionsLeft;
        return result;
    }
}
