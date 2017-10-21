package de.woock.ddd.stattauto.fuhrpark.entity.station;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;
import lombok.Data;

@Data
@Entity
@SuppressWarnings("serial")
public class Station implements Serializable {
	
	@Id
	@GeneratedValue
	private Long stationsId;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Auto> fahrzeuge = new ArrayList<>();
	
	private Auswahlkriterien auswahlkriterien;
	private Spezifikation    spezifikation;
	
	public Station () {}
	
	public Station(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		validateStationsParameter(auswahlkriterien, spezifikation);
		this.auswahlkriterien = auswahlkriterien;
		this.spezifikation    = spezifikation;
	}

	public void autoEinrichten(Auto auto) {
		fahrzeuge.add(auto);
	}
	
	private void validateStationsParameter(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		if (auswahlkriterien == null || spezifikation == null) {
			throw new IllegalArgumentException("Parameter der Station duerfen nicht 'null' sein");
		}
	}
}
