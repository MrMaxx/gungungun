package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.domain.Hero;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the Hero entity.
 */
public interface HeroRepository extends JpaRepository<Hero,Long> {

    @Query("" +
            "select hero " +
            "   from Hero hero" +
            "   left join fetch hero.behaviors " +
            "where hero.id = :heroId " +
            "   AND hero.party.id = :partyId " +
            "   AND hero.party.user.id = :userId")
    Hero findHero(@Param("heroId")Long heroId, @Param("partyId")Long partyId, @Param("userId")Long userId);

    @Query("" +
            "select distinct hero " +
            "   from Hero hero " +
            "   left join fetch hero.behaviors " +
            "where hero.party.id = :partyId " +
            "   AND hero.party.user.id = :userId")
    List<Hero> findHeroes(@Param("partyId")Long partyId, @Param("userId")Long userId);

}
