package de.overwatch.gungungun.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.overwatch.gungungun.domain.util.CustomDateTimeDeserializer;
import de.overwatch.gungungun.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Fight.
 */
@Entity
@Table(name = "T_FIGHT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "created_at", nullable = false)
    private DateTime createdAt;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "processed_at", nullable = false)
    private DateTime processedAt;

    @Column(name = "resulting_events")
    @Lob
    private String resultingEvents;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "T_FIGHT_PARTICIPATINGPARTY",
               joinColumns = @JoinColumn(name="Fights_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="participatingPartys_id", referencedColumnName="ID"))
    private Set<Party> participatingPartys = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = true)
    private Party winner;

    @ManyToOne
    private Arena arena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Party getWinner() {
        return winner;
    }

    public void setWinner(Party winner) {
        this.winner = winner;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(DateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getResultingEvents() {
        return resultingEvents;
    }

    public void setResultingEvents(String resultingEvents) {
        this.resultingEvents = resultingEvents;
    }

    public Set<Party> getParticipatingPartys() {
        return participatingPartys;
    }

    public void setParticipatingPartys(Set<Party> partys) {
        this.participatingPartys = partys;
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

        Fight fight = (Fight) o;

        if ( ! Objects.equals(id, fight.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", createdAt='" + createdAt + "'" +
                ", processedAt='" + processedAt + "'" +
                ", resultingEvents='" + resultingEvents + "'" +
                '}';
    }
}
