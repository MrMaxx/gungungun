package de.overwatch.gungungun.game.model;


public class BoardCoordinate {

    private Coordinate coordinate;
    private boolean transparent;
    private boolean permeable;

    public BoardCoordinate(Coordinate coordinate, boolean transparent, boolean permeable) {
        this.coordinate = coordinate;
        this.transparent = transparent;
        this.permeable = permeable;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isPermeable() {
        return permeable;
    }
}
