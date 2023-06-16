package at.htl.dart.entity;

public class Game {
    Integer id;
    Player currentPlayer;
    GameType gameType;

    public Game(){

    }

    public Game(Player currentPlayer, GameType gameType){
        this.currentPlayer = currentPlayer;
        this.gameType = gameType;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public GameType getGameType(){
        return this.gameType;
    }

    public void setGameType(GameType gameType){
        this.gameType = gameType;
    }
}