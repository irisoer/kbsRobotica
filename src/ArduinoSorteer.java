import jssc.SerialPortException;

import java.sql.SQLException;

public class ArduinoSorteer extends Arduino {
    public ArduinoSorteer() {
        super('s');
    }

    public int getKleur(Bpp bpp) throws SerialPortException, InterruptedException {
        this.bandAan();
        int index = -1;
        String test = "#";
        while(this.serialPort.readBytes(1)[0] != test.getBytes()[0]) {}
        char color = (char)this.serialPort.readBytes(1)[0];
        String kleur = "";
        System.out.println(color);
        if(color == 'r') kleur = "Red";
        else if (color == 'g') kleur = "Dark Green";
        else if (color == 'b') kleur = "Blue";
        System.out.println(kleur);
        try {
            Artikel a = Database.selecteerArtikel(kleur);
            System.out.println(a);
            index = bpp.findBinNum(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(index == -1) {
            this.serialPort.writeString("1:"); // pusher aan zetten
        } else {
            this.serialPort.writeString("0:");
            this.closePort();
        }
        return index;
    }

    private void bandAan() throws SerialPortException {
        try {   this.openPort();
            this.serialPort.writeString("0:");}
        catch (SerialPortException e) { }

    }
}
