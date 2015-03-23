package de.overwatch.gungungun.game.util;

import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.domain.SpawnPoint;
import de.overwatch.gungungun.game.GameState;
import de.overwatch.gungungun.game.GameStateBuilder;
import de.overwatch.gungungun.game.builder.TestArenaBuilder;
import de.overwatch.gungungun.game.builder.TestPartyBuilder;
import de.overwatch.gungungun.game.builder.TestTokenBlueprint;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class LosArenaBoardTest {

    private GameState state;

    private HeroToken heroToken1;
    private HeroToken heroToken2;

    private ArenaBoard arenaBoard;

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

        SpawnPoint sp1 = new SpawnPoint();
        sp1.setGroupId(1);
        sp1.setX(7);
        sp1.setY(5);

        SpawnPoint sp2 = new SpawnPoint();
        sp2.setGroupId(2);
        sp2.setX(8);
        sp2.setY(4);

        Collection<SpawnPoint> spawns = new LinkedList<>();
        spawns.add(sp1);
        spawns.add(sp2);

        // we use the GameStateBuilder just for transforming our ArenaCoordinates
        state = new GameStateBuilder()
                .withArenaCoordinates(arena.getArenaCoordinates())
                .withParties(parties, spawns)
                .build();

        for(HeroToken heroToken : state.getHeroes()){
            if(heroToken.getId().equals(hero1.getId())){
                heroToken1 = heroToken;
            }
            if(heroToken.getId().equals(hero2.getId())){
                heroToken2 = heroToken;
            }
        }

        arenaBoard = new ArenaBoard(state.getCoordinatesMap(), state.getHeroes());
    }
	

	@Test
	public void testLos() throws Exception{

        arenaBoard = new ArenaBoard(state.getCoordinatesMap(), new LinkedList<HeroToken>());


	}

    private void assertNoLosOnCoordinate(Coordinate source, Coordinate target){
        Assert.assertTrue(arenaBoard.isLOSFreeToTarget(source, target));
    }
/*
    @Test
    public void testTokenSeeingToken() throws Exception{

        losBoard.removeToken(heroToken1);
        losBoard.removeToken(heroToken2);

        heroToken2.setCoordinate( new Coordinate( 8,3 ) );
        heroToken2.setDirection( Direction.SOUTH );

        heroToken1.setCoordinate( new Coordinate( 7,5 ) );
        heroToken1.setDirection( Direction.NORTH );

        losBoard.addToken(heroToken1);
        losBoard.addToken(heroToken2);

        assertLosOnCoordinate(new Coordinate(8,3), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,5), heroToken2);

    }

	@Test
	public void testTokenMovement() throws Exception{

        losBoard.removeToken(heroToken1);
        losBoard.removeToken(heroToken2);

        assertNoLosOnCoordinate(new Coordinate(7,4));
        assertNoLosOnCoordinate(new Coordinate(7,3));
        assertNoLosOnCoordinate(new Coordinate(7,2));
        assertNoLosOnCoordinate(new Coordinate(8,2));
        assertNoLosOnCoordinate(new Coordinate(8,3));
        assertNoLosOnCoordinate(new Coordinate(8,4));

        heroToken1.setCoordinate( new Coordinate( 7,5 ) );
        heroToken1.setDirection( Direction.NORTH );

		losBoard.addToken(heroToken1);

        assertLosOnCoordinate(new Coordinate(7,4), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,3), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,2), heroToken1);
        assertLosOnCoordinate(new Coordinate(8,2), heroToken1);
        assertLosOnCoordinate(new Coordinate(8,3), heroToken1);
        assertLosOnCoordinate(new Coordinate(8,4), heroToken1);

        assertNoLosOnCoordinate(new Coordinate(8,5));
        assertNoLosOnCoordinate(new Coordinate(8,6));
        assertNoLosOnCoordinate(new Coordinate(7,6));

		heroToken1.setDirection( Direction.SOUTH );
		losBoard.updateEnemy( heroToken1 );

        assertNoLosOnCoordinate(new Coordinate(7,4));
        assertNoLosOnCoordinate(new Coordinate(7,3));
        assertNoLosOnCoordinate(new Coordinate(7,2));
        assertNoLosOnCoordinate(new Coordinate(8,2));
        assertNoLosOnCoordinate(new Coordinate(8,3));
        assertNoLosOnCoordinate(new Coordinate(8,4));

        assertLosOnCoordinate(new Coordinate(8,6), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,6), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,7), heroToken1);
        assertLosOnCoordinate(new Coordinate(7,8), heroToken1);

	}*/
}
