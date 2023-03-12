package at.htl.dart.entity;

public class Game {
    Integer gameId;
    Player currentPlayer;
    GameType gameType;

    public Game(){

    }

    public Game(Player currentPlayer, GameType gameType){
        this.currentPlayer = currentPlayer;
        this.gameType = gameType;
    }

    public Integer getID(){
        return this.gameId;
    }

    public void setID(Integer id){
        this.gameId = id;
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