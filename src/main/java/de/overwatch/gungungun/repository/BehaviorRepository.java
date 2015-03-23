package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Behavior;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Behavior entity.
 */
public interface BehaviorRepository extends JpaRepository<Behavior,Long> {

}
