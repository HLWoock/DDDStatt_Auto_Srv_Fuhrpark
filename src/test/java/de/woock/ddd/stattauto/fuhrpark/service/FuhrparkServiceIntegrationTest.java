package de.woock.ddd.stattauto.fuhrpark.service;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.woock.ddd.stattauto.DddStattAuto_Srv_Fuhrpark;
import de.woock.ddd.stattauto.fuhrpark.AbstractTest;
import de.woock.ddd.stattauto.fuhrpark.entity.Gps;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Auswahlkriterien;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Spezifikation;
import de.woock.ddd.stattauto.fuhrpark.entity.station.Station;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DddStattAuto_Srv_Fuhrpark.class, 
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
                properties = {"spring.datasource.url=jdbc:mysql://localhost:3306/stattauto_ddd", 
                		      "spring.datasource.username=root", 
                		      "spring.datasource.password=titbit#1", 
                		      "spring.datasource.driver-class-name=com.mysql.jdbc.Driver"})
public class FuhrparkServiceIntegrationTest extends AbstractTest { 
	
	@Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	private MockMvc                        mockMvc;
	private RestDocumentationResultHandler document;

	
	@Autowired
	private FuhrparkService fuhrparkService;
	
	
	@Value("${local.server.port}")
	int port;
	
	@Before
    public void setUp() {
        document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
        mockMvc  = MockMvcBuilders.webAppContextSetup(this.context)
                                  .apply(documentationConfiguration(this.restDocumentation).uris()
                                  		                                                   .withScheme("http")
                                   		                                                   .withHost("localhost")
                                   		                                                   .withPort(8181))
                                  .alwaysDo(document)
                                  .build();
    }

