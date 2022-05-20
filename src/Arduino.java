import jssc.*;
import jssc.SerialPort;
import jssc.SerialPortList;

import static jssc.SerialPort.*;
import static jssc.SerialPort.PARITY_NONE;

public abstract class Arduino {
    protected SerialPort serialPort;
    static String used = "";

    public Arduino(char readyChar) {
        SerialPort test;
        for (String port: SerialPortList.getPortNames()) {
            if(port.equals(used)) continue;
            try {
                test = new SerialPort(port);
                test.openPort();
                System.out.println("Test " + port);
                test.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);      //Opstarten
                char ch = (char) test.readBytes(1)[0];
                while(ch != '!') {
                    System.out.print(ch);
                    ch = (char) test.readBytes(1)[0];
                };
                char lh = (char) test.readBytes(1)[0];
                System.out.println("ja " + ch + lh);
                if(lh == readyChar) {
                    System.out.println(port + getClass());
                    this.serialPort = test;
                    used = port;
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

    public void openPort() throws SerialPortException {
        this.serialPort.openPort();
        this.serialPort.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
        System.out.println("test");
        char ch = (char) this.serialPort.readBytes(1)[0];
//        while(ch != '!') {
//            System.out.print(ch);
//            ch = (char) this.serialPort.readBytes(1)[0];
//        };
     }

     public void closePort() throws SerialPortException {
        this.serialPort.closePort();
     }
    // Beide arduino's moeten kleuren kunnen scannen
    // Beide arduino's moeten connectie kunnen maken
    

}
