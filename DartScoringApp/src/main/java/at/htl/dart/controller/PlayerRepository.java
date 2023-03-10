package at.htl.dart.controller;

import at.htl.dart.entity.Player;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository implements RequiredMethods<Player> {
    private final DataSource dataSource = Database.getDataSource();

    @Override
    public void save(Player entity) {
        if(entity.getPlayerId() == null){
            insert(entity);
        }else {
            update(entity);
        }
    }

    @Override
    public void update(Player entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("UPDATE DSA_Player SET NAME=? WHERE P_ID=?");

            pStatement.setString(1, entity.getName());
            pStatement.setInt(2, entity.getPlayerId());

            pStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Player entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO DSA_Player NAME VALUES ?");

            pStatement.setString(1, entity.getName());

            pStatement.executeUpdate();

            try(ResultSet keys = pStatement.getGeneratedKeys()){
                if(keys.next()){
                    entity.setPlayerId(keys.getInt(1));
                }
            }catch (Exception e){

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Player entity) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM DSA_Player WHERE P_ID=?");

            pStatement.setInt(1, entity.getPlayerId());

            pStatement.executeUpdate();

            entity.setPlayerId(null);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> findAll() {
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_Player");
            ResultSet resultSet = pStatement.executeQuery();

            List<Player> playerList = new ArrayList<>();

            while(resultSet.next()){
                Player player = new Player();

                player.setPlayerId(resultSet.getInt("P_ID"));
                player.setName(resultSet.getString("NAME"));

                playerList.add(player);
            }

            return playerList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Player findById(long... ids) {

        long id = ids[0];

        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM DSA_Player WHERE P_ID=?");

            pStatement.setInt(1, (int)id);

            ResultSet resultSet = pStatement.executeQuery();

            if(resultSet.next()){
                Player player = new Player();

                player.setPlayerId(resultSet.getInt("P_ID"));
                player.setName(resultSet.getString("NAME"));

                return player;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
