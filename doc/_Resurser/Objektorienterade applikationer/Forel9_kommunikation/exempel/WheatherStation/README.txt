En enkel v�derstation
=====================
Se OH fr�n f�rel. om kommunikation i Java.

Exemplet visar bl.a.:
- UML-modellering
- Klient-serverkommunikation
- Objektstr�mmar
- Anv�ndning av tr�dar

F�r detaljer betr. exekvering av programmen kontakta UH.

Inneh�ll
--------

README.txt	-- denna fil

Modell
======
v�derstation.pps        --  Powerpoint-bild som visar den �vergripande systemarkitekturen

clientservermodell.zuml
-- UML-modell utvecklad med Poseidon.
-- Modellen inneh�ller f�ljande diagram:

Klassdiagram
------------
Weather service package dependencies	-- beskriver paketens inb�rdes relationer
sensor_client				-- klassdiagram f�r sensorapplikationen
display_client				-- klassdiagram f�r displayapplikationen
weather_server				-- klassdiagram f�r serverapplikationen

Sekvensdiagram
--------------
Socket receiver creation                -- scenariot beskriver skapandet av de tv� tr�darna 
                                           som lyssnar efter sensor- resp. displayklientsocketar
Sensor client handling                  -- scenariot beskriver en sensorklients interaktion med servern
Display client handling                 -- scenariot beskriver en displayklients interaktion med servern


Javakod
=======
UML-modellen och javaimplementationen har samma paketstruktur.

package wheatherlib
-------------------
Paketet finns kompilerat i arkivfilen weatherlib.jar (som m�ste importeras till ev. eclipse-projekt)

TemperatureObservation.java	
-- Objekt av klassen (plats+temperatur) skickas fr�n sensorklienterna till servern.
-- Det �r allts� tv� *olika applikationer* som anv�nder denna klass.

package sensor_client
---------------------
Main.java		-- Huvudprogram, fr�gar efter temperatursensorns namn
UserInterface.java	-- Ett enkelt Gui d�r man matar in ett temperaturv�rde 
DataSender.java		-- S�nder temperaturv�rden till servern

package display_client
----------------------
Main.java		-- Huvudprogram
UserInterface.java	-- Ett enkelt Gui som visar temperaturv�rden i en textarea vid tryck p� update-knapp 
Communicator.java	-- S�nder uppdateringsbeg�ran till servern och tar emot temperaturv�rden.

package wheather_server
-----------------------
Main.java			-- Huvudprogram
Server.java			-- Lagrar temperaturv�rden i en tabell (Map)
TimeStampedValue.java		-- Temperaturv�rde + ankomsstid (se weatherlib)

ClientSocketReceiver.java	  -- Basklass f�r klientsocketlyssnare
SensorClientSocketReceiver.java	  -- Lyssnar efter sensorklientanslutningar
DisplayClientSocketReceiver.java  -- Lyssnar efter displayklientanslutningar

ClientHandler.java		-- Basklass f�r klienthanterare
SensorClientHandler.java	-- Tar emot temperaturobservationsobjekt och f�rmedlar dem till Server
DisplayClientHandler.java	-- Tar emot uppdateringsbeg�ran fr�n displayklienter och 
				-- s�nder tillbaks samtliga v�rden som finns i servern.

(Kompilering: se README i resp. katalog)