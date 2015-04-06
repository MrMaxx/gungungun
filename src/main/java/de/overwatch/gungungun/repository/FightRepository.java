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

    @Query("select distinct fight from Fight fight " +
            "   left join fetch fight.participatingPartys pp" +
            "   left join fetch pp.user" +
            "   left join fetch fight.winner w " +
            "   left join fetch w.user " +
            "   where EXISTS " +
            "       (SELECT f FROM Fight f join f.participatingPartys pp" +
            "           where pp.user.id = :userId" +
            "               AND f.if=fight.id" +
            "       )" +
            "   order by fight.id desc")
    List<Fight> findByUserId(@Param("userId") Long userId);

    @Query("" +
            "select fight " +
            "   from Fight fight " +
            "   left join fetch fight.participatingPartys " +
            "   left join fetch fight.arena " +
            "where fight.processedAt is null")
    Collection<Fight> findOpenFights();

}
