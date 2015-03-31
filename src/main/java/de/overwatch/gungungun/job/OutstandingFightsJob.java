package de.overwatch.gungungun.job;



import de.overwatch.gungungun.service.FightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutstandingFightsJob {


    private final Logger log = LoggerFactory.getLogger(OutstandingFightsJob.class);

    @Autowired
    private FightService fightService;

    @Scheduled(fixedDelay = 10000)  // (cron="0/10 * * * * *")
    public void executeJob(){

        log.info("executing OutstandingFightsJob");
        fightService.processOpenFights();
    }

}
