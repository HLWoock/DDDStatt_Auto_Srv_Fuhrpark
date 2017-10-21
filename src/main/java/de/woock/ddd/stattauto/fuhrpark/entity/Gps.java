package de.woock.ddd.stattauto.fuhrpark.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
@SuppressWarnings("serial")
public class Gps implements Serializable {
	double lat;
	double lng;
	
	public Gps() {}
	
	public Gps(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
}
