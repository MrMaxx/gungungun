package de.overwatch.gungungun.service.ranking;


public class UserWithRank {

    private Long id;
    private String username;
    private int score;
    private Integer rank;

    public UserWithRank(Long id, String username, int score, int rank) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.rank = rank;;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
