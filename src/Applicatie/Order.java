package Applicatie;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order extends Database {
    private Database database;
    private static Bpp bpp;
    private ArrayList<Artikel> artikelen;
    private int klantID = 0;
    private String klantNaam = null;
    private String klantAdres = null;
    private String klantPostcode = null;
    private String stadNaam = null;
    private String orderlijst = "hallo";
    private static int orderNr =1;

    private int artikelNrRood = 73;
    private int artikelNrGeel = 71;
    private int artikelNrBlauw = 60;

    public int aantalGeel;
    public int aantalBlauw;
    public int aantalRood;

    public Order(){}

    public ArrayList<Artikel> getArtikelen() {
        return artikelen;
    }

    public Order(int[] rgbAantallen) {
        aantalRood = rgbAantallen[0];
        aantalGeel = rgbAantallen[1];
        aantalBlauw = rgbAantallen[2];
        this.artikelen = new ArrayList<>();
        voegArtikelToe(artikelNrRood, aantalRood); // Rode artikelen
        voegArtikelToe(artikelNrGeel, aantalGeel); // Gele artikelen
        voegArtikelToe(artikelNrBlauw, aantalBlauw); // Blauwe artikelen

        berekenBpp();
        System.out.println(bpp);
    }

    public void getOrder(){
            artikelen = new ArrayList<>();
            for (int i = 0; i < aantalGeel; i++) {
                artikelen.add(selecteerArtikel(artikelNrGeel));
            }
            for (int i = 0; i < aantalBlauw; i++) {
                artikelen.add(selecteerArtikel(artikelNrBlauw));
            }
            for (int i = 0; i < aantalRood; i++) {
                artikelen.add(selecteerArtikel(artikelNrRood));
            }

            bpp = new Bpp(artikelen, 12);
            orderlijst = bpp.toString();
    }

    public void maakPakbon(int customerId){
        try {
            startConnection();
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT CustomerID, CustomerName, DeliveryAddressLine1, DeliveryPostalCode, CityName FROM nerdygadgets.customers AS cu \n" +
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

            XWPFParagraph p1 = document.createParagraph();
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

            XWPFParagraph bestelling = document.createParagraph();
            XWPFRun run3 = bestelling.createRun();

            String[] parts = orderlijst.split("\n");
            for (String part : parts
            ) {
                run3.setText(part);
                run3.addBreak();
            }

            document.write(new FileOutputStream("Applicatie.Order" + orderNr + ".docx"));
            orderNr++;
        }catch (IOException ie){}
        catch (SQLException se){}
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
        bpp = new Bpp(artikelen, 12);
    }

    public static Bpp getBpp() {
        return bpp;
    }
}


