package at.htl.dart.controller;

import at.htl.dart.entity.Game;
import at.htl.dart.entity.GameType;
import at.htl.dart.entity.Player;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameTypeRepository implements RequiredMethods<GameType> {
    private final DataSource dataSource = Database.getDataSource();
    @Override
    public void save(GameType entity) {
        if(entity.getId() == null){
            insert(entity);
        }else {
            update(entity);
        }
    }

    @Override
    public void update(GameType entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("UPDATE DSA_GameType SET POINTS=? WHERE GT_ID=?");

            pStatement.setInt(1, entity.getPoints());
            pStatement.setInt(2, entity.getId());

            pStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insert(GameType entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO DSA_GAMETYPE POINTS VALUES ?");

            pStatement.setInt(1, entity.getPoints());

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
    public void delete(GameType entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM DSA_GameType WHERE GT_ID=?");

            pStatement.setInt(1, entity.getId());

            pStatement.executeUpdate();

            entity.setId(null);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<GameType> findAll() {
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GameType");
            ResultSet resultSet = pStatement.executeQuery();

            List<GameType> gameTypeList = new ArrayList<>();

            while(resultSet.next()){
                GameType gameType = new GameType();

                gameType.setId(resultSet.getInt("GT_ID"));
                gameType.setPoints(resultSet.getInt("POINTS"));

                gameTypeList.add(gameType);
            }

            return gameTypeList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public GameType findById(long... ids) {

        long id = ids[0];

        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_GameType WHERE GT_ID=?");

            pStatement.setInt(1, (int)id);

            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()){
                GameType gameType = new GameType();

                gameType.setId(resultSet.getInt("GT_ID"));
                gameType.setPoints(resultSet.getInt("POINTS"));

                return gameType;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
