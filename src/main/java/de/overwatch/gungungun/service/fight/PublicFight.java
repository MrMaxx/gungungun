package de.overwatch.gungungun.service.fight;

import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.domain.Party;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.LinkedList;


public class PublicFight {

    private Long id;
    private Collection<PublicUser> participatingUser;
    private PublicUser winner;
    private DateTime createdAt;
    private DateTime processedAt;
    private PublicArena arena;

    public PublicFight(Fight fight) {
        this.id = fight.getId();
        this.participatingUser = new LinkedList<>();
        for(Party party : fight.getParticipatingPartys()){
            participatingUser.add(new PublicUser(party.getUser()));
        }
        if(fight.getWinner() != null){
            this.winner = new PublicUser(fight.getWinner().getUser());
        }

        this.createdAt = fight.getCreatedAt();
        this.processedAt = fight.getProcessedAt();
        this.arena = new PublicArena(fight.getArena());
    }

    public Long getId() {
        return id;
    }

    public Collection<PublicUser> getParticipatingUser() {
        return participatingUser;
    }

    public PublicUser getWinner() {
        return winner;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getProcessedAt() {
        return processedAt;
    }

    public PublicArena getArena() {
        return arena;
    }
}
