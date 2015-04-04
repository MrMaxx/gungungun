package de.overwatch.gungungun.game.game.model;


import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.util.Direction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class CoordinateTest {


    @Test
    public void testFrontFields(){

        Coordinate center = new Coordinate(5,5);

        Collection<Coordinate> frontFields = center.getFrontFields(Direction.EAST);
        Assert.assertTrue(frontFields.contains(new Coordinate(6,4)));
        Assert.assertTrue(frontFields.contains(new Coordinate(6,5)));
        Assert.assertTrue(frontFields.contains(new Coordinate(6,6)));

        frontFields = center.getFrontFields(Direction.NORTH);
        Assert.assertTrue(frontFields.contains(new Coordinate(4,4)));
        Assert.assertTrue(frontFields.contains(new Coordinate(5,4)));
        Assert.assertTrue(frontFields.contains(new Coordinate(6,4)));

        frontFields = center.getFrontFields(Direction.WEST);
        Assert.assertTrue(frontFields.contains(new Coordinate(4,4)));
        Assert.assertTrue(frontFields.contains(new Coordinate(4,5)));
        Assert.assertTrue(frontFields.contains(new Coordinate(4,6)));

        frontFields = center.getFrontFields(Direction.SOUTH);
        Assert.assertTrue(frontFields.contains(new Coordinate(4,6)));
        Assert.assertTrue(frontFields.contains(new Coordinate(5,6)));
        Assert.assertTrue(frontFields.contains(new Coordinate(6,6)));

        frontFields = new Coordinate(9, 6).getFrontFields(Direction.SOUTH);
        //Assert.assertTrue(frontFields.contains(new Coordinate(10,5)));
        Direction newDirection = Direction.getDirectionTowardsCoordinate(new Coordinate(9, 6), new Coordinate(10,5));
        Assert.assertEquals(Direction.NORTH, newDirection);
    }

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
