package com.omnetric.transmissionoutage.outage.test;



import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageRepository;
import com.omnetric.transmissionoutage.outage.OutageService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OutageServiceTest {
	
	
	@TestConfiguration
    static class OutageServiceTestContextConfiguration {
  
        @Bean
        public OutageService outageService() {
            return new OutageService();
        }
    }

	@Autowired
    private OutageService outageService;
	
	@MockBean
	private OutageRepository outageRepository;
	
	@Before
	public void setUp() {
		
		Outage outage = new com.omnetric.transmissionoutage.outage.Outage("mrid1","ACTIVE","DESC", new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()));		      
		Mockito.when(outageRepository.findOne(outage.getMRID()))
	      .thenReturn(outage);
	}
	
	
	@Test
	public void testGetAnOutage() throws Exception {
		
		String mrid = "mrid1";
		Outage found = outageService.getOutage(mrid);
		  
		assertThat(found.getMRID())
		      .isEqualTo(mrid);
		
		
			   
	}
	

}
