package de.woock.ddd.stattauto.fuhrpark.adapter.dto;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Bild {
	
	private ImageMap map;
	
	public Bild () { }
}
