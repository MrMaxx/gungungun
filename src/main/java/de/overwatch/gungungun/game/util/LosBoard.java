package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import org.apache.commons.logging.Log;

import java.util.*;

@Deprecated
public class LosBoard {

	private boolean isInitialized = false;
	
	private Map<Coordinate, Set<HeroToken>> losMap = new HashMap<>();
	private ArenaBoard arenaBoard = null;
	
	public LosBoard( ArenaBoard arenaBoard, Collection<HeroToken> heroTokens ) {
		
		this.isInitialized = true;
		
		this.arenaBoard = arenaBoard;
		losMap = new HashMap<>();
		
		for( HeroToken token : heroTokens ){
			addToken(token);
		}
	}

	private void addFrontFields( Coordinate source, Coordinate target, Direction direction, Queue<Coordinate> notVisitedCoordinates, 
			Set<Coordinate> visitedCoordinates ){
		
		for( Coordinate frontCoordinate : target.getFrontFields(direction) ){
			if( !visitedCoordinates.contains( frontCoordinate ) && ! notVisitedCoordinates.contains( frontCoordinate ) 
					&& !arenaBoard.isCoordinateBlockingLOS(frontCoordinate) ){
				
				if( isLOSFree(source, frontCoordinate ) ){
					notVisitedCoordinates.add( frontCoordinate );
				} else {
					visitedCoordinates.add(frontCoordinate);
				}
				
			}else{
				visitedCoordinates.add(frontCoordinate);
			}
		}
	}
	public boolean isLOSFree( Coordinate source, Coordinate target ){
		LosLine losLine = Bresenham.getLosLine( source, target );
		
		for( Coordinate coordinate: losLine.getLine() ){
			if( arenaBoard.isCoordinateBlockingLOS(coordinate) ){
				return false;
			}
		}
		boolean leftIsBlocked = false;
		boolean rightIsBlocked = false;
		for( LinePair pair: losLine.getLinePairs() ){
			if(  arenaBoard.isCoordinateBlockingLOS(pair.getLeft()) ){
				leftIsBlocked = true;
			}
			if(  arenaBoard.isCoordinateBlockingLOS(pair.getRight()) ){
				rightIsBlocked = true;
			}
			if( leftIsBlocked && rightIsBlocked ){
				return false;
			}
		}
		return true;
	}
	public void updateEnemy( HeroToken token ){
		removeToken(token);
		addToken(token);
	}
	public void addToken(HeroToken token){
		Set<Coordinate> visitedCoordinates = new HashSet<Coordinate>();
		Queue<Coordinate> notVisitedCoordinates = new LinkedList<Coordinate>();
		addFrontFields(token.getCoordinate(), token.getCoordinate(), token.getDirection(), notVisitedCoordinates, visitedCoordinates );
		
		while( notVisitedCoordinates.size() > 0 ){
			
			Coordinate current = notVisitedCoordinates.poll();
			Set<HeroToken> tokens = losMap.get( current );
			if( tokens == null ){
				tokens = new HashSet<>();
				losMap.put(current, tokens);
			}
			tokens.add(token);
			visitedCoordinates.add(current);
			addFrontFields( token.getCoordinate(), current, token.getDirection(), notVisitedCoordinates, visitedCoordinates );
		}		
	}
	public void removeToken(HeroToken token){
		for(Coordinate coordinate : losMap.keySet()){
			Set<HeroToken> enemiesWithLos = losMap.get(coordinate);
			if(enemiesWithLos!=null){
				enemiesWithLos.remove(token);
				if( enemiesWithLos.size() == 0 ){
					losMap.put( coordinate, null );
				}
			}
		}
	}

	public Collection<HeroToken> getHeroTokensHavingLOS( Coordinate coordinate ){
        Collection<HeroToken> tokensWithLos = losMap.get( coordinate );
        if(tokensWithLos == null){
            return Collections.EMPTY_SET;
        }
		return tokensWithLos;
	}
	
	public void debugLosMap( Log logger ){

		for( Coordinate coordinate : losMap.keySet() ){
			Set<HeroToken> tokens = losMap.get( coordinate );
			if( tokens != null ){
				logger.debug( "Coordinate "+coordinate+" is watched" );
			}
		}
		
	}

}
