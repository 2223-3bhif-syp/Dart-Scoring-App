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
        Player p1 = new Player("Ben");

        p1.setId(1);

        assertEquals(p1.getId(), 1);
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