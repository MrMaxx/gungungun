package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.TokenBlueprint;
import de.overwatch.gungungun.repository.TokenBlueprintRepository;
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
 * REST controller for managing TokenBlueprint.
 */
@RestController
@RequestMapping("/api")
public class TokenBlueprintResource {

    private final Logger log = LoggerFactory.getLogger(TokenBlueprintResource.class);

    @Inject
    private TokenBlueprintRepository tokenBlueprintRepository;

    /**
     * POST  /tokenBlueprints -> Create a new tokenBlueprint.
     */
    @RequestMapping(value = "/tokenBlueprints",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody TokenBlueprint tokenBlueprint) throws URISyntaxException {
        log.debug("REST request to save TokenBlueprint : {}", tokenBlueprint);
        if (tokenBlueprint.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new tokenBlueprint cannot already have an ID").build();
        }
        tokenBlueprintRepository.save(tokenBlueprint);
        return ResponseEntity.created(new URI("/api/tokenBlueprints/" + tokenBlueprint.getId())).build();
    }

    /**
     * PUT  /tokenBlueprints -> Updates an existing tokenBlueprint.
     */
    @RequestMapping(value = "/tokenBlueprints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody TokenBlueprint tokenBlueprint) throws URISyntaxException {
        log.debug("REST request to update TokenBlueprint : {}", tokenBlueprint);
        if (tokenBlueprint.getId() == null) {
            return create(tokenBlueprint);
        }
        tokenBlueprintRepository.save(tokenBlueprint);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /tokenBlueprints -> get all the tokenBlueprints.
     */
    @RequestMapping(value = "/tokenBlueprints",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TokenBlueprint> getAll() {
        log.debug("REST request to get all TokenBlueprints");
        return tokenBlueprintRepository.findAll();
    }

    /**
     * GET  /tokenBlueprints/:id -> get the "id" tokenBlueprint.
     */
    @RequestMapping(value = "/tokenBlueprints/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TokenBlueprint> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get TokenBlueprint : {}", id);
        TokenBlueprint tokenBlueprint = tokenBlueprintRepository.findOne(id);
        if (tokenBlueprint == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tokenBlueprint, HttpStatus.OK);
    }

    /**
     * DELETE  /tokenBlueprints/:id -> delete the "id" tokenBlueprint.
     */
    @RequestMapping(value = "/tokenBlueprints/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete TokenBlueprint : {}", id);
        tokenBlueprintRepository.delete(id);
    }
}
