package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.repository.HeroRepository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HeroResource REST controller.
 *
 * @see HeroResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class HeroResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";

    @Inject
    private HeroRepository heroRepository;

    private MockMvc restHeroMockMvc;

    private Hero hero;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HeroResource heroResource = new HeroResource();
        ReflectionTestUtils.setField(heroResource, "heroRepository", heroRepository);
        this.restHeroMockMvc = MockMvcBuilders.standaloneSetup(heroResource).build();
    }

    @Before
    public void initTest() {
        hero = new Hero();
        hero.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createHero() throws Exception {
        // Validate the database is empty
        assertThat(heroRepository.findAll()).hasSize(0);

        // Create the Hero
        restHeroMockMvc.perform(post("/api/heros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hero)))
                .andExpect(status().isCreated());

        // Validate the Hero in the database
        List<Hero> heros = heroRepository.findAll();
        assertThat(heros).hasSize(1);
        Hero testHero = heros.iterator().next();
        assertThat(testHero.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllHeros() throws Exception {
        // Initialize the database
        heroRepository.saveAndFlush(hero);

        // Get all the heros
        restHeroMockMvc.perform(get("/api/heros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(hero.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getHero() throws Exception {
        // Initialize the database
        heroRepository.saveAndFlush(hero);

        // Get the hero
        restHeroMockMvc.perform(get("/api/heros/{id}", hero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(hero.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHero() throws Exception {
        // Get the hero
        restHeroMockMvc.perform(get("/api/heros/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHero() throws Exception {
        // Initialize the database
        heroRepository.saveAndFlush(hero);

        // Update the hero
        hero.setName(UPDATED_NAME);
        restHeroMockMvc.perform(put("/api/heros")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hero)))
                .andExpect(status().isOk());

        // Validate the Hero in the database
        List<Hero> heros = heroRepository.findAll();
        assertThat(heros).hasSize(1);
        Hero testHero = heros.iterator().next();
        assertThat(testHero.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteHero() throws Exception {
        // Initialize the database
        heroRepository.saveAndFlush(hero);

        // Get the hero
        restHeroMockMvc.perform(delete("/api/heros/{id}", hero.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Hero> heros = heroRepository.findAll();
        assertThat(heros).hasSize(0);
    }
}
