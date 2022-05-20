import jssc.SerialPortException;

public class ArduinoInpak extends Arduino {
    public ArduinoInpak() throws jssc.SerialPortException {
        super('i');
    }

    public void draaiNaarPlatform(int index) throws SerialPortException, InterruptedException {
        if(index == -1) return;
        this.openPort();
        this.serialPort.writeString(String.valueOf(index) + String.valueOf(index) + ":");
        System.out.println("verstuurd naar " + String.valueOf(index));
        char test = (char) this.serialPort.readBytes(1)[0];
        while(test != ':') {
            System.out.print(test);
            test = (char) this.serialPort.readBytes(1)[0];
        }
        System.out.println("Gedraaid");
        this.closePort();
    }
}
