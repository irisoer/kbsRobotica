import java.sql.*;


public class Database {
    private static Connection connection;



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

    private static void startConnection() throws SQLException {
        String password = "";
        String username = "root";
        String url = "jdbc:mysql://localhost:3306/nerdygadgets";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static void runQuery(String query) throws SQLException {
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

    public static Artikel getArtikelFromID(int stockItemID) throws SQLException {
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

    public static Artikel getArtikelVanKleur(String kleur) throws SQLException {
        startConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ColorName, TypicalWeightPerUnit, StockItemName, StockItemID FROM nerdygadgets.colors\n" +
                "LEFT JOIN stockitems s on colors.ColorID = s.ColorID\n" +
                "WHERE ColorName = ?");

        preparedStatement.setString(1, kleur);
        ResultSet result = preparedStatement.executeQuery();
        Artikel artikel = null;
        while (result.next()) {
            artikel = new Artikel(result);
        }
        result.close();
        endConnection();
        return artikel;
    }

    private static void endConnection() throws SQLException {
        connection.close();
    }
}
