package de.overwatch.gungungun.game;


import de.overwatch.gungungun.game.execute.GameMoveExecutor;
import de.overwatch.gungungun.game.gameevent.GameEndedEvent;
import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.gameevent.RoundEndedEvent;
import de.overwatch.gungungun.game.gamemove.*;
import de.overwatch.gungungun.game.model.HeroToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class GameEngine {

    private final Logger log = LoggerFactory.getLogger(GameEngine.class);

    @Inject
    private AttackGameMoveFactory attackGameMoveFactory;
    @Inject
    private ShootAtGameMoveFactory shootAtGameMoveFactory;
    @Inject
    private MoveToGameMoveFactory moveToGameMoveFactory;

    @Inject
    private GameMoveEvaluator gameMoveEvaluator;

    @Inject
    private GameMoveExecutor gameMoveExecutor;

    public List<GameEvent> calculateGame(GameState gameState){

        List<GameEvent> gameEvents = new LinkedList<>();

        // we need some termination
        while(gameState.getRound() < 1000){
            log.debug("#### Starting round {}", gameState.getRound());
            for(HeroToken heroToken: gameState.getHeroes()){
                if(heroToken.isDead()){
                    log.debug("### Omitting dead HeroToken with ID = {}", heroToken.getId());
                    continue;
                }
                log.debug("### Processing HeroToken with ID = {}", heroToken.getId());
                while(heroToken.getActionsLeft() > 0){
                    log.debug("### HeroToken has {} actions left.", heroToken.getActionsLeft());
                    Collection<GameMove> gameMoves = new LinkedList<>();
                    gameMoves.addAll(attackGameMoveFactory.create(gameState, heroToken));
                    gameMoves.addAll(shootAtGameMoveFactory.create(gameState, heroToken));
                    gameMoves.addAll(moveToGameMoveFactory.create(gameState, heroToken));

                    // this can happen if the ActionPoints left are >0 but not sufficient for a possible GameMove
                    if(gameMoves.size() == 0){
                        break;
                    }

                    gameMoveEvaluator.evaluateGameMoves(gameState, heroToken, gameMoves);
                    // Todo: debug result
                    GameMove bestMove = gameMoveEvaluator.chooseBestGameMove(gameMoves);

                    log.debug("Best GameMove: {}", bestMove);

                    List<GameEvent> resultingGameEvents = gameMoveExecutor.execute(gameState, heroToken, bestMove);
                    gameEvents.addAll(resultingGameEvents);

                    log.debug("Resulting GameEvents: {}", resultingGameEvents);
                    // if only one Party is left we have to end the game
                    if(gameState.isOnlyOnePartyLeft()){
                        gameEvents.add(new GameEndedEvent());
                        return gameEvents;
                    }
                }
            }
            gameEvents.add(new RoundEndedEvent(gameState.getRound()));
            gameState.nextRound();
        }
        return gameEvents;
    }

}
