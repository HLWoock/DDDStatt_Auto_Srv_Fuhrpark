package de.woock.ddd.stattauto.fuhrpark.adapter.dto;

import org.springframework.hateoas.ResourceSupport;

import de.woock.ddd.stattauto.fuhrpark.entity.Gps;
import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.FahrzeugKlasse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoResource<Auto> extends ResourceSupport {
	private Long           AutoId;
	private String         kennung;
	private String         typ;
	private FahrzeugKlasse klasse;
	private String         details;
	private Gps            position;
}
