package com.omnetric.transmissionoutage.outage.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageRepository;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
public class OutageIntegrationTest {
	
	
    private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;
 
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders
				.webAppContextSetup(this.context)
				.addFilter(springSecurityFilterChain).build();
				//.apply(springSecurity()) 
				//.build();
	}
	
	
	private String obtainAccessToken(String username, String password) throws Exception {
		  
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", "my-trusted-client");
	    params.add("username", username);
	    params.add("password", password);
	 
	    ResultActions result 
	      = mvc.perform(post("/oauth/token")
	        .params(params)
	        .with(httpBasic("my-trusted-client","secret"))
	        .accept("application/json;charset=UTF-8"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"));
	 
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
	
	
	
	@Autowired
    private OutageRepository outageRepository;
	
	@Test
	public void testGetAllOutages()
	  throws Exception {
		
		     
		Outage outage = new com.omnetric.transmissionoutage.outage.Outage("mrid1","ACTIVE","DESC", new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()));		      
		outageRepository.save(outage);
	 
		String accessToken = obtainAccessToken("bill", "abc123");
	    		
		mvc.perform(get("/api/outages")
				   .header("Authorization", "Bearer " + accessToken)
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", hasSize(1)))
	               .andExpect(jsonPath("$[0].mrid", is(outage.getMRID())));
		
		
					
	}
	
	

}
