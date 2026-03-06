package org.example.cob.handlers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.ErrorEvent;
import org.example.cob.customevents.ReturnSelectEvent;
import org.example.cob.database.DatabasePool;
import org.example.cob.util.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class PoolHandlerTest {
    DatabasePool db;
    PoolHandler poolHandler;
    EventBus eventBus;
    String name;
    String params;
    String error;

    @BeforeEach
    public void instantiate(){
        eventBus = new EventBus();
        eventBus.register(new Listener());
        db = new DatabasePool("test.db");
        poolHandler = new PoolHandler("test.db");
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            stat.execute("DELETE FROM parameters WHERE 1=1;");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    class Listener{
        @Subscribe
        void handleSelectEvent(ReturnSelectEvent returnSelectEvent){
            name = returnSelectEvent.getSelectResult().getLast().getName();
            params = returnSelectEvent.getSelectResult().getLast().getParameters();
        }
    }

    class ErrorListener{
        @Subscribe
        void handleErrorEvent(ErrorEvent errorEvent){
            error = errorEvent.getError();
        }
    }

    @Test
    void insertToDatabase(){


        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            poolHandler.insert("test-name","test-params");
            ResultSet results = stat.executeQuery("SELECT name, params FROM parameters;");
            Parameter parameter = new Parameter(results.getString("name"), results.getString("params"));
            assertEquals("test-name", parameter.getName(), "name not inserted into database");
            assertEquals("test-params", parameter.getParameters(), "params not inserted into database");
        }
        catch(SQLException e){
            fail(e);
        }
    }

    @Test
    void selectsFromDatabase(){
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            stat.executeQuery("INSERT INTO parameters (name, params) VALUES ('test-name', 'test-params');");
            poolHandler.select();
            assertEquals("test-name", name, "name not selected from database");
            assertEquals("test-params", params, "params not selected from database");

        }
        catch(SQLException e){
            fail(e);
        }
    }

}
