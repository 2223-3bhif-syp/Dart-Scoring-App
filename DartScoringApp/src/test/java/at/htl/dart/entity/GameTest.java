package at.htl.dart.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Player p1;
    GameType gameType1;

    @BeforeEach
    void setUp(){
        p1 = new Player("Ben");
        p1.setPlayerId(1);

        gameType1 = new GameType(501);
        gameType1.setId(2);
    }

    @Test
    void getID() {
        Game g1 = new Game(p1, gameType1);

        assertEquals(g1.getID(), null);
    }

    @Test
    void setID() {
        Game g1 = new Game(p1, gameType1);

        g1.setID(3);

        assertEquals(g1.getID(), 3);
    }

    @Test
    void getCurrentPlayer() {
        Game g1 = new Game(p1, gameType1);

        assertEquals(g1.getCurrentPlayer(), p1);
    }

    @Test
    void setCurrentPlayer() {
        Game g1 = new Game(p1, gameType1);
        Player p2 = new Player("Yusuf");

        g1.setCurrentPlayer(p2);

        assertEquals(g1.getCurrentPlayer(), p2);
    }

    @Test
    void getGameType() {
        Game g1 = new Game(p1, gameType1);

        assertEquals(g1.getGameType(), gameType1);
    }

    @Test
    void setGameType() {
        Game g1 = new Game(p1, gameType1);
        GameType gameType2 = new GameType(301);

        g1.setGameType(gameType2);

        assertEquals(g1.getGameType(), gameType2);
    }
}