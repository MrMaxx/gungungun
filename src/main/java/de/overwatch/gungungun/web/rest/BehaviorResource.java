package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.game.heuristic.HeuristicName;
import de.overwatch.gungungun.repository.BehaviorRepository;
import de.overwatch.gungungun.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Behavior.
 */
@RestController
@RequestMapping("/api/users/{userId}/parties/{partyId}/heroes/{heroId}")
public class BehaviorResource {

    private final Logger log = LoggerFactory.getLogger(BehaviorResource.class);

    @Inject
    private BehaviorRepository behaviorRepository;
    @Inject
    private HeroRepository heroRepository;


    /**
     * PUT  /behaviors -> Updates an existing behavior.
     */
    @RequestMapping(value = "/behaviors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(
            @PathVariable("heroId")Long heroId,
            @PathVariable("partyId")Long partyId,
            @PathVariable("userId")Long userId,
            @RequestBody Behavior behavior
    ) throws URISyntaxException {
        log.debug("REST request to update Behavior : {}", behavior);

        Behavior originalBehavior = behaviorRepository.findOne(behavior.getId());
        if(originalBehavior == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        originalBehavior.setPriority(behavior.getPriority());
        originalBehavior.setHeuristicName(behavior.getHeuristicName());

        Hero hero = heroRepository.findHero(heroId, partyId, userId);
        if(hero == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        behaviorRepository.save(originalBehavior);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /behaviors -> get all the behaviors.
     */
    @RequestMapping(value = "/behaviors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Behavior> getAll() {
        log.debug("REST request to get all Behaviors");
        return behaviorRepository.findAll();
    }

}
