package org.example.cob.handlers;

import org.example.cob.database.DatabasePool;
import org.example.cob.util.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.sql.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


/*public class PoolHandlerTest {
    DatabasePool db;
    PoolHandler poolHandler;

    @BeforeEach
    public void instantiate(){
        db = new DatabasePool("test.db");
        poolHandler = new PoolHandler("test.db");
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            stat.executeQuery("DROP * FROM parameters;");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Test
    void insertToDatabase(){


        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        )
        {
            JsonObject json = Json.createObjectBuilder()
                .add("name", "test-name")
                .add("params", "test-params")
                .build();
            String insertVals = json.toString();
            poolHandler.insert(insertVals);
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

            assertEquals("test-name", parameter.getName(), "name not inserted into database");

        }
        catch(SQLException e){
            fail(e);
        }
    }

}*/
