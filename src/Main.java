import jssc.SerialPortException;

import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
//    public static void main(String[] args) throws SerialPortException{
//        SerialPort port = new SerialPort("COM5");                       //maken poort object
//        port.openPort();
//        port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
//        while(port.readBytes(1)[0] !=121);                              //Leest meest recente bytes die die doorkrijgt uit Arduino tot 121 = "y"
//        int random = ThreadLocalRandom.current().nextInt(1,181);    //Voor nu hoeveel graden de java applicatie doorstuurt naar Arduino inpak
//        port.writeInt(random);
//        System.out.println(random);
//        port.closePort();
//    }
//    public static void main(String[] args) {
//        try{
//            ArduinoSorteer arduinoSorteer = new ArduinoSorteer();
//        } catch (jssc.SerialPortException e) {
//            System.out.println(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        Integer weight[] = { 2, 5, 1, 1, 7, 2, 3, 8 };
//        int c = 10;
//        int n = weight.length;
//        Bpp bpp = new Bpp(weight, c);
//        System.out.print(Arrays.toString(weight));
//        System.out.println(" Verdeeld over bins: " + bpp);
//        System.out.println("Draaien naar bakje "+ bpp.remBinItem(5));
//        System.out.println();
//        System.out.println(bpp);
//    }

//    public static void main(String[] args) throws SQLException {
//        Database db;
//        db = new Database();
//        ArrayList<Artikel> result = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            result.add(db.getProduct(60));
//            result.add(db.getProduct(70));
//            result.add(db.getProduct(73));
//        }
//        result.add(db.getProduct(73));
//        Bpp bpp = new Bpp(result, 20);
////            ArduinoInpak inpak = new ArduinoInpak();
//        System.out.println(bpp);
//    }

	public static void main(String[] args) throws SerialPortException, SQLException {
		ArduinoSorteer sorteer = new ArduinoSorteer();
        ArrayList<Artikel> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(Database.getArtikelFromID(60));
            result.add(Database.getArtikelFromID(73));
        }
        result.add(Database.getArtikelFromID(70));
        Bpp bpp = new Bpp(result, 20);
        System.out.println(bpp);
        while(!bpp.isLeeg()) {
            System.out.println(sorteer.getKleur(bpp));
            System.out.println(bpp);

        }
	}


}
