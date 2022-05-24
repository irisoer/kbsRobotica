import GUI.*;
import jssc.SerialPortException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

//public class Main extends JFrame{
//	public static void main(String[] args) {
//
//		//testen schermen >
//		JFrame frame = new JFrame();
//		frame.setSize(800, 400);
//		frame.setLayout(new GridLayout());
//
//
//		ErrorScherm e = new ErrorScherm();
//		frame.add(e);
//
//
////		SorteerScherm s = new SorteerScherm();
////		frame.add(s);
//
////		VerwerkKleur vk = new VerwerkKleur();		//todo: plek voor meer aantallen (net als in dozen)
////		frame.add(vk);
//
////		VerwerkDoos vd = new VerwerkDoos();			//todo: nog niet volledig werkend
////		frame.add(vd);
//
////		VerwerkVoorraad vv = new VerwerkVoorraad();
////		frame.add(vv);
//
//		frame.setVisible(true);
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

    //	public static void main(String[] args) throws SerialPortException, SQLException, InterruptedException {
//        ArduinoInpak inpak = new ArduinoInpak();
////        inpak.draaiNaarPlatform(0);
//
//		ArduinoSorteer sorteer = new ArduinoSorteer();
//        ArrayList<Artikel> result = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            result.add(Database.selecteerArtikel(60));
//            result.add(Database.selecteerArtikel(73));
//        }
//        result.add(Database.selecteerArtikel(70));
//        Bpp bpp = new Bpp(result, 12);
//
//        System.out.println(bpp);
//        while(!bpp.isLeeg()) {
//            int index = sorteer.getKleur(bpp);
//            System.out.println(index);
//            inpak.draaiNaarPlatform(index);
//            System.out.println(bpp);
//
//        }
//	}
//    public static void main(String[] args) throws SQLException {
//        System.out.println("-------------------------------");
//        Database voorraad = new Database();
//        ArrayList<Integer> getvoorraadArray = new ArrayList<>();
//        getvoorraadArray.add(Database.getVoorraad(60));
//        getvoorraadArray.add(Database.getVoorraad(70));
//        getvoorraadArray.add(Database.getVoorraad(73));
//        for (Integer voorraad1 : getvoorraadArray) {
//            System.out.println(voorraad1);
//        }
////    result.add(Database.selecteerArtikel(70));
////    Bpp bpp = new Bpp(result, 12);
////    System.out.println(bpp);
////    while(!bpp.isLeeg()) {
////        int index = sorteer.getKleur(bpp);
////        System.out.println(index);
//////            inpak.draaiNaarPlatform(index);
////        System.out.println(bpp);
//
//        ArrayList<Integer> updatedVoorraadArray = new ArrayList<>();
//        updatedVoorraadArray.add(Database.updateVoorraad(60, 4));
//        updatedVoorraadArray.add(Database.updateVoorraad(70, 7));
//        updatedVoorraadArray.add(Database.updateVoorraad(73, 7));
//        for (Integer voorraad2 : updatedVoorraadArray) {
//            System.out.println(voorraad2);
//        }


public static void main(String[] args) throws IOException, SQLException {
    Order order = new Order();
    order.maakPakbon(20);
    order.getOrder();

	}
}


