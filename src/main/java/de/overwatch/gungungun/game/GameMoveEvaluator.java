package de.overwatch.gungungun.game;


import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.heuristic.*;
import de.overwatch.gungungun.game.model.ActiveBehavior;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class GameMoveEvaluator {

    @Inject
    private HeuristicStore heuristicStore;


    public boolean hasValidBehaviors(HeroToken heroToken){
        for (ActiveBehavior behavior : heroToken.getBehaviors()) {
            if(behavior.getHeuristicName() != HeuristicName.NONE){
                return true;
            }
        }
        return false;
    }

    public void evaluateGameMoves(GameState gameState, HeroToken heroToken, Collection<GameMove> gameMoves){
        Collection<ActiveBehavior> behaviors = new LinkedList<>();
        behaviors.addAll(heroToken.getBehaviors());
        behaviors.add(new ActiveBehavior(HeuristicName.DIRECT_DISTANCE_IS_BETTER, 100));

        for(GameMove gameMove: gameMoves) {
            for (ActiveBehavior behavior : behaviors) {
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
