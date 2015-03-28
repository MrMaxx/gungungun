package de.overwatch.gungungun.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.overwatch.gungungun.game.heuristic.HeuristicName;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Behavior.
 */
@Entity
@Table(name = "T_BEHAVIOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Behavior implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "heuristic_name")
    @Enumerated(EnumType.STRING)
    private HeuristicName heuristicName;

    @JsonIgnore
    @ManyToOne
    private Hero hero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public HeuristicName getHeuristicName() {
        return heuristicName;
    }

    public void setHeuristicName(HeuristicName heuristicName) {
        this.heuristicName = heuristicName;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Behavior behavior = (Behavior) o;

        if ( ! Objects.equals(id, behavior.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Behavior{" +
                "id=" + id +
                ", priority='" + priority + "'" +
                ", heuristicName='" + heuristicName + "'" +
                '}';
    }
}
