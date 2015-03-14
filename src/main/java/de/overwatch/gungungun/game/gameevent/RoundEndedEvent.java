package de.overwatch.gungungun.game.gameevent;


public class RoundEndedEvent extends GameEvent {

    private int round;

    public RoundEndedEvent(int round) {
        super();
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    @Override
    public GameEventType getType() {
        return GameEventType.ROUND_ENDED;
    }

    @Override
    public String toString() {
        return "RoundEndedEvent{" +
                "round=" + round +
                "} " + super.toString();
    }
}
