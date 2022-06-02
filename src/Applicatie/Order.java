package Applicatie;

import GUI.Frame;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Order{
    private static Bpp bpp;
    private ArrayList<Artikel> artikelen;
    private static int klantID = 0;

    private static String klantNaam = null;
    private static String klantAdres = null;
    private static String klantPostcode = null;
    private static String stadNaam = null;
    private static String orderlijst;

    public static int orderNr;
    public static int nieuweOrderNr;
    public static int orderlines;
    public static int customerId;

    private static int artikelNrRood = 73;
    private static int artikelNrGeel = 71;
    private static int artikelNrBlauw = 60;

    public static int aantalGeel;
    public static int aantalBlauw;
    public static int aantalRood;

    public Order(){}


    public Order(int[] rgbAantallen) {
        aantalRood = rgbAantallen[0];
        aantalGeel = rgbAantallen[1];
        aantalBlauw = rgbAantallen[2];
        this.artikelen = new ArrayList<>();
        voegArtikelToe(artikelNrRood, aantalRood); // Rode artikelen
        voegArtikelToe(artikelNrGeel, aantalGeel); // Gele artikelen
        voegArtikelToe(artikelNrBlauw, aantalBlauw); // Blauwe artikelen
        customerId = new Random().nextInt(900, 1000);
        berekenBpp();
        System.out.println(bpp);
    }

    public void getOrder(){
            artikelen = new ArrayList<>();
            for (int i = 0; i < aantalGeel; i++) {
                artikelen.add(Database.selecteerArtikel(artikelNrGeel));
            }
            for (int i = 0; i < aantalBlauw; i++) {
                artikelen.add(Database.selecteerArtikel(artikelNrBlauw));
            }
            for (int i = 0; i < aantalRood; i++) {
                artikelen.add(Database.selecteerArtikel(artikelNrRood));
            }
            try {
                bpp = new Bpp(artikelen, 12);
            } catch (Exception e) {
                e.printStackTrace();
            }
        orderlijst = bpp.toString();
    }


    public static void maakPakbon(){
        try {                               //haalt de gegevens van de klant op uit de database zodat deze kunnen worden gebruikt in de pakbon
            Database.startConnection();
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement("SELECT CustomerID, CustomerName, DeliveryAddressLine1, DeliveryPostalCode, CityName FROM nerdygadgets.customers AS cu \n" +
                    "LEFT JOIN nerdygadgets.cities AS ci ON cu.PostalCityID = ci.CityID \n" +
                    "WHERE CustomerID = ?");
            preparedStatement.setInt(1, customerId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                klantID = result.getInt("CustomerID");
                klantNaam = result.getString("CustomerName");
                klantAdres = result.getString("DeliveryAddressLine1");
                klantPostcode = result.getString("DeliveryPostalCode");
                stadNaam = result.getString("CityName");
            }
            result.close();

            // AANMAKEN PAKBON ORDER

            XWPFDocument document = new XWPFDocument();

            XWPFParagraph p1 = document.createParagraph();      //p1 is klantgegevens
            XWPFRun run1 = p1.createRun();
            run1.setBold(false);
            run1.setText("KlantNr: " + klantID);
            run1.addBreak();
            run1.setText(klantNaam);
            run1.addBreak();
            run1.setText(klantAdres);
            run1.addBreak();
            run1.setText(klantPostcode + " " + stadNaam);
            run1.addBreak();
            run1.addBreak();

            XWPFParagraph p2 = document.createParagraph();
            XWPFRun run2 = p2.createRun();
            run2.setBold(true);
            run2.setText("Uw bestelling: ");

            XWPFParagraph bestelling = document.createParagraph();     //Hier komt de daadwerkelijke bestelling
            XWPFRun run3 = bestelling.createRun();

            String[] parts = bpp.toString().split("\n");
            for (String part : parts
            ) {
                run3.setText(part);
                run3.addBreak();
            }
            orderNr = Database.selecteerLaasteOrderID();
            document.write(new FileOutputStream("Order" + orderNr + ".docx"));   //pad waar de pakbon wordt opgeslagen
        }catch (IOException ie){}
        catch (SQLException se){}
    }

    public static void uploadVoorraadNaarDatabase(){        //roept update voorraad functie aan met jusite gegevens
        Database.setVoorraad(artikelNrRood, Frame.aantalRood);
        Database.setVoorraad(artikelNrGeel, Frame.aantalGeel);
        Database.setVoorraad(artikelNrBlauw, Frame.aantalBlauw);

    }

    public static void uploadOrderNaarDatabase(){          //upload de order naar de database
        try{
            Database.startConnection();
            PreparedStatement preparedStatement1 = Database.getConnection().prepareStatement("INSERT INTO orders (CustomerID) VALUES (?)");
            preparedStatement1.setInt(1, customerId);
            preparedStatement1.executeUpdate();
            nieuweOrderNr = Database.selecteerLaasteOrderID();
            System.out.println("NIEUWE OPRDER" + nieuweOrderNr);
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement("INSERT INTO orderlines (Quantity, OrderID, StockItemID) VALUES (?, ?, ?),(?,?,?),(?,?,?);");
            preparedStatement.setInt(1, Frame.aantalRood);
            preparedStatement.setInt(2, nieuweOrderNr);
            preparedStatement.setInt(3, artikelNrRood);
            preparedStatement.setInt(4, Frame.aantalGeel);
            preparedStatement.setInt(5, nieuweOrderNr);
            preparedStatement.setInt(6,artikelNrGeel);
            preparedStatement.setInt(7, Frame.aantalBlauw);
            preparedStatement.setInt(8, nieuweOrderNr);
            preparedStatement.setInt(9,artikelNrBlauw);
            preparedStatement.executeUpdate();
            System.out.println("Geupdate");
            }

        catch (SQLException e) {
            throw
                    new RuntimeException(e);
        }
    }

    public void voegArtikelToe(int artikelNum) {
            this.artikelen.add(Database.selecteerArtikel(artikelNum));
    }

    public void voegArtikelToe(int artikelNum, int aantal) {
        for (int i = 0; i < aantal; i++) {
            voegArtikelToe(artikelNum);
        }
    }

    public void berekenBpp() {
        try {
            bpp = new Bpp(artikelen, 12);
        } catch (Exception e) {
            Frame.showError(e.getMessage());
        }
    }

    public static Bpp getBpp() {
        return bpp;
    }
}


