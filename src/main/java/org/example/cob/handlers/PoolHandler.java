package org.example.cob.handlers;

import org.example.cob.customevents.CustomEventUtitlity;
import org.example.cob.customevents.ErrorEvent;
import org.example.cob.customevents.ReturnSelectEvent;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.example.cob.database.DatabasePool;
import org.example.cob.eventbus.SwanEventBus;
import org.example.cob.util.Parameter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PoolHandler {
    DatabasePool db;
    private List<Parameter> parameters;

    public PoolHandler(String database){
        db = new DatabasePool(database);
    }

    public void insert(String name, String parameters) {
        if(name.length() < 61) {
            String query = "INSERT INTO parameters (name, params) VALUES (?,?);";
            try (
                    Connection conn = db.connection();
                    PreparedStatement insertIntoDb = conn.prepareStatement(query);
            ) {
                insertIntoDb.setString(1, name);
                insertIntoDb.setString(2, parameters);
                insertIntoDb.execute();
            } catch (SQLException e) {
                     emitError(e.toString()) ;
            }
        }
        else {

            emitError("Name too long");
        }
    }

    public void select() {
        parameters = new ArrayList<>();
        try(
            Connection conn = db.connection();
            Statement stat = conn.createStatement();
        ) {
            ResultSet results = stat.executeQuery("SELECT * FROM parameters;");
            do{
                parameters.add(new Parameter(results.getString("name"), results.getString("params")));
                results.next();
            }while(results.next());

        }
        catch(SQLException e){
            emitError(e.toString());
            return;
        }
        returnSelectResult(parameters);
    }

    public void returnSelectResult(List<Parameter> results){
        SwanEventBus.returnEventBus().post(new ReturnSelectEvent(results));
    }

    private void emitError(String error){
        SwanEventBus.returnEventBus().post(new ErrorEvent(error));
    }
}
