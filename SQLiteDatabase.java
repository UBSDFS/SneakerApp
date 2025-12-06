/*Name: Ulysses Burden III
*Assignment: SQLite Database Connection
*Date: December 3, 2025
* SQLite class to create a connection to a SQLite database.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {

    public static Connection connect(String database) {
        String url = "jdbc:sqlite:" + database;
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }
}
