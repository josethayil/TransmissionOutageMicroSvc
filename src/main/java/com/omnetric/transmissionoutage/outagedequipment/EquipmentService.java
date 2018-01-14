package com.omnetric.transmissionoutage.outagedequipment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
	
	
	
	@Autowired
	private EquipmentRepository equipmentRepository;

	public List<Equipment> getAllEquipments(String outageId) {
		List<Equipment> equipments = new ArrayList<Equipment>();
		equipmentRepository.findByOutageMRID(outageId).forEach(equipments:: add);;
		return equipments;
	}

	public Equipment getEquipment(String mRID) {
		return equipmentRepository.findOne(mRID);
	}

	public void addEquipment(Equipment equipment) {
		equipmentRepository.save(equipment);

	}

	public void updateEquipment(Equipment equipment) {
		equipmentRepository.save(equipment);
	}

	public void deleteEquipment(String mRID) {
		equipmentRepository.delete(mRID);
	}

}
