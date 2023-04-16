package at.htl.dart.entity;

public class GameParticipation {
    Player player;
    Game game;
    int points;

    public GameParticipation(){

    }

    public GameParticipation(Player player, Game game, Integer points){
        this.player = player;
        this.game = game;
        this.points = points;
    }

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

    public int getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
