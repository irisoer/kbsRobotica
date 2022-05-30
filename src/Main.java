//public class Main extends JFrame{
//	public static void main(String[] args) {
//

import GUI.Frame;
import GUI.OpstartScherm;

public class Main {
//    public static void main(String[] args) throws SerialPortException{
//        SerialPort port = new SerialPort("COM5");                       //maken poort object
//        port.openPort();
//        port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
//        while(port.readBytes(1)[0] !=121);                              //Leest meest recente bytes die die doorkrijgt uit Applicatie.Arduino tot 121 = "y"
//        int random = ThreadLocalRandom.current().nextInt(1,181);    //Voor nu hoeveel graden de java applicatie doorstuurt naar Applicatie.Arduino inpak
//        port.writeInt(random);
//        System.out.println(random);
//        port.closePort();
//    }
//    public static void main(String[] args) {
//        try{
//            Applicatie.ArduinoSorteer arduinoSorteer = new Applicatie.ArduinoSorteer();
//        } catch (jssc.SerialPortException e) {
//            System.out.println(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        Integer weight[] = { 2, 5, 1, 1, 7, 2, 3, 8 };
//        int c = 10;
//        int n = weight.length;
//        Applicatie.Bpp bpp = new Applicatie.Bpp(weight, c);
//        System.out.print(Arrays.toString(weight));
//        System.out.println(" Verdeeld over bins: " + bpp);
//        System.out.println("Draaien naar bakje "+ bpp.remBinItem(5));
//        System.out.println();
//        System.out.println(bpp);
//    }

//    public static void main(String[] args) throws SQLException {
//        Applicatie.Database db;
//        db = new Applicatie.Database();
//        ArrayList<Applicatie.Artikel> result = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            result.add(db.getProduct(60));
//            result.add(db.getProduct(70));
//            result.add(db.getProduct(73));
//        }
//        result.add(db.getProduct(73));
//        Applicatie.Bpp bpp = new Applicatie.Bpp(result, 20);
////            Applicatie.ArduinoInpak inpak = new Applicatie.ArduinoInpak();
//        System.out.println(bpp);
//    }

	//	public static void main(String[] args) throws SerialPortException, SQLException, InterruptedException {
//        Applicatie.ArduinoInpak inpak = new Applicatie.ArduinoInpak();
////        inpak.draaiNaarPlatform(0);
//
//		Applicatie.ArduinoSorteer sorteer = new Applicatie.ArduinoSorteer();
//        ArrayList<Applicatie.Artikel> result = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            result.add(Applicatie.Database.selecteerArtikel(60));
//            result.add(Applicatie.Database.selecteerArtikel(73));
//        }
//        result.add(Applicatie.Database.selecteerArtikel(70));
//        Applicatie.Bpp bpp = new Applicatie.Bpp(result, 12);
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
//        Applicatie.Database voorraad = new Applicatie.Database();
//        ArrayList<Integer> getvoorraadArray = new ArrayList<>();
//        getvoorraadArray.add(Applicatie.Database.getVoorraad(60));
//        getvoorraadArray.add(Applicatie.Database.getVoorraad(70));
//        getvoorraadArray.add(Applicatie.Database.getVoorraad(73));
//        for (Integer voorraad1 : getvoorraadArray) {
//            System.out.println(voorraad1);
//        }
//    result.add(Applicatie.Database.selecteerArtikel(70));
//    Applicatie.Bpp bpp = new Applicatie.Bpp(result, 12);
//    System.out.println(bpp);
//    while(!bpp.isLeeg()) {
//        int index = sorteer.getKleur(bpp);
//        System.out.println(index);
////            inpak.draaiNaarPlatform(index);
//        System.out.println(bpp);
//
//        ArrayList<Integer> updatedVoorraadArray = new ArrayList<>();
//        updatedVoorraadArray.add(Applicatie.Database.updateVoorraad(60, 4));
//        updatedVoorraadArray.add(Applicatie.Database.updateVoorraad(70, 7));
//        updatedVoorraadArray.add(Applicatie.Database.updateVoorraad(73, 7));
//        for (Integer voorraad2 : updatedVoorraadArray) {
//            System.out.println(voorraad2);
//        }


//public static void main(String[] args) throws IOException, SQLException {                     // testen order pakbon
//    Applicatie.Order order = new Applicatie.Order();
//    order.getOrder();
//    order.maakPakbon(2);
//	}}

	public static void main(String[] args) {

		Frame frame = new Frame();
		frame.setScherm(Frame.Schermen.OpstartScherm);
		//testen schermen >
//                JFrame frame = new JFrame();
//                frame.setSize(800, 400);
//                frame.setLayout(new GridLayout());

//		ErrorScherm e = new ErrorScherm();
//		frame.add(e);

//		SorteerScherm s = new SorteerScherm();
//		frame.add(s);
//
//		VerwerkKleurScherm vk = new VerwerkKleurScherm();		//todo: meer aantallen (net als in dozen)
//		frame.add(vk);
////
//                VerwerkDoosScherm vd = new VerwerkDoosScherm();			//todo: nog niet volledig werkend
//                frame.add(vd);
////
//		VerwerkVoorraadScherm vv = new VerwerkVoorraadScherm();
//		frame.add(vv);
//
//                frame.setVisible(true);

	}
}



