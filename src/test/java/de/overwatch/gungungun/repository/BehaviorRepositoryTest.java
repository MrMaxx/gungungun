package de.overwatch.gungungun.repository;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Behavior;
import de.overwatch.gungungun.domain.Hero;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the HeroResource REST controller.
 *
 * @see de.overwatch.gungungun.web.rest.HeroResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BehaviorRepositoryTest {

    @Inject
    private BehaviorRepository behaviorRepository;

    @Test
    @Transactional
    public void getBehaviors() throws Exception {
        // Validate the database is empty
        List<Behavior> behaviors = behaviorRepository.findBehaviors(1l,1l,2l);
        assertThat(behaviors).hasSize(2);
    }

}
