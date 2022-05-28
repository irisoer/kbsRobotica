package GUI;

import java.awt.*;

public interface ProductStandaard {
    //Standaard product
    int hoogteDoos = 155;       //hoogte doos
    int breedteDoos = 110;      //breedte doos
    int grootProduct = 80;    //Groot formaat, voor sorteren
    int kleinProduct = grootProduct /2;      //Klein formaat voor verwerk/order schermen
    Color rood = Color.RED;     //todo: kleuren verwerken in statements in subklasses
    Color geel = Color.ORANGE;
    Color blauw = Color.BLUE;

//
//    int marge = 5;              //producten uitlijnen binnen doos
//    int xDoos1 = 150;           //x positie doos 1
//    int xDoos2 = 350;           //x positie doos 2
//    int xDoos3 = 550;           //x positie doos 3
//    int yDoos = 165;            //y positie van dozen
}
