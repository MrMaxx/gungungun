package de.overwatch.gungungun.game;


import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.heuristic.*;
import de.overwatch.gungungun.game.model.ActiveBehavior;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
public class GameMoveEvaluator {

    @Inject
    private HeuristicStore heuristicStore;


    public void evaluateGameMoves(GameState gameState, HeroToken heroToken, Collection<GameMove> gameMoves){

        for(GameMove gameMove: gameMoves) {
            for (ActiveBehavior behavior : heroToken.getBehaviors()) {
                AbstractHeuristic heuristic = heuristicStore.getHeuristicByName(behavior.getHeuristicName());
                gameMove.addBaseScore(
                        heuristic.evaluate(gameState, heroToken, gameMove),
                        heuristic.getClass().getSimpleName());
            }
        }

    }

    /*
     *  Take gameMove.getCost() into consideration somehow
     */
    public GameMove chooseBestGameMove(Collection<GameMove> gameMoves){

        GameMove bestMove = null;
        for(GameMove gameMove: gameMoves){
            if(bestMove==null || gameMove.getOverallScore()>bestMove.getOverallScore()){
                bestMove = gameMove;
            }
        }
        return bestMove;

    }


}
