package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.Arena;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Arena entity.
 */
public interface ArenaRepository extends JpaRepository<Arena,Long> {

}
