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

    }

    @Override
    public void insert(Player entity) {

    }

    @Override
    public void delete(Player entity) {

    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public Player findById(long id) {
        return null;
    }
}
