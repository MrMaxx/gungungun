package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.repository.BehaviorRepository;
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
@RequestMapping("/api")
public class BehaviorResource {

    private final Logger log = LoggerFactory.getLogger(BehaviorResource.class);

    @Inject
    private BehaviorRepository behaviorRepository;

    /**
     * POST  /behaviors -> Create a new behavior.
     */
    @RequestMapping(value = "/behaviors",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Behavior behavior) throws URISyntaxException {
        log.debug("REST request to save Behavior : {}", behavior);
        if (behavior.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new behavior cannot already have an ID").build();
        }
        behaviorRepository.save(behavior);
        return ResponseEntity.created(new URI("/api/behaviors/" + behavior.getId())).build();
    }

    /**
     * PUT  /behaviors -> Updates an existing behavior.
     */
    @RequestMapping(value = "/behaviors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Behavior behavior) throws URISyntaxException {
        log.debug("REST request to update Behavior : {}", behavior);
        if (behavior.getId() == null) {
            return create(behavior);
        }
        behaviorRepository.save(behavior);
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

    /**
     * GET  /behaviors/:id -> get the "id" behavior.
     */
    @RequestMapping(value = "/behaviors/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Behavior> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Behavior : {}", id);
        Behavior behavior = behaviorRepository.findOne(id);
        if (behavior == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(behavior, HttpStatus.OK);
    }

    /**
     * DELETE  /behaviors/:id -> delete the "id" behavior.
     */
    @RequestMapping(value = "/behaviors/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Behavior : {}", id);
        behaviorRepository.delete(id);
    }
}
