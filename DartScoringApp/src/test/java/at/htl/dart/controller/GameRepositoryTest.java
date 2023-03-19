package at.htl.dart.controller;

import at.htl.dart.database.SqlRunner;
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

class GameRepositoryTest {
    private static final String tableName = "DSA_GAME";

    @BeforeEach
    public void setUp() {
        // to make sure every Table is empty and set up right
        SqlRunner.dropTablesAndCreateEmptyTables();
        Player p1 = new Player("Gustaf");
    }

    @AfterEach
    public void tearDown() {
        // to clear the tables again of all the test values
        SqlRunner.dropTablesAndCreateEmptyTables();
    }
    @Test
    void save() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();
        Game game2 = new Game();

        Player currentPlayer1 = new Player("Gustaf");
        Player currentPlayer2 = new Player("Yusuf");

        playerRepository.save(currentPlayer1);
        playerRepository.save(currentPlayer2);

        GameType gameType1 = new GameType(501);
        GameType gameType2 = new GameType(301);

        gameTypeRepository.save(gameType1);
        gameTypeRepository.save(gameType2);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);
        game2.setCurrentPlayer(currentPlayer2);
        game2.setGameType(gameType2);

        gameRepository.save(game1);
        gameRepository.save(game2);

        game2.setGameType(gameType1);

        gameRepository.save(game2);

        assertThat(game1.getID()).isEqualTo(1);
        assertThat(game2.getID()).isEqualTo(2);

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getID())
                .value().isEqualTo(game2.getID());

        Assertions.assertThat(table).column("GAME_TYPE_ID")
                .value().isEqualTo(gameType1.getId())
                .value().isEqualTo(gameType1.getId());

        Assertions.assertThat(table).column("CURRENT_PLAYER")
                .value().isEqualTo(currentPlayer1.getPlayerId())
                .value().isEqualTo(currentPlayer2.getPlayerId());
    }

    @Test
    void update() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();

        Player currentPlayer1 = new Player("Gustaf");
        Player currentPlayer2 = new Player("Yusuf");

        playerRepository.save(currentPlayer1);
        playerRepository.save(currentPlayer2);

        GameType gameType1 = new GameType(501);

        gameTypeRepository.save(gameType1);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);

        gameRepository.save(game1);

        game1.setCurrentPlayer(currentPlayer2);

        gameRepository.update(game1);

        assertThat(game1.getID()).isEqualTo(1);

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getID());

        Assertions.assertThat(table).column("GAME_TYPE_ID")
                .value().isEqualTo(gameType1.getId());

        Assertions.assertThat(table).column("CURRENT_PLAYER")
                .value().isEqualTo(currentPlayer2.getPlayerId());
    }

    @Test
    void insert() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();

        Player currentPlayer1 = new Player("Gustaf");

        playerRepository.save(currentPlayer1);

        GameType gameType1 = new GameType(501);

        gameTypeRepository.save(gameType1);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);

        gameRepository.save(game1);

        assertThat(game1.getID()).isEqualTo(1);

        Assertions.assertThat(table).column("G_ID")
                .value().isEqualTo(game1.getID());

        Assertions.assertThat(table).column("GAME_TYPE_ID")
                .value().isEqualTo(gameType1.getId());

        Assertions.assertThat(table).column("CURRENT_PLAYER")
                .value().isEqualTo(currentPlayer1.getPlayerId());
    }

    @Test
    void delete() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();

        Player currentPlayer1 = new Player("Gustaf");

        playerRepository.save(currentPlayer1);

        GameType gameType1 = new GameType(501);

        gameTypeRepository.save(gameType1);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);

        gameRepository.save(game1);
        gameRepository.delete(game1);

        Assertions.assertThat(table).column("G_ID").hasNumberOfRows(0);
        Assertions.assertThat(table).column("GAME_TYPE_ID").hasNumberOfRows(0);
        Assertions.assertThat(table).column("CURRENT_PLAYER").hasNumberOfRows(0);
    }

    @Test
    void findAll() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();
        Game game2 = new Game();

        Player currentPlayer1 = new Player("Gustaf");
        Player currentPlayer2 = new Player("Yusuf");

        playerRepository.save(currentPlayer1);
        playerRepository.save(currentPlayer2);

        GameType gameType1 = new GameType(501);
        GameType gameType2 = new GameType(301);

        gameTypeRepository.save(gameType1);
        gameTypeRepository.save(gameType2);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);
        game2.setCurrentPlayer(currentPlayer2);
        game2.setGameType(gameType2);

        gameRepository.save(game1);
        gameRepository.save(game2);

        List<Game> gameList = new ArrayList<>();

        gameList.add(game1);
        gameList.add(game2);

        List<Game> foundList = gameRepository.findAll();

        assertThat(game1.getID()).isEqualTo(1);
        assertThat(game2.getID()).isEqualTo(2);

        assertThat(gameList.size()).isEqualTo(foundList.size());

        for(int i = 0; i < gameList.size(); i++){
            assertThat(gameList.get(i).getID())
                    .isEqualTo(foundList.get(i).getID());

            assertThat(gameList.get(i).getCurrentPlayer().getPlayerId())
                    .isEqualTo(foundList.get(i).getCurrentPlayer().getPlayerId());

            assertThat(gameList.get(i).getCurrentPlayer().getName())
                    .isEqualTo(foundList.get(i).getCurrentPlayer().getName());


            assertThat(gameList.get(i).getGameType().getId())
                    .isEqualTo(foundList.get(i).getGameType().getId());

            assertThat(gameList.get(i).getGameType().getPoints())
                    .isEqualTo(foundList.get(i).getGameType().getPoints());
        }
    }

    @Test
    void findById() {
        Table table = new Table(Database.getDataSource(), tableName);

        GameRepository gameRepository = new GameRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        GameTypeRepository gameTypeRepository = new GameTypeRepository();

        Game game1 = new Game();

        Player currentPlayer1 = new Player("Gustaf");

        playerRepository.save(currentPlayer1);

        GameType gameType1 = new GameType(501);

        gameTypeRepository.save(gameType1);

        game1.setCurrentPlayer(currentPlayer1);
        game1.setGameType(gameType1);

        gameRepository.save(game1);

        assertThat(game1.getID()).isEqualTo(1);

        assertThat(game1.getID())
                .isEqualTo(gameRepository.findById(game1.getID()).getID());
        assertThat(game1.getCurrentPlayer().getPlayerId())
                .isEqualTo(gameRepository.findById(game1.getID()).getCurrentPlayer().getPlayerId());
        assertThat(game1.getCurrentPlayer().getName())
                .isEqualTo(gameRepository.findById(game1.getID()).getCurrentPlayer().getName());
        assertThat(game1.getGameType().getId())
                .isEqualTo(gameRepository.findById(game1.getID()).getGameType().getId());
        assertThat(game1.getGameType().getPoints())
                .isEqualTo(gameRepository.findById(game1.getID()).getGameType().getPoints());
    }
}