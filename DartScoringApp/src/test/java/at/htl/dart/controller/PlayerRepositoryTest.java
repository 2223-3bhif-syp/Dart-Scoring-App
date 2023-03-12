package at.htl.dart.controller;

import at.htl.dart.controller.Database;
import at.htl.dart.controller.PlayerRepository;
import at.htl.dart.database.SqlRunner;
import at.htl.dart.entity.Player;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRepositoryTest {
    private static String tableName = "DSA_PLAYER";

    @BeforeEach
    public void setUP(){
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach void tearDown() {
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();

        p1.setName("Ben");

        playerRepository.save(p1);

        List<Player> players = playerRepository.findAll();
        assertEquals(p1.getPlayerId(), 1);


    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}