import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    static Connection con;

    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to your database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_project", // your DB name
                    "root", // MySQL username
                    ""      // MySQL password (leave empty if none)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
