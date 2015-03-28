package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.repository.HeroRepository;

import de.overwatch.gungungun.web.rest.HeroResource;
import org.junit.Assert;
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
 * @see de.overwatch.gungungun.web.rest.HeroResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class HeroRepositoryTest {

    @Inject
    private HeroRepository heroRepository;

    @Test
    @Transactional
    public void getHero() throws Exception {
        // Validate the database is empty
        Hero hero = heroRepository.findHero(1l,1l,2l);
        Assert.assertNotNull(hero);
    }

}
