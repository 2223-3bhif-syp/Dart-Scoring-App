package at.htl.dart.controller;

import at.htl.dart.database.SqlRunner;
import at.htl.dart.entity.GameParticipation;
import at.htl.dart.entity.GameType;
import at.htl.dart.entity.Player;
import at.htl.dart.entity.Game;

import org.assertj.db.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.assertj.db.type.Table;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

class GameParticipationRepositoryTest {
    private static final String tableName = "DSA_GAMEPARTICIPATION";

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

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();
        GameParticipation gameParticipation2 = new GameParticipation();

        GameType gameType1 = new GameType(501);
        GameType gameType2 = new GameType(301);

        Player player1 = new Player("Gustaf");
        Player player2 = new Player("Dusan");

        Game game1 = new Game();
        Game game2 = new Game();

        gameTypeRepository.save(gameType1);
        gameTypeRepository.save(gameType2);

        playerRepository.save(player1);
        playerRepository.save(player2);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        game2.setGameType(gameType2);
        game2.setCurrentPlayer(player2);

        gameRepository.save(game1);
        gameRepository.save(game2);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipation2.setGame(game2);
        gameParticipation2.setPlayer(player2);
        gameParticipation2.setPoints(gameType2.getPoints());

        gameParticipationRepository.save(gameParticipation1);
        gameParticipationRepository.save(gameParticipation2);

        gameParticipation2.setPoints(280);

        gameParticipationRepository.save(gameParticipation2);

        Assertions.assertThat(table).column("P_ID")
                .value().isEqualTo(player1.getId())
                .value().isEqualTo(player2.getId());

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getId())
                .value().isEqualTo(game2.getId());

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(gameType1.getPoints())
                .value().isEqualTo(280);
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();

        GameType gameType1 = new GameType(501);

        Player player1 = new Player("Gustaf");

        Game game1 = new Game();

        gameTypeRepository.save(gameType1);

        playerRepository.save(player1);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        gameRepository.save(game1);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipationRepository.save(gameParticipation1);

        gameParticipation1.setPoints(470);

        gameParticipationRepository.save(gameParticipation1);

        Assertions.assertThat(table).column("P_ID")
                .value().isEqualTo(player1.getId());

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getId());

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(470);
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();

        GameType gameType1 = new GameType(501);

        Player player1 = new Player("Vuk");

        Game game1 = new Game();

        gameTypeRepository.save(gameType1);

        playerRepository.save(player1);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        gameRepository.save(game1);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipationRepository.save(gameParticipation1);

        Assertions.assertThat(table).column("P_ID")
                .value().isEqualTo(player1.getId());

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getId());

        Assertions.assertThat(table).column("POINTS")
                .value().isEqualTo(gameType1.getPoints());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();

        GameType gameType1 = new GameType(501);

        Player player1 = new Player("Vuk");

        Game game1 = new Game();

        gameTypeRepository.save(gameType1);

        playerRepository.save(player1);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        gameRepository.save(game1);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipationRepository.save(gameParticipation1);

        gameParticipationRepository.delete(gameParticipation1);

        Assertions.assertThat(table).column("P_ID").hasNumberOfRows(0);

        Assertions.assertThat(table).column("G_ID").hasNumberOfRows(0);

        Assertions.assertThat(table).column("POINTS").hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();
        GameParticipation gameParticipation2 = new GameParticipation();

        GameType gameType1 = new GameType(501);
        GameType gameType2 = new GameType(301);

        Player player1 = new Player("Di-Tri-Tetra");
        Player player2 = new Player("Di-Tri-Dimitri");

        Game game1 = new Game();
        Game game2 = new Game();

        gameTypeRepository.save(gameType1);
        gameTypeRepository.save(gameType2);

        playerRepository.save(player1);
        playerRepository.save(player2);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        game2.setGameType(gameType2);
        game2.setCurrentPlayer(player2);

        gameRepository.save(game1);
        gameRepository.save(game2);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipation2.setGame(game2);
        gameParticipation2.setPlayer(player2);
        gameParticipation2.setPoints(gameType2.getPoints());

        gameParticipationRepository.save(gameParticipation1);
        gameParticipationRepository.save(gameParticipation2);

        List<GameParticipation> gameParticipationList = new ArrayList<>();

        gameParticipationList.add(gameParticipation1);
        gameParticipationList.add(gameParticipation2);

        List<GameParticipation> foundList = gameParticipationRepository.findAll();

        assertThat(gameParticipationList.size()).isEqualTo(foundList.size());

        for(int i = 0; i < gameParticipationList.size(); i++){
            assertThat(gameParticipationList.get(i).getPoints())
                    .isEqualTo(foundList.get(i).getPoints());

            assertThat(gameParticipationList.get(i).getPlayer().getId())
                    .isEqualTo(foundList.get(i).getPlayer().getId());

            assertThat(gameParticipationList.get(i).getPlayer().getName())
                    .isEqualTo(foundList.get(i).getPlayer().getName());

            assertThat(gameParticipationList.get(i).getGame().getId())
                    .isEqualTo(foundList.get(i).getGame().getId());
        }
    }

    @Test
    void findById() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameParticipationRepository gameParticipationRepository = new GameParticipationRepository();
        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        GameParticipation gameParticipation1 = new GameParticipation();

        GameType gameType1 = new GameType(501);

        Player player1 = new Player("Di-Tri-Tetra");

        Game game1 = new Game();

        gameTypeRepository.save(gameType1);

        playerRepository.save(player1);

        game1.setGameType(gameType1);
        game1.setCurrentPlayer(player1);

        gameRepository.save(game1);

        gameParticipation1.setGame(game1);
        gameParticipation1.setPlayer(player1);
        gameParticipation1.setPoints(gameType1.getPoints());

        gameParticipationRepository.save(gameParticipation1);

        assertThat(gameParticipation1.getPoints())
                .isEqualTo(gameTypeRepository.findById(gameParticipation1.getPlayer().getId() ,gameParticipation1.getGame().getId()).getPoints());
    }
}