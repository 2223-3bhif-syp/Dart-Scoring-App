package at.htl.dart.entity;

public class GameParticipation {
    Player player;
    Game game;
    Integer Points;

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getPoints() {
        return this.Points;
    }

    public void setPoints(Integer points) {
        Points = points;
    }
}
