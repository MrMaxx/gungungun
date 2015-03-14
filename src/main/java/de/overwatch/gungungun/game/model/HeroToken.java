package de.overwatch.gungungun.game.model;


import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.game.util.Direction;

public class HeroToken {

    private Long id;
    private String name;
    private Long partyId;
    private String tokenKey;
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

    public HeroToken(Hero hero, Coordinate coordinate) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.partyId = hero.getParty().getId();
        this.tokenKey = hero.getTokenBlueprint().getTokenKey();
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
}
