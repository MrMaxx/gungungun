package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.repository.ArenaRepository;

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
 * Test class for the ArenaResource REST controller.
 *
 * @see ArenaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class ArenaResourceTest {

    private static final String DEFAULT_ARENA_KEY = "SAMPLE_TEXT";
    private static final String UPDATED_ARENA_KEY = "UPDATED_TEXT";

    @Inject
    private ArenaRepository arenaRepository;

    private MockMvc restArenaMockMvc;

    private Arena arena;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArenaResource arenaResource = new ArenaResource();
        ReflectionTestUtils.setField(arenaResource, "arenaRepository", arenaRepository);
        this.restArenaMockMvc = MockMvcBuilders.standaloneSetup(arenaResource).build();
    }

    @Before
    public void initTest() {
        arena = new Arena();
        arena.setArenaKey(DEFAULT_ARENA_KEY);
    }

    @Test
    @Transactional
    public void createArena() throws Exception {
        // Validate the database is empty
        assertThat(arenaRepository.findAll()).hasSize(0);

        // Create the Arena
        restArenaMockMvc.perform(post("/api/arenas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(arena)))
                .andExpect(status().isCreated());

        // Validate the Arena in the database
        List<Arena> arenas = arenaRepository.findAll();
        assertThat(arenas).hasSize(1);
        Arena testArena = arenas.iterator().next();
        assertThat(testArena.getArenaKey()).isEqualTo(DEFAULT_ARENA_KEY);
    }

    @Test
    @Transactional
    public void getAllArenas() throws Exception {
        // Initialize the database
        arenaRepository.saveAndFlush(arena);

        // Get all the arenas
        restArenaMockMvc.perform(get("/api/arenas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(arena.getId().intValue()))
                .andExpect(jsonPath("$.[0].arenaKey").value(DEFAULT_ARENA_KEY.toString()));
    }

    @Test
    @Transactional
    public void getArena() throws Exception {
        // Initialize the database
        arenaRepository.saveAndFlush(arena);

        // Get the arena
        restArenaMockMvc.perform(get("/api/arenas/{id}", arena.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(arena.getId().intValue()))
            .andExpect(jsonPath("$.arenaKey").value(DEFAULT_ARENA_KEY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArena() throws Exception {
        // Get the arena
        restArenaMockMvc.perform(get("/api/arenas/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArena() throws Exception {
        // Initialize the database
        arenaRepository.saveAndFlush(arena);

        // Update the arena
        arena.setArenaKey(UPDATED_ARENA_KEY);
        restArenaMockMvc.perform(put("/api/arenas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(arena)))
                .andExpect(status().isOk());

        // Validate the Arena in the database
        List<Arena> arenas = arenaRepository.findAll();
        assertThat(arenas).hasSize(1);
        Arena testArena = arenas.iterator().next();
        assertThat(testArena.getArenaKey()).isEqualTo(UPDATED_ARENA_KEY);
    }

    @Test
    @Transactional
    public void deleteArena() throws Exception {
        // Initialize the database
        arenaRepository.saveAndFlush(arena);

        // Get the arena
        restArenaMockMvc.perform(delete("/api/arenas/{id}", arena.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Arena> arenas = arenaRepository.findAll();
        assertThat(arenas).hasSize(0);
    }
}
