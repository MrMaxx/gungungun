package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.SpawnPoint;
import de.overwatch.gungungun.repository.SpawnPointRepository;

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
 * Test class for the SpawnPointResource REST controller.
 *
 * @see SpawnPointResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class SpawnPointResourceTest {


    private static final Integer DEFAULT_X = 0;
    private static final Integer UPDATED_X = 1;

    private static final Integer DEFAULT_Y = 0;
    private static final Integer UPDATED_Y = 1;

    private static final Integer DEFAULT_GROUP_ID = 0;
    private static final Integer UPDATED_GROUP_ID = 1;

    @Inject
    private SpawnPointRepository spawnPointRepository;

    private MockMvc restSpawnPointMockMvc;

    private SpawnPoint spawnPoint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SpawnPointResource spawnPointResource = new SpawnPointResource();
        ReflectionTestUtils.setField(spawnPointResource, "spawnPointRepository", spawnPointRepository);
        this.restSpawnPointMockMvc = MockMvcBuilders.standaloneSetup(spawnPointResource).build();
    }

    @Before
    public void initTest() {
        spawnPoint = new SpawnPoint();
        spawnPoint.setX(DEFAULT_X);
        spawnPoint.setY(DEFAULT_Y);
        spawnPoint.setGroupId(DEFAULT_GROUP_ID);
    }

    @Test
    @Transactional
    public void createSpawnPoint() throws Exception {
        // Validate the database is empty
        assertThat(spawnPointRepository.findAll()).hasSize(0);

        // Create the SpawnPoint
        restSpawnPointMockMvc.perform(post("/api/spawnPoints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(spawnPoint)))
                .andExpect(status().isCreated());

        // Validate the SpawnPoint in the database
        List<SpawnPoint> spawnPoints = spawnPointRepository.findAll();
        assertThat(spawnPoints).hasSize(1);
        SpawnPoint testSpawnPoint = spawnPoints.iterator().next();
        assertThat(testSpawnPoint.getX()).isEqualTo(DEFAULT_X);
        assertThat(testSpawnPoint.getY()).isEqualTo(DEFAULT_Y);
        assertThat(testSpawnPoint.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    }

    @Test
    @Transactional
    public void getAllSpawnPoints() throws Exception {
        // Initialize the database
        spawnPointRepository.saveAndFlush(spawnPoint);

        // Get all the spawnPoints
        restSpawnPointMockMvc.perform(get("/api/spawnPoints"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(spawnPoint.getId().intValue()))
                .andExpect(jsonPath("$.[0].x").value(DEFAULT_X))
                .andExpect(jsonPath("$.[0].y").value(DEFAULT_Y))
                .andExpect(jsonPath("$.[0].groupId").value(DEFAULT_GROUP_ID));
    }

    @Test
    @Transactional
    public void getSpawnPoint() throws Exception {
        // Initialize the database
        spawnPointRepository.saveAndFlush(spawnPoint);

        // Get the spawnPoint
        restSpawnPointMockMvc.perform(get("/api/spawnPoints/{id}", spawnPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(spawnPoint.getId().intValue()))
            .andExpect(jsonPath("$.x").value(DEFAULT_X))
            .andExpect(jsonPath("$.y").value(DEFAULT_Y))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID));
    }

    @Test
    @Transactional
    public void getNonExistingSpawnPoint() throws Exception {
        // Get the spawnPoint
        restSpawnPointMockMvc.perform(get("/api/spawnPoints/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpawnPoint() throws Exception {
        // Initialize the database
        spawnPointRepository.saveAndFlush(spawnPoint);

        // Update the spawnPoint
        spawnPoint.setX(UPDATED_X);
        spawnPoint.setY(UPDATED_Y);
        spawnPoint.setGroupId(UPDATED_GROUP_ID);
        restSpawnPointMockMvc.perform(put("/api/spawnPoints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(spawnPoint)))
                .andExpect(status().isOk());

        // Validate the SpawnPoint in the database
        List<SpawnPoint> spawnPoints = spawnPointRepository.findAll();
        assertThat(spawnPoints).hasSize(1);
        SpawnPoint testSpawnPoint = spawnPoints.iterator().next();
        assertThat(testSpawnPoint.getX()).isEqualTo(UPDATED_X);
        assertThat(testSpawnPoint.getY()).isEqualTo(UPDATED_Y);
        assertThat(testSpawnPoint.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    }

    @Test
    @Transactional
    public void deleteSpawnPoint() throws Exception {
        // Initialize the database
        spawnPointRepository.saveAndFlush(spawnPoint);

        // Get the spawnPoint
        restSpawnPointMockMvc.perform(delete("/api/spawnPoints/{id}", spawnPoint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SpawnPoint> spawnPoints = spawnPointRepository.findAll();
        assertThat(spawnPoints).hasSize(0);
    }
}
