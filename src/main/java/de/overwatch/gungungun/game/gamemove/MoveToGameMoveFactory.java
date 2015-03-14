package de.overwatch.gungungun.game.gamemove;

import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.ArenaBoard;
import de.overwatch.gungungun.game.util.Direction;
import de.overwatch.gungungun.game.util.NoPathExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class MoveToGameMoveFactory implements GameMoveFactory{

    private final Logger log = LoggerFactory.getLogger(MoveToGameMoveFactory.class);

    @Override
    public Collection<GameMove> create(GameState gameState, HeroToken heroToken) {
        Collection<GameMove> result = new LinkedList<>();

        Collection<Coordinate> reachableCoordinates = heroToken.getCoordinate().getCoordinateInRange(heroToken.getActionsLeft());
        ArenaBoard arenaBoard = gameState.getArenaBoard();

        for(Coordinate coordinate : reachableCoordinates){
            MoveToGameMove move = createGameMove(heroToken, coordinate, arenaBoard);
            if( move != null && move.getCost() <= heroToken.getActionsLeft()){
                result.add(move);
            }
        }
        return result;
    }

    /**
     * Determins the MoveToSequence including the need to turn the Token to a new Direction
     */
    private MoveToGameMove createGameMove(HeroToken heroToken, Coordinate targetCoordinate, ArenaBoard arenaBoard){
        List<MoveToGameMove.MoveToSequence> moveToSequence = new LinkedList<>();
        try{
            //log.debug("Creating MoveToGameMove from {} to {}", heroToken.getCoordinate(), targetCoordinate);
            List<Coordinate> path = arenaBoard.getShortestPath(heroToken.getCoordinate(), targetCoordinate);
            // if we try to get to the same coordinate the token is already standing at
            if(path.size()==0){
                return null;
            }
            Direction currentDirection = heroToken.getDirection();
            Coordinate currentCoordinate = heroToken.getCoordinate();
            for(int i = 0; i<path.size(); i++){
                Coordinate nextCoordinate = path.get(i);
                Direction newDirection = null;
                if(!currentCoordinate.getFrontFields(currentDirection).contains(nextCoordinate)){
                    newDirection = Direction.getDirectionTowardsCoordinate(currentCoordinate, nextCoordinate);
                }
                moveToSequence.add(new MoveToGameMove.MoveToSequence(nextCoordinate, newDirection));

                /*
                    If the last MoveToSequence includes a turn we can reject this MoveToMove, because
                    its already included with the other MoveToGameMoves and we do not want to end with a turn, which
                    does not hold any value for us with our limited Heuristics.
                 */
                if(i == path.size() && newDirection != null){
                    return null;
                }
            }
        }catch(NoPathExistsException npee){
            // Do Nothing...this is expected and can happen
            return null;
        }

        return new MoveToGameMove(moveToSequence);
    }
}
