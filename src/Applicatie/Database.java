package Applicatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private static Connection connection;
    static Random random = new Random();
    static int laagsteCustomerID = 900;
    static int hoogsteCustomerID = 1000;

    public static void startConnection() throws SQLException {
            String password = "";
            String username = "root";
            String url = "jdbc:mysql://localhost:3306/nerdygadgets";
            connection = DriverManager.getConnection(url, username, password);
    }

    public static Artikel selecteerArtikel(int stockItemID) {
        try {
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
        } catch (SQLException e) {
        }
        return null;
    }

    public static Artikel selecteerArtikel(String kleur) {
        try {
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
        } catch (SQLException e) {
        }
        return null;
    }

    public static Integer[] selecteerOrderNums() {
        try {
            startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT OrderID FROM orders ORDER BY OrderID");
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Integer> ordernrsAL = new ArrayList<>();
            ordernrsAL.add(null);
            while (result.next()) {
                ordernrsAL.add(result.getInt("OrderID"));
            }
            Integer[] orderNrs = new Integer[ordernrsAL.size()];
            orderNrs = ordernrsAL.toArray(orderNrs);
            return orderNrs;
        } catch (SQLException e) {
            System.out.println("JAAA ERROR");
            e.printStackTrace();
        }
        return null;
    }

    public static int[] selecteerOrder(int orderNum) {
        try {
            startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT OrderID, StockItemID, Quantity FROM orderlines WHERE OrderID = ?");
            preparedStatement.setInt(1, orderNum);
            ResultSet result = preparedStatement.executeQuery();
            int[] artikelenOrder = new int[3];
            int customerID = 0;
            while (result.next()) {
                switch (result.getInt("StockItemID")) {
                    case 60:    //Blauwe artikel
                        artikelenOrder[2] = result.getInt("Quantity");
                    case 71:    //Gele artikel
                        artikelenOrder[1] = result.getInt("Quantity");
                    case 73:    //Rode artikel
                        artikelenOrder[0] = result.getInt("Quantity");
                }
                Order.orderNr = result.getInt("OrderID");
            }
            return artikelenOrder;
        } catch (SQLException e) {
        }
        return null;
    }

    public static int getCustomerID(){
        int customerID = 0;
        try{
        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT CustomerID FROM orders WHERE OrderID = ?");
        preparedStatement1.setInt(1, Order.orderNr);
        ResultSet result1 = preparedStatement1.executeQuery();
        while(result1.next()){
            customerID = result1.getInt("CustomerID");
        }
        }
        catch (SQLException e){
            customerID = random.nextInt(laagsteCustomerID,hoogsteCustomerID)+laagsteCustomerID;
        }
        return customerID;
    }


    /**
     * <h3>Voorraad in de database</h3>
     * <p>
     * Dit stuk code haalt de voorraad op en werkt deze bij in de database
     *
     * @author Iris Oerlemans
     */

    public static Integer getVoorraad(int stockItemID) {
        try {
            startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT QuantityOnHand FROM nerdygadgets.stockitemholdings I \n" +
                    "WHERE StockItemID = ?");
            preparedStatement.setInt(1, stockItemID);
            ResultSet result = preparedStatement.executeQuery();
            int quantity = 0;
            while (result.next()) {
                quantity = result.getInt("QuantityOnHand");
            }
            result.close();
            return quantity;
        } catch (SQLException e) {
        }
        return 0;
    }

    public static int updateVoorraad(int StockitemID, int hoeveelheidBesteld) {
        try {
            startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE nerdygadgets.stockitemholdings \n" +
                    "SET QuantityOnHand = (QuantityOnHand - ?) \n" +
                    "WHERE StockitemID = ? ");
            preparedStatement.setInt(2, StockitemID);
            preparedStatement.setInt(1, hoeveelheidBesteld);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
        }
        return 0;
    }

    /**
     * <h3>Einde voorraad in de database</h3>
     */


    public static Connection getConnection() {
        return connection;
    }

    private static void endConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }
}
