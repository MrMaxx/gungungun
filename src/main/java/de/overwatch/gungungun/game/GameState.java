package de.overwatch.gungungun.game;


import de.overwatch.gungungun.game.gameevent.GameEvent;
import de.overwatch.gungungun.game.model.BoardCoordinate;
import de.overwatch.gungungun.game.model.Coordinate;
import de.overwatch.gungungun.game.model.HeroToken;
import de.overwatch.gungungun.game.util.ArenaBoard;
import de.overwatch.gungungun.game.util.LosBoard;

import java.util.*;

public class GameState {

    private Map<Long, HeroToken> idToHeroTokenMap = new HashMap<>();
    private Map<Coordinate, BoardCoordinate> coordinatesMap = new HashMap<>();

    private ArenaBoard arenaBoard;

    private int round;

    List<GameEvent> gameEvents = new LinkedList<>();
    Long winningPartyId;

    public GameState(Map<Long, HeroToken> idToHeroTokenMap, Map<Coordinate, BoardCoordinate> coordinatesMap) {
        this.idToHeroTokenMap = idToHeroTokenMap;
        this.coordinatesMap = coordinatesMap;
        this.arenaBoard = new ArenaBoard(coordinatesMap, idToHeroTokenMap.values());
        this.round = 1;
    }

    public void addGameEvent(GameEvent event){
        gameEvents.add(event);
    }
    public void addGameEvents(List<GameEvent> events){
        gameEvents.addAll(events);
    }

    public List<GameEvent> getGameEvents() {
        return gameEvents;
    }

    public int getRound() {
        return round;
    }

    public boolean isOnlyOnePartyLeft(){
        Long firstPartyAlive = null;
        for(HeroToken heroToken: idToHeroTokenMap.values()){
            if(!heroToken.isDead()){
                if(firstPartyAlive == null){
                    firstPartyAlive = heroToken.getPartyId();
                }
                if(!firstPartyAlive.equals(heroToken.getPartyId())){
                    return false;
                }
            }
        }
        return true;
    }

    public void nextRound(){
        round++;
        for(HeroToken heroToken: idToHeroTokenMap.values()){
            heroToken.setActionsLeft(heroToken.getActionsPerTurn());
        }
    }

    public Collection<HeroToken> getHeroes() {
        return new LinkedList<>(idToHeroTokenMap.values());
    }

    public Collection<HeroToken> getEnemyHeroes(HeroToken activeToken) {
        List<HeroToken> enemyTokens = new LinkedList<>();
        for(HeroToken heroToken: idToHeroTokenMap.values()){
            if(!activeToken.getPartyId().equals(heroToken.getPartyId())){
                enemyTokens.add(heroToken);
            }
        }
        return enemyTokens;
    }

    public void heroMoved(HeroToken heroToken) {
        // Todo: this is pretty costly...make it better someday
        this.arenaBoard = new ArenaBoard(coordinatesMap, idToHeroTokenMap.values());
    }

    public void heroKilled(HeroToken heroToken) {
        idToHeroTokenMap.remove(heroToken.getId());
        // Todo: this is pretty costly...make it better someday
        this.arenaBoard = new ArenaBoard(coordinatesMap, idToHeroTokenMap.values());
    }

    public Map<Coordinate, BoardCoordinate> getCoordinatesMap() {
        return coordinatesMap;
    }

    public ArenaBoard getArenaBoard() {
        return arenaBoard;
    }

    public Long getWinningPartyId() {
        return winningPartyId;
    }

    public void setWinningPartyId(Long winningPartyId) {
        this.winningPartyId = winningPartyId;
    }
}
