#include <Wire.h>
#include "Adafruit_TCS34725.h"

/* Connect SCL    to analog 5
   Connect SDA    to analog 4
   Connect VDD    to 3.3V DC
   Connect GROUND to common ground */

Adafruit_TCS34725 tcs = Adafruit_TCS34725();

int draainaar = -1; // het bakje waar we naar toe moeten draaien, -1 is niet draaien
int laatst = -1; // het bakje waar we momenteel zijn, -1 is onbekend
int pogingen = 0; // pogingen om naar het volgende bakje te draaien, als dit een bepaalde limiet bereikt (en de motor dus is blijven haken) herstelt de motor zich met restore()
int action = -1; // de huidige actie, 1 voor stilstaan, 0 voor draaien
int olaatst = -1; // de vorige laatste waarde
uint16_t r, g, b, c, colorTemp, lux; // rood, groen, blauw, lichtwaarde

// de posities van de bakjes
int rood = 0;
int geel = 1;
int blauw = 2;
int wit = 3;

// waardes voor kleursensor
int minC = 6 // minimale lichtwaarde wat wordt gezien als valide kleur
int geelR = 6 // rood waarde van geel
int geelG = 6 // groen waarde van geel
int geelMaxRGverschil = 4 // max verschil tussen rood en groen van geel
float geelBfractie = 0.75 // max gedeelte van rood wat blauw mag zijn voor geel
int witR = 12
int witB = 12
int witG = 12
int roodR = 4
int roodMaxC = 15
int blauwB = 6

void vooruit(int snelheid){
  //deze functie laat de motor vooruit gaan met int snelheid als snelheid
  digitalWrite(12, HIGH); //zet motor vooruit
  digitalWrite(9, LOW); // zet brake uit
  analogWrite(3, snelheid); //bepaald snelheid motor met max van 255
 }
 
  void rem(){
  //deze functie laat de motor stoppen
  digitalWrite(9, HIGH); // zet brake aan (stopt motor)
  }

  void achteruit(int snelheid){
  //deze functie laat de motor achteruit gaan met int snelheid als snelheid
  digitalWrite(12, LOW); //zet motor achteruit
  digitalWrite(9, LOW); // zet brake uit
  analogWrite(3, snelheid); //bepaald snelheid motor met max van 255
  }

void setup(void) {
  Serial.begin(9600);
  Serial.println("!i"); // geeft aan de java applicatie door welke module dit is
  pinMode(12, OUTPUT); //motor richting
  pinMode(9, OUTPUT); //brake
  while (!tcs.begin()) { // wacht tot de sensor is gevonden
  }
  scan(); // scan om het huidige bakje te zien
  while (laatst == -1) { // blijf draaien tot we een bakje zien
    vooruit(255);
    delay(20);
    rem();
    scan();
    delay(20);
  }
}

unsigned long vorigeMillis = millis(); // wordt gebruikt om een timer bij te houden
void scan() {
  tcs.getRawData(&r, &g, &b, &c); // lees output van kleursensor uit
  colorTemp = tcs.calculateColorTemperature(r, g, b);
  colorTemp = tcs.calculateColorTemperature_dn40(r, g, b, c);
  lux = tcs.calculateLux(r, g, b);
  if(c >= minC) {
    if (r > geelR and g > geelG and abs(r-g) < geelMaxRGverschil and b < floor(r*geelBfractie)) {
      laatst = geel; // geel
    } else if (r > witR and b > witB and g > witG) {
      laatst = wit; // white
    } else if(r > b and r > g and r > roodR and c < roodMaxC) {
      laatst = rood; // rood
    } else if (b > r && b > g && b > blauwB) {
      laatst = blauw; // blauw
    }
  }
}
bool backwards = false; // bepaalt welke kant we op draaien
bool turbo = false; // bepaalt of we met hoge snelheid draaien
void restore() { // herstelt de motor als deze blijft haken door kort naar achteren en daarna weer naar voren te gaan
  if (backwards) {
    vooruit(255);
  } else {
    achteruit(255);
  }
  delay(50);
  if (backwards) {
    achteruit(255);
  } else {
    vooruit(255);
  }
  delay(50);
  rem();
}

