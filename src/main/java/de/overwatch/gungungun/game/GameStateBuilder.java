package de.overwatch.gungungun.game;

import de.overwatch.gungungun.domain.ArenaCoordinate;
import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.domain.SpawnPoint;
import de.overwatch.gungungun.game.model.BoardCoordinate;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;

import java.util.*;

public class GameStateBuilder {


    Map<Coordinate, BoardCoordinate> coordinatesMap = new HashMap<>();
    Map<Long, HeroToken> idToHeroTokenMap = new HashMap<>();

    public GameStateBuilder withArenaCoordinates(Collection<ArenaCoordinate> arenaCoordinates){
        for(ArenaCoordinate arenaCoordinate : arenaCoordinates){
            Coordinate coordinate = new Coordinate(arenaCoordinate.getX(), arenaCoordinate.getY());
            BoardCoordinate boardCoordinate = new BoardCoordinate(
                    coordinate,
                    arenaCoordinate.getTransparent(),
                    arenaCoordinate.getPermeable());
            coordinatesMap.put(coordinate,boardCoordinate);
        }
        return this;
    }

    public GameStateBuilder withParties(Collection<Party> parties, Collection<SpawnPoint> spawnPoints){

        Map<Integer,List<Coordinate>> groupIdToCoordinatesMap = new HashMap<>();
        for(SpawnPoint spawnPoint: spawnPoints){
            List<Coordinate> coordinates = groupIdToCoordinatesMap.get(spawnPoint.getGroupId());
            if(coordinates == null){
                coordinates = new LinkedList<>();
                groupIdToCoordinatesMap.put(spawnPoint.getGroupId(), coordinates);
            }
            coordinates.add(new Coordinate(spawnPoint.getX(), spawnPoint.getY()));
        }

        int groupId = 0;
        for(Party party : parties){
            groupId++;
            for(Hero hero: party.getHeroes()){
                List<Coordinate> spawnsInGroup = groupIdToCoordinatesMap.get(groupId);
                Coordinate spawnPoint = spawnsInGroup.get(0);
                if(spawnPoint==null){
                    throw new IllegalArgumentException("Not enough SpawnPoints left...groupId="+groupId+".");
                }
                spawnsInGroup.remove(spawnPoint);
                idToHeroTokenMap.put(hero.getId(), new HeroToken(hero, spawnPoint));
            }
        }
        return this;
    }

    public GameState build(){
        return new GameState(idToHeroTokenMap, coordinatesMap);
    }

}
