import jssc.SerialPortException;

import java.sql.SQLException;

public class ArduinoSorteer extends Arduino {
    public ArduinoSorteer() throws jssc.SerialPortException {
        super('s');
    }

    public int getKleur(Bpp bpp) throws SerialPortException {
        int index = -1;
        while(this.getSerialPort().readBytes(1)[0] != 35) {}
        char color = (char)this.getSerialPort().readBytes(1)[0];
        String kleur = "";
        if(color == 'r') kleur = "Red";
        else if (color == 'g') kleur = "Dark Green";
        else if (color == 'b') kleur = "Blue";
        try {
            Artikel a = Database.getArtikelVanKleur(kleur);
            index = bpp.findBinNum(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }
}
