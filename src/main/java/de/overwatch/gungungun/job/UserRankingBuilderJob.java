package de.overwatch.gungungun.job;

import de.overwatch.gungungun.domain.User;
import de.overwatch.gungungun.repository.UserRepository;
import de.overwatch.gungungun.service.ranking.UserRankingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRankingBuilderJob implements InitializingBean {


    private static final Logger LOGGER = Logger.getLogger(UserRankingBuilderJob.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRankingService userRankingService;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Starting to ramp up UserRanking Service Cache.");

        List<User> allUsers = userRepository.findAll();

        for(User user : allUsers){
            userRankingService.addUser(user);

        }
        LOGGER.info("Added "+allUsers.size()+" Users to UserRanking Service Cache.");
        LOGGER.info("Finished to ramp up UserRanking Service Cache.");
    }
}
