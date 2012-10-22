En enkel väderstation
=====================
Se OH från förel. om kommunikation i Java.

Exemplet visar bl.a.:
- UML-modellering
- Klient-serverkommunikation
- Objektströmmar
- Användning av trådar

För detaljer betr. exekvering av programmen kontakta UH.

Innehåll
--------

README.txt	-- denna fil

Modell
======
väderstation.pps        --  Powerpoint-bild som visar den övergripande systemarkitekturen

clientservermodell.zuml
-- UML-modell utvecklad med Poseidon.
-- Modellen innehåller följande diagram:

Klassdiagram
------------
Weather service package dependencies	-- beskriver paketens inbördes relationer
sensor_client				-- klassdiagram för sensorapplikationen
display_client				-- klassdiagram för displayapplikationen
weather_server				-- klassdiagram för serverapplikationen

Sekvensdiagram
--------------
Socket receiver creation                -- scenariot beskriver skapandet av de två trådarna 
                                           som lyssnar efter sensor- resp. displayklientsocketar
Sensor client handling                  -- scenariot beskriver en sensorklients interaktion med servern
Display client handling                 -- scenariot beskriver en displayklients interaktion med servern


Javakod
=======
UML-modellen och javaimplementationen har samma paketstruktur.

package wheatherlib
-------------------
Paketet finns kompilerat i arkivfilen weatherlib.jar (som måste importeras till ev. eclipse-projekt)

TemperatureObservation.java	
-- Objekt av klassen (plats+temperatur) skickas från sensorklienterna till servern.
-- Det är alltså två *olika applikationer* som använder denna klass.

package sensor_client
---------------------
Main.java		-- Huvudprogram, frågar efter temperatursensorns namn
UserInterface.java	-- Ett enkelt Gui där man matar in ett temperaturvärde 
DataSender.java		-- Sänder temperaturvärden till servern

package display_client
----------------------
Main.java		-- Huvudprogram
UserInterface.java	-- Ett enkelt Gui som visar temperaturvärden i en textarea vid tryck på update-knapp 
Communicator.java	-- Sänder uppdateringsbegäran till servern och tar emot temperaturvärden.

package wheather_server
-----------------------
Main.java			-- Huvudprogram
Server.java			-- Lagrar temperaturvärden i en tabell (Map)
TimeStampedValue.java		-- Temperaturvärde + ankomsstid (se weatherlib)

ClientSocketReceiver.java	  -- Basklass för klientsocketlyssnare
SensorClientSocketReceiver.java	  -- Lyssnar efter sensorklientanslutningar
DisplayClientSocketReceiver.java  -- Lyssnar efter displayklientanslutningar

ClientHandler.java		-- Basklass för klienthanterare
SensorClientHandler.java	-- Tar emot temperaturobservationsobjekt och förmedlar dem till Server
DisplayClientHandler.java	-- Tar emot uppdateringsbegäran från displayklienter och 
				-- sänder tillbaks samtliga värden som finns i servern.

(Kompilering: se README i resp. katalog)