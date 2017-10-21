package de.woock.ddd.stattauto.fuhrpark.adapter.dto;

import org.springframework.hateoas.ResourceSupport;

import de.woock.ddd.stattauto.fuhrpark.entity.station.Auswahlkriterien;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationsResource<Station> extends ResourceSupport {
	private Long             stationsId;
	private Auswahlkriterien auswahlkriterien;
}