    @Test
    public void listStation() throws Exception {
        createSampleStation(new Auswahlkriterien("Frankfurt", "FF", "Sachsenhausen", "Darmstaedter Landstrasse"), 
        		            new Spezifikation("Beschreibung", new Gps(54.0, 19.0), "U7"));
        document("listStation", getResponseFieldsForStations());
        mockMvc.perform(get("/Stationen/").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(document("index", getResponseFieldsForStations()));
    }

    @Test
	public void holeEineStationMitSpeziellerId() throws Exception {
    	document("holeEineStationMitSpeziellerId", getResponseFieldForStation());
        mockMvc.perform(get("/Stationen/1").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }
    
    @Test
    public void zeigeStationMitSpeziellemKuerzel() throws Exception {
    	document("zeigeStationMitSpeziellemKuerzel",getResponseFieldForStationMitKuerzel());
        mockMvc.perform(get("/Stationen/mitKuerzel/HR").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());	
    }
    
    @Test
    public void autosAnStation() throws Exception {
    	document("autosAnStation", getResponseFieldForAutosAnStationMitId());
    	mockMvc.perform(get("/Stationen/1/Autos").accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk());
    }

    @Test
    public void getAutoImage() throws Exception {
    	document("getAutoImage", getResponseFieldForAutoAnStationMitId());
    	mockMvc.perform(get("/Stationen/Auto/1/map").accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk());
    }
    
    @Test
    public void getStationsSpezifikation() throws Exception {
    	document("getStationsSpezifikation", getResponseFieldForSpezifikationAnStationMitId());
    	mockMvc.perform(get("/Stationen/1/Specifikation").accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk());
    }
    
	private Station createSampleStation(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
	    return fuhrparkService.neueStationEinrichten(auswahlkriterien, spezifikation); 
	}

	private ResponseFieldsSnippet getResponseFieldsForStations() {
		return responseFields(fieldWithPath("[].stationsId")                .description("The station's ID"),
		                      fieldWithPath("[].auswahlkriterien.stadt")    .description("auswahlkriterien.stadt"),
		                      fieldWithPath("[].auswahlkriterien.kuerzel")  .description("auswahlkriterien.kuerzel"),
		                      fieldWithPath("[].auswahlkriterien.stadtteil").description("auswahlkriterien.stadtteil"),
		                      fieldWithPath("[].auswahlkriterien.standort") .description("auswahlkriterien.standort"),
 		                      fieldWithPath("[].stadt")                     .description("Thes station's stadt"),
		                      fieldWithPath("[].kuerzel")                   .description("The station's Kuerzel"),
		                      fieldWithPath("[].stadtteil")                 .description("The station's stadtteil"),
		                      fieldWithPath("[].standort")                  .description("the station's standort"),
		                      fieldWithPath("[].links.[].rel")              .description("the station's standort"),
		                      fieldWithPath("[].links.[].href")             .description("the station's standort"));
	}

	private ResponseFieldsSnippet getResponseFieldForStation() {
		return responseFields(fieldWithPath("stationsId")                .description("The station's ID"),
		                      fieldWithPath("auswahlkriterien.stadt")    .description("Stadt, in der die Station ist"),
		                      fieldWithPath("auswahlkriterien.kuerzel")  .description("Kuerzel der Stadt"),
		                      fieldWithPath("auswahlkriterien.stadtteil").description("Stadtteil in dem sich die Station befindet"),
		                      fieldWithPath("auswahlkriterien.standort") .description("Naehere Beschreibung des Standortes"),
		                      fieldWithPath("stadt")                     .description("Thes station's stadt"),
		                      fieldWithPath("kuerzel")                   .description("The station's Kuerzel"),
		                      fieldWithPath("stadtteil")                 .description("The station's stadtteil"),
		                      fieldWithPath("standort")                  .description("the station's standort"),
		                      fieldWithPath("_links.spezifikation")      .description("url um Spezifikationen zu holen"),
		                      fieldWithPath("_links.autos")              .description("url um die Liste der Autos zu holen"),
		                      fieldWithPath("_links.self")               .description("url auf diese Station selbst"));
	}

	private ResponseFieldsSnippet getResponseFieldForStationMitKuerzel() {
		return responseFields(fieldWithPath("stationsId")                .description("The station's ID"),
		                      fieldWithPath("fahrzeuge.[].id")           .description("Id fuer ein Fahrzeug"),
		                      fieldWithPath("fahrzeuge.[].kennung")      .description("GPS Latitude zur Station"),
		                      fieldWithPath("fahrzeuge.[].typ")          .description("GPS Latitude zur Station"),
		                      fieldWithPath("fahrzeuge.[].klasse")       .description("GPS Latitude zur Station"),
		                      fieldWithPath("fahrzeuge.[].details")      .description("GPS Latitude zur Station"),
 		                      fieldWithPath("fahrzeuge.[].bild.map.map") .description("Bilddaten der Station"),
		                      fieldWithPath("fahrzeuge.[].position.lng") .description("GPS Latitude zur Station"),
		                      fieldWithPath("fahrzeuge.[].position.lat") .description("GPS Longitude zur Station"),
		                      fieldWithPath("auswahlkriterien.stadt")    .description("auswahlkriterien.stadt"),
		                      fieldWithPath("auswahlkriterien.kuerzel")  .description("auswahlkriterien.kuerzel"),
		                      fieldWithPath("auswahlkriterien.stadtteil").description("auswahlkriterien.stadtteil"),
		                      fieldWithPath("auswahlkriterien.standort") .description("auswahlkriterien.standort"),
		                      fieldWithPath("spezifikation.beschreibung").description("Beschreibung zur Station"),
		                      fieldWithPath("spezifikation.position.lat").description("GPS Latitude der Station"),
		                      fieldWithPath("spezifikation.position.lng").description("GPS Longitude der Station"),
		                      fieldWithPath("spezifikation.oepnv")       .description("Erreichbarkeit mit Oepnv"));
	}

	private ResponseFieldsSnippet getResponseFieldForAutosAnStationMitId() {
		return responseFields(fieldWithPath("[].autoId")      .description("GPS Latitude zur Station"),
				              fieldWithPath("[].kennung")      .description("GPS Latitude zur Station"),
		                      fieldWithPath("[].typ")          .description("GPS Latitude zur Station"),
		                      fieldWithPath("[].klasse")       .description("GPS Latitude zur Station"),
		                      fieldWithPath("[].details")      .description("GPS Latitude zur Station"),
		                      fieldWithPath("[].position.lng") .description("GPS Latitude zur Station"),
		                      fieldWithPath("[].position.lat") .description("GPS Longitude zur Station"),
		                      fieldWithPath("[].links.[].rel") .description(""),
		                      fieldWithPath("[].links.[].href").description(""));
	}
	
	private ResponseFieldsSnippet getResponseFieldForSpezifikationAnStationMitId() {
		return responseFields(fieldWithPath("beschreibung").description("Beschreibung zur Station"),
		                      fieldWithPath("position.lat").description("GPS Latitude der Station"),
		                      fieldWithPath("position.lng").description("GPS Longitude der Station"),
		                      fieldWithPath("oepnv")       .description("Erreichbarkeit mit Oepnv"));
	}

	private Snippet getResponseFieldForAutoAnStationMitId() {
		return responseFields(fieldWithPath("map").description("Die Bitmap des Autos"));
	}
}
