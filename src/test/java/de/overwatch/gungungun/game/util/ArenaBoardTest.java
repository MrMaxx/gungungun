package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.GameStateBuilder;
import de.overwatch.gungungun.game.builder.TestArenaBuilder;
import de.overwatch.gungungun.game.builder.TestHeroBuilder;
import de.overwatch.gungungun.game.builder.TestPartyBuilder;
import de.overwatch.gungungun.game.builder.TestTokenBlueprint;
import de.overwatch.gungungun.game.model.BoardCoordinate;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ArenaBoardTest {

    private ArenaBoard arenaBoard;
    private HeroToken heroToken;
    private GameState state;

	@Before
    public void init(){
        Arena arena = new TestArenaBuilder().build();

        state = new GameStateBuilder().withArenaCoordinates(arena.getArenaCoordinates()).build();

        Party party = new TestPartyBuilder().withHero(TestTokenBlueprint.GRUNT).build();
        Hero hero = party.getHeroes().iterator().next();

        heroToken = new HeroToken(hero, new Coordinate( 7,4 ));
        heroToken.setDirection(Direction.NORTH);


        arenaBoard = new ArenaBoard(state.getCoordinatesMap(), Collections.singleton(heroToken));
    }
	
	@Test
	public void testBlockings() throws Exception{

		Assert.assertTrue(arenaBoard.isCoordinateBlockingLOS(new Coordinate(4, 5)));
		Assert.assertTrue(arenaBoard.isCoordinateBlockingLOS(new Coordinate(1, 1)));
		
		Assert.assertFalse(arenaBoard.isCoordinateBlockingLOS(new Coordinate(2, 4)));
		Assert.assertFalse(arenaBoard.isCoordinateBlockingLOS(new Coordinate(7, 9)));
		
		Assert.assertTrue(arenaBoard.isCoordinateBlockingMovement(new Coordinate(7, 4)));
		Assert.assertTrue(arenaBoard.isCoordinateBlockingMovement(new Coordinate(5, 4)));
		
		Assert.assertFalse(arenaBoard.isCoordinateBlockingMovement(new Coordinate(2, 6)));
		Assert.assertFalse(arenaBoard.isCoordinateBlockingMovement(new Coordinate(7, 10)));
		
	}

    @Test(expected = NoPathExistsException.class)
    public void testShortestPathsDoesntExist() throws Exception{
        arenaBoard.getShortestPath( new Coordinate(7,8), new Coordinate(99,99) );
    }

    @Test(expected = NoPathExistsException.class)
    public void testIsland() throws Exception{
        Map<Coordinate, BoardCoordinate> coordinatesMap = state.getCoordinatesMap();

        Coordinate coordinate = new Coordinate(14,14);
        BoardCoordinate bc = new BoardCoordinate(coordinate,true,true);
        coordinatesMap.put(coordinate,bc);
        arenaBoard = new ArenaBoard(coordinatesMap, Collections.singleton(heroToken));

        arenaBoard.getShortestPath( new Coordinate(7,8), coordinate );
    }

	@Test
	public void testShortestPaths() throws Exception{

		List<Coordinate> shortPath = arenaBoard.getShortestPath( new Coordinate(7,8), new Coordinate(4,7) );
		
		//Assert.assertEquals( new Coordinate(7,8), shortPath.get( 0 ) );
		Assert.assertEquals(new Coordinate(6, 9), shortPath.get(0));
		Assert.assertEquals(new Coordinate(5, 10), shortPath.get(1));
		Assert.assertEquals(new Coordinate(4, 10), shortPath.get(2));
		Assert.assertEquals(new Coordinate(3, 9), shortPath.get(3));
		Assert.assertEquals(new Coordinate(3, 8), shortPath.get(4));
		Assert.assertEquals(new Coordinate(4, 7), shortPath.get(5));
				
		List<Coordinate> shortPath2 = arenaBoard.getShortestPath( new Coordinate(7,6), new Coordinate(6,2) );
		
		//Assert.assertEquals( new Coordinate(7,6), shortPath2.get( 0 ) );
		Assert.assertEquals(new Coordinate(7, 5), shortPath2.get(0));
		Assert.assertEquals(new Coordinate(8, 4), shortPath2.get(1));
		Assert.assertEquals(new Coordinate(7, 3), shortPath2.get(2));
		Assert.assertEquals(new Coordinate(6, 2), shortPath2.get(3));
	}
}
