package de.overwatch.gungungun.game.gameevent;


public class GameEndedEvent extends GameEvent {

    @Override
    public GameEventType getType() {
        return GameEventType.GAME_ENDED;
    }

    @Override
    public String toString() {
        return "GameEndedEvent{} " + super.toString();
    }
}
