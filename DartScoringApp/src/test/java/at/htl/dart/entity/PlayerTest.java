package at.htl.dart.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getPlayerId() {
        Player p1 = new Player("Ben");

        assertEquals(p1.getId(), null);
    }

    @Test
    void setPlayerId() {
        // arrange
        Player p1 = new Player("Ben");

        //act
        p1.setId(1);

        //assert
        assertEquals(p1.getId(), 1);
    }

    @Test
    void getName() {
        // arrange
        Player p1 = new Player("Ben");

        // assert
        assertEquals(p1.getName(), "Ben");
    }

    @Test
    void setName() {
        // arrange
        Player p1 = new Player("Ben");

        // act
        p1.setName("Yusuf");

        //assert
        assertEquals(p1.getName(), "Yusuf");
    }
}