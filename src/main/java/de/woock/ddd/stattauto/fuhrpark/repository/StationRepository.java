package de.woock.ddd.stattauto.fuhrpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

	Station findStationByAuswahlkriterienKuerzel(String kuerzel);
	Station findStationByAuswahlkriterienStadtAndAuswahlkriterienStadtteilAndAuswahlkriterienStandort(String stadt, String stadtteil, String standort);
}