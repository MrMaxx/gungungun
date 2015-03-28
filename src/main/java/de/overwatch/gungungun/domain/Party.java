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
 * A Party.
 */
@Entity
@Table(name = "T_PARTY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "party")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Hero> heroes = new HashSet<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heros) {
        this.heroes = heros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Party party = (Party) o;

        if ( ! Objects.equals(id, party.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", name='" + name + "'" +
                '}';
    }
}
