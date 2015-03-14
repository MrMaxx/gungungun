package de.overwatch.gungungun.game.gamemove;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.util.Direction;

import java.util.List;

public class MoveToGameMove extends GameMove{

    private List<MoveToSequence> path;

    public MoveToGameMove(List<MoveToSequence> path) {
        this.path = path;
    }
    @Override
    public int getCost(){
        int totalCost = 0;
        if(path==null){return 0;}
        for(MoveToSequence mts: path){
            totalCost++;
            if(mts.getDirection()!=null){
                totalCost++;
            }
        }
        return totalCost;
    }

    public List<MoveToSequence> getPath() {
        return path;
    }

    @Override
    public GameMoveType getType() {
        return GameMoveType.MOVE_TO;
    }

    @Override
    public String toString() {
        return "MoveToGameMove{" +
                "path=" + path +
                "} " + super.toString();
    }

    public static class MoveToSequence{
        private Coordinate coordinate;
        private Direction direction;

        public MoveToSequence(Coordinate coordinate, Direction direction) {
            this.coordinate = coordinate;
            this.direction = direction;
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public Direction getDirection() {
            return direction;
        }

        @Override
        public String toString() {
            return "{" +
                    "coordinate=" + coordinate +
                    ", direction=" + direction +
                    '}';
        }
    }
}
