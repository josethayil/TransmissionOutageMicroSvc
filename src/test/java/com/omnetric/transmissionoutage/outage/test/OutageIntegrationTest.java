package com.omnetric.transmissionoutage.outage.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageRepository;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OutageIntegrationTest {
	
	
    private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity()) 
				.build();
	}
	
	@Autowired
    private OutageRepository outageRepository;
	
	@Test
	public void testGetAllOutages()
	  throws Exception {
	     
		Outage outage = new com.omnetric.transmissionoutage.outage.Outage("mrid1","ACTIVE","DESC", new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()));		      
		outageRepository.save(outage);
	 
	    		
		mvc.perform(get("/api/outages").with(user("my-trusted-client").password("secret"))
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andExpect(jsonPath("$[0].mrid", is(outage.getMRID())));
	}
	
	

}
