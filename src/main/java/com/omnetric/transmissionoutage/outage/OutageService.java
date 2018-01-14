package com.omnetric.transmissionoutage.outage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutageService {

	@Autowired
	private OutageRepository outageRepository;

	public List<Outage> getAllOutages() {
		List<Outage> outages = new ArrayList<Outage>();
		outageRepository.findAll().forEach(outages::add);
		return outages;
	}

	public Outage getOutage(String mrid) {
		return outageRepository.findOne(mrid);
	}

	public void addOutage(Outage outage) {
		outageRepository.save(outage);

	}

	public void updateOutage(Outage outage, String mrid) {
		outageRepository.save(outage);
	}

	public void deleteOutage(String mrid) {
		// outagesList.removeIf(t -> t.getId().equalsIgnoreCase(id));
		outageRepository.delete(mrid);
	}

}
