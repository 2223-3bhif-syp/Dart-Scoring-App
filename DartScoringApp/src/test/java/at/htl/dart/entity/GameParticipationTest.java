package at.htl.dart.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameParticipationTest {

    /*
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);
        Game g1 = new Game(p1, gameType1);

        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);
    * */
    @Test
    void getPlayer() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);
        Game g1 = new Game(p1, gameType1);

        GameParticipation gameParticipation1;

        // act
        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        // assert
        assertEquals(gameParticipation1.getPlayer(), p1);
    }

    @Test
    void setPlayer() {
        //arrange
        Player p1 = new Player("Ben");
        Player p2 = new Player("Yusuf");

        GameType gameType1 = new GameType(501);
        Game g1 = new Game(p1, gameType1);

        GameParticipation gameParticipation1;

        //act
        p1.setId(1);
        p2.setId(4);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());
        gameParticipation1.setPlayer(p2);

        // assert
        assertEquals(gameParticipation1.getPlayer(), p2);
    }

    @Test
    void getGame() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);
        Game g1 = new Game(p1, gameType1);

        GameParticipation gameParticipation1;

        // act
        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        // assert
        assertEquals(gameParticipation1.getGame(), g1);
    }

    @Test
    void setGame() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);
        Game g2 = new Game();

        GameParticipation gameParticipation1;

        // act
        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        g2.setId(4);
        gameParticipation1.setGame(g2);

        // assert
        assertEquals(gameParticipation1.getGame(), g2);
    }

    @Test
    void getPoints() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);
        Game g2 = new Game();

        GameParticipation gameParticipation1;

        // act
        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        // assert
        assertEquals(gameParticipation1.getPoints(), g1.getGameType().getPoints());
    }

    @Test
    void setPoints() {
        // arrange
        Player p1 = new Player("Ben");
        GameType gameType1 = new GameType(501);

        Game g1 = new Game(p1, gameType1);
        Game g2 = new Game();

        GameParticipation gameParticipation1;

        // act
        p1.setId(1);
        g1.setId(3);
        gameType1.setId(2);

        gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());
        gameParticipation1.setPoints(301);

        // assert
        assertEquals(gameParticipation1.getPoints(), 301);
    }
}