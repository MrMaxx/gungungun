package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.BoardCoordinate;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;

import java.io.Serializable;
import java.util.*;

public class ArenaBoard implements Serializable{


    private Map<Coordinate, BoardCoordinate> coordinatesMap = new HashMap<>();


	private Map<Coordinate, Boolean> levelCoordinateMap = new HashMap<Coordinate, Boolean>();
	private Map<Coordinate, Boolean> levelCoordinateBlockingLOSMap = new HashMap<Coordinate, Boolean>();
	private Map<Coordinate, Boolean> levelCoordinateBlockingMovementMap = new HashMap<Coordinate, Boolean>();

	private Map<Coordinate, Map<Coordinate, Coordinate>> shortestPaths = new HashMap<Coordinate, Map<Coordinate,Coordinate>>();

	public boolean isCoordinateInLevel( Coordinate coordinate ){
		return levelCoordinateMap.get( coordinate ) != null && levelCoordinateMap.get( coordinate );
	}
	public boolean isCoordinateBlockingMovement( Coordinate coordinate ){
		return ( levelCoordinateMap.get( coordinate ) == null ||
                levelCoordinateBlockingMovementMap.get( coordinate ) == null ||
                    (levelCoordinateBlockingMovementMap.get( coordinate ) != null
                    && levelCoordinateBlockingMovementMap.get( coordinate ))
                );
	}
	public boolean isCoordinateBlockingLOS( Coordinate coordinate ){
		return ( levelCoordinateMap.get( coordinate ) == null ||
                levelCoordinateBlockingLOSMap.get( coordinate ) == null ||
                    (levelCoordinateBlockingLOSMap.get( coordinate ) != null
                    && levelCoordinateBlockingLOSMap.get( coordinate ))
				 );
	}
	public List<Coordinate> getShortestPathWithoutTargetCoordinate( Coordinate source, Coordinate target ) throws NoPathExistsException{
		List<Coordinate> result = getShortestPath( source, target );
		result.remove( result.size()-1 );
		return result;
	}
	public List<Coordinate> getShortestPath( Coordinate from, Coordinate to ) throws NoPathExistsException{

		List<Coordinate> result = new LinkedList<Coordinate>();

		int step = 0;
		Coordinate addNext = from;
		result.add( addNext );
		while( !addNext.equals( to ) && step<100 ){

			Map<Coordinate, Coordinate> nextMap = shortestPaths.get( addNext );
			addNext = nextMap.get( to );
            if(addNext == null){
                throw new NoPathExistsException();
            }
			result.add( addNext );

		}
		result.remove( from );
		return result;
	}

