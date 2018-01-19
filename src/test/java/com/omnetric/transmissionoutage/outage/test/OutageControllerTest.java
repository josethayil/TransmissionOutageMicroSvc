package com.omnetric.transmissionoutage.outage.test;

import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageController;
import com.omnetric.transmissionoutage.outage.OutageService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(OutageController.class)
@ContextConfiguration
@WebAppConfiguration
public class OutageControllerTest {
	
	
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
	
	
	@MockBean
    private OutageService service;
	
	@Test
	public void testGetAllOutages()
	  throws Exception {
	     
		Outage outage = new com.omnetric.transmissionoutage.outage.Outage("mrid1","ACTIVE","DESC", new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()));		      
		List<Outage> allOutages = singletonList(outage);
	 
	    given(service.getAllOutages()).willReturn(allOutages);
	    
		mvc.perform(get("/api/outages").with(user("my-trusted-client").password("secret"))
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andExpect(jsonPath("$[0].mrid", is(outage.getMRID())));
	    System.out.println("---------------  Controller Test Completed---------------");          
	}
	
	

}
