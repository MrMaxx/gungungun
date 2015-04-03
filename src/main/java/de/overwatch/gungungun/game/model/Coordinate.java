package de.overwatch.gungungun.game.model;

import de.overwatch.gungungun.game.util.Direction;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public Collection<Coordinate> getCoordinateInRange( int diameter ){
        Collection<Coordinate> result = new LinkedList<Coordinate>();
        if( diameter < 1 ){ return result;	}
        for( int x= this.getX()-diameter; x<=this.getX()+diameter; x++ ){
            for( int y= this.getY()-diameter; y<=this.getY()+diameter; y++ ){
                result.add( new Coordinate(x, y) );
            }
        }
        return result;
    }

    public boolean isIn45Degree( Direction myDirection, Coordinate otherCoordinate ){
        int normalizedX = otherCoordinate.getX() - getX();
        int normalizedY = otherCoordinate.getY() - getY();

        if( Direction.NORTH.equals( myDirection ) ){
            return ( normalizedY < 0 && Math.abs( normalizedY ) >= Math.abs( normalizedX ) );
        }else if( Direction.SOUTH.equals( myDirection ) ){
            return ( normalizedY > 0 && Math.abs( normalizedY ) >= Math.abs( normalizedX ) );
        }else if( Direction.EAST.equals( myDirection ) ){
            return ( normalizedX > 0 && normalizedX >= Math.abs( normalizedY ) );
        }else if( Direction.WEST.equals( myDirection ) ){
            return ( normalizedX < 0 && Math.abs( normalizedX ) >= Math.abs( normalizedY ) );
        }
        return false;
    }

    public int getDistanceInFields(Coordinate other){
        int absX = Math.abs(this.getX() - other.getX());
        int absY = Math.abs(this.getY() - other.getY());
        if(absX>absY) return absX;
        return absY;
    }

    public double getDistance(Coordinate other){
        int x = this.getX() - other.getX();
        int y = this.getY() - other.getY();
        return Math.sqrt((x*x) + (y*y));
    }

    public List<Coordinate> getXFrontFieldRows( Direction d, int rowCount){
        List<Coordinate> result = new LinkedList<Coordinate>();
        if( rowCount <= 0 )
            return result;
        int xx,yy;
        if( d.equals(Direction.NORTH) ){
            for( int count = 1; count <= rowCount; count ++){
                for( xx=-count ; xx<(1+count); xx++ )
                    result.add( new Coordinate( this.getX()+xx , this.getY()-count ) );
            }
        }
        if( d.equals(Direction.EAST) ){
            for( int count = 1; count <= rowCount; count ++){
                for( yy=-count ; yy<(1+count); yy++ )
                    result.add( new Coordinate( this.getX()+count , this.getY()+yy ) );
            }
        }
        if( d.equals(Direction.SOUTH) ){
            for( int count = 1; count <= rowCount; count ++){
                for( xx=-count ; xx<(1+count); xx++ )
                    result.add( new Coordinate( this.getX()+xx , this.getY()+count ) );
            }
        }
        if( d.equals(Direction.WEST) ){
            for( int count = 1; count <= rowCount; count ++){
                for( yy=-count ; yy<(1+count); yy++ )
                    result.add( new Coordinate( this.getX()-count , this.getY()+yy ) );
            }
        }
        return result;
    }

    public List<Coordinate>getFrontFields( Direction d ){
        return getXFrontFieldRows( d, 1);
    }

    public Coordinate getCoordinateSouthOf(){
        return new Coordinate(getX(),getY()+1);
    }
    public Coordinate getCoordinateSouthWestOf(){
        return new Coordinate(getX()-1,getY()+1);
    }
    public Coordinate getCoordinateSouthEastOf(){
        return new Coordinate(getX()+1,getY()+1);
    }
    public Coordinate getCoordinateNorthOf(){
        return new Coordinate(getX(),getY()-1);
    }
    public Coordinate getCoordinateNorthWestOf(){
        return new Coordinate(getX()-1,getY()-1);
    }
    public Coordinate getCoordinateNorthEastOf(){
        return new Coordinate(getX()+1,getY()-1);
    }
    public Coordinate getCoordinateEastOf(){
        return new Coordinate(getX()+1,getY());
    }
    public Coordinate getCoordinateWestOf(){
        return new Coordinate(getX()-1,getY());
    }

    public boolean isDiagonallyTo(Coordinate other){
        return ( ( getX() == other.getX()-1 || getX() == other.getX()+1 ) &&
                ( getY() == other.getY()-1 || getY() == other.getY()+1 ) );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
