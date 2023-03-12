package at.htl.dart.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getPlayerId() {
        Player p1 = new Player("Ben");

        assertEquals(p1.getPlayerId(), null);
    }

    @Test
    void setPlayerId() {
        Player p1 = new Player("Ben");

        p1.setPlayerId(1);

        assertEquals(p1.getPlayerId(), 1);
    }

    @Test
    void getName() {
        Player p1 = new Player("Ben");

        assertEquals(p1.getName(), "Ben");
    }

    @Test
    void setName() {
        Player p1 = new Player("Ben");

        p1.setName("Yusuf");

        assertEquals(p1.getName(), "Yusuf");
    }
}