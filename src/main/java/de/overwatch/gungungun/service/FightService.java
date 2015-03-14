package de.overwatch.gungungun.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.game.GameEngine;
import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.GameStateFactory;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.repository.FightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class FightService {


    @Inject
    private FightRepository fightRepository;
    @Inject
    private GameStateFactory gameStateFactory;
    @Inject
    private GameEngine gameEngine;

    public void processOpenFights(){
        Gson gson = new GsonBuilder().create();
        Collection<Fight> openFights = fightRepository.findOpenFights();

        for(Fight fight: openFights){

            GameState gameState = gameStateFactory.createGameState(fight);
            List<GameEvent> resultingEvents = gameEngine.calculateGame(gameState);
            String eventJson = gson.toJson(resultingEvents);
            fight.setResultingEvents(eventJson);

        }


    }




}
