package Applicatie;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection connection;

    public static void startConnection() throws SQLException {
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

    public static Artikel selecteerArtikel(int stockItemID) throws SQLException {
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

    public static Artikel selecteerArtikel(String kleur) throws SQLException {
        startConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ColorName, TypicalWeightPerUnit, StockItemName, StockItemID FROM nerdygadgets.colors\n" +
                "LEFT JOIN stockitems s on colors.ColorID = s.ColorID\n" +
                "WHERE ColorName = ? && StockItemID IN (60, 71, 73)");

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

    // todo: een functie die alle ordernums ophaalt voor startscherm moet Integer[] zijn omdat er een null in moet komen voor leeg vakje
    public static Integer[] selecteerOrderNums() {
        try{
            startConnection();
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT OrderID FROM orders ORDER BY OrderID");
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Integer> ordernrsAL = new ArrayList<>();

            while(result.next()){
                ordernrsAL.add(result.getInt("OrderID"));
            }
            Integer[] orderNrs = new Integer[ordernrsAL.size()];
            orderNrs = ordernrsAL.toArray(orderNrs);
            return orderNrs;
        }
        catch (SQLException e){

        }
        return null;
    }

    // todo: functie die in volgorde rgb de aantallen ophaalt vanaf een orderNum voor het startscherm
    public static int[] selecteerOrder(int orderNum) {
        try {
            startConnection();
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT StockItemID, Quantity FROM orderlines WHERE OrderID = ?");
            preparedStatement.setInt(1, orderNum);
            ResultSet result = preparedStatement.executeQuery();
            int[] artikelenOrder = new int[3];
            while (result.next()) {
                switch (result.getInt("StockItemID")) {
                    case 60:
                        artikelenOrder[2] = result.getInt("Quantity");
                    case 71:
                        artikelenOrder[1] = result.getInt("Quantity");
                    case 73:
                        artikelenOrder[0] = result.getInt("Quantity");
                }
            }
            return artikelenOrder;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * <h3>Voorraad in de database</h3>
     *
     * Dit stuk code haalt de voorraad op en werkt deze bij in de database
     *
     * @author Iris Oerlemans
     */
    public static Integer getVoorraad(int stockItemID) throws SQLException {
        startConnection();
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT QuantityOnHand FROM nerdygadgets.stockitemholdings I \n" +
                "WHERE StockItemID = ?");
        preparedStatement.setInt(1, stockItemID);
        ResultSet result = preparedStatement.executeQuery();
        int quantity = 0;
        while(result.next()){
            quantity = result.getInt("QuantityOnHand");
        }
        result.close();
        return quantity;
    }

    public static int updateVoorraad(int StockitemID, int hoeveelheidBesteld) throws SQLException {
        startConnection();
        PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE nerdygadgets.stockitemholdings \n" +
                "SET QuantityOnHand = (QuantityOnHand - ?) \n"+
                "WHERE StockitemID = ? ");
        preparedStatement.setInt(2, StockitemID);
        preparedStatement.setInt(1, hoeveelheidBesteld);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    /**
     * <h3>Einde voorraad in de database</h3>
     */


    public static Connection getConnection() {
        return connection;
    }

    private static void endConnection() throws SQLException {
        connection.close();
    }
}
