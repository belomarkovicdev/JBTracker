#include <TinyGPSPlus.h>
#include <HardwareSerial.h>
#include <HTTPClient.h>


// GPS UART podešavanje
#define GPS_RX 4   // GPS TX ide na ovaj pin
#define GPS_TX 5   // GPS RX ide na ovaj pin
#define GPS_BAUD 9600

// SIM800L UART podešavanje
#define MODEM_RX 17  // SIM800L TX
#define MODEM_TX 16  // SIM800L RX
#define MODEM_BAUD 9600

// Inicijalizacija
TinyGPSPlus gps;
HardwareSerial SerialGPS(1);   // UART1 za GPS
HardwareSerial SerialSIM(2);   // UART2 za SIM800L

void setup() {
  Serial.begin(115200);
  delay(2000);

  // GPS UART
  SerialGPS.begin(GPS_BAUD, SERIAL_8N1, GPS_RX, GPS_TX);
  // SIM800L UART
  SerialSIM.begin(MODEM_BAUD, SERIAL_8N1, MODEM_RX, MODEM_TX);

  // Konektovanje SIM800L na mrežu
  connectToNetwork();
}

void loop() {
  while (gpsSerial.available()) {
    gps.encode(gpsSerial.read());

    if (gps.location.isUpdated()) {
      float latitude = gps.location.lat();
      float longitude = gps.location.lng();
      sendLocation(latitude, longitude);
      delay(10000);  // Pauza 10 sekundi
    }
  }
}

void connectToNetwork() {
  Serial.println("Povezivanje na mrežu...");

  SerialSIM.println("AT");
  delay(1000);
  SerialSIM.println("AT+CPIN?");
  delay(1000);
  SerialSIM.println("AT+CREG?");
  delay(1000);
  SerialSIM.println("AT+CGATT=1");
  delay(2000);
  SerialSIM.println("AT+CIPSHUT");
  delay(1000);
  SerialSIM.println("AT+CIPMUX=0");
  delay(1000);
  SerialSIM.println("AT+CSTT=\"internet\",\"yettel\",\"gprs\"");
  delay(1000);
  SerialSIM.println("AT+CIICR");
  delay(3000);
  SerialSIM.println("AT+CIFSR");  // Dobijanje IP adrese
  delay(1000);
  Serial.println("SIM800L povezan na mrežu.");
}

void sendLocation(float lat, float lon) {
  Serial.println("Slanje lokacije...");

  String deviceId = "1234567890";
  long timestamp = gps.time.value();  // ako koristiš TinyGPS++
  double accuracy = gps.hdop.hdop();  // horizontal dilution of precision
  String speed = String(gps.speed.kmph(), 2);

  String json = "{";
  json += "\"id\":\"" + deviceId + "\",";
  json += "\"lat\":" + String(lat, 6) + ",";
  json += "\"lon\":" + String(lon, 6) + ",";
  json += "\"timestamp\":" + String(timestamp) + ",";
  json += "\"accuracy\":" + String(accuracy, 2) + ",";
  json += "\"speed\":\"" + speed + "\"";
  json += "}";

  Serial.println("JSON:");
  Serial.println(json);

  SerialSIM.println("AT+CIPSTART=\"TCP\",\"109.92.67.92\",\"8000\"");
  delay(2000);
  SerialSIM.println("AT+CIPSEND");
  delay(2000);
  SerialSIM.print("POST /api/location HTTP/1.1\r\n");
  SerialSIM.print("Host: 109.92.67.92\r\n");
  SerialSIM.print("Content-Type: application/json\r\n");
  SerialSIM.print("Content-Length: " + String(json.length()) + "\r\n");
  SerialSIM.print("\r\n");
  SerialSIM.print(json);
  SerialSIM.write(0x1A); // kraj podatka (CTRL+Z)
  delay(5000);
}

  