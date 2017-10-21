package de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug;

import javax.persistence.Embeddable;

import de.woock.ddd.stattauto.fuhrpark.adapter.dto.ImageMap;
import lombok.Data;

@Data
@Embeddable
public class Bild {
	
	private ImageMap map;
	
	public Bild () { }
}
