import jssc.*;
import java.util.concurrent.ThreadLocalRandom;

import static jssc.SerialPort.*;


public class Main {
    public static void main(String[] args) throws SerialPortException{
        SerialPort port = new SerialPort("COM5");                       //maken poort object
        port.openPort();
        port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
        while(port.readBytes(1)[0] !=121);                              //Leest meest recente bytes die die doorkrijgt uit Arduino tot 121 = "y"
        int random = ThreadLocalRandom.current().nextInt(1,181);    //Voor nu hoeveel graden de java applicatie doorstuurt naar Arduino inpak
        port.writeInt(random);
        System.out.println(random);
        port.closePort();

    }

}
