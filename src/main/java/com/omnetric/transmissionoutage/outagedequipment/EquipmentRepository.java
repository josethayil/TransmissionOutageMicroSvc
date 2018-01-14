package com.omnetric.transmissionoutage.outagedequipment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, String> {
	
	public List<Equipment> findByName(String name);
	
	public List<Equipment> findByPsrtype(String psrtype);
	
	public List<Equipment> findByOutageMRID(String outageMrid);

}
