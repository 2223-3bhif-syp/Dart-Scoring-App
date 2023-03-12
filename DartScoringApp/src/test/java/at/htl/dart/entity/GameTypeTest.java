package at.htl.dart.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTypeTest {

    @Test
    void getId() {
        GameType g1 = new GameType(501);

        assertEquals(g1.getId(), null);
    }

    @Test
    void setId() {
        GameType g1 = new GameType(501);

        g1.setId(1);

        assertEquals(g1.getId(), 1);
    }

    @Test
    void getPoints() {
        GameType g1 = new GameType(501);

        assertEquals(g1.getPoints(), 501);
    }

    @Test
    void setPoints() {
        GameType g1 = new GameType(501);

        g1.setPoints(301);

        assertEquals(g1.getPoints(), 301);
    }
}