import java.sql.*;


public class Database {
    private Connection connection;



//                Statement stmt = connection.createStatement();
//                ResultSet resultSet = stmt.executeQuery("SELECT * FROM colors");
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("ColorID");
//                    String colorName = resultSet.getString("ColorName");
//
//                    System.out.println(id + " -- " + colorName);
//
//                }
//                stmt.close();

    private void startConnection() throws SQLException {
        String password = "";
        String username = "root";
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public void runQuery(String query) throws SQLException {
        startConnection();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("ColorID");
            String colorName = resultSet.getString("ColorName");

            System.out.println(id + " -- " + colorName);

        }
        stmt.close();
        endConnection();
    }

    public Artikel getProduct(int stockItemID) throws SQLException {
        startConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ColorName, TypicalWeightPerUnit, StockItemName, StockItemID FROM nerdygadgets.colors\n" +
                                                                              "LEFT JOIN stockitems s on colors.ColorID = s.ColorID\n" +
                                                                              "WHERE StockItemID = ?");
        preparedStatement.setInt(1, stockItemID);
        ResultSet result = preparedStatement.executeQuery();
        Artikel artikel = null;
        while (result.next()) {
            artikel = new Artikel(result);
        }
        result.close();
        endConnection();
        return artikel;
    }

    private void endConnection() throws SQLException {
        connection.close();
    }
}
