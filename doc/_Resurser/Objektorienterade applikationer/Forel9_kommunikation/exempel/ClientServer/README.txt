En enkel v�derstation
=====================
Se sista OH fr�n f�rel. om kommunikation i Java.

Exemplet visar bl.a.:
- UML-modellering
- Klient-serverkommunikation
- Objektstr�mmar
- Anv�ndning av tr�dar

F�r detaljer betr. exekvering av programmen kontakta UH.

Inneh�ll
--------

README	-- denna fil

v�derstation.pps	-- Powerpoint-bild

UML
===
UML-modell utvecklad med Poseidon.
Modellen inneh�ller f�ljande diagram:

Klassdiagram:
-------------
Weather service package dependencies	-- beskriver paketens inb�rdes relationer
sensor_client				-- klassdiagram f�r sensorapplikationen
display_client				-- klassdiagram f�r displayapplikationen
weather_server				-- klassdiagram f�r serverapplikationen

Sekvensdiagram:
---------------
Socket receiver creation                -- scenariot beskriver skapandet av de tv� tr�darna 
                                           som lyssnar efter sensor- resp. displayklientsocketar
Sensor client handling                  -- scenariot beskriver en sensorklients interaktion med servern
Display client handling                 -- scenariot beskriver en displayklients interaktion med servern

Paket
=====
UML-modellen och javaimplementationen har samma paketstruktur.

wheatherlib
-----------

Paket finns i arkivfilen weatherlib.jar (som m�ste importeras till eclipse-projektet)

class TemperatureObservation;	
-- Objekt av klassen (plats+temperatur) skickas fr�n sensorklienterna till servern.
-- Det �r allts� tv� *olika applikationer* som anv�nder detta paket.


sensor_client
-------------
Main.java		-- Huvudprogram, fr�gar efter temperatursensorns namn
UserInterface.java	-- Ett enkelt Gui d�r man matar in ett temperaturv�rde 
DataSender.java		-- S�nder temperaturv�rden till servern

display_client
--------------

Main.java		-- Huvudprogram
UserInterface.java	-- Ett enkelt Gui som visar temperaturv�rden i en textarea vid tryck p� update-knapp 
Communicator.java	-- S�nder uppdateringsbeg�ran till servern och tar emot temperaturv�rden.

wheather_server
---------------
Main.java			-- Huvudprogram
Server.java			-- Lagrar temperaturv�rden i en tabell (Map)
TimeStampedValue.java		-- Temperaturv�rde + ankomsstid

ClientSocketReceiver.java	  -- Basklass f�r klientsocketlyssnare
SensorClientSocketReceiver.java	  -- Lyssnar efter sensorklientanslutningar
DisplayClientSocketReceiver.java  -- Lyssnar efter displayklientanslutningar

ClientHandler.java		-- Basklass f�r klienthanterare
SensorClientHandler.java	-- Tar emot temperaturobservationsobjekt och f�rmedlar dem till Server
DisplayClientHandler.java	-- Tar emot uppdateringsbeg�ran fr�n displayklienter och 
				-- s�nder tillbaks samtliga v�rden som finns i servern.
