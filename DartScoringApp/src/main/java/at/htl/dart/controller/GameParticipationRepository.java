package at.htl.dart.controller;

import at.htl.dart.entity.GameParticipation;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameParticipationRepository implements RequiredMethods<GameParticipation>{
    private final DataSource dataSource = Database.getDataSource();
    @Override
    public void save(GameParticipation entity) {
        if(this.findById(entity.getPlayer().getId(), entity.getGame().getId()) == null){
            insert(entity);
        }else {
            update(entity);
        }
    }

    @Override
    public void update(GameParticipation entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("UPDATE DSA_GAMEPARTICIPATION SET POINTS=? WHERE P_ID=? AND G_ID=?");

            pStatement.setInt(1, entity.getPoints());
            pStatement.setInt(2, entity.getPlayer().getId());
            pStatement.setInt(3, entity.getGame().getId());

            pStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insert(GameParticipation entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO DSA_GAMEPARTICIPATION (P_ID, G_ID, POINTS) VALUES (?, ?, ?)");

            int playerId = entity.getPlayer().getId();
            int gameId = entity.getGame().getId();

            if(this.findById(playerId, gameId) == null){
                pStatement.setInt(1, playerId);
                pStatement.setInt(2, gameId);
                pStatement.setInt(3, entity.getPoints());

                pStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(GameParticipation entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM DSA_GAMEPARTICIPATION WHERE P_ID=? AND G_ID=?");

            pStatement.setInt(1, entity.getPlayer().getId());
            pStatement.setInt(2, entity.getGame().getId());

            pStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<GameParticipation> findAll() {
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GAMEPARTICIPATION");
            ResultSet resultSet = pStatement.executeQuery();

            List<GameParticipation> gameParticipationList = new ArrayList<>();

            while(resultSet.next()){
                GameParticipation gameParticipation = new GameParticipation();

                PlayerRepository playerRepository = new PlayerRepository();
                GameRepository gameRepository = new GameRepository();

                gameParticipation.setPlayer(playerRepository.findById(resultSet.getInt("P_ID")));
                gameParticipation.setGame(gameRepository.findById(resultSet.getInt("G_ID")));
                gameParticipation.setPoints(resultSet.getInt("POINTS"));

                gameParticipationList.add(gameParticipation);
            }

            return gameParticipationList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public GameParticipation findById(int... ids) {

        if(ids.length < 2){
            return null;
        }

        int playerId = ids[0];
        int gameId = ids[1];

        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GAMEPARTICIPATION WHERE P_ID=? AND G_ID=?");

            pStatement.setInt(1, playerId);
            pStatement.setInt(2, gameId);

            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()){
                GameParticipation gameParticipation = new GameParticipation();

                PlayerRepository playerRepository = new PlayerRepository();
                GameRepository gameRepository = new GameRepository();

                gameParticipation.setPlayer(playerRepository.findById(resultSet.getInt("P_ID")));
                gameParticipation.setGame(gameRepository.findById(resultSet.getInt("G_ID")));
                gameParticipation.setPoints(resultSet.getInt("POINTS"));

                return gameParticipation;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
