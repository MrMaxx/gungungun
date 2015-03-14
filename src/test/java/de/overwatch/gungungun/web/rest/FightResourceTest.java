package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.repository.FightRepository;

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
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FightResource REST controller.
 *
 * @see FightResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class FightResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    private static final DateTime DEFAULT_CREATED_AT = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_CREATED_AT = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_CREATED_AT_STR = dateTimeFormatter.print(DEFAULT_CREATED_AT);

    private static final DateTime DEFAULT_PROCESSED_AT = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_PROCESSED_AT = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_PROCESSED_AT_STR = dateTimeFormatter.print(DEFAULT_PROCESSED_AT);
    private static final String DEFAULT_RESULTING_EVENTS = "SAMPLE_TEXT";
    private static final String UPDATED_RESULTING_EVENTS = "UPDATED_TEXT";

    @Inject
    private FightRepository fightRepository;

    private MockMvc restFightMockMvc;

    private Fight fight;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FightResource fightResource = new FightResource();
        ReflectionTestUtils.setField(fightResource, "fightRepository", fightRepository);
        this.restFightMockMvc = MockMvcBuilders.standaloneSetup(fightResource).build();
    }

    @Before
    public void initTest() {
        fight = new Fight();
        fight.setCreatedAt(DEFAULT_CREATED_AT);
        fight.setProcessedAt(DEFAULT_PROCESSED_AT);
        fight.setResultingEvents(DEFAULT_RESULTING_EVENTS);
    }

    @Test
    @Transactional
    public void createFight() throws Exception {
        // Validate the database is empty
        assertThat(fightRepository.findAll()).hasSize(0);

        // Create the Fight
        restFightMockMvc.perform(post("/api/fights")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fight)))
                .andExpect(status().isCreated());

        // Validate the Fight in the database
        List<Fight> fights = fightRepository.findAll();
        assertThat(fights).hasSize(1);
        Fight testFight = fights.iterator().next();
        assertThat(testFight.getCreatedAt().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testFight.getProcessedAt().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_PROCESSED_AT);
        assertThat(testFight.getResultingEvents()).isEqualTo(DEFAULT_RESULTING_EVENTS);
    }

    @Test
    @Transactional
    public void getAllFights() throws Exception {
        // Initialize the database
        fightRepository.saveAndFlush(fight);

        // Get all the fights
        restFightMockMvc.perform(get("/api/fights"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(fight.getId().intValue()))
                .andExpect(jsonPath("$.[0].createdAt").value(DEFAULT_CREATED_AT_STR))
                .andExpect(jsonPath("$.[0].processedAt").value(DEFAULT_PROCESSED_AT_STR))
                .andExpect(jsonPath("$.[0].resultingEvents").value(DEFAULT_RESULTING_EVENTS.toString()));
    }

    @Test
    @Transactional
    public void getFight() throws Exception {
        // Initialize the database
        fightRepository.saveAndFlush(fight);

        // Get the fight
        restFightMockMvc.perform(get("/api/fights/{id}", fight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(fight.getId().intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT_STR))
            .andExpect(jsonPath("$.processedAt").value(DEFAULT_PROCESSED_AT_STR))
            .andExpect(jsonPath("$.resultingEvents").value(DEFAULT_RESULTING_EVENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFight() throws Exception {
        // Get the fight
        restFightMockMvc.perform(get("/api/fights/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFight() throws Exception {
        // Initialize the database
        fightRepository.saveAndFlush(fight);

        // Update the fight
        fight.setCreatedAt(UPDATED_CREATED_AT);
        fight.setProcessedAt(UPDATED_PROCESSED_AT);
        fight.setResultingEvents(UPDATED_RESULTING_EVENTS);
        restFightMockMvc.perform(put("/api/fights")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(fight)))
                .andExpect(status().isOk());

        // Validate the Fight in the database
        List<Fight> fights = fightRepository.findAll();
        assertThat(fights).hasSize(1);
        Fight testFight = fights.iterator().next();
        assertThat(testFight.getCreatedAt().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testFight.getProcessedAt().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_PROCESSED_AT);
        assertThat(testFight.getResultingEvents()).isEqualTo(UPDATED_RESULTING_EVENTS);
    }

    @Test
    @Transactional
    public void deleteFight() throws Exception {
        // Initialize the database
        fightRepository.saveAndFlush(fight);

        // Get the fight
        restFightMockMvc.perform(delete("/api/fights/{id}", fight.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Fight> fights = fightRepository.findAll();
        assertThat(fights).hasSize(0);
    }
}
