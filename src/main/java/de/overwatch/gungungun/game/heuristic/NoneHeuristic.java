package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.model.HeroToken;

public class NoneHeuristic extends AbstractHeuristic {


    @Override
    public float evaluate(GameState gameState, HeroToken heroToken, GameMove gameMove) {
        return 0;
    }

}
