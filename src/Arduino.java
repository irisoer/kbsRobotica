import jssc.*;
import jssc.SerialPort;
import jssc.SerialPortList;

import static jssc.SerialPort.*;
import static jssc.SerialPort.PARITY_NONE;

public abstract class Arduino {
    private SerialPort serialPort;

    public Arduino(char readyChar) {
        SerialPort test;
        for (String port: SerialPortList.getPortNames()) {
            try {
                test = new SerialPort(port);
                test.openPort();
                test.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
                while(test.readBytes(1)[0] != "!".getBytes()[0]) {

                };
                if(test.readBytes(1)[0] == readyChar) {
                    System.out.println(port + this.getClass());
                    this.serialPort = test;
                };
                test.closePort();
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
