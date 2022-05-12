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





void setup() {
 Serial.begin(9600);
  
  //Channel A
  pinMode(12, OUTPUT); //motor richting
  pinMode(9, OUTPUT); //brake
Serial.print("!s");
}


void loop(){

  vooruit(255);
  delay(1000);
  achteruit(255);
  delay(1000);
  rem();
  delay(1000);
  
 
  

  
}
