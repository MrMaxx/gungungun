package de.overwatch.gungungun.service;

import de.overwatch.gungungun.Application;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.repository.FightRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

/**
 * Test class for the UserResource REST controller.
 *
 * @see de.overwatch.gungungun.service.UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FightServiceTest {

    private final Logger log = LoggerFactory.getLogger(FightServiceTest.class);

    @Inject
    private FightService fightService;
    @Inject
    private FightRepository fightRepository;

    @Test
    public void test() {

        fightService.processOpenFights();
        Fight fight = fightRepository.findOne(1L);
        Assert.assertNotNull(fight.getResultingEvents());
        log.debug("{}", fight.getResultingEvents());
    }
}
