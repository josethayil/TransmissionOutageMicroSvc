package com.omnetric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.ApplicationContextAware;

import com.omnetric.transmissionoutage.outage.Outage;
import com.omnetric.transmissionoutage.outage.OutageService;
import com.omnetric.transmissionoutage.outagedequipment.Equipment;
import com.omnetric.transmissionoutage.outagedequipment.EquipmentService;

@SpringBootApplication
public class EquipmentOutageMicroSvc implements ApplicationContextAware {
	
	
	     private ApplicationContext ctx;
	 
	     public void setApplicationContext(ApplicationContext context) {
	    	 this.ctx=context;
	    	 
	    	 String arr[] =   ctx.getBeanNamesForType(OutageService.class);
	    	 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + arr.length);
	    	 OutageService svc =   (OutageService)ctx.getBean(OutageService.class);
	    	 java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	    	 Outage outage = new Outage("Otg123","ACTIVE","Active Outage", sqlDate, sqlDate);
	    	 svc.addOutage(outage);
	    	 
	    	 EquipmentService eqsvc =   (EquipmentService)ctx.getBean(EquipmentService.class);
	    	 Equipment eq = new Equipment("Eq123","Caribou Switch 65","ACLINESEG","Otg123" );
	    	 eqsvc.addEquipment(eq);
	    	 
	    	 
	    	 
	     }

	     public static void main(String[] args) {
	    	 SpringApplication.run(EquipmentOutageMicroSvc.class, args);
		
	    	 System.out.println("----------------------------------------------");		
	    	 System.out.println("------EquipmentOutageMicroSvc Main method ----------------------------------------");
	    	 System.out.println("----------------------------------------------");
		
	     }
}
