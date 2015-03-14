package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Party;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Party entity.
 */
public interface PartyRepository extends JpaRepository<Party,Long> {

    @Query("select party from Party party where party.user.login = ?#{principal.username}")
    List<Party> findAllForCurrentUser();

}
