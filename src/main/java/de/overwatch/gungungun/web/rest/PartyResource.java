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
@RequestMapping("/api/users/{userId}")
public class PartyResource {

    private final Logger log = LoggerFactory.getLogger(PartyResource.class);

    @Inject
    private PartyRepository partyRepository;

    /**
     * PUT  /partys -> Updates an existing party.
     */
    @RequestMapping(value = "/parties",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(
            @PathVariable("userId")Long userId,
            @RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to update Party : {}", party);
        if (party.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Party orginalParty = partyRepository.findParty(party.getId(), userId);
        if (orginalParty != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        orginalParty.setName(party.getName());
        partyRepository.save(orginalParty);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /partys -> get all the partys.
     */
    @RequestMapping(value = "/parties",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Party> getAll(@PathVariable("userId")Long userId) {
        log.debug("REST request to get all Partys");
        return partyRepository.findParties(userId);
    }

    /**
     * GET  /partys/:id -> get the "id" party.
     */
    @RequestMapping(value = "/parties/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Party> get(
            @PathVariable Long id,
            @PathVariable("userId")Long userId,
            HttpServletResponse response) {
        log.debug("REST request to get Party : {}", id);
        Party party = partyRepository.findParty(id, userId);
        if (party == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(party, HttpStatus.OK);
    }

}
