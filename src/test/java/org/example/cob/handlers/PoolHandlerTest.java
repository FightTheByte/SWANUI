package org.example.cob.handlers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.*;
import org.example.cob.database.DatabasePool;
import org.example.cob.eventbus.SwanEventBus;
import org.example.cob.util.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class PoolHandlerTest {
    DatabasePool db;
    PoolHandler poolHandler;
    String name;
    String params;
    String error;

    @BeforeEach
    public void instantiate(){

        SwanEventBus.returnEventBus().register(new Listener());
        SwanEventBus.returnEventBus().register(new ErrorListener());
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
            System.out.println(e.toString());
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
    void insertToDatabaseTest(){


        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            poolHandler.insert(new InsertEvent("test-name","test-params"));
            ResultSet results = stat.executeQuery("SELECT name, params FROM parameters;");
            Parameter parameter = new Parameter(results.getString("name"), results.getString("params"));
            assertEquals("test-name", parameter.getName(), "name not inserted into database");
            assertEquals("test-params", parameter.getParameters(), "params not inserted into database");
        }
        catch(SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void insertToDatabaseErrorTest(){
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            poolHandler.insert(new InsertEvent("t-nametest-nametest-namenametest-nametest-nametest-nametnametest-nam","test-params"));
            ResultSet results = stat.executeQuery("SELECT name, params FROM parameters;");

            assertNotNull(error);
        }
        catch(SQLException e){
            fail(e.toString());
        }
    }

    @Test
    void selectsFromDatabaseTest(){
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            stat.execute("INSERT INTO parameters (name, params) VALUES ('test-name', 'test-params');");
            poolHandler.select(new SelectEvent());
            assertEquals("test-name", name, "name not selected from database");
            assertEquals("test-params", params, "params not selected from database");

        }
        catch(SQLException e){
            fail(e.toString());
        }
    }

}
