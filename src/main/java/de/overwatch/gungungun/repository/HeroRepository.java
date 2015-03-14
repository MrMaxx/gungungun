package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Hero;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Hero entity.
 */
public interface HeroRepository extends JpaRepository<Hero,Long> {

}
