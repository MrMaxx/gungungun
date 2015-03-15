package de.overwatch.gungungun.game.gameevent;


public abstract class GameEvent {

    private GameEventType type;
    public GameEvent(){
        this.type = getType();
    }

    public abstract GameEventType getType();

    public static enum GameEventType{
        MOVE_TO,
        SHOOT,
        ATTACK,
        TOKEN_DIED,
        TOKEN_SPAWNED,
        TURN_TO,
        GAME_ENDED,
        ROUND_ENDED
    }

}
