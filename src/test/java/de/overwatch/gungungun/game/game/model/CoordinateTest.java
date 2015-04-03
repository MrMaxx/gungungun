package de.overwatch.gungungun.game.game.model;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.util.Direction;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {


    @Test
    public void testGetDirectionTowerdsoordinateIsEast(){
        Coordinate source = new Coordinate(5,5);

        Assert.assertTrue(source.isIn45Degree(Direction.EAST, new Coordinate(8,8)));
        Assert.assertTrue(source.isIn45Degree(Direction.EAST, new Coordinate(8,5)));
        Assert.assertTrue(source.isIn45Degree(Direction.EAST, new Coordinate(8,2)));

        Assert.assertTrue(source.isIn45Degree(Direction.NORTH, new Coordinate(2,2)));
        Assert.assertTrue(source.isIn45Degree(Direction.NORTH, new Coordinate(5,2)));
        Assert.assertTrue(source.isIn45Degree(Direction.NORTH, new Coordinate(8,2)));

        Assert.assertTrue(source.isIn45Degree(Direction.WEST, new Coordinate(2,2)));
        Assert.assertTrue(source.isIn45Degree(Direction.WEST, new Coordinate(2,5)));
        Assert.assertTrue(source.isIn45Degree(Direction.WEST, new Coordinate(2,8)));

        Assert.assertTrue(source.isIn45Degree(Direction.SOUTH, new Coordinate(2,8)));
        Assert.assertTrue(source.isIn45Degree(Direction.SOUTH, new Coordinate(5,8)));
        Assert.assertTrue(source.isIn45Degree(Direction.SOUTH, new Coordinate(8,8)));

        Assert.assertFalse(source.isIn45Degree(Direction.WEST, new Coordinate(8,8)));
        Assert.assertFalse(source.isIn45Degree(Direction.WEST, new Coordinate(8,5)));
        Assert.assertFalse(source.isIn45Degree(Direction.WEST, new Coordinate(8,2)));

        Assert.assertFalse(source.isIn45Degree(Direction.SOUTH, new Coordinate(2,2)));
        Assert.assertFalse(source.isIn45Degree(Direction.SOUTH, new Coordinate(5,2)));
        Assert.assertFalse(source.isIn45Degree(Direction.SOUTH, new Coordinate(8,2)));

        Assert.assertFalse(source.isIn45Degree(Direction.EAST, new Coordinate(2,2)));
        Assert.assertFalse(source.isIn45Degree(Direction.EAST, new Coordinate(2,5)));
        Assert.assertFalse(source.isIn45Degree(Direction.EAST, new Coordinate(2,8)));

        Assert.assertFalse(source.isIn45Degree(Direction.NORTH, new Coordinate(2,8)));
        Assert.assertFalse(source.isIn45Degree(Direction.NORTH, new Coordinate(5,8)));
        Assert.assertFalse(source.isIn45Degree(Direction.NORTH, new Coordinate(8,8)));
    }

}
