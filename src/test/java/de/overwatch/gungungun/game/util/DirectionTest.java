package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

public class DirectionTest {


    @Test
    public void testGetDirectionTowerdsoordinateIsEast(){
        Coordinate source = new Coordinate(5,5);
        Coordinate target = new Coordinate(10,5);
        Assert.assertEquals(Direction.EAST, Direction.getDirectionTowardsCoordinate(source, target));
    }

    @Test
    public void testGetDirectionTowerdsoordinateIsWest(){
        Coordinate source = new Coordinate(5,5);
        Coordinate target = new Coordinate(1,5);
        Assert.assertEquals(Direction.WEST, Direction.getDirectionTowardsCoordinate(source, target));
    }

    @Test
    public void testGetDirectionTowerdsoordinateIsNorth(){
        Coordinate source = new Coordinate(5,5);
        Coordinate target = new Coordinate(5,2);
        Assert.assertEquals(Direction.NORTH, Direction.getDirectionTowardsCoordinate(source, target));
    }

    @Test
    public void testGetDirection45Deg(){
        Collection<Direction> directions = new HashSet<>();
        directions.add(Direction.SOUTH);
        directions.add(Direction.EAST);

        Direction calculatedDirection = Direction.getDirectionTowardsCoordinate(new Coordinate(5,5), new Coordinate(6,6));

        Assert.assertTrue(directions.contains(calculatedDirection));
    }



}
