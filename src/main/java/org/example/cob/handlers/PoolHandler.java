package org.example.cob.handlers;

import org.example.cob.database.DatabasePool;
import org.example.cob.eventbus.SwanEventBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class PoolHandler {
    DatabasePool db;

    public PoolHandler(String database){
        db = new DatabasePool(database);
    }

    public void insert(String name, String parameters) {
        if(name.length() < 251) {
            String query = "INSERT INTO parameters (name, params) VALUES (?,?);";
            try (
                    Connection conn = db.connection();
                    PreparedStatement insertIntoDb = conn.prepareStatement(query);
            ) {
                insertIntoDb.setString(1, name);
                insertIntoDb.setString(2, parameters);
                insertIntoDb.execute();
            } catch (SQLException e) {

            }
        }
    }

    public void select() {
    }

    public void returnSelectResult(){

    }
}
