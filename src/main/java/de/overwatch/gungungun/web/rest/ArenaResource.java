package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.repository.ArenaRepository;
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
 * REST controller for managing Arena.
 */
@RestController
@RequestMapping("/api")
public class ArenaResource {

    private final Logger log = LoggerFactory.getLogger(ArenaResource.class);

    @Inject
    private ArenaRepository arenaRepository;

    /**
     * GET  /arenas -> get all the arenas.
     */
    @RequestMapping(value = "/arenas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Arena> getAll() {
        log.debug("REST request to get all Arenas");
        return arenaRepository.findAll();
    }

    /**
     * GET  /arenas/:id -> get the "id" arena.
     */
    @RequestMapping(value = "/arenas/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Arena> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Arena : {}", id);
        Arena arena = arenaRepository.findOne(id);
        if (arena == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(arena, HttpStatus.OK);
    }

}
