package at.htl.dart.entity;

public class GameType {
    Integer id;
    int points;

    public GameType(){

    }

    public GameType(Integer points){
        this.points = points;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}