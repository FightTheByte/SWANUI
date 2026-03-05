package org.example.cob.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePool {

    String database;

    public DatabasePool(String databaseName){
        this.database = "jdbc:sqlite:" + databaseName;

        try(
            Connection conn = DriverManager.getConnection(database);
            Statement stat = conn.createStatement();
        ) {
            stat.execute("CREATE TABLE IF NOT EXISTS parameters (id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(250) NOT NULL, params TEXT NOT NULL);");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection connection() {
        try{
            return DriverManager.getConnection(database);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
