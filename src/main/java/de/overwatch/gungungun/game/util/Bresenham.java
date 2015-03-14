package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.Coordinate;

import java.util.HashSet;
import java.util.Set;


public class Bresenham {

	public static LosLine getLosLine( Coordinate source, Coordinate target ){
		return getLosLine( source.getX(), source.getY(), target.getX(), target.getY() );
	}
	
	public static LosLine getLosLine(int x0, int y0, int x1, int y1){
		Set<Coordinate> line = new HashSet<Coordinate>();
		Set<LinePair> linePairs = new HashSet<LinePair>();
	
		Coordinate startCoordinate = new Coordinate( x0, y0 );
		Coordinate endCoordinate = new Coordinate( x1, y1 );
		
		boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
	     
		if (steep){
			int tmp = x0;
			x0 = y0;
			y0 = tmp;
			tmp = x1;
			x1 = y1;
			y1 = tmp;
		}
	    if (x0 > x1){
	    	int tmp = x1;
			x1 = x0;
			x0 = tmp;
			tmp = y1;
			y1 = y0;
			y0 = tmp;
	    }
	    double deltax = x1 - x0;
	    double deltay = Math.abs(y1 - y0);
	    double error = 0;
	    double deltaerr = deltay/deltax;
	    int y = y0;
	    int ystep = 0;
	    if (y0 < y1) 
	    	ystep = 1;
	    else 
	    	ystep = -1;
	    
	    Coordinate lastCoordinate = null;
	    if( steep ){
	    	lastCoordinate = new Coordinate( y0, x0 );
	    }else{
	    	lastCoordinate = new Coordinate( x0, y0 );
	    }
	    boolean addSquare = false;
	    Coordinate coord = null;
	    for(int x=x0; x<=x1; x++){
	    	if( addSquare ){
	    		if(steep){
	    			coord = new Coordinate(y-ystep,x);
					line.add(coord);
		    	}else{
		    		coord = new Coordinate(x,y-ystep);
					line.add(coord);
		    	}	
	    	}
	    	if(steep){
	    		coord = new Coordinate(y,x);
				line.add(coord);
	    	}else{
	    		coord = new Coordinate(x,y);
				line.add(coord);
	    	}
	    	
	    	if( !addSquare ){
	    		if(coord.isDiagonallyTo(lastCoordinate)){
		    		linePairs.add(LinePair.getNonDiagonalCoordinates(lastCoordinate, coord));
	    		}
	    	}else{
	    		addSquare = false;
	    	}
	    	
	    	lastCoordinate = new Coordinate( coord );
	    	
	    	error = error + deltaerr;
	    	// we have to have a fault tolerance
	    	 
	    	if( error +(deltaerr/2) >= 0.51 || error -(deltaerr/2) >= 0.51){
	    		if( ( error - (deltaerr/2) > 0.49 ) && error - (deltaerr/2) < 0.51 ){
		    	}else{
		    		addSquare = true;
		    	}
	    		
	    		y = y + ystep;
	    		error = error - 1.0;
	    	}
	    }
	    // we have to remove the start and target Coordinates, cause they do not matter
	    line.remove( startCoordinate );
	    line.remove( endCoordinate );
	    
		return new LosLine( line, linePairs );
	}
}
