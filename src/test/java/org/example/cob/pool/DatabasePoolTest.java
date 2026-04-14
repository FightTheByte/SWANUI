package org.example.cob.pool;

import org.example.cob.database.DatabasePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DatabasePoolTest {

    DatabasePool db;

    @BeforeEach
    public void instantiate(){
        db = new DatabasePool(":memory:");
    }

    @Test
    void testDbSelects(){
        try(
                Connection conn = db.connection();
                Statement stat = conn.createStatement();
        ){
            stat.execute("CREATE TABLE IF NOT EXISTS parameters (id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(250) NOT NULL, params TEXT NOT NULL);");
            stat.execute("INSERT INTO parameters (name, params) VALUES ('test', 'test');");
            ResultSet rs = stat.executeQuery("select name from parameters;");
            String result = rs.getString("name");
            assertEquals("test", result);
        } catch (SQLException e) {
            fail(e);
        }
    }
}
