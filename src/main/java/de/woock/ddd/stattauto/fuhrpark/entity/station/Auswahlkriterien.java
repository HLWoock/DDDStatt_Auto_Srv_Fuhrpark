package de.woock.ddd.stattauto.fuhrpark.entity.station;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
@SuppressWarnings("serial")
public class Auswahlkriterien implements Serializable {

	private String stadt;
	private String kuerzel;
	private String stadtteil;
	private String standort;
	
	public Auswahlkriterien() { }

	public Auswahlkriterien(String stadt, String kuerzel, String stadtteil, String standort) {
		this.stadt     = stadt;
		this.kuerzel   = kuerzel;
		this.stadtteil = stadtteil;
		this.standort  = standort;
	}
}
