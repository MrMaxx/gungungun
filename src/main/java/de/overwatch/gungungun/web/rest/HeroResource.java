package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.TokenBlueprint;
import de.overwatch.gungungun.repository.HeroRepository;
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
 * REST controller for managing Hero.
 */
@RestController
@RequestMapping("/api/users/{userId}/parties/{partyId}")
public class HeroResource {

    private final Logger log = LoggerFactory.getLogger(HeroResource.class);

    @Inject
    private HeroRepository heroRepository;

    @Inject
    private TokenBlueprintRepository tokenBlueprintRepository;

    /**
     * PUT  /heros -> Updates an existing hero.
     */
    @RequestMapping(value = "/heroes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(
            @PathVariable("partyId")Long partyId,
            @PathVariable("userId")Long userId,
            @RequestBody Hero hero
    ) throws URISyntaxException {
        log.debug("REST request to update Hero : {}", hero);
        if (hero.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Hero originalHero = heroRepository.findHero(hero.getId(), partyId, userId);
        if (originalHero == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        TokenBlueprint blueprint = tokenBlueprintRepository.findOne(hero.getTokenBlueprint().getId());
        if(blueprint == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        originalHero.setName(hero.getName());
        originalHero.setTokenBlueprint(blueprint);
        heroRepository.save(originalHero);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /heros -> get all the heros.
     */
    @RequestMapping(value = "/heroes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Hero> getAll(
            @PathVariable("partyId")Long partyId,
            @PathVariable("userId")Long userId ) {
        log.debug("REST request to get all Heros");
        return heroRepository.findHeroes(partyId, userId);
    }

    /**
     * GET  /heros/:id -> get the "id" hero.
     */
    @RequestMapping(value = "/heroes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hero> get(
            @PathVariable("partyId")Long partyId,
            @PathVariable("userId")Long userId,
            @PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Hero : {}", id);
        Hero hero = heroRepository.findHero(id, partyId, userId);
        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

}
