package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.domain.User;
import de.overwatch.gungungun.repository.ArenaRepository;
import de.overwatch.gungungun.repository.FightRepository;
import de.overwatch.gungungun.repository.PartyRepository;
import de.overwatch.gungungun.repository.UserRepository;
import de.overwatch.gungungun.web.rest.util.PaginationUtil;
import org.joda.time.DateTime;
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
import java.util.Random;

/**
 * REST controller for managing Fights.
 */
@RestController
@RequestMapping("/api")
public class FightResource {

    private final Logger log = LoggerFactory.getLogger(FightResource.class);

    @Inject
    private FightRepository fightRepository;
    @Inject
    private ArenaRepository arenaRepository;
    @Inject
    private PartyRepository partyRepository;

    /**
     * POST  /fights -> Create a new fight.
     */
    @RequestMapping(value = "/fights",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(
            @RequestParam(value = "attackingUserId", required = true) Long attackingUserId,
            @RequestParam(value = "defendingUserId", required = true) Long defendingUserId
    ) throws URISyntaxException {
        log.debug("REST request to create Fight : attacker:{}, defender:{}", attackingUserId, defendingUserId);

        List<Party> attackingParties =  partyRepository.findParties(attackingUserId);
        List<Party> defendingParties =  partyRepository.findParties(defendingUserId);

        List<Arena> arenas = arenaRepository.findAll();


        if(attackingParties == null || defendingParties == null || arenas == null
                || attackingParties.size() == 0 || defendingParties.size() == 0
                || arenas.size() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Random rand = new Random();
        Arena arena = arenas.get(rand.nextInt(arenas.size()));

        Fight fight = new Fight();
        fight.setArena(arena);
        fight.getParticipatingPartys().add(attackingParties.get(0));
        fight.getParticipatingPartys().add(defendingParties.get(0));
        fight.setCreatedAt(new DateTime());

        fightRepository.save(fight);
        return ResponseEntity.created(new URI("/api/fights/" + fight.getId())).build();
    }


    /**
     * GET  /fights -> get all the fights.
     */
    @RequestMapping(value = "/fights",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Fight>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Fight> page = fightRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fights", offset, limit);
        return new ResponseEntity<List<Fight>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fights/:id -> get the "id" fight.
     */
    @RequestMapping(value = "/fights/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Fight> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Fight : {}", id);
        Fight fight = fightRepository.findOneWithEagerRelationships(id);
        if (fight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fight, HttpStatus.OK);
    }

}
