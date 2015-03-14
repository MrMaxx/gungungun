package de.overwatch.gungungun.game.gamemove;

import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.Direction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class AttackGameMoveFactory implements GameMoveFactory{

    @Override
    public Collection<GameMove> create(GameState gameState, HeroToken heroToken) {
        Collection<GameMove> result = new LinkedList<>();

        for(HeroToken otherToken: gameState.getHeroes()){
            // Only shoot at others
            if( ! heroToken.getPartyId().equals(otherToken.getPartyId() )
                    // everything costs 1 ActionPoint for now, if you dont have it your fucked
                    && heroToken.getActionsLeft() > 0
                    // is the other Token in Range of my Weapon
                    && heroToken.getShortRangeAttackRange() >= heroToken.getCoordinate().getDistance(otherToken.getCoordinate())){
                AttackGameMove am = new AttackGameMove(otherToken);
                // Turn around if needed
                if(!heroToken.getCoordinate().isIn45Degree(heroToken.getDirection(), otherToken.getCoordinate())){
                    am.setTurnToDirection(
                            Direction.getDirectionTowardsCoordinate(heroToken.getCoordinate(), otherToken.getCoordinate()));
                }
                result.add(am);
            }
        }
        return result;
    }
}
