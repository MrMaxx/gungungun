package de.overwatch.gungungun.web.rest;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.TokenBlueprint;
import de.overwatch.gungungun.repository.TokenBlueprintRepository;

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
 * Test class for the TokenBlueprintResource REST controller.
 *
 * @see TokenBlueprintResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class TokenBlueprintResourceTest {

    private static final String DEFAULT_TOKEN_KEY = "SAMPLE_TEXT";
    private static final String UPDATED_TOKEN_KEY = "UPDATED_TEXT";

    private static final Integer DEFAULT_HEALTH = 0;
    private static final Integer UPDATED_HEALTH = 1;

    private static final Integer DEFAULT_ACTIONS_PER_TURN = 0;
    private static final Integer UPDATED_ACTIONS_PER_TURN = 1;

    private static final Integer DEFAULT_LONG_RANGE_ATTACK_RANGE = 0;
    private static final Integer UPDATED_LONG_RANGE_ATTACK_RANGE = 1;

    private static final Integer DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MINIMUM = 0;
    private static final Integer UPDATED_LONG_RANGE_WEAPON_DAMAGE_MINIMUM = 1;

    private static final Integer DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM = 0;
    private static final Integer UPDATED_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM = 1;

    private static final Integer DEFAULT_SHORT_RANGE_ATTACK_RANGE = 0;
    private static final Integer UPDATED_SHORT_RANGE_ATTACK_RANGE = 1;

    private static final Integer DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM = 0;
    private static final Integer UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM = 1;

    private static final Integer DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM = 0;
    private static final Integer UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM = 1;

    private static final Integer DEFAULT_MOVE_RANGE = 0;
    private static final Integer UPDATED_MOVE_RANGE = 1;

    @Inject
    private TokenBlueprintRepository tokenBlueprintRepository;

    private MockMvc restTokenBlueprintMockMvc;

    private TokenBlueprint tokenBlueprint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TokenBlueprintResource tokenBlueprintResource = new TokenBlueprintResource();
        ReflectionTestUtils.setField(tokenBlueprintResource, "tokenBlueprintRepository", tokenBlueprintRepository);
        this.restTokenBlueprintMockMvc = MockMvcBuilders.standaloneSetup(tokenBlueprintResource).build();
    }

    @Before
    public void initTest() {
        tokenBlueprint = new TokenBlueprint();
        tokenBlueprint.setTokenKey(DEFAULT_TOKEN_KEY);
        tokenBlueprint.setHealth(DEFAULT_HEALTH);
        tokenBlueprint.setActionsPerTurn(DEFAULT_ACTIONS_PER_TURN);
        tokenBlueprint.setLongRangeAttackRange(DEFAULT_LONG_RANGE_ATTACK_RANGE);
        tokenBlueprint.setLongRangeWeaponDamageMinimum(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MINIMUM);
        tokenBlueprint.setLongRangeWeaponDamageMaximum(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM);
        tokenBlueprint.setShortRangeAttackRange(DEFAULT_SHORT_RANGE_ATTACK_RANGE);
        tokenBlueprint.setShortRangeWeaponDamageMinimum(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM);
        tokenBlueprint.setShortRangeWeaponDamageMaximum(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM);
        tokenBlueprint.setMoveRange(DEFAULT_MOVE_RANGE);
    }

    @Test
    @Transactional
    public void createTokenBlueprint() throws Exception {
        // Validate the database is empty
        assertThat(tokenBlueprintRepository.findAll()).hasSize(0);

        // Create the TokenBlueprint
        restTokenBlueprintMockMvc.perform(post("/api/tokenBlueprints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tokenBlueprint)))
                .andExpect(status().isCreated());

        // Validate the TokenBlueprint in the database
        List<TokenBlueprint> tokenBlueprints = tokenBlueprintRepository.findAll();
        assertThat(tokenBlueprints).hasSize(1);
        TokenBlueprint testTokenBlueprint = tokenBlueprints.iterator().next();
        assertThat(testTokenBlueprint.getTokenKey()).isEqualTo(DEFAULT_TOKEN_KEY);
        assertThat(testTokenBlueprint.getHealth()).isEqualTo(DEFAULT_HEALTH);
        assertThat(testTokenBlueprint.getActionsPerTurn()).isEqualTo(DEFAULT_ACTIONS_PER_TURN);
        assertThat(testTokenBlueprint.getLongRangeAttackRange()).isEqualTo(DEFAULT_LONG_RANGE_ATTACK_RANGE);
        assertThat(testTokenBlueprint.getLongRangeWeaponDamageMinimum()).isEqualTo(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MINIMUM);
        assertThat(testTokenBlueprint.getLongRangeWeaponDamageMaximum()).isEqualTo(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM);
        assertThat(testTokenBlueprint.getShortRangeAttackRange()).isEqualTo(DEFAULT_SHORT_RANGE_ATTACK_RANGE);
        assertThat(testTokenBlueprint.getShortRangeWeaponDamageMinimum()).isEqualTo(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM);
        assertThat(testTokenBlueprint.getShortRangeWeaponDamageMaximum()).isEqualTo(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM);
        assertThat(testTokenBlueprint.getMoveRange()).isEqualTo(DEFAULT_MOVE_RANGE);
    }

    @Test
    @Transactional
    public void getAllTokenBlueprints() throws Exception {
        // Initialize the database
        tokenBlueprintRepository.saveAndFlush(tokenBlueprint);

        // Get all the tokenBlueprints
        restTokenBlueprintMockMvc.perform(get("/api/tokenBlueprints"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(tokenBlueprint.getId().intValue()))
                .andExpect(jsonPath("$.[0].tokenKey").value(DEFAULT_TOKEN_KEY.toString()))
                .andExpect(jsonPath("$.[0].health").value(DEFAULT_HEALTH))
                .andExpect(jsonPath("$.[0].actionsPerTurn").value(DEFAULT_ACTIONS_PER_TURN))
                .andExpect(jsonPath("$.[0].longRangeAttackRange").value(DEFAULT_LONG_RANGE_ATTACK_RANGE))
                .andExpect(jsonPath("$.[0].longRangeWeaponDamageMinimum").value(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MINIMUM))
                .andExpect(jsonPath("$.[0].longRangeWeaponDamageMaximum").value(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM))
                .andExpect(jsonPath("$.[0].shortRangeAttackRange").value(DEFAULT_SHORT_RANGE_ATTACK_RANGE))
                .andExpect(jsonPath("$.[0].shortRangeWeaponDamageMinimum").value(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM))
                .andExpect(jsonPath("$.[0].shortRangeWeaponDamageMaximum").value(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM))
                .andExpect(jsonPath("$.[0].moveRange").value(DEFAULT_MOVE_RANGE));
    }

    @Test
    @Transactional
    public void getTokenBlueprint() throws Exception {
        // Initialize the database
        tokenBlueprintRepository.saveAndFlush(tokenBlueprint);

        // Get the tokenBlueprint
        restTokenBlueprintMockMvc.perform(get("/api/tokenBlueprints/{id}", tokenBlueprint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(tokenBlueprint.getId().intValue()))
            .andExpect(jsonPath("$.tokenKey").value(DEFAULT_TOKEN_KEY.toString()))
            .andExpect(jsonPath("$.health").value(DEFAULT_HEALTH))
            .andExpect(jsonPath("$.actionsPerTurn").value(DEFAULT_ACTIONS_PER_TURN))
            .andExpect(jsonPath("$.longRangeAttackRange").value(DEFAULT_LONG_RANGE_ATTACK_RANGE))
            .andExpect(jsonPath("$.longRangeWeaponDamageMinimum").value(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MINIMUM))
            .andExpect(jsonPath("$.longRangeWeaponDamageMaximum").value(DEFAULT_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM))
            .andExpect(jsonPath("$.shortRangeAttackRange").value(DEFAULT_SHORT_RANGE_ATTACK_RANGE))
            .andExpect(jsonPath("$.shortRangeWeaponDamageMinimum").value(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM))
            .andExpect(jsonPath("$.shortRangeWeaponDamageMaximum").value(DEFAULT_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM))
            .andExpect(jsonPath("$.moveRange").value(DEFAULT_MOVE_RANGE));
    }

    @Test
    @Transactional
    public void getNonExistingTokenBlueprint() throws Exception {
        // Get the tokenBlueprint
        restTokenBlueprintMockMvc.perform(get("/api/tokenBlueprints/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTokenBlueprint() throws Exception {
        // Initialize the database
        tokenBlueprintRepository.saveAndFlush(tokenBlueprint);

        // Update the tokenBlueprint
        tokenBlueprint.setTokenKey(UPDATED_TOKEN_KEY);
        tokenBlueprint.setHealth(UPDATED_HEALTH);
        tokenBlueprint.setActionsPerTurn(UPDATED_ACTIONS_PER_TURN);
        tokenBlueprint.setLongRangeAttackRange(UPDATED_LONG_RANGE_ATTACK_RANGE);
        tokenBlueprint.setLongRangeWeaponDamageMinimum(UPDATED_LONG_RANGE_WEAPON_DAMAGE_MINIMUM);
        tokenBlueprint.setLongRangeWeaponDamageMaximum(UPDATED_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM);
        tokenBlueprint.setShortRangeAttackRange(UPDATED_SHORT_RANGE_ATTACK_RANGE);
        tokenBlueprint.setShortRangeWeaponDamageMinimum(UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM);
        tokenBlueprint.setShortRangeWeaponDamageMaximum(UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM);
        tokenBlueprint.setMoveRange(UPDATED_MOVE_RANGE);
        restTokenBlueprintMockMvc.perform(put("/api/tokenBlueprints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tokenBlueprint)))
                .andExpect(status().isOk());

        // Validate the TokenBlueprint in the database
        List<TokenBlueprint> tokenBlueprints = tokenBlueprintRepository.findAll();
        assertThat(tokenBlueprints).hasSize(1);
        TokenBlueprint testTokenBlueprint = tokenBlueprints.iterator().next();
        assertThat(testTokenBlueprint.getTokenKey()).isEqualTo(UPDATED_TOKEN_KEY);
        assertThat(testTokenBlueprint.getHealth()).isEqualTo(UPDATED_HEALTH);
        assertThat(testTokenBlueprint.getActionsPerTurn()).isEqualTo(UPDATED_ACTIONS_PER_TURN);
        assertThat(testTokenBlueprint.getLongRangeAttackRange()).isEqualTo(UPDATED_LONG_RANGE_ATTACK_RANGE);
        assertThat(testTokenBlueprint.getLongRangeWeaponDamageMinimum()).isEqualTo(UPDATED_LONG_RANGE_WEAPON_DAMAGE_MINIMUM);
        assertThat(testTokenBlueprint.getLongRangeWeaponDamageMaximum()).isEqualTo(UPDATED_LONG_RANGE_WEAPON_DAMAGE_MAXIMUM);
        assertThat(testTokenBlueprint.getShortRangeAttackRange()).isEqualTo(UPDATED_SHORT_RANGE_ATTACK_RANGE);
        assertThat(testTokenBlueprint.getShortRangeWeaponDamageMinimum()).isEqualTo(UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MINIMUM);
        assertThat(testTokenBlueprint.getShortRangeWeaponDamageMaximum()).isEqualTo(UPDATED_SHORT_RANGE_WEAPON_DAMAGE_MAXIMUM);
        assertThat(testTokenBlueprint.getMoveRange()).isEqualTo(UPDATED_MOVE_RANGE);
    }

    @Test
    @Transactional
    public void deleteTokenBlueprint() throws Exception {
        // Initialize the database
        tokenBlueprintRepository.saveAndFlush(tokenBlueprint);

        // Get the tokenBlueprint
        restTokenBlueprintMockMvc.perform(delete("/api/tokenBlueprints/{id}", tokenBlueprint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TokenBlueprint> tokenBlueprints = tokenBlueprintRepository.findAll();
        assertThat(tokenBlueprints).hasSize(0);
    }
}
