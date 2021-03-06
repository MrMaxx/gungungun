package de.overwatch.gungungun.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.game.GameEngine;
import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.GameStateFactory;
import de.overwatch.gungungun.repository.FightRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

@Service
@Transactional
public class FightService {


    @Inject
    private FightRepository fightRepository;
    @Inject
    private GameStateFactory gameStateFactory;
    @Inject
    private GameEngine gameEngine;

    private final Logger log = LoggerFactory.getLogger(FightService.class);

    public void processOpenFights(){
        Gson gson = new GsonBuilder().create();
        Collection<Fight> openFights = fightRepository.findOpenFights();

        for(Fight fight: openFights){
            try {
                GameState gameState = gameStateFactory.createGameState(fight);
                gameEngine.calculateGame(gameState);
                String eventJson = gson.toJson(gameState.getGameEvents());
                fight.setResultingEvents(eventJson);
                fight.setProcessedAt(new DateTime());
                if (gameState.getWinningPartyId() != null) {
                    Party winner = null;
                    for (Party party : fight.getParticipatingPartys()) {
                        if (party.getId().equals(gameState.getWinningPartyId())) {
                            winner = party;
                            break;
                        }
                    }
                    fight.setWinner(winner);
                }
            }catch(Exception e){
                log.error("Tried to process Fight with id = "+fight.getId(), e);
            }

        }


    }




}
