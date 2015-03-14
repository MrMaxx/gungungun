package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Fight;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the Fight entity.
 */
public interface FightRepository extends JpaRepository<Fight,Long> {

    @Query("select fight from Fight fight left join fetch fight.participatingPartys where fight.id =:id")
    Fight findOneWithEagerRelationships(@Param("id") Long id);

    @Query("" +
            "select fight " +
            "   from Fight fight " +
            "   left join fetch fight.participatingPartys " +
            "   left join fetch fight.arena " +
            "where fight.processedAt is null")
    Collection<Fight> findOpenFights();

}
