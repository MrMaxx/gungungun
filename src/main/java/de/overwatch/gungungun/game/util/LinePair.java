package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.Coordinate;

public class LinePair {

	private Coordinate left;
	private Coordinate right;
	
	private LinePair(Coordinate first, Coordinate second){
		this.left = first;
		this.right = second;
	}
	public Coordinate getLeft(){
		return left;
	}
	public Coordinate getRight(){
		return right;
	}
	public static final LinePair getNonDiagonalCoordinates( Coordinate a, Coordinate b ){
		Coordinate xCoordinate = new Coordinate( a.getX(), a.getY() - (a.getY() - b.getY()) );
		Coordinate yCoordinate = new Coordinate( a.getX() - (a.getX() - b.getX()), a.getY() );

		return new LinePair( xCoordinate, yCoordinate);
	}
}
