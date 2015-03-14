package de.overwatch.gungungun.game;


import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.repository.ArenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class GameStateFactory {


    public GameState createGameState(Fight fight){

        return new GameStateBuilder()
                .withArenaCoordinates(fight.getArena().getArenaCoordinates())
                .withParties(fight.getParticipatingPartys(), fight.getArena().getSpawnPoints())
                .build();

    }



}
