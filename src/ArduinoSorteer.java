import jssc.SerialPortException;

public class ArduinoSorteer extends Arduino {
    public ArduinoSorteer() throws jssc.SerialPortException {
        super('s');
    }
}
