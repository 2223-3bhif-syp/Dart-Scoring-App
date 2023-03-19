package at.htl.dart.controller;

import at.htl.dart.database.SqlRunner;
import at.htl.dart.entity.GameType;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameTypeRepositoryTest {
    private static final String tableName = "DSA_GAMETYPE";

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

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType();
        GameType g2 = new GameType(501);

        g1.setPoints(501);

        gameTypeRepository.save(g1);
        gameTypeRepository.save(g2);

        g2.setPoints(301);

        gameTypeRepository.save(g2);

        assertThat(g1.getId()).isEqualTo(1);
        assertThat(g2.getId()).isEqualTo(2);

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(501)
                .value().isEqualTo(301);

        Assertions.assertThat(table).column("GT_ID")
                .value().isEqualTo(g1.getId())
                .value().isEqualTo(g2.getId());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType();

        g1.setPoints(501);
        gameTypeRepository.save(g1);

        g1.setPoints(301);
        gameTypeRepository.update(g1);

        assertThat(g1.getId()).isEqualTo(1);

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(301);

        Assertions.assertThat(table).column("GT_ID")
                .value().isEqualTo(g1.getId());
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType(501);

        gameTypeRepository.insert(g1);

        assertThat(g1.getId()).isEqualTo(1);

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(501);

        Assertions.assertThat(table).column("GT_ID")
                .value().isEqualTo(g1.getId());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType(501);

        gameTypeRepository.save(g1);
        gameTypeRepository.delete(g1);

        Assertions.assertThat(table).column("POINTS").hasNumberOfRows(0);

        Assertions.assertThat(table).column("GT_ID").hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType(501);
        GameType g2 = new GameType(301);
        GameType g3 = new GameType(101);

        gameTypeRepository.save(g1);
        gameTypeRepository.save(g2);
        gameTypeRepository.save(g3);

        List<GameType> gameTypeList = new ArrayList<>();

        gameTypeList.add(g1);
        gameTypeList.add(g2);
        gameTypeList.add(g3);

        List<GameType> foundList = gameTypeRepository.findAll();

        assertThat(g1.getId()).isEqualTo(1);
        assertThat(g2.getId()).isEqualTo(2);
        assertThat(g3.getId()).isEqualTo(3);

        assertThat(gameTypeList.size()).isEqualTo(foundList.size());

        for(int i = 0; i < gameTypeList.size(); i++){
            assertThat(gameTypeList.get(i).getId())
                    .isEqualTo(foundList.get(i).getId());

            assertThat(gameTypeList.get(i).getPoints())
                    .isEqualTo(foundList.get(i).getPoints());
        }
    }

    @Test
    void findById() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameType g1 = new GameType(501);

        gameTypeRepository.save(g1);

        assertThat(g1.getId()).isEqualTo(1);

        assertThat(g1.getId())
                .isEqualTo(gameTypeRepository.findById(g1.getId()).getId());
        assertThat(g1.getPoints())
                .isEqualTo(gameTypeRepository.findById(g1.getId()).getPoints());
    }
}