package de.overwatch.gungungun.game.heuristic;


import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.gamemove.GameMove;
import de.overwatch.gungungun.game.model.HeroToken;

public interface Heuristic {

    public float evaluate(GameState state, HeroToken token, GameMove gameMove);

}
