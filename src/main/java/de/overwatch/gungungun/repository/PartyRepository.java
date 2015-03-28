package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Party;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PartyRepository extends JpaRepository<Party,Long> {

    @Query("" +
            "select party " +
            "   from Party party " +
            "where party.id = :partyId " +
            "   AND party.user.id = :userId")
    Party findParty(@Param("partyId")Long partyId, @Param("userId")Long userId);

    @Query("" +
            "select party " +
            "   from Party party " +
            "where party.user.id = :userId")
    List<Party> findParties(@Param("userId")Long userId);

}
