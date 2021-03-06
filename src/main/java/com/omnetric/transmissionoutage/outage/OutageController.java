package com.omnetric.transmissionoutage.outage;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutageController {
	
	@Autowired
	private OutageService outageService;
	
	@RequestMapping("/api/outages")
	public List<Outage> getAllOutages() {
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!! CALLED- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");		
		return 	outageService.getAllOutages();
				
	}
	
	@RequestMapping("/api/outages/{mrid}")
	public Outage getAnOutage(@PathVariable String mrid) {
		return outageService.getOutage(mrid);
	}

	@RequestMapping(method = RequestMethod.POST, value="/api/outages")
	public void addOutage(@RequestBody Outage outage) {
		outageService.addOutage(outage);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/api/outages/{mrid}")
	public void updateOutage(@RequestBody Outage outage, @PathVariable String mrid) {
		outageService.updateOutage(outage, mrid);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/api/outages/{mrid}")
	public void deleteOutage(@PathVariable String mrid) {
		outageService.deleteOutage(mrid);
	}
}
