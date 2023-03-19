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
        if(this.findById((long)(entity.getPlayer().getPlayerId()), (long)(entity.getGame().getID())) == null){
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
            pStatement.setInt(2, entity.getPlayer().getPlayerId());
            pStatement.setInt(3, entity.getGame().getID());

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

            int playerId = entity.getPlayer().getPlayerId();
            int gameId = entity.getGame().getID();

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

            pStatement.setInt(1, entity.getPlayer().getPlayerId());
            pStatement.setInt(2, entity.getGame().getID());

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
    public GameParticipation findById(long... ids) {

        if(ids.length < 2){
            return null;
        }

        long playerId = ids[0];
        long gameId = ids[1];

        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GAMEPARTICIPATION WHERE P_ID=? AND G_ID=?");

            pStatement.setInt(1, (int)playerId);
            pStatement.setInt(2, (int)gameId);

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
