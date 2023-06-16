package at.htl.dart.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getID() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);

        // act
        p1.setId(1);
        gameType1.setId(2);

        // assert
        assertNull(g1.getId());
    }

    @Test
    void setID() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);

        // act
        p1.setId(1);
        gameType1.setId(2);

        g1.setId(3);

        // assert
        assertEquals(g1.getId(), 3);
    }

    @Test
    void getCurrentPlayer() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);

        // act
        p1.setId(1);
        gameType1.setId(2);

        // assert
        assertEquals(g1.getCurrentPlayer(), p1);
    }

    @Test
    void setCurrentPlayer() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);
        Player p2 = new Player("Yusuf");

        // act
        p1.setId(1);
        gameType1.setId(2);

        g1.setCurrentPlayer(p2);

        // assert
        assertEquals(g1.getCurrentPlayer(), p2);
    }

    @Test
    void getGameType() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);

        // act
        p1.setId(1);
        gameType1.setId(2);

        // assert
        assertEquals(g1.getGameType(), gameType1);
    }

    @Test
    void setGameType() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);
        GameType gameType2 = new GameType(301);

        // act
        p1.setId(1);
        gameType1.setId(2);

        g1.setGameType(gameType2);

        // assert
        assertEquals(g1.getGameType(), gameType2);
    }
}