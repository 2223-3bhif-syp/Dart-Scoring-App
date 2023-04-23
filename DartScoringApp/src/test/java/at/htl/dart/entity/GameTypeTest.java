package at.htl.dart.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTypeTest {

    @Test
    void getId() {
        // arrange
        GameType g1 = new GameType(501);

        // assert
        assertEquals(g1.getId(), null);
    }

    @Test
    void setId() {
        // arrange
        GameType g1 = new GameType(501);

        // act
        g1.setId(1);

        // assert
        assertEquals(g1.getId(), 1);
    }

    @Test
    void getPoints() {
        // arrange
        GameType g1 = new GameType(501);

        // assert
        assertEquals(g1.getPoints(), 501);
    }

    @Test
    void setPoints() {
        // arrange
        GameType g1 = new GameType(501);

        // act
        g1.setPoints(301);

        // assert
        assertEquals(g1.getPoints(), 301);
    }
}