int vertraging = 25; // hoe lang we vooruit blijven gaan voordat we gaan remmen
int totaalTijd = 40; // hoe veel ms we doen over 1 "loop" van vooruit gaan en remmen
void draai() {
  if (draainaar != -1) {
    unsigned long huidigeMillis = millis();
    if ((unsigned long)(huidigeMillis - vorigeMillis) >= totaalTijd) { // wanneer we over de totale tijd heen zitten
      vorigeMillis = huidigeMillis; // reset vorigeMillis weer naar huidige tijd zodat de timer opnieuw begint
    }
    if ((unsigned long)(huidigeMillis - vorigeMillis) < vertraging) { // blijf vooruit (of achteruit) gaan terwijl we nog niet over vertraging heen zijn
      if (backwards) {
        achteruit(255);
      } else {
        vooruit(255);
      }
      if (action != 0) { // wanneer we voor het eerst weer vooruit gaan in een loop, verhoog "pogingen" (wanneer deze te hoog is zijn we blijven haken)
        pogingen++;
        action = 0;
      }
    } else if ((unsigned long)(huidigeMillis - vorigeMillis) >= vertraging) { // wanneer we over vertraging heen gaan
      action = 1;
      int a = draainaar-laatst;
      if (a < 0) {
        a = 4+a;
      }
      if (!turbo and a <= 1) { // als we niet in "turbomodus" zitten en de afstand tot een bakje is minder dan 1, remmen we af
        rem();
      }
    }
  }
}

void verwerkSerial() {
  String tInput = Serial.readStringUntil(':');
  delay(500);
  Serial.read(); // er wordt telkens een extra karakter ontvangen over serial na Serial.readStringUntil, dit lost dat op
  int inputint;
  inputint = (int)(tInput[0])-48; // zet ingekomen string om naar getal door ASCII waarde van 0 af te trekken
  if (inputint < 0 or inputint > 3) {
  } else if (inputint == laatst) {
    Serial.println(":Already on position:");
  } else {
    draainaar = inputint;
    int a = (draainaar-laatst)%4;
    int b = (laatst-draainaar)%4;
    if (a < 0) {
      a = 4+a;
    }
    if (b < 0) {
      b = 4+b;
    }
    if (a > b) {
      achteruit = true;
      turbo = true;
    } else {
      achteruit = false;
      if (a >= 2) {
        // we leggen een grote afstand af, zet tijd dat we vooruit gaan lager
        turbo = false;
        vertraging = 25;
      } else {
        turbo = false;
        vertraging = 35;
      }
    }
    restore(); // de motor blijft vaak haken wanneer hij uit stilstand aan gaat, restore() helpt hier tegen
  }
}

void loop(void) {
  draai()
  scan(); // lees kleursensor uit
  if(c >= 6) {
    if (olaatst != laatst) { // wanneer vorige waarde van laatst niet gelijk is aan laatst zijn we van bakje veranderd
      olaatst = laatst;
      pogingen = 0;
      if (draainaar == laatst) { // als dit het bakje is die we willen hebben stoppen we en geven we aan de HMI applicatie door dat we er zijn
        Serial.println(":Complete:");
        draainaar = -1;
        rem();
      }
    }
  }
  if (pogingen > 30) { // de motor is na 30 pogingen nog steeds niet bij het volgende bakje, neem aan dat we zijn blijven haken en herstel
    restore();
    pogingen = 0; // reset het aantal pogingen
  }
  if (Serial.available() > 0) {
    verwerkSerial() // lees serial uit en verwerk dit als bakje om naartoe te draaien
  }
}