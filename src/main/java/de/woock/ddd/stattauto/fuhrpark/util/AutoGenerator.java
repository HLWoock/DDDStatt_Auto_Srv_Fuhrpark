package de.woock.ddd.stattauto.fuhrpark.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;
import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.FahrzeugKlasse;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;

public class AutoGenerator {
	static final String       letters   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final Random       rand      = new Random();
	static       List<String> typeS     = null;
	static       List<String> typeM     = null;
	static       List<String> typeL     = null;
	static {
		typeS     = Arrays.asList("Smart Fortwo", "Opel Corsa", "Fiat Ponto", "Peugeot 106");
		typeM     = Arrays.asList("Golf GTI", "Volvo S60", "Opel Astra Combi");
		typeL     = Arrays.asList("VW Passat Combi", "Mercedes E220", "BMW 5i", "Volvo S80", "Audi RS 7");
	}

	public static Auto createAuto(Station station) {
		FahrzeugKlasse fahrzeugKlasse = createFahrzeugKlasse();
		return new Auto(createNummernschild(), 
				        createType(fahrzeugKlasse), 
				        fahrzeugKlasse, 
				        "", 
				        station.getSpezifikation().getPosition());
	}
	
	private static String createType(FahrzeugKlasse kfzKlasse) {
		switch (kfzKlasse) {
		case S:	return typeS.get(new Random().nextInt(typeS.size()));
		case M:	return typeM.get(new Random().nextInt(typeM.size()));
		case L:	return typeL.get(new Random().nextInt(typeL.size()));
		default: 		break;
		}
		return null;
	}

	private static String createNummernschild() {
		return String.format("HH %s%s %d", letters.charAt(rand.nextInt(25)), 
				                           letters.charAt(rand.nextInt(25)), 
				                           rand.nextInt(10000));
	}
	
	private static FahrzeugKlasse createFahrzeugKlasse() {
		return FahrzeugKlasse.values()[new Random().nextInt(3)];
	}
}
