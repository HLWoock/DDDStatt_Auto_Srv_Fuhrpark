package de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.woock.ddd.stattauto.fuhrpark.adapter.dto.ImageMap;
import de.woock.ddd.stattauto.fuhrpark.entity.Gps;
import lombok.Data;

@Data
@Entity
public class Auto {

	@Id
	@GeneratedValue
	private Long           id;
	private String         kennung;
	private String         typ;
	private FahrzeugKlasse klasse;
	private String         details;
	private Bild           bild;
	private Gps            position;
	
	public Auto(){}

	public Auto(String kennung, String typ, FahrzeugKlasse klasse, String details, Gps position) {
		this.kennung  = kennung;
		this.typ      = typ;
		this.klasse   = klasse;
		this.details  = details;
		this.position = position;
		this.bild     = new Bild();
		
		try {
			bild.setMap(new ImageMap(Files.readAllBytes(new File(String.format("src/main/resources/static/images/auto/%s.png", typ)).toPath())));
		} catch (IOException e) {
			bild.setMap(null);
		}

	}
}
