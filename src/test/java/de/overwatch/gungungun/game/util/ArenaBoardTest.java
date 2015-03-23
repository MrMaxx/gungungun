package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.domain.SpawnPoint;
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

import java.util.*;

public class ArenaBoardTest {

    private ArenaBoard arenaBoard;

    private HeroToken heroToken1;
    private HeroToken heroToken2;

    private GameState state;

	@Before
    public void init(){
        Arena arena = new TestArenaBuilder().build();

        Party party1 = new TestPartyBuilder().withHero(TestTokenBlueprint.GRUNT).build();
        Hero hero1 = party1.getHeroes().iterator().next();

        Party party2 = new TestPartyBuilder().withHero(TestTokenBlueprint.GRUNT).build();
        Hero hero2 = party2.getHeroes().iterator().next();

        Collection<Party> parties = new LinkedList<>();
        parties.add(party1);
        parties.add(party2);

        // we are not going to use these spawnpoints
        SpawnPoint sp1 = new SpawnPoint();
        sp1.setGroupId(1);
        sp1.setX(2);
        sp1.setY(4);

        SpawnPoint sp2 = new SpawnPoint();
        sp2.setGroupId(2);
        sp2.setX(2);
        sp2.setY(5);

        Collection<SpawnPoint> spawns = new LinkedList<>();
        spawns.add(sp1);
        spawns.add(sp2);

        // we use the GameStateBuilder just for transforming our ArenaCoordinates
        state = new GameStateBuilder()
                .withArenaCoordinates(arena.getArenaCoordinates())
                .withParties(parties, spawns)
                .build();

        arenaBoard = new ArenaBoard(state.getCoordinatesMap(), state.getHeroes());
    }
	
	@Test
	public void testBlockings() throws Exception{

		Assert.assertTrue(arenaBoard.isCoordinateBlockingLOS(new Coordinate(4, 5)));
		Assert.assertTrue(arenaBoard.isCoordinateBlockingLOS(new Coordinate(1, 1)));
		
		Assert.assertTrue(arenaBoard.isCoordinateBlockingLOS(new Coordinate(2, 4)));
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
        arenaBoard = new ArenaBoard(coordinatesMap, state.getHeroes());

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

    @Test
    public void testLos(){
        Assert.assertTrue(arenaBoard.isLOSFreeToTarget(new Coordinate(7,11), new Coordinate(7,5)));
        Assert.assertFalse(arenaBoard.isLOSFreeToTarget(new Coordinate(7,11), new Coordinate(7,3)));

        // Coordinate 7,4 is not transparent, but its still part of the Map so the Los until it is free
        Assert.assertTrue(arenaBoard.isLOSFreeToTarget(new Coordinate(7,11), new Coordinate(7,4)));
    }
}
