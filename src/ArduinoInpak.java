import jssc.SerialPortException;

public class ArduinoInpak extends Arduino {
    public ArduinoInpak() throws jssc.SerialPortException {
        super('i');
    }

    public void draaiNaar(int index) throws SerialPortException {
        this.getSerialPort().openPort();
        this.getSerialPort().writeString(String.valueOf(index) + ":");
        // todo: goede index opvragen en rest van de serial uit de code van arduino
        while(this.getSerialPort().readBytes(1)[0] != String.valueOf(index).getBytes()[0]) {

        };
        System.out.println("Klaar");
        this.getSerialPort().closePort();
    }
}
