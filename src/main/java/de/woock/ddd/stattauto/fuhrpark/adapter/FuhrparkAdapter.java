package de.woock.ddd.stattauto.fuhrpark.adapter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.ddd.stattauto.fuhrpark.adapter.dto.AutoResource;
import de.woock.ddd.stattauto.fuhrpark.adapter.dto.ImageMap;
import de.woock.ddd.stattauto.fuhrpark.adapter.dto.StationsResource;
import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Auswahlkriterien;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Spezifikation;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;
import de.woock.ddd.stattauto.fuhrpark.service.FuhrparkService;


@RestController
@RequestMapping("/Stationen")
@ExposesResourceFor(Station.class)
//@CrossOrigin(origins="http://localhost:4200")
public class FuhrparkAdapter {

	@Autowired FuhrparkService fuhrparkService;
	@Autowired Tracer tracer;

	@PostMapping("/einrichten")
	Station stationEinrichten(@RequestBody Station station) {
		tracer.getCurrentSpan().logEvent(String.format("neue Station einrichten %s", station));
		return fuhrparkService.neueStationEinrichten(station.getAuswahlkriterien(), station.getSpezifikation());
	}

	@RequestMapping("/stationen")
	public List<StationsResource<Station>> stationen() { 
		List<Station>                   stationen = fuhrparkService.stationsauswahl();
		int                             size      = stationen.size();
		List<StationsResource<Station>> resources = new ArrayList<>(size);
		
		tracer.getCurrentSpan().logEvent(String.format("# Stationen: %s", size));	
		
		for(Station station : stationen) {
			StationsResource<Station> resource = new StationsResource<>();
			resource.setAuswahlkriterien(station.getAuswahlkriterien());
			resource.setStationsId(station.getStationsId());
			
			resource.add(linkTo(methodOn(FuhrparkAdapter.class).stationsSpezifikation(station.getStationsId())).withRel("spezifikation"));
			resource.add(linkTo(methodOn(FuhrparkAdapter.class).fahrzeugeAnStation(station.getStationsId())).withRel("fahrzeuge"));
	
			resource.add(linkTo(FuhrparkAdapter.class).slash(station.getStationsId()).withSelfRel());
	
			resources.add(resource);
		}
		return resources;
	}

	@RequestMapping("/station/id/{stationsId}")
	public StationsResource<Station> stationMitId(@PathVariable Long stationsId) {
		tracer.getCurrentSpan().logEvent(String.format("StationsResource mit id: %d vom Server geholt", stationsId));
		
		Station station = fuhrparkService.zeigeStationAn(stationsId);
		
		StationsResource<Station> resource = new StationsResource<>();
		resource.setAuswahlkriterien(station.getAuswahlkriterien());
		resource.setStationsId(station.getStationsId());
		
		resource.add(linkTo(methodOn(FuhrparkAdapter.class).stationsSpezifikation(station.getStationsId())).withRel("spezifikation"));
		resource.add(linkTo(methodOn(FuhrparkAdapter.class).fahrzeugeAnStation(station.getStationsId())).withRel("fahrzeuge"));
	
		resource.add(linkTo(FuhrparkAdapter.class).slash(station.getStationsId()).withSelfRel());
		
		tracer.getCurrentSpan().logEvent(String.format("Resource: %s", resource));

		return resource;
	}

	@GetMapping("/station/kuerzel/{kuerzel}")
	//@CrossOrigin(origins="http://localhost:4200")
	public Station stationMitKuerzel(@PathVariable String kuerzel) {
		return fuhrparkService.zeigeStationFuerKuerzel(kuerzel);
	}

	@RequestMapping("/station/id/{stationsId}/spezifikation")
	public Spezifikation stationsSpezifikation(@PathVariable Long stationsId) {
		return fuhrparkService.zeigeStationAn(stationsId).getSpezifikation();
	}
	
	@RequestMapping("/auswahlkriterien/{kuerzel}")
	public Auswahlkriterien stationsAuswahlkriterienFuerKuerzel(@PathVariable String kuerzel) {
		Station station = fuhrparkService.zeigeStationFuerKuerzel(kuerzel);
		return station.getAuswahlkriterien();
	}

	@RequestMapping("/station/{stadt}/{stadtteil}/{standort}/stationsId")
	public Long stationsId(@PathVariable(name="stadt") String stadt,
			               @PathVariable(name="stadtteil") String stadtteil, 
			               @PathVariable(name="standort")String standort) {
		Span span = tracer.getCurrentSpan();
		span.setBaggageItem("Baggage Key", "Baggage Value");
		span.logEvent("Log Event");
		tracer.addTag("Tag Key", "Tag Value");
		tracer.close(span);

		return fuhrparkService.getStationsId(stadt, stadtteil, standort);
	}

	@RequestMapping("/kuerzel") 
	public List<String> stationsKuerzel() {
		return fuhrparkService.alleKuerzel();
	}

	@RequestMapping("/station/id/{stationsId}/fahrzeuge")
	public List<AutoResource<Auto>> fahrzeugeAnStation(@PathVariable long stationsId) {
		List<Auto>               autos     = fuhrparkService.zeigeStationAn(stationsId).getFahrzeuge();
		List<AutoResource<Auto>> resources = new ArrayList<>(autos.size());
		
		for (Auto auto: autos) {
			AutoResource<Auto> resource = new AutoResource<>();
			resource.setAutoId(auto.getId());
			resource.setKennung(auto.getKennung());
			resource.setTyp(auto.getTyp());
			resource.setKlasse(auto.getKlasse());
			resource.setPosition(auto.getPosition());
			resource.setDetails(auto.getDetails());
			
			resource.add(linkTo(methodOn(FuhrparkAdapter.class).fahrzeugImage(auto.getId())).withRel("map"));
			resource.add(linkTo(FuhrparkAdapter.class).slash(stationsId).slash(methodOn(FuhrparkAdapter.class).fahrzeugeAnStation(stationsId)).withRel("station"));
			resources.add(resource);
		}
		
		return resources;
	}

	@RequestMapping("/station/id/{stationsId}/fahrzeugIds")
	public List<Long> fahrzeugIdsAnStationMitId(@PathVariable Long stationsId) {
		return fuhrparkService.getFahrzeugIdsAnStationsId(stationsId);
	}
	
	@RequestMapping("/station/kuerzel/{kuerzel}/fahrzeugIds")
	public List<Long> fahrzeugIdsAnStationMitKuerzel(@PathVariable String kuerzel) {
		return fuhrparkService.getFahrzeugIdsAnStationsKuerzel(kuerzel);
	}

	@RequestMapping("/fahrzeug/id/{fahrzeugId}")
	public Auto fahrzeugMitId(@PathVariable Long fahrzeugId) {
		return fuhrparkService.getFahrzeugMitId(fahrzeugId);
	}
	
	@RequestMapping("/fahrzeug/id/{fahrzeugId}/map")
	public ImageMap fahrzeugImage(@PathVariable Long fahrzeugId) {
		ImageMap autoImage = fuhrparkService.zeigeAutoAn(fahrzeugId);
//		tracer.addTag("image Size", autoImage.getMap().length);

		return autoImage;
	}
}
