package com.omnetric.transmissionoutage.outagedequipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omnetric.transmissionoutage.outage.Outage;

@RestController
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping("/outages/{mrid}/equipments")
	public List<Equipment> getAllEquipments(@PathVariable String mrid) {
		return equipmentService.getAllEquipments(mrid);
				
	}
	
	@RequestMapping("/outages/{id}/courses/{mrid}")
	public Equipment getAnEquipment(@PathVariable String mrid) {
		return equipmentService.getEquipment(mrid);
	}

	@RequestMapping(method = RequestMethod.POST, value="/outages/{mRID}/equipments")
	public void addEquipment(@RequestBody Equipment equipment, @PathVariable String mRID) {
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		equipment.setOutage(new Outage(mRID, "", "",sqlDate,sqlDate));
		equipmentService.addEquipment(equipment);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/outages/{outageId}/equipments/{mRID}")
	public void updateEquipment(@RequestBody Equipment equipment, @PathVariable String outageId, @PathVariable String mRID) {
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		equipment.setOutage(new Outage(outageId, "", "",sqlDate,sqlDate));
		equipmentService.updateEquipment(equipment);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/outages/{outageId}/equipments/{mRID}")
	public void deleteEquipment(@PathVariable String mRID) {
		equipmentService.deleteEquipment(mRID);
	}
}
