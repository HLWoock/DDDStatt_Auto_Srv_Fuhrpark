package de.woock.ddd.stattauto.fuhrpark.adapter.dto;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ImageMap {

	@Lob
	private byte[] map;
	
	public ImageMap(byte[] map) {
		this.map = map;
	}
	
	public ImageMap() {}
}
