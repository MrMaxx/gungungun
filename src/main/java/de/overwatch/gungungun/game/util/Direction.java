package de.overwatch.gungungun.game.util;

import de.overwatch.gungungun.game.model.Coordinate;

public enum Direction {
	NORTH, 
	EAST, 
	SOUTH, 
	WEST;

	public static Direction getDirection( String directionString ){
		for( Direction direction: Direction.values() ){
			if( direction.name().equals(  directionString ) ){
				return direction;
			}
		}
		throw new IllegalArgumentException( "There is no Direction defined with name = "+directionString );
	}
	public static final Direction get180DegreeDirection( Direction direction) {
		if( direction.equals( Direction.NORTH ))
			return Direction.SOUTH;
		if( direction.equals( Direction.SOUTH ))
			return Direction.NORTH;
		if( direction.equals( Direction.WEST ))
			return Direction.EAST;
		if( direction.equals( Direction.EAST ))
			return Direction.WEST;
		throw new RuntimeException("not a valid Direction");
	}
	/**	
	 * 	Turns a Direction on a Coordinate towards another Coordinate
	 * 
	 * 	Does nothing, if the Coordinates are the same
	 */
	public static final Direction getDirectionTowardsCoordinate(Coordinate ownCoordinate, Coordinate otherCoordinate){
		int x = ownCoordinate.getX() - otherCoordinate.getX();
		int y = ownCoordinate.getY() - otherCoordinate.getY();
		if(Math.abs(x) < Math.abs(y)){
			if(y>0)
				return Direction.NORTH;
			else if (y<0)
				return Direction.SOUTH;
		} else if(Math.abs(x) > Math.abs(y)){ 
			if(x>0)
				return Direction.WEST;
			else if (x<0)
				return Direction.EAST;
		}else if(Math.abs(x) == Math.abs(y)){
            if( y < 0 ){
                return Direction.SOUTH;
            }
            if( y > 0 ){
                return Direction.NORTH;
            }
        }
		//same Coordinate
		return Direction.NORTH;
	}
	public static final Direction getTurnRightDirection( Direction currentDirection ){
		if( currentDirection.equals( Direction.NORTH ))
			return Direction.EAST;
		if( currentDirection.equals( Direction.SOUTH ))
			return Direction.WEST;
		if( currentDirection.equals( Direction.WEST ))
			return Direction.NORTH;
		if( currentDirection.equals( Direction.EAST ))
			return Direction.SOUTH;
		throw new RuntimeException("not a valid Direction");
	}
	
	public static final Direction getTurnLeftDirection( Direction currentDirection ){
		if( currentDirection.equals( Direction.NORTH ))
			return Direction.WEST;
		if( currentDirection.equals( Direction.SOUTH ))
			return Direction.EAST;
		if( currentDirection.equals( Direction.WEST ))
			return Direction.SOUTH;
		if( currentDirection.equals( Direction.EAST ))
			return Direction.NORTH;
		throw new RuntimeException("not a valid Direction");
	}
}
