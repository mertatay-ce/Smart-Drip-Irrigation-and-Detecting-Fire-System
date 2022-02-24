#include <ThingSpeak.h>  //To connect our thingspeak channel with the esp8266 through this code.
#include <ESP8266WiFi.h>  // To connect the esp with internet
#include <DHT.h>

const char* ssid     = "FiberHGW_TPD384_2.4GHz";    //Name of your wifi network
const char* password = "kp4HuhPj";  // Wifi password

const char* server = "api.thingspeak.com";
String apiKey = "95FCYLBH419LOIWW";
/* Change these values based on your calibration values */
#define soilWet 300  // Define max value we consider soil 'wet'  //the values measured during the calibration to classify soil as wet/dry 
#define soilDry 750   // Define min value we consider soil 'dry'

#define sensorPin A0    //connect the sensor to analog pin of esp8266
#define M1 D0 
#define M2 D1 


long myChannelNumber = 1608925;    //Channel number of thingspeak
const char myWriteAPIKey[] = "95FCYLBH419LOIWW";  //Api key from thingspeak channel
#define DHTPIN 0  
DHT dht(DHTPIN, DHT11);
WiFiClient  client;

void setup() {
   Serial.begin(9600); 
   delay(100);
   pinMode(M1, OUTPUT);
   pinMode(M2, OUTPUT);
   Serial.println();
   Serial.println();
   Serial.print("Connecting to ");
   Serial.println(ssid);
  
   WiFi.begin(ssid, password);  // Connecting esp to wifi
  
   while (WiFi.status() != WL_CONNECTED)  //This loop will work untill esp connects to the wifi
   {   
   delay(500);
   Serial.print(".");
   }
  
   Serial.println("");
   Serial.println("WiFi connected");  
   Serial.println("IP address: ");
   Serial.println(WiFi.localIP());
   ThingSpeak.begin(client);       //The client here is esp8266 
  }


void loop() {
  float h = dht.readHumidity();
  float t = dht.readTemperature();
      
  
  //get the reading from the function below and print it
  int moisture = analogRead(sensorPin);    //Read the analog values
  Serial.print("Analog Output: ");  //Print the analog values
  Serial.println(moisture);

  // Determine status of our soil using the values already defined for wet and dry soil
  if (moisture < soilWet) 
  {
    Serial.println("Status: Soil is too wet");
    Serial.println("Status: motor duruyor");
    digitalWrite(M1, LOW);
    digitalWrite(M2, LOW);
    
  } 
  else if (moisture >= soilWet && moisture < soilDry) 
  {
    Serial.println("Status: Soil moisture is perfect");
    digitalWrite(M1, LOW);
    digitalWrite(M2, LOW);
  } 
  else 
  {
    Serial.println("Status: Soil is too dry - time to water!");
    Serial.println("Status: Motor çalışıyor");
    digitalWrite(M1, HIGH);
    digitalWrite(M2, LOW);
  }
  
  delay(500);  // Take a reading every  half a second for testing
                // Normally you should take reading perhaps once or twice a day
  Serial.println(); 
   
  ThingSpeak.writeField(myChannelNumber,1,moisture, myWriteAPIKey);

}