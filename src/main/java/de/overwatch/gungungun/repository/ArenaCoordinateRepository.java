package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.ArenaCoordinate;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArenaCoordinate entity.
 */
public interface ArenaCoordinateRepository extends JpaRepository<ArenaCoordinate,Long> {

}
