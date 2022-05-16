import java.sql.*;


public class Database {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT CountryID, CountryName FROM countries");
                while (resultSet.next()) {
                    int id = resultSet.getInt("CountryID");
                    String countryName = resultSet.getString("CountryName");

                    System.out.println(id + " -- " + countryName);

                }
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println("fout " + e.getMessage());

        }

    }
}
