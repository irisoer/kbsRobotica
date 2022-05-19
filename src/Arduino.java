import jssc.*;
import jssc.SerialPort;
import jssc.SerialPortList;

import static jssc.SerialPort.*;
import static jssc.SerialPort.PARITY_NONE;

public abstract class Arduino {
    protected SerialPort serialPort;

    public Arduino(char readyChar) {
        SerialPort test;
        for (String port: SerialPortList.getPortNames()) {
            System.out.println("Test");
            try {
                test = new SerialPort("COM9");
                test.openPort();
                test.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
                while(test.readBytes(1)[0] != 33) {

                };
                if(test.readBytes(1)[0] == readyChar) {
                    System.out.println(port);
                    this.serialPort = test;
                };
            } catch (SerialPortException e) {
                continue;
            }

        }
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    // Beide arduino's moeten kleuren kunnen scannen
    // Beide arduino's moeten connectie kunnen maken
    

}
