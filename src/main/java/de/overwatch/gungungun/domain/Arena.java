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
 * A Arena.
 */
@Entity
@Table(name = "T_ARENA")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Arena implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "arena_key")
    private String arenaKey;

    @OneToMany(mappedBy = "arena")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ArenaCoordinate> arenaCoordinates = new HashSet<>();

    @OneToMany(mappedBy = "arena")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SpawnPoint> spawnPoints = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArenaKey() {
        return arenaKey;
    }

    public void setArenaKey(String arenaKey) {
        this.arenaKey = arenaKey;
    }

    public Set<ArenaCoordinate> getArenaCoordinates() {
        return arenaCoordinates;
    }

    public void setArenaCoordinates(Set<ArenaCoordinate> arenaCoordinates) {
        this.arenaCoordinates = arenaCoordinates;
    }

    public Set<SpawnPoint> getSpawnPoints() {
        return spawnPoints;
    }

    public void setSpawnPoints(Set<SpawnPoint> spawnPoints) {
        this.spawnPoints = spawnPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Arena arena = (Arena) o;

        if ( ! Objects.equals(id, arena.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Arena{" +
                "id=" + id +
                ", arenaKey='" + arenaKey + "'" +
                '}';
    }
}
