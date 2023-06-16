package at.htl.dart.entity;

public class Player {
    Integer id;
    String name;

    public Player(){

    }

    public Player(String name){
        this.name = name;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer playerId){
        this.id = playerId;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}