package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.game.heuristic.HeuristicName;
import de.overwatch.gungungun.repository.BehaviorRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BehaviorResource REST controller.
 *
 * @see BehaviorResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class BehaviorResourceTest {


    private static final Integer DEFAULT_PRIORITY = 0;
    private static final Integer UPDATED_PRIORITY = 1;
    private static final String DEFAULT_HEURISTIC_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_HEURISTIC_NAME = "UPDATED_TEXT";

    @Inject
    private BehaviorRepository behaviorRepository;

    private MockMvc restBehaviorMockMvc;

    private Behavior behavior;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BehaviorResource behaviorResource = new BehaviorResource();
        ReflectionTestUtils.setField(behaviorResource, "behaviorRepository", behaviorRepository);
        this.restBehaviorMockMvc = MockMvcBuilders.standaloneSetup(behaviorResource).build();
    }

    @Before
    public void initTest() {
        behavior = new Behavior();
        behavior.setPriority(DEFAULT_PRIORITY);
        behavior.setHeuristicName(HeuristicName.CLOSER_DISTANCE_TO_NEAREST_ENEMY);
    }

    @Test
    @Transactional
    public void createBehavior() throws Exception {
        // Validate the database is empty
        assertThat(behaviorRepository.findAll()).hasSize(0);

        // Create the Behavior
        restBehaviorMockMvc.perform(post("/api/behaviors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(behavior)))
                .andExpect(status().isCreated());

        // Validate the Behavior in the database
        List<Behavior> behaviors = behaviorRepository.findAll();
        assertThat(behaviors).hasSize(1);
        Behavior testBehavior = behaviors.iterator().next();
        assertThat(testBehavior.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testBehavior.getHeuristicName()).isEqualTo(DEFAULT_HEURISTIC_NAME);
    }

    @Test
    @Transactional
    public void getAllBehaviors() throws Exception {
        // Initialize the database
        behaviorRepository.saveAndFlush(behavior);

        // Get all the behaviors
        restBehaviorMockMvc.perform(get("/api/behaviors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(behavior.getId().intValue()))
                .andExpect(jsonPath("$.[0].priority").value(DEFAULT_PRIORITY))
                .andExpect(jsonPath("$.[0].heuristicName").value(DEFAULT_HEURISTIC_NAME.toString()));
    }

    @Test
    @Transactional
    public void getBehavior() throws Exception {
        // Initialize the database
        behaviorRepository.saveAndFlush(behavior);

        // Get the behavior
        restBehaviorMockMvc.perform(get("/api/behaviors/{id}", behavior.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(behavior.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.heuristicName").value(DEFAULT_HEURISTIC_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBehavior() throws Exception {
        // Get the behavior
        restBehaviorMockMvc.perform(get("/api/behaviors/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBehavior() throws Exception {
        // Initialize the database
        behaviorRepository.saveAndFlush(behavior);

        // Update the behavior
        behavior.setPriority(UPDATED_PRIORITY);
        behavior.setHeuristicName(HeuristicName.GOOD_SHOOTING_POSITION);
        restBehaviorMockMvc.perform(put("/api/behaviors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(behavior)))
                .andExpect(status().isOk());

        // Validate the Behavior in the database
        List<Behavior> behaviors = behaviorRepository.findAll();
        assertThat(behaviors).hasSize(1);
        Behavior testBehavior = behaviors.iterator().next();
        assertThat(testBehavior.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testBehavior.getHeuristicName()).isEqualTo(UPDATED_HEURISTIC_NAME);
    }

    @Test
    @Transactional
    public void deleteBehavior() throws Exception {
        // Initialize the database
        behaviorRepository.saveAndFlush(behavior);

        // Get the behavior
        restBehaviorMockMvc.perform(delete("/api/behaviors/{id}", behavior.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Behavior> behaviors = behaviorRepository.findAll();
        assertThat(behaviors).hasSize(0);
    }
}
