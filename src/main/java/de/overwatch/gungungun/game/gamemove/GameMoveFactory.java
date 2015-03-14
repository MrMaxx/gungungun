package de.overwatch.gungungun.game.gamemove;

import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.model.HeroToken;

import java.util.Collection;

public interface GameMoveFactory {

    Collection<GameMove> create(GameState gameState, HeroToken heroToken);

}
