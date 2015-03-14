package de.overwatch.gungungun.game.gamemove;


import java.util.LinkedList;
import java.util.List;

public abstract class GameMove {

    protected List<GameMoveScore> baseScores = new LinkedList<>();

    public float getOverallScore(){
        float result = 0;
        for(GameMoveScore score: baseScores){
            result += score.getScore();
        }
        return result;
    }

    public abstract int getCost();

    public void addBaseScore(float score, String source) {
        baseScores.add(new GameMoveScore(score, source));
    }

    public abstract GameMoveType getType();

    public static enum GameMoveType{
        MOVE_TO,
        SHOOT,
        ATTACK,
        WAIT
    }

    public class GameMoveScore{
        private float score;
        private String source;

        public GameMoveScore(float score, String source) {
            this.score = score;
            this.source = source;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}