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

    private int aantalGeel = 3;
    private int aantalBlauw = 1;
    private int aantalRood = 2;

    public void getOrder() throws SQLException {
        artikelen = new ArrayList<>();
        for (int i = 0; i < aantalGeel; i++) {
            artikelen.add(selecteerArtikel(71));
        }
        for (int i = 0; i < aantalBlauw; i++) {
            artikelen.add(selecteerArtikel(60));
        }
        for (int i = 0; i < aantalRood; i++) {
            artikelen.add(selecteerArtikel(73));
        }

        bpp = new Bpp(artikelen, 12);
        orderlijst = bpp.toString();
        System.out.println(orderlijst);

    }

    public void maakPakbon(int customerId) throws IOException, SQLException {
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
        run3.setText(orderlijst);



        document.write(new FileOutputStream("E:/Order" + orderNr + ".docx"));
        orderNr++;
        System.out.println(orderNr);
    }

}


