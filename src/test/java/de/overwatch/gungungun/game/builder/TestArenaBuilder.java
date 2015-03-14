package de.overwatch.gungungun.game.builder;


import de.overwatch.gungungun.domain.Arena;
import de.overwatch.gungungun.domain.ArenaCoordinate;
import de.overwatch.gungungun.game.model.Coordinate;

import java.util.*;

public class TestArenaBuilder {

    public Arena build(){

        Arena arena = new Arena();
        arena.setArenaKey("TEST_ARENA");
        arena.setId(99L);

        Set<ArenaCoordinate> arenaCoordinates = new HashSet<>();

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(2, 4)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(2, 5)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(2, 6)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(2, 7)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 3)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 4)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 5)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 6)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 7)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 8)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 9)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(3, 10)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(4, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(4, 4)));

        // NOT PERMEABLE AND NOT TRANSPARENT
        arenaCoordinates.add(createArenaCoordinate(arena, false, false, new Coordinate(4, 5)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(4, 6)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(4, 7)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(4, 10)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(5, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(5, 10)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(6, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(6, 9)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(6, 10)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(6, 11)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 3)));

        // NOT PERMEABLE
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 4)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 5)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 6)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 7)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 8)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 9)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 10)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(7, 11)));

        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 2)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 3)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 4)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 5)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 6)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 9)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 10)));
        arenaCoordinates.add(createArenaCoordinate(arena, true, true, new Coordinate(8, 11)));

        arena.setArenaCoordinates(arenaCoordinates);

        return arena;
    }

    private ArenaCoordinate createArenaCoordinate(Arena arena, boolean permeable, boolean transparent, Coordinate c){
        return this.createArenaCoordinate(arena, permeable, transparent, c.getX(), c.getY());
    }
    private ArenaCoordinate createArenaCoordinate(Arena arena, boolean permeable, boolean transparent, int x, int y){
        Random rand = new Random();
        ArenaCoordinate ac = new ArenaCoordinate();
        ac.setId(rand.nextLong());
        ac.setArena(arena);
        ac.setPermeable(permeable);
        ac.setTransparent(transparent);
        ac.setX(x);
        ac.setY(y);
        return ac;
    }


}
