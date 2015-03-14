package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.domain.TokenBlueprint;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TokenBlueprint entity.
 */
public interface TokenBlueprintRepository extends JpaRepository<TokenBlueprint,Long> {

}
