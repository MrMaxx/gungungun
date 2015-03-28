package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.domain.Hero;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Behavior entity.
 */
public interface BehaviorRepository extends JpaRepository<Behavior,Long> {

    @Query("" +
            "select behavior " +
            "   from Behavior behavior " +
            "where behavior.hero.id = :heroId " +
            "   AND behavior.hero.party.id = :partyId " +
            "   AND behavior.hero.party.user.id = :userId")
    List<Behavior> findBehaviors(@Param("heroId")Long heroId, @Param("partyId")Long partyId, @Param("userId")Long userId);

}
