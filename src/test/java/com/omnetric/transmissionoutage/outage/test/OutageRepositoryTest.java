package com.omnetric.transmissionoutage.outage.test;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OutageRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private OutageRepository outageRepository;
	
	@Test
    public void testFindByName() {
    	       
        // given
		Outage outage = new com.omnetric.transmissionoutage.outage.Outage("mrid1","ACTIVE","DESC", new java.sql.Date(new java.util.Date().getTime()), new java.sql.Date(new java.util.Date().getTime()));		      
        entityManager.persist(outage);
        entityManager.flush();
     
        // when
        Outage found = outageRepository.findOne("mrid1");
        
     
        // then
        assertThat(found.getMRID())
          .isEqualTo(outage.getMRID());
        
    }
	

}
