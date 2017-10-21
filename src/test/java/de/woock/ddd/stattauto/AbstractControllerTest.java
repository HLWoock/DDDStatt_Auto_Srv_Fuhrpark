package de.woock.ddd.stattauto;

import java.io.IOException;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.woock.ddd.stattauto.fuhrpark.adapter.FuhrparkAdapter;

public abstract class AbstractControllerTest extends AbstractTest {

	@Autowired WebApplicationContext context;

	protected MockMvc mvc;
	
	@Before
	protected void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	protected void setUp(FuhrparkAdapter adapter) {
		mvc = MockMvcBuilders.standaloneSetup(adapter).build();
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}
}
