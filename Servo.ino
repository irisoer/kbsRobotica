#include<Servo.h>
Servo Myservo;
int input;



void setup() {
  Myservo.attach(3);
  Serial.begin(9600);
  String readyChar = "!s";
  Serial.println(readyChar);
}

void loop() {
  if(Serial.available()){
    input = Serial.read();
    Myservo.write(input);
    delay(15);
    Serial.println(input);
  }
}
