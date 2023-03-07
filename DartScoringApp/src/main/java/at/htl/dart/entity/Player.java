package at.htl.dart.entity;

public class Player {
    Integer playerId;
    String name;

    public Integer getPlayerId(){
        return this.playerId;
    }

    public void setPlayerId(Integer playerId){
        this.playerId = playerId;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}