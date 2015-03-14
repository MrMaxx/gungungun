package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.repository.PartyRepository;
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
 * REST controller for managing Party.
 */
@RestController
@RequestMapping("/api")
public class PartyResource {

    private final Logger log = LoggerFactory.getLogger(PartyResource.class);

    @Inject
    private PartyRepository partyRepository;

    /**
     * POST  /partys -> Create a new party.
     */
    @RequestMapping(value = "/partys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to save Party : {}", party);
        if (party.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new party cannot already have an ID").build();
        }
        partyRepository.save(party);
        return ResponseEntity.created(new URI("/api/partys/" + party.getId())).build();
    }

    /**
     * PUT  /partys -> Updates an existing party.
     */
    @RequestMapping(value = "/partys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to update Party : {}", party);
        if (party.getId() == null) {
            return create(party);
        }
        partyRepository.save(party);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /partys -> get all the partys.
     */
    @RequestMapping(value = "/partys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Party> getAll() {
        log.debug("REST request to get all Partys");
        return partyRepository.findAll();
    }

    /**
     * GET  /partys/:id -> get the "id" party.
     */
    @RequestMapping(value = "/partys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Party> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Party : {}", id);
        Party party = partyRepository.findOne(id);
        if (party == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(party, HttpStatus.OK);
    }

    /**
     * DELETE  /partys/:id -> delete the "id" party.
     */
    @RequestMapping(value = "/partys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Party : {}", id);
        partyRepository.delete(id);
    }
}
