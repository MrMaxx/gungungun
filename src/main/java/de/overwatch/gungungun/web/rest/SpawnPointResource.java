package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.SpawnPoint;
import de.overwatch.gungungun.repository.SpawnPointRepository;
import de.overwatch.gungungun.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing SpawnPoint.
 */
@RestController
@RequestMapping("/api")
public class SpawnPointResource {

    private final Logger log = LoggerFactory.getLogger(SpawnPointResource.class);

    @Inject
    private SpawnPointRepository spawnPointRepository;

    /**
     * POST  /spawnPoints -> Create a new spawnPoint.
     */
    @RequestMapping(value = "/spawnPoints",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody SpawnPoint spawnPoint) throws URISyntaxException {
        log.debug("REST request to save SpawnPoint : {}", spawnPoint);
        if (spawnPoint.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new spawnPoint cannot already have an ID").build();
        }
        spawnPointRepository.save(spawnPoint);
        return ResponseEntity.created(new URI("/api/spawnPoints/" + spawnPoint.getId())).build();
    }

    /**
     * PUT  /spawnPoints -> Updates an existing spawnPoint.
     */
    @RequestMapping(value = "/spawnPoints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody SpawnPoint spawnPoint) throws URISyntaxException {
        log.debug("REST request to update SpawnPoint : {}", spawnPoint);
        if (spawnPoint.getId() == null) {
            return create(spawnPoint);
        }
        spawnPointRepository.save(spawnPoint);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /spawnPoints -> get all the spawnPoints.
     */
    @RequestMapping(value = "/spawnPoints",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SpawnPoint>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<SpawnPoint> page = spawnPointRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/spawnPoints", offset, limit);
        return new ResponseEntity<List<SpawnPoint>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /spawnPoints/:id -> get the "id" spawnPoint.
     */
    @RequestMapping(value = "/spawnPoints/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SpawnPoint> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get SpawnPoint : {}", id);
        SpawnPoint spawnPoint = spawnPointRepository.findOne(id);
        if (spawnPoint == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spawnPoint, HttpStatus.OK);
    }

    /**
     * DELETE  /spawnPoints/:id -> delete the "id" spawnPoint.
     */
    @RequestMapping(value = "/spawnPoints/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete SpawnPoint : {}", id);
        spawnPointRepository.delete(id);
    }
}
