package de.overwatch.gungungun.game.util;

import de.overwatch.gungungun.game.model.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class LosLine {

	private Set<Coordinate> line = new HashSet<Coordinate>();
	private Set<LinePair> linePairs = new HashSet<LinePair>();
	
	public LosLine(Set<Coordinate> aLine, Set<LinePair>someLinePairs){
		line = aLine;
		linePairs= someLinePairs;
	}

	public Set<Coordinate> getLine() {
		return line;
	}

	public Set<LinePair> getLinePairs() {
		return linePairs;
	}
}
