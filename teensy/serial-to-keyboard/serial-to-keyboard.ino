const int ledPin = 13;

void setup() {
  pinMode(ledPin, OUTPUT);
  Serial1.begin(9600);
  Keyboard.begin();
  digitalWrite(ledPin, HIGH); delay(100); digitalWrite(ledPin, LOW);
}

void loop() {
  if (Serial1.available() > 0) {
    digitalWrite(ledPin, HIGH); delay(10); digitalWrite(ledPin, LOW);
    while (Serial1.available() > 0) {
      int b = Serial1.read();
      if(b==9) {
        Keyboard.press(KEY_TAB);
        Keyboard.release(KEY_TAB);
      } else {
        Keyboard.write(b);
      }
      delay(5);
    }
  }
}
