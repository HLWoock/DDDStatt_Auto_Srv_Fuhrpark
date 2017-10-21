package de.woock.ddd.stattauto.fuhrpark.entity.station;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import de.woock.ddd.stattauto.fuhrpark.entity.Gps;
import lombok.Data;

@Data
@Embeddable
@SuppressWarnings("serial")
public class Spezifikation implements Serializable {
	@Column(length=1024) private String     beschreibung;
	                     private Gps        position;
	                     private String     oepnv;
	
	public Spezifikation () { }

	public Spezifikation(String beschreibung, Gps position, String oepnv) {
		this.beschreibung = beschreibung;
		this.position     = position;
		this.oepnv        = oepnv;
	}
	

}
