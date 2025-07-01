import java.sql.*;

public class books_database_create {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "*rajan12345#";
        String databaseName = "books_db";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            String createDbQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            st.executeUpdate(createDbQuery);
        
            System.out.println("Database '" + databaseName + "' created successfully.");
            con.close();

            con = DriverManager.getConnection(url + databaseName, username, password);
            st = con.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "author VARCHAR(255)," +
                    "genre VARCHAR(100)," +
                    "publisher VARCHAR(255)," +
                    "language VARCHAR(50)," +
                    "price FLOAT)";

            st.executeUpdate(createTableQuery);
            System.out.println("Table 'books' created successfully.");

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();  // better debugging output
        }
    }
}
