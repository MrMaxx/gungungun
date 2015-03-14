package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.SpawnPoint;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SpawnPoint entity.
 */
public interface SpawnPointRepository extends JpaRepository<SpawnPoint,Long> {

}
