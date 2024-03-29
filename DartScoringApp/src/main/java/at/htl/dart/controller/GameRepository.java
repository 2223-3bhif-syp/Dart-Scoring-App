package at.htl.dart.controller;

import at.htl.dart.entity.Game;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepository implements RequiredMethods<Game>{
    private final DataSource dataSource = Database.getDataSource();
    @Override
    public void save(Game entity) {
        if(entity.getId() == null){
            insert(entity);
        }else {
            update(entity);
        }
    }

    @Override
    public void update(Game entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("UPDATE DSA_Game SET CURRENT_PLAYER=?, GAME_TYPE_ID=? WHERE G_ID=?");

            pStatement.setInt(1, entity.getCurrentPlayer().getId());
            pStatement.setInt(2, entity.getGameType().getId());
            pStatement.setInt(3, entity.getId());

            pStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Game entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO DSA_Game (CURRENT_PLAYER, GAME_TYPE_ID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            pStatement.setInt(1, entity.getCurrentPlayer().getId());
            pStatement.setInt(2, entity.getGameType().getId());

            pStatement.executeUpdate();

            try(ResultSet keys = pStatement.getGeneratedKeys()){
                if(keys.next()){
                    entity.setId(keys.getInt(1));
                }
            }catch (Exception e){

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Game entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM DSA_GAME WHERE G_ID=?");

            pStatement.setInt(1, entity.getId());

            pStatement.executeUpdate();

            entity.setId(null);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Game> findAll() {
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GAME");
            ResultSet resultSet = pStatement.executeQuery();

            List<Game> gameList = new ArrayList<>();

            while(resultSet.next()){
                Game game = new Game();

                GameTypeRepository gameTypeRepository = new GameTypeRepository();
                PlayerRepository playerRepository = new PlayerRepository();

                game.setId(resultSet.getInt("G_ID"));
                game.setGameType(gameTypeRepository.findById(resultSet.getInt("GAME_TYPE_ID")));
                game.setCurrentPlayer(playerRepository.findById(resultSet.getInt("CURRENT_PLAYER")));

                gameList.add(game);
            }

            return gameList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Game findById(int... ids) {

        int id = ids[0];

        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GAME WHERE G_ID=?");

            pStatement.setInt(1, (int)id);

            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()){
                Game game = new Game();

                GameTypeRepository gameTypeRepository = new GameTypeRepository();
                PlayerRepository playerRepository = new PlayerRepository();

                game.setId(resultSet.getInt("G_ID"));
                game.setGameType(gameTypeRepository.findById(resultSet.getInt("GAME_TYPE_ID")));
                game.setCurrentPlayer(playerRepository.findById(resultSet.getInt("CURRENT_PLAYER")));

                return game;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
