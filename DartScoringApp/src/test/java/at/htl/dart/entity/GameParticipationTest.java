package at.htl.dart.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameParticipationTest {
    Player p1;
    Game g1;

    @BeforeEach
    void setUp(){
        p1 = new Player("Ben");
        p1.setPlayerId(1);

        GameType gameType1 = new GameType(501);
        gameType1.setId(2);

        g1 = new Game(p1, gameType1);
        g1.setID(3);
    }

    @Test
    void getPlayer() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        assertEquals(gameParticipation1.getPlayer(), p1);
    }

    @Test
    void setPlayer() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        Player p2 = new Player("Yusuf");
        p2.setPlayerId(4);

        gameParticipation1.setPlayer(p2);

        assertEquals(gameParticipation1.getPlayer(), p2);
    }

    @Test
    void getGame() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        assertEquals(gameParticipation1.getGame(), g1);
    }

    @Test
    void setGame() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        Game g2 = new Game();
        g2.setID(4);

        gameParticipation1.setGame(g2);

        assertEquals(gameParticipation1.getGame(), g2);
    }

    @Test
    void getPoints() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        assertEquals(gameParticipation1.getPoints(), g1.getGameType().getPoints());
    }

    @Test
    void setPoints() {
        GameParticipation gameParticipation1 = new GameParticipation(p1, g1, g1.getGameType().getPoints());

        gameParticipation1.setPoints(301);

        assertEquals(gameParticipation1.getPoints(), 301);
    }
}