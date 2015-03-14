package de.overwatch.gungungun.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TokenBlueprint.
 */
@Entity
@Table(name = "T_TOKENBLUEPRINT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TokenBlueprint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token_key")
    private String tokenKey;

    @Column(name = "health")
    private Integer health;

    @Column(name = "actions_per_turn")
    private Integer actionsPerTurn;

    @Column(name = "long_range_attack_range")
    private Integer longRangeAttackRange;

    @Column(name = "long_range_weapon_damage_minimum")
    private Integer longRangeWeaponDamageMinimum;

    @Column(name = "long_range_weapon_damage_maximum")
    private Integer longRangeWeaponDamageMaximum;

    @Column(name = "short_range_attack_range")
    private Integer shortRangeAttackRange;

    @Column(name = "short_range_weapon_damage_minimum")
    private Integer shortRangeWeaponDamageMinimum;

    @Column(name = "short_range_weapon_damage_maximum")
    private Integer shortRangeWeaponDamageMaximum;

    @Column(name = "move_range")
    private Integer moveRange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getActionsPerTurn() {
        return actionsPerTurn;
    }

    public void setActionsPerTurn(Integer actionsPerTurn) {
        this.actionsPerTurn = actionsPerTurn;
    }

    public Integer getLongRangeAttackRange() {
        return longRangeAttackRange;
    }

    public void setLongRangeAttackRange(Integer longRangeAttackRange) {
        this.longRangeAttackRange = longRangeAttackRange;
    }

    public Integer getLongRangeWeaponDamageMinimum() {
        return longRangeWeaponDamageMinimum;
    }

    public void setLongRangeWeaponDamageMinimum(Integer longRangeWeaponDamageMinimum) {
        this.longRangeWeaponDamageMinimum = longRangeWeaponDamageMinimum;
    }

    public Integer getLongRangeWeaponDamageMaximum() {
        return longRangeWeaponDamageMaximum;
    }

    public void setLongRangeWeaponDamageMaximum(Integer longRangeWeaponDamageMaximum) {
        this.longRangeWeaponDamageMaximum = longRangeWeaponDamageMaximum;
    }

    public Integer getShortRangeAttackRange() {
        return shortRangeAttackRange;
    }

    public void setShortRangeAttackRange(Integer shortRangeAttackRange) {
        this.shortRangeAttackRange = shortRangeAttackRange;
    }

    public Integer getShortRangeWeaponDamageMinimum() {
        return shortRangeWeaponDamageMinimum;
    }

    public void setShortRangeWeaponDamageMinimum(Integer shortRangeWeaponDamageMinimum) {
        this.shortRangeWeaponDamageMinimum = shortRangeWeaponDamageMinimum;
    }

    public Integer getShortRangeWeaponDamageMaximum() {
        return shortRangeWeaponDamageMaximum;
    }

    public void setShortRangeWeaponDamageMaximum(Integer shortRangeWeaponDamageMaximum) {
        this.shortRangeWeaponDamageMaximum = shortRangeWeaponDamageMaximum;
    }

    public Integer getMoveRange() {
        return moveRange;
    }

    public void setMoveRange(Integer moveRange) {
        this.moveRange = moveRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TokenBlueprint tokenBlueprint = (TokenBlueprint) o;

        if ( ! Objects.equals(id, tokenBlueprint.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TokenBlueprint{" +
                "id=" + id +
                ", tokenKey='" + tokenKey + "'" +
                ", health='" + health + "'" +
                ", actionsPerTurn='" + actionsPerTurn + "'" +
                ", longRangeAttackRange='" + longRangeAttackRange + "'" +
                ", longRangeWeaponDamageMinimum='" + longRangeWeaponDamageMinimum + "'" +
                ", longRangeWeaponDamageMaximum='" + longRangeWeaponDamageMaximum + "'" +
                ", shortRangeAttackRange='" + shortRangeAttackRange + "'" +
                ", shortRangeWeaponDamageMinimum='" + shortRangeWeaponDamageMinimum + "'" +
                ", shortRangeWeaponDamageMaximum='" + shortRangeWeaponDamageMaximum + "'" +
                ", moveRange='" + moveRange + "'" +
                '}';
    }
}
