#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_DEBUG Serial

#include <TinyGsmClient.h>
#include <HardwareSerial.h>
#include <TinyGPS++.h>
#include <ArduinoHttpClient.h>

// SIM800L serijski port
#define MODEM_RST            -1
#define MODEM_PWRKEY         -1
#define MODEM_POWER_ON       -1
#define MODEM_TX             17  // TX ESP32 -> RX SIM800L
#define MODEM_RX             16  // RX ESP32 -> TX SIM800L
#define MODEM_BAUDRATE       9600

// GPS serijski port
#define GPS_TX               5   // TX GPS -> RX ESP32
#define GPS_RX               4   // RX GPS -> TX ESP32
#define GPS_BAUDRATE         9600

// Mobilna mreža
const char apn[]  = "internet"; // npr "internet"
const char user[] = "yettel";
const char pass[] = "gprs";

// Server postavke
const char server[] = "109.92.67.92"; // Tvoj server
const int port = 8000;                     // Port (npr. 80 za HTTP)
const char endpoint[] = "/api/location";        // Endpoint npr. /api/gps

// Device ID
const char deviceId[] = "1234567890";      // <-- Ovde postavi tvoj ID

// TinyGSM modem i GPS
HardwareSerial modemSerial(1);
TinyGsm modem(modemSerial);

HardwareSerial gpsSerial(2);
TinyGPSPlus gps;

// Klijent za HTTP
TinyGsmClient client(modem);
HttpClient http(client, server, port);

void setup() {
  Serial.begin(115200);
  delay(10);

  // Start modema
  modemSerial.begin(MODEM_BAUDRATE, SERIAL_8N1, MODEM_RX, MODEM_TX);
  
  // Start GPS-a
  gpsSerial.begin(GPS_BAUDRATE, SERIAL_8N1, GPS_RX, GPS_TX);

  // Restart modema
  Serial.println("Restart modema...");
  modem.restart();

  // SIM kartica provera
  if (!modem.getSimStatus()) {
    Serial.println("SIM kartica nije detektovana!");
    while (true);
  }

  // Mreža
  Serial.println("Povezivanje na mrežu...");
  if (!modem.waitForNetwork(10000)) {
    Serial.println("Nema mreže");
    while (true);
  }
  Serial.println("Mreža pronađena!");

  // Internet
  Serial.println("Povezivanje na GPRS...");
  if (!modem.gprsConnect(apn, user, pass)) {
    Serial.println("Neuspešno povezivanje na GPRS");
    while (true);
  }
  Serial.println("GPRS povezan!");
}

void loop() {
  // Čitanje GPS podataka
  while (gpsSerial.available() > 0) {
    gps.encode(gpsSerial.read());
  }

  if (gps.location.isUpdated()) {
    float latitude = gps.location.lat();
    float longitude = gps.location.lng();
    double accuracy = gps.hdop.hdop() * 5.0; // Približna tačnost u metrima (HDOP * 5)
    double speed = gps.speed.kmph();          // Brzina u km/h
    long timestamp = gps.time.value();       // UTC timestamp u formatu hhmmsscc

    Serial.print("GPS Lat: ");
    Serial.println(latitude, 6);
    Serial.print("GPS Lon: ");
    Serial.println(longitude, 6);
    Serial.print("Accuracy: ");
    Serial.println(accuracy);
    Serial.print("Speed: ");
    Serial.println(speed);
    Serial.print("Timestamp: ");
    Serial.println(timestamp);

    // Priprema JSON-a
    String json = "{";
    json += "\"id\":\"";
    json += deviceId;
    json += "\",\"lat\":";
    json += String(latitude, 6);
    json += ",\"lon\":";
    json += String(longitude, 6);
    json += ",\"timestamp\":";
    json += String(timestamp);
    json += ",\"accuracy\":";
    json += String(accuracy, 2);
    json += ",\"speed\":";
    json += String(speed, 2);
    json += "}";

    Serial.println("JSON koji saljemo:");
    Serial.println(json);

    // Slanje na server
    Serial.println("Saljem podatke na server...");
    http.setTimeout(15000);  // 15 sekundi timeout
    http.beginRequest();
    http.post(endpoint);
    http.sendHeader("Content-Type", "application/json");
    http.sendHeader("Content-Length", json.length());
    http.beginBody();
    http.print(json);
    http.endRequest();

    int statusCode = http.responseStatusCode();
    String response = http.responseBody();

    Serial.print("Status: ");
    Serial.println(statusCode);
    Serial.print("Server odgovor: ");
    Serial.println(response);

    // Čekaj pre sledeće lokacije
    delay(5000); // 10 sekundi
  }
}
