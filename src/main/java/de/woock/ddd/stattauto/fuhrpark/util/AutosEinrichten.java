package de.woock.ddd.stattauto.fuhrpark.util;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;
import de.woock.ddd.stattauto.fuhrpark.service.FuhrparkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutosEinrichten {
	
	@Autowired
	FuhrparkService fuhrparkService;
	
	Random   rand    = new Random();
	
	@Test()
	public void autosAnlegen()  {
		
		List<Station> stationen = fuhrparkService.stationsauswahl();
		
		stationen.forEach(s -> {
				for (int i = 1; i <= 3 + rand.nextInt(4); i++) {
					Auto auto = AutoGenerator.createAuto(s);
					s.autoEinrichten(auto);
				}
				fuhrparkService.stationsdatenAendern(s);
			});
	}
}