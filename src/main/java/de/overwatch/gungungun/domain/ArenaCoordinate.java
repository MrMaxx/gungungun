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
 * A ArenaCoordinate.
 */
@Entity
@Table(name = "T_ARENACOORDINATE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ArenaCoordinate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "transparent")
    private Boolean transparent;

    @Column(name = "permeable")
    private Boolean permeable;

    @Column(name = "x")
    private Integer x;

    @Column(name = "y")
    private Integer y;

    @JsonIgnore
    @ManyToOne
    private Arena arena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTransparent() {
        return transparent;
    }

    public void setTransparent(Boolean transparent) {
        this.transparent = transparent;
    }

    public Boolean getPermeable() {
        return permeable;
    }

    public void setPermeable(Boolean permeable) {
        this.permeable = permeable;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArenaCoordinate arenaCoordinate = (ArenaCoordinate) o;

        if ( ! Objects.equals(id, arenaCoordinate.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArenaCoordinate{" +
                "id=" + id +
                ", transparent='" + transparent + "'" +
                ", permeable='" + permeable + "'" +
                ", x='" + x + "'" +
                ", y='" + y + "'" +
                '}';
    }
}
