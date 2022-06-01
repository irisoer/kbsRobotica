package Applicatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private static Connection connection;       //slaat de connectie met de database op
    static Random random = new Random();        //Wordt gebruikt voor het random kiezen van een CustomerID, voor als er een nieuwe order wordt ingevoerd.
    static int laagsteCustomerID = 900;         //Laagste en hoogste customerID zijn de customer IDS waartussen de random functie mag kiezen
    static int hoogsteCustomerID = 1000;

    public static void startConnection() throws SQLException {      //maak connectie met de database
            String password = "";                                   //todo: moet dit nog verborgen worden ofzo?
            String username = "root";
            String url = "jdbc:mysql://localhost:3306/nerdygadgets";
            connection = DriverManager.getConnection(url, username, password);
    }

    public static Artikel selecteerArtikel(int stockItemID) {       //Selecteer een artikel iut de database aan de hand van een StockItemID
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

    public static Artikel selecteerArtikel(String kleur) {         //Selecteer een artikel uit de database aan de hand van een kleur
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

    public static Integer[] selecteerOrderNums() {          //Selecteer ALLE orders (de nummers daarvan) uit de database en sorteer deze.
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
            orderNrs = ordernrsAL.toArray(orderNrs);        //Sla ordernummers op in een array. Dit wordt gebruikt in een HMI scherm
            return orderNrs;
        } catch (SQLException e) {
        }
        return null;
    }

    public static int[] selecteerOrder(int orderNum) {      //Selecteer orders uit de database aan de hand van een order nummer (connected aan het hierboven genoemde HMI scherm)
        try {
            startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT OrderID, StockItemID, Quantity FROM orderlines WHERE OrderID = ?");
            preparedStatement.setInt(1, orderNum);
            ResultSet result = preparedStatement.executeQuery();
            int[] artikelenOrder = new int[3];
            int customerID = 0;
            while (result.next()) {                         //De resultaten worden opgeslagen in een array zodat deze gebruikt kan worden in eerder genoemd scherm
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

    public static int getCustomerID(){                  //Selecteer de customerID van geselecteerd order. Dit kan worden gebruikt voor de opmaak van de pakbon.
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




    public static Connection getConnection() {  //todo: waar gebruiken we dit echt?
        return connection;
    }

    private static void endConnection(){       //todo: Waar gebruiken we dit echt?
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }
}
