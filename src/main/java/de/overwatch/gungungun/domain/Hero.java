package de.overwatch.gungungun.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Hero.
 */
@Entity
@Table(name = "T_HERO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    private Party party;

    @ManyToOne
    private TokenBlueprint tokenBlueprint;

    @OneToMany(mappedBy = "hero")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Behavior> behaviors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public TokenBlueprint getTokenBlueprint() {
        return tokenBlueprint;
    }

    public void setTokenBlueprint(TokenBlueprint tokenBlueprint) {
        this.tokenBlueprint = tokenBlueprint;
    }

    public Set<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hero hero = (Hero) o;

        if ( ! Objects.equals(id, hero.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + "'" +
                '}';
    }
}
