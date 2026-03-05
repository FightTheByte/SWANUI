package org.example.cob.handlers;

import org.example.cob.database.DatabasePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/*public class PoolHandlerTest {
    DatabasePool db;
    PoolHandler poolHandler;

    @BeforeEach
    public void instantiate(){
        db = new DatabasePool("test.db");
        poolHandler = new PoolHandler("test.db");
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

        }
        catch(SQLException e){

        }
    }

    @Test
    void selectsFromDatabase(){

    }

    @Test
    void selectResultEventEmits(){

    }
}*/
