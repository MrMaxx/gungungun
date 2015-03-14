package de.overwatch.gungungun.game;


import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.heuristic.CloserDistanceToNearestEnemyHeuristic;
import de.overwatch.gungungun.game.heuristic.Heuristic;
import de.overwatch.gungungun.game.model.HeroToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
public class GameMoveEvaluator {

    private static final List<Heuristic> HEURISTICS = new LinkedList<>();
    static{
        HEURISTICS.add(new CloserDistanceToNearestEnemyHeuristic());
    }


    public void evaluateGameMoves(GameState gameState, HeroToken heroToken, Collection<GameMove> gameMoves){

        for(GameMove gameMove: gameMoves){
            for(Heuristic heuristic: HEURISTICS){
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
            if(bestMove==null || bestMove.getOverallScore()>bestMove.getOverallScore()){
                bestMove = gameMove;
            }
        }
        return bestMove;

    }


}
