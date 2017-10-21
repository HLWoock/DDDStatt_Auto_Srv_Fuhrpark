package de.woock.ddd.stattauto.fuhrpark.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import de.woock.ddd.stattauto.fuhrpark.adapter.dto.ImageMap;
import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Auswahlkriterien;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Spezifikation;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;
import de.woock.ddd.stattauto.fuhrpark.repository.AutoRepository;
import de.woock.ddd.stattauto.fuhrpark.repository.StationRepository;

@Service
public class FuhrparkService {
	
	private static Logger log = Logger.getLogger(FuhrparkService.class);
	
	@Autowired JmsTemplate     jmsTemplate;
	@Autowired CounterService  counterService;
	
	@Autowired private StationRepository stationRepository;
	@Autowired private AutoRepository    autoRepository;
	
	public Station neueStationEinrichten(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		Station neueStation = stationRepository.save(new Station(auswahlkriterien, spezifikation));
		log.debug(String.format("neue Station eingerichtet: %s", neueStation));
		if (neueStation.getStationsId() != 0) {
			sendNewStationEvent(neueStation);
		} else {
			log.debug(String.format("neue Station konnte nicht erstellt werden: %s", neueStation.getAuswahlkriterien().getKuerzel()));
		}
		return neueStation;
	}
	
	public List<Station> stationsauswahl() {
		counterService.increment("FuhrparkService.stationsauswahl");
		return stationRepository.findAll();
	}
	
	public Station zeigeStationAn(Long stationsId) {
		counterService.increment("FuhrparkService.zeigeStationAn");
		return stationRepository.getOne(stationsId);
	}

	public List<String> alleKuerzel() {
		counterService.increment("FuhrparkService.alleKuerzel");
		return stationRepository.findAll().stream().map(s -> s.getAuswahlkriterien().getKuerzel()).collect(Collectors.toList());
	}

	public Station zeigeStationFuerKuerzel(String kuerzel) {
		counterService.increment("FuhrparkService.zeigeStationFuerKuerzel");
		return stationRepository.findStationByAuswahlkriterienKuerzel(kuerzel);
	}

	public void stationsdatenAendern(Station station) {
		counterService.increment("FuhrparkService.stationsdatenAendern");
		stationRepository.save(station);
	}

	public ImageMap zeigeAutoAn(Long id) {
		counterService.increment("FuhrparkService.zeigeAutoAn");
		return autoRepository.findById(id).getBild().getMap();
	}
	
	private void sendNewStationEvent(Station station) {
		log.debug("sende Nachricht für neue Station");
		counterService.increment("FuhrparkService.sendNewStationEvent");
		jmsTemplate.setDefaultDestinationName("NeueStation");
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(station.getStationsId());
			}
		});
	}

	public Long getStationsId(String stadt, String stadtteil, String standort) {
		Station station = stationRepository.findStationByAuswahlkriterienStadtAndAuswahlkriterienStadtteilAndAuswahlkriterienStandort(stadt, stadtteil, standort);
		return station.getStationsId();
	}

	public List<Long> getFahrzeugIdsAnStationsId(Long id) {
		Station station = stationRepository.findOne(id);
		return station.getFahrzeuge().stream()
				                     .map(f -> (Long) f.getId())
				                     .collect(Collectors.toList());
	}

	public List<Long> getFahrzeugIdsAnStationsKuerzel(String kuerzel) {
		Station station = stationRepository.findStationByAuswahlkriterienKuerzel(kuerzel);
		return station.getFahrzeuge().stream()
				                     .map(f -> (Long) f.getId())
				                     .collect(Collectors.toList());
	}

	public Auto getFahrzeugMitId(Long id) {
		return autoRepository.findOne(id);
	}
}
