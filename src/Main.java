import jssc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static jssc.SerialPort.*;


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


}
