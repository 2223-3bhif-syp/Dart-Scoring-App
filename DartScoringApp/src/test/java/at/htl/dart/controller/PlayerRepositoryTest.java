package at.htl.dart.controller;

import at.htl.dart.database.SqlRunner;
import at.htl.dart.entity.Player;

import static org.assertj.db.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class PlayerRepositoryTest {
    private static final String tableName = "DSA_PLAYER";

    @BeforeEach
    public void setUp() {
        // to make sure every Table is empty and set up right
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @AfterEach
    public void tearDown() {
        // to clear the tables again of all the test values
        SqlRunner.dropTablesAndCreateEmptyTables();
    }

    @Test
    void save() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();
        Player p2 = new Player("Yusuf");

        p1.setName("Ben");

        playerRepository.save(p1);
        playerRepository.save(p2);

        p2.setName("Alex");

        playerRepository.save(p2);

        assertThat(p1.getPlayerId()).isEqualTo(1);
        assertThat(p2.getPlayerId()).isEqualTo(2);

        assertThat(table).column("P_NAME")
                .value().isEqualTo("Ben")
                .value().isEqualTo("Alex");

        assertThat(table).column("P_ID")
                .value().isEqualTo(p1.getPlayerId())
                .value().isEqualTo(p2.getPlayerId());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();

        p1.setName("Ben");

        playerRepository.save(p1);

        p1.setName("Yusuf");

        playerRepository.update(p1);

        assertThat(p1.getPlayerId()).isEqualTo(1);

        assertThat(table).column("P_NAME").value().isEqualTo("Yusuf");
        assertThat(table).column("P_ID").value().isEqualTo(p1.getPlayerId());
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();

        p1.setName("Ben");

        playerRepository.insert(p1);

        assertThat(p1.getPlayerId()).isEqualTo(1);

        assertThat(table).column("P_NAME").value().isEqualTo(p1.getName());
        assertThat(table).column("P_ID").value().isEqualTo(p1.getPlayerId());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();

        p1.setName("Ben");

        playerRepository.save(p1);
        playerRepository.delete(p1);

        assertThat(table).column("P_NAME").hasNumberOfRows(0);
        assertThat(table).column("P_ID").hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        p1.setName("Ben");
        p2.setName("Yusuf");
        p3.setName("Alex");

        List<Player> playerList = new ArrayList<>();

        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);

        playerRepository.save(p1);
        playerRepository.save(p2);
        playerRepository.save(p3);

        List<Player> foundList = playerRepository.findAll();

        assertThat(p1.getPlayerId()).isEqualTo(1);
        assertThat(p2.getPlayerId()).isEqualTo(2);
        assertThat(p3.getPlayerId()).isEqualTo(3);

        assertThat(playerList.size()).isEqualTo(foundList.size());

        for(int i = 0; i < playerList.size(); i++){
            assertThat(playerList.get(i).getPlayerId())
                    .isEqualTo(foundList.get(i).getPlayerId());

            assertThat(playerList.get(i).getName())
                    .isEqualTo(foundList.get(i).getName());
        }
    }

    @Test
    void findById() {
        Table table = new Table(Database.getDataSource(), tableName);

        PlayerRepository playerRepository = new PlayerRepository();

        Player p1 = new Player();

        p1.setName("Ben");

        playerRepository.insert(p1);

        assertThat(p1.getPlayerId()).isEqualTo(playerRepository.findById(p1.getPlayerId()).getPlayerId());
        assertThat(p1.getName()).isEqualTo(playerRepository.findById(p1.getPlayerId()).getName());
    }
}