    public ArenaBoard(Map<Coordinate, BoardCoordinate> coordinatesMap, Collection<HeroToken> heroTokens){

        this.coordinatesMap = coordinatesMap;

        for( Map.Entry<Coordinate,BoardCoordinate> entry : coordinatesMap.entrySet() ){
            levelCoordinateMap.put(entry.getKey(),Boolean.TRUE);
            levelCoordinateBlockingMovementMap.put(entry.getKey(), !entry.getValue().isPermeable());
            levelCoordinateBlockingLOSMap.put(entry.getKey(), !entry.getValue().isTransparent());
		}
        for(HeroToken heroToken: heroTokens){
            levelCoordinateBlockingMovementMap.put(heroToken.getCoordinate(), Boolean.TRUE);
            levelCoordinateBlockingLOSMap.put(heroToken.getCoordinate(), Boolean.TRUE);
        }
		
		for( Coordinate coordinate : coordinatesMap.keySet() ){
			
			Set<Coordinate> visitedCoordinates = new HashSet<Coordinate>();
			Queue<CoordinateWithFirstNeighbor> notVisitedCoordinates = new LinkedList<CoordinateWithFirstNeighbor>();
			
			addInitialNeighbors( notVisitedCoordinates, coordinate );
			
			while( notVisitedCoordinates.size() > 0 ){
				
				CoordinateWithFirstNeighbor startPoint = notVisitedCoordinates.poll();
				Map<Coordinate, Coordinate> targets = shortestPaths.get( coordinate );
				if( targets == null ){
					targets = new HashMap<Coordinate, Coordinate>();
					shortestPaths.put( coordinate, targets );
				}
				targets.put( startPoint.getCoordinate(), startPoint.getFirstNeighbor() );
				visitedCoordinates.add( startPoint.getCoordinate() );
				
				addNeighbors( startPoint,
						notVisitedCoordinates, 
						visitedCoordinates );
			}
		}
		
	}
	private void addInitialNeighbors( Queue<CoordinateWithFirstNeighbor> notVisitedCoordinates, Coordinate initialCoordinate ){
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateSouthOf(), initialCoordinate.getCoordinateSouthOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateEastOf(), initialCoordinate.getCoordinateEastOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateNorthOf(), initialCoordinate.getCoordinateNorthOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateWestOf(), initialCoordinate.getCoordinateWestOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateSouthEastOf(), initialCoordinate.getCoordinateSouthEastOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateSouthWestOf(), initialCoordinate.getCoordinateSouthWestOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateNorthEastOf(), initialCoordinate.getCoordinateNorthEastOf() ) );
		addInitialNeighbor( notVisitedCoordinates, new CoordinateWithFirstNeighbor( initialCoordinate.getCoordinateNorthWestOf(), initialCoordinate.getCoordinateNorthWestOf() ) );
	}
	private void addInitialNeighbor( Queue<CoordinateWithFirstNeighbor> notVisitedCoordinates, CoordinateWithFirstNeighbor toAdd ){
		if( ! this.isCoordinateBlockingMovement( toAdd.getCoordinate() )){
			notVisitedCoordinates.add( toAdd );
		}
	}
	private void addNeighbors( CoordinateWithFirstNeighbor startPoint, Queue<CoordinateWithFirstNeighbor> notVisitedCoordinates, Set<Coordinate> visitedCoordinates ){
		for( Coordinate neighbor : getCoordinatesInRightOrder( startPoint.getCoordinate() ) ){
			CoordinateWithFirstNeighbor next = new CoordinateWithFirstNeighbor( startPoint.getFirstNeighbor(), neighbor );
			if( !visitedCoordinates.contains( neighbor ) && ! notVisitedCoordinates.contains( next ) &&
					! this.isCoordinateBlockingMovement( neighbor )){
				notVisitedCoordinates.add( next );
			}
		}
	}
	private List<Coordinate> getCoordinatesInRightOrder( Coordinate start ){
		List<Coordinate> result = new LinkedList<Coordinate>();
		result.add( start.getCoordinateSouthOf() );
		result.add( start.getCoordinateEastOf() );
		result.add( start.getCoordinateNorthOf() );
		result.add( start.getCoordinateWestOf() );
		result.add( start.getCoordinateSouthEastOf() );
		result.add( start.getCoordinateSouthWestOf() );
		result.add( start.getCoordinateNorthEastOf() );
		result.add( start.getCoordinateNorthWestOf() );
		
		return result;
	}
	
	
	private class CoordinateWithFirstNeighbor{
		
		private Coordinate firstNeighbor;
		private Coordinate coordinate;
		
		public CoordinateWithFirstNeighbor(Coordinate firstNeighbor,
				Coordinate coordinate) {
			super();
			this.firstNeighbor = firstNeighbor;
			this.coordinate = coordinate;
		}
		public boolean equals(Object o){
			try{
				return this.getCoordinate().equals( ((CoordinateWithFirstNeighbor)o).getCoordinate() );
			}catch(ClassCastException cce){ }
			return super.equals( o );
		}
		public Coordinate getFirstNeighbor() {
			return firstNeighbor;
		}
		public Coordinate getCoordinate() {
			return coordinate;
		}
	}
	
}
