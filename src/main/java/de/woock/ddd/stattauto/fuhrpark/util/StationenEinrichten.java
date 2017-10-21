package de.woock.ddd.stattauto.fuhrpark.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.woock.ddd.stattauto.fuhrpark.entity.Gps;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Auswahlkriterien;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Spezifikation;
import de.woock.ddd.stattauto.fuhrpark.service.FuhrparkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationenEinrichten {
	
	@Autowired
	FuhrparkService fuhrparkService;
	
	@Test()
	public void stationenAnlegen()  {
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HR", "Harburg", "Harburg Rathaus"),
				                              new Spezifikation("Zufahrt zum Parkhaus ueber Hoerstender Strasse.", 
				                              new Gps(9.9796192, 53.4597933), "S3/S31 Harburg"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "LH", "Langenhorn", "Langenhorn Nord"),
				                              new Spezifikation("Auf der gegenueberliegenden Seite der U-Bahn", 
				                              new Gps(10.016496, 53.661095), "U1 Langenhorn Nord"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HL", "Eimsbuettel", "Hellkamp"),
                                              new Spezifikation("Hellkamp 31-33, etwa 50m nach der Ecke Osterstrasse in Richtung Stellinger Weg", 
                                              new Gps(9.948172, 53.578389), "U2, Bus 113"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "CK", "Eimsbuettel", "Christuskirche 26"),
                                              new Spezifikation("Im Weidenstieg; Parkplatz neben dem Kreisverkehr an der Gemeinde und dem Getraenkemarkt", 
                                              new Gps(9.961088, 53.570195), "U2"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "TR", "Eimsbuettel", "Troplowitzstr. 7"),
                                              new Spezifikation("In der Troplowitzstrasse, ganz am Ende auf dem Werkparkplatz der Fa. Beisersdorf.", 
                                              new Gps(9.965136, 53.584794), "U2, 181 Bus"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HB", "Harburg", "Harburg Bhf. P+R"),
                                              new Spezifikation("Zufahrt zum Parkhaus ueber Hoerstener Strasse.", 
                                              new Gps(9.998536, 53.451412), "S3/S31"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HO", "Hoheluft", "Kaiser-Friedrich-Ufer"),
                                              new Spezifikation("Direkt am Isebekkanal gelegen, kurz hinterm Grindelberg", 
                                              new Gps(9.975384, 53.577956), "U3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "RO", "Rothenburgsort", "Billstrasse"),
				                              new Spezifikation("An der Bille gelegen.", 
						                      new Gps(10.042988, 53.540094), "S2, S21"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "AK", "Altona", "Koenigstrasse"),
                                              new Spezifikation("Abends ist eine Taschenlampe manchmal hilfreich.", 
                                              new Gps(9.945677, 53.548512), "S1, S2, S3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "AF", "Altona", "Fischmarkt"),
                                              new Spezifikation("Direkt neben der Fischauktionshalle./nAuf Hochwasserwarnung achten. Auto dann weiter oben in der Grossen Elbstrasse parken.", 
                                              new Gps(9.950534, 53.544807), "Hafenfaehre 62"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "WL", "Wandsbek", "Litzowstrasse"),
                                              new Spezifikation("In der Naehe des Quarrees", 
                                              new Gps(10.068567, 53.574645), "U1"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BN", "Bergedorf", "Nettelnburger Str."),
                                              new Spezifikation("5 min Fussweg auf der Nettelnburger Str.", 
                                              new Gps(10.180129, 53.478600), "S2, S21"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HC", "HafenCity", "New-Orleans-Strasse"),
                                              new Spezifikation("Achtung auf die vielen Touristen", 
                                              new Gps(10.000120, 53.539451), "U4"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "NR", "Neustadt", "Rothesodstrasse"),
                                              new Spezifikation("An der Ecke Zeughausstrasse", 
                                              new Gps(9.974088, 53.547571), "U3, S1, S2, S3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SP", "St.Pauli", "Heiligengeistfeld"),
                                              new Spezifikation("Seitenspiegel bitte einklappen", 
                                              new Gps(9.972029, 53.557003), "U3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SL", "St. Georg", "Lindenstrasse"),
                                              new Spezifikation("10 min Fussweg, weg vom Bahnhof", 
                                              new Gps(10.018530, 53.553084), "U1, U3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HH", "Hammerbrook", "Heidenkampsweg"),
                                              new Spezifikation("Viele Einbahnstassen", 
                                              new Gps(10.028956, 53.546310), "S3, S31"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HM", "Hamm", "Hammer Landstrasse"),
                                              new Spezifikation("Auf der rechten Strassenseite parken.", 
                                              new Gps(10.060832, 53.554424), "U2, U4"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HW", "Horn", "Washingtonallee"),
                                              new Spezifikation("Parkplatz durch das Tor erreichbar", 
                                              new Gps(10.087157, 53.551354), "U2, U4"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BM", "Billstedt", "Merkenstrasse"),
                                              new Spezifikation("10m von der Moellner Landstrasse abgelegen", 
                                              new Gps(10.126437, 53.538911), "U2, U4"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "VP", "Veddel", "Peutestrasse"),
                                              new Spezifikation("keine OPNV Anbindung", 
                                              new Gps(10.046186, 53.521548), "S3, S31"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "WN", "Wilhelmsburg", "Neuenfelder Str."),
                                              new Spezifikation("Bei der Behoerde fuer Umwelt", 
                                              new Gps(10.004790, 53.498503), "S3, S31"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "OH", "Ottensen", "Ottenser Hauptstrasse"),
                                              new Spezifikation("Parkhaus vom Mercado Center kann auch genutzt werden", 
                                              new Gps(9.932803, 53.552338), "S1, S2, S3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BK", "Bahrenfeld", "Kielkamp"),
                                              new Spezifikation("Direkt auf dem Parkplatz", 
                                              new Gps(9.898453, 53.573004), "Busse: 1, 2, 3, 286, 601, 602"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "GF", "Gross Flottbek", "Boecklingstrasse"),
                                              new Spezifikation("Bitte eng parken.", 
                                              new Gps(9.887111, 53.562615), "Busse: 1, 186, 286, 601")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "FW", "Finkenwerder", "Landscheideweg"),
                                              new Spezifikation("Elbe kann nur durch den Elbtunnel ueberquert werden.", 
                                              new Gps(9.858743, 53.527950), "Bus: 251")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "NB", "Neuwerk", "Insel"),
                                              new Spezifikation("Sie befinden sich auf einer Insel\nTraktoren haben Vorfahrt", 
                                              new Gps(8.503765, 53.922641), "Faehre oder zu Fuss")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "LB", "Lurup", "Binsbarg"),
                                              new Spezifikation("Fussballspiele und Veranstalltungen am Wochenende.", 
                                              new Gps(9.903794, 53.592445), "Bus:22 ,S3, S22, Zug: A1 "));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "OR", "Osdorf", "Rugenfeld"),
                                              new Spezifikation("Guter Anschluss B431", 
                                              new Gps(9.849451, 53.578103), "Busse: 21, 37")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BB", "Blankenese", "Elbchaussee"),
                                              new Spezifikation("Kino in der Naehe", 
                                              new Gps(9.813768, 53.559399), "Busse: 48, 49, S1, S11")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BS", "Blankenese", "S-Bahn"),
                                              new Spezifikation("Direkt an der S-Bahn", 
                                              new Gps(9.816516, 53.564557), "Busse: 48, 49, S1, S11"));  
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SO", "Suelldorf", "Ohlnhof"),
                                              new Spezifikation("Auf Blitzer achten", 
                                              new Gps(9.793654, 53.586788), "S1, S11"));  
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SF", "Suelldorf", "Grothen Flerren"),
                                              new Spezifikation("Zohne 30 beachten", 
                                              new Gps(9.772889,53.592177), "S1, S11"));   
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "EG", "Eimsbuettel", "Gazellenkamp"),
                                              new Spezifikation("Hinter dem Zoo", 
                                              new Gps(9.939736, 53.599592), "Bus: 181, U2")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "RS", "Rotherbaum", "St. Johannis"),
                                              new Spezifikation("Sonntags Gottesdienst", 
                                              new Gps( 9.992912, 53.570791), "Busse: 109, 605"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "LL", "Lokstedt", "Lohkoppelweg 22"),
                                              new Spezifikation("U-Bahn ist etwas weiter entfernt.", 
                                              new Gps(9.958086, 53.592635), "Bus: 22, 39, 281, 391, 181, U2"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "NK", "Niendorf", "Kleingartenverein"),
                                              new Spezifikation("Nicht auf den Parkplaetzen der Kleingaertner parken.", 
                                              new Gps(9.969512, 53.630215), "Busse: 191, 604"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SG", "Stellingen", "Gutenbergstrasse 35"),
                                              new Spezifikation("Zoo in der Naehe", 
                                              new Gps(9.926855, 53.587930), "Busse: 4, 183, 283, 603, S3, S21"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "EK", "Eppendorf", "Kellinghusenstrasse"),
                                              new Spezifikation("Parkplatz auf dem Gelaende der Evangelische Familienbildung", 
                                              new Gps(9.992642, 53.587893), "Busse: 22, 25, 26, U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "WS", "Winterhude", "Suedring 32"),
                                              new Spezifikation("Am Stadtpark", 
                                              new Gps(10.016969, 53.591029), "Busse: 6, 179, 600, U3")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "UM", "Uhlenhorst", "Mundsburg"),
                                              new Spezifikation("Am Aspia Hamburg Uhlenhorst", 
                                              new Gps(10.021426, 53.570707), "Bus: 6, U3")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HU", "Hohenfelde", "Uhlandstrasse"),
                                              new Spezifikation("Achtung, viele Blitzer", 
                                              new Gps(10.027723, 53.564189), "U3")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "DA", "Dulsberg", "Alter Teichweg"),
                                              new Spezifikation("Direkt am Park", 
                                              new Gps(10.066176, 53.585385), "U1"));  
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BW", "Barmbek", "Alte Woehr"),
                                              new Spezifikation("Guter Croque Laden in der Naehe", 
                                              new Gps(10.037436, 53.597393), "Bus: 23, S1, S11")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "OF", "Ohlsdorf", "Friedhof"),
                                              new Spezifikation("Nicht auf dem Gelaende des Friedhofs parken", 
                                              new Gps(10.039052, 53.629911), "U1, S1, S11")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "FK", "Fuhlsbuettel", "Kurveneck 4"),
                                              new Spezifikation("Seitenspiegel einklappen", 
                                              new Gps(10.016841, 53.636215), "U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "JJ", "Jenfeld", "Jennfelder Allee"),
                                              new Spezifikation("Starcar Parkplaetze duerfen mit benutzt werden.", 
                                              new Gps(10.124403, 53.570227), "Busse: 10, 27, 37, 263, 618, E62")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "TE", "Tonndorf", "Einkaufszentrum"),
                                              new Spezifikation("Di. und Fr. ist Wochenmarkt", 
                                              new Gps(10.124113, 53.585863), "Busse: 9, 27, 167,608, RB: 81")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "FF", "Farmsen", "Farmsen"),
                                              new Spezifikation("P+R Parkplaetze", 
                                              new Gps(10.117617, 53.606394), "U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "FB", "Farmsen", "Berne"),
                                              new Spezifikation("P+R Parkplaetze", 
                                              new Gps(10.138944, 53.626593), "U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BE", "Berne", "Eismeerweg"),
                                              new Spezifikation("An der Esso Tankstelle", 
                                              new Gps(10.146659, 53.624605), "U1"));  
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "VV", "Volksdorf", "Volksdorf"),
                                              new Spezifikation("Di. und Fr. Markt\nAn der Evangelisch-Lutherischen Kirchengemeinde", 
                                              new Gps(10.164531, 53.651028), "U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "PA", "Poppenbuettel", "Alstertaler Einkaufszentrum"),
                                              new Spezifikation("Polizeikommissariat in der Naehe", 
                                              new Gps(10.091162, 53.650738), "S1, S11"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SS", "Sasel", "Saseler Chaussee"),
                                              new Spezifikation("Am Naturschutzgebiet\nMotor nicht unnoetig laufen lassen.", 
                                              new Gps(10.113993, 53.660719), "Busse: 174, 374, 607, 627")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "MP", "Maiendorf", "Parkhaus"),
                                              new Spezifikation("P+R Parkhaus", 
                                              new Gps(10.155517, 53.638692), "U1")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "HS", "Hamburg-Mitte", "Steinwerder"),
                                              new Spezifikation("Musikals gut zu erreichen", 
                                              new Gps(9.972054, 53.538560), "Faehre: 73")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BOe", "Billstedt", "Oejendorfer Park"),
                                              new Spezifikation("Direkt am Park", 
                                              new Gps(10.145407, 53.549489), "Mit OePNV nicht zu erreichen")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "BA", "Billbrook", "Am Kraftwerk"),
                                              new Spezifikation("Am Tiefstackkanal", 
                                              new Gps(10.070910, 53.530326), "Bus: 130")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "WF", "Waltershof", "Finkenwerder Strasse"),
                                              new Spezifikation("Im Logistik-Zentrum", 
                                              new Gps(9.902600, 53.514747), "Mit OePNV nicht zu erreichen")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "SK", "Steinwerder", "Koehlbrand"),
                                              new Spezifikation("Unter der Koehlbrandbruecke", 
                                              new Gps(9.972008, 53.518018 ), "Mit OePNV nicht zu erreichen")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Kiel", "GK", "Gaaden", "Kieler Kamp"),
                                              new Spezifikation("Autos sind markiert", 
                                              new Gps(10.138474, 54.291192), ""));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Kiel", "DD", "Duesternbrook", "Dueppelstrasse"),
                                              new Spezifikation("Autos sind markiert", 
                                              new Gps(10.145736, 54.337985), "")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Kiel", "HK", "Holtenau", "Kirchengemeinde"),
                                              new Spezifikation("Autos sind markiert", 
                                              new Gps(10.150626, 54.371821), ""));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Kiel", "MS", "Mettenhof", "Russeer Weg"),
                                              new Spezifikation("Autos sind markiert", 
                                              new Gps(10.064390, 54.319963), "")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Luebeck", "MB", "Marli", "Buelowstrasse"),
                                              new Spezifikation("Fahrradstreifen nicht zuparken", 
                                              new Gps(10.714089, 53.870067), "")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Luebeck", "SZ", "St. Lorenz Sued", "ZOB"),
                                              new Spezifikation("Gute Verbindung zum Hbf und zum ZOB.", 
                                              new Gps(10.670717, 53.865505), "Fernbahn, alle Buslinien"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Luebeck", "HA", "Herrenburg", "Am Bahnhof"),
                                              new Spezifikation("Parkplatz von Aldi darf benutzt werden", 
                                              new Gps(10.759717, 53.837482), "Regionalbahn"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Bremen", "BA", "Bockland", "Auf den Bloecken"),
                                              new Spezifikation("Vorsicht Seniorenheim", 
                                              new Gps(8.737543, 53.134795), "")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Bremen", "OB", "Oberneuland", "Bonner Strasse"),
                                              new Spezifikation("Am Blockdieksee", 
                                              new Gps(8.930518, 53.077170), ""));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Pinneberg", "TD", "Thesdorf", "Diesterwegstrasse"),
                                              new Spezifikation("Direkt an der S-Bahn", 
                                              new Gps(9.812274, 53.644390), "Bus: 285, S3"));
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Bad Schwartau", "BB", "Bad Schwartau", "Bismarkstrasse"),
                                              new Spezifikation("Naehe Schwartauer Werke", 
                                              new Gps(10.705896, 53.917029), "Regionalbahn")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Eutin", "SJ", "Schloss", "Jungfernstieg"),
                                              new Spezifikation("Direkt am Schloss", 
                                              new Gps(10.619359, 54.136761), "Regionalbahn")); 
		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Timmendorf", "TS", "Timmendorf", "Strandstrasse"),
                                              new Spezifikation("Wenige Meter bis zum Strand", 
                                              new Gps(10.803669, 53.989294), "Regionalbahn")); 
//		fuhrparkService.neueStationEinrichten(new Auswahlkriterien("Hamburg", "", "", ""),
//                                            new Spezifikation("", 
//                                            new Gps(, ), ""));
		
	}
}

