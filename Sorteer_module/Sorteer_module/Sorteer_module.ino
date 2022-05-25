
#include <Servo.h>
#include <Wire.h>
#include "Adafruit_TCS34725.h"
#define STEPPER_PIN_1 9
#define STEPPER_PIN_2 10
#define STEPPER_PIN_3 11
#define STEPPER_PIN_4 12
int step_number = 0;
Servo Myservo;
String inputservo;
String laatst;
bool onder = false;
int returnInt = 0;
#define draai 0
#define stil 1
#define allesstil 2
bool codenetgestart = true;
int tijd;
int laatstescan;



/* kleurensensor:
   SCL -> A5        blauw
   SDA -> A4        geel
   VDD -> 3.3V      groen 
   ground -> GROUND wit


   servo -> 3

   steppenmotor:
   PIN_1 -> 9
   PIN_2 -> 10
   PIN_3 -> 11
   PIN_4 -> 12



*/

/* Initialise with default values (int time = 2.4ms, gain = 1x) */
Adafruit_TCS34725 tcs = Adafruit_TCS34725();

/* Initialise with specific int time and gain values */
//Adafruit_TCS34725 tcs = Adafruit_TCS34725(TCS34725_INTEGRATIONTIME_614MS, TCS34725_GAIN_1X);



void setup(void) {

  Serial.setTimeout(10);

  pinMode(STEPPER_PIN_1, OUTPUT);
  pinMode(STEPPER_PIN_2, OUTPUT);
  pinMode(STEPPER_PIN_3, OUTPUT);
  pinMode(STEPPER_PIN_4, OUTPUT);

  Serial.begin(9600);

  Myservo.attach(3);

  while (!tcs.begin()) {
  } 

  Myservo.write(0);

  Serial.println("!s");
  waitForSerialChar(':');
  
}




void loop() {
  tijd = millis();
  switch (returnInt) {
    case draai:

    if(tijd - laatstescan> 30000){
      Serial.println(":s");
    }

      OneStep(true);
      delay(3);


      uint16_t r, g, b, c, colorTemp, lux;

      tcs.getRawData(&r, &g, &b, &c);

      if (c >= 8 && !onder && !codenetgestart) {
        if(r > b and r > g and r > 4 and c < 15) {
           if (laatst != "r") {
             laatst = "r";
             onder = true;
             laatstescan = tijd;
           }
          // Serial.print("Rood: "); Serial.print(r, DEC); Serial.print(" ");
         } else if (b > r and b > g and b > 6) {
           if (laatst != "b") {
             laatst = "b";
             onder = true;
             laatstescan = tijd;
           }
           //Serial.print("Blauw: "); Serial.print(b, DEC); Serial.print(" ");
         } else if (g > r and g > b) {
           if (laatst != "g") {
             laatst = "g";
             onder = true;
             laatstescan = tijd;
           }
         }
      } else if (onder && !codenetgestart) {

        Serial.println("#" + laatst);
        onder = false;
        returnInt = 2;
        //    laatst = "";
      } else if (c < 6 && codenetgestart) {
        codenetgestart = false;
      } else {
        onder = false;
        laatst = "";
      }

      break;

    case stil:

      Myservo.write(180);
      delay(1000);
      Myservo.write(0);
      delay(1000);

      returnInt = 0;
      break;


    case allesstil:

      waitForSerialChar(':');





  }
}


void waitForSerialChar(char c) {
  String rString;
  rString = Serial.readString();
  rString.trim();
  while (rString.indexOf(c) == -1) {
    rString = Serial.readString();
  }
  Serial.println(rString[rString.indexOf(c) - 1]);
  returnInt = int(rString[rString.indexOf(c) - 1]) - '0';
}


void OneStep(bool dir) {
  if (dir) {
    switch (step_number) {
      case 0:
        digitalWrite(STEPPER_PIN_1, HIGH);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, LOW);
        break;
      case 1:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, HIGH);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, LOW);
        break;
      case 2:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, HIGH);
        digitalWrite(STEPPER_PIN_4, LOW);
        break;
      case 3:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, HIGH);
        break;
    }
  } else {
    switch (step_number) {
      case 0:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, HIGH);
        break;
      case 1:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, HIGH);
        digitalWrite(STEPPER_PIN_4, LOW);
        break;
      case 2:
        digitalWrite(STEPPER_PIN_1, LOW);
        digitalWrite(STEPPER_PIN_2, HIGH);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, LOW);
        break;
      case 3:
        digitalWrite(STEPPER_PIN_1, HIGH);
        digitalWrite(STEPPER_PIN_2, LOW);
        digitalWrite(STEPPER_PIN_3, LOW);
        digitalWrite(STEPPER_PIN_4, LOW);


    }
  }
  step_number++;
  if (step_number > 3) {
    step_number = 0;
  }
}
