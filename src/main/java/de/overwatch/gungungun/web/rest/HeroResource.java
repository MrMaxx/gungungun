package de.overwatch.gungungun.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.overwatch.gungungun.domain.Hero;
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
 * REST controller for managing Hero.
 */
@RestController
@RequestMapping("/api")
public class HeroResource {

    private final Logger log = LoggerFactory.getLogger(HeroResource.class);

    @Inject
    private HeroRepository heroRepository;

    /**
     * POST  /heros -> Create a new hero.
     */
    @RequestMapping(value = "/heros",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Hero hero) throws URISyntaxException {
        log.debug("REST request to save Hero : {}", hero);
        if (hero.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new hero cannot already have an ID").build();
        }
        heroRepository.save(hero);
        return ResponseEntity.created(new URI("/api/heros/" + hero.getId())).build();
    }

    /**
     * PUT  /heros -> Updates an existing hero.
     */
    @RequestMapping(value = "/heros",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Hero hero) throws URISyntaxException {
        log.debug("REST request to update Hero : {}", hero);
        if (hero.getId() == null) {
            return create(hero);
        }
        heroRepository.save(hero);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /heros -> get all the heros.
     */
    @RequestMapping(value = "/heros",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Hero> getAll() {
        log.debug("REST request to get all Heros");
        return heroRepository.findAll();
    }

    /**
     * GET  /heros/:id -> get the "id" hero.
     */
    @RequestMapping(value = "/heros/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Hero> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Hero : {}", id);
        Hero hero = heroRepository.findOne(id);
        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    /**
     * DELETE  /heros/:id -> delete the "id" hero.
     */
    @RequestMapping(value = "/heros/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Hero : {}", id);
        heroRepository.delete(id);
    }
}
