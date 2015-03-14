package de.overwatch.gungungun.game.util;

import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
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

public class LosBoardTest {

    private LosBoard losBoard;
    private ArenaBoard arenaBoard;
    private GameState state;

    private HeroToken heroToken;

    @Before
    public void init(){
        Arena arena = new TestArenaBuilder().build();

        Party party = new TestPartyBuilder().withHero(TestTokenBlueprint.GRUNT).build();
        Hero hero = party.getHeroes().iterator().next();

        heroToken = new HeroToken(hero, new Coordinate( 7,5 ));
        heroToken.setDirection(Direction.NORTH);

        state = new GameStateBuilder()
                .withArenaCoordinates(arena.getArenaCoordinates())
                .build();

        arenaBoard = new ArenaBoard(state.getCoordinatesMap(), state.getHeroes());
        losBoard = new LosBoard(arenaBoard, state.getHeroes());
    }
	

	@Test
	public void testLosWithNoTokens() throws Exception{

        for(Coordinate coordinate: state.getCoordinatesMap().keySet()){
            assertNoLosOnCoordinate(coordinate);
        }

	}

    private void assertNoLosOnCoordinate(Coordinate coordinate){
        Assert.assertTrue(losBoard.getHeroTokensHavingLOS(coordinate).size() == 0);
    }

    private void assertLosOnCoordinate(Coordinate coordinate, HeroToken token){
        Collection<HeroToken> heroesWithLos = losBoard.getHeroTokensHavingLOS(coordinate);
        Assert.assertTrue(losBoard.getHeroTokensHavingLOS(coordinate).size() > 0);
        Assert.assertTrue(heroesWithLos.contains(token));
    }

	@Test
	public void testTokenMovement() throws Exception{

        losBoard.removeToken(heroToken);

        heroToken.setCoordinate( new Coordinate( 7,5 ) );
        heroToken.setDirection( Direction.NORTH );

        assertNoLosOnCoordinate(new Coordinate(7,4));
        assertNoLosOnCoordinate(new Coordinate(7,3));
        assertNoLosOnCoordinate(new Coordinate(7,2));
        assertNoLosOnCoordinate(new Coordinate(8,2));
        assertNoLosOnCoordinate(new Coordinate(8,3));
        assertNoLosOnCoordinate(new Coordinate(8,4));

		losBoard.addToken(heroToken);

        assertLosOnCoordinate(new Coordinate(7,4), heroToken);
        assertLosOnCoordinate(new Coordinate(7,3), heroToken);
        assertLosOnCoordinate(new Coordinate(7,2), heroToken);
        assertLosOnCoordinate(new Coordinate(8,2), heroToken);
        assertLosOnCoordinate(new Coordinate(8,3), heroToken);
        assertLosOnCoordinate(new Coordinate(8,4), heroToken);

        assertNoLosOnCoordinate(new Coordinate(8,5));
        assertNoLosOnCoordinate(new Coordinate(8,6));
        assertNoLosOnCoordinate(new Coordinate(7,6));

		heroToken.setDirection( Direction.SOUTH );
		losBoard.updateEnemy( heroToken );

        assertNoLosOnCoordinate(new Coordinate(7,4));
        assertNoLosOnCoordinate(new Coordinate(7,3));
        assertNoLosOnCoordinate(new Coordinate(7,2));
        assertNoLosOnCoordinate(new Coordinate(8,2));
        assertNoLosOnCoordinate(new Coordinate(8,3));
        assertNoLosOnCoordinate(new Coordinate(8,4));

        assertLosOnCoordinate(new Coordinate(8,6), heroToken);
        assertLosOnCoordinate(new Coordinate(7,6), heroToken);
        assertLosOnCoordinate(new Coordinate(7,7), heroToken);
        assertLosOnCoordinate(new Coordinate(7,8), heroToken);

	}
}
