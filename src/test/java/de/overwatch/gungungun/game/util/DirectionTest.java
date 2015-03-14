package de.overwatch.gungungun.game.util;


import de.overwatch.gungungun.game.model.Coordinate;
import org.junit.Assert;
import org.junit.Test;

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
    public void testGetDirectionTowerdsoordinateIsSouth(){
        Coordinate source = new Coordinate(5,5);
        Coordinate target = new Coordinate(5,10);
        Assert.assertEquals(Direction.SOUTH, Direction.getDirectionTowardsCoordinate(source, target));
    }

}
