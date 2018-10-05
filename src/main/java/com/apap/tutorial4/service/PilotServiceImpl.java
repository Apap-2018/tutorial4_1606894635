package com.apap.tutorial4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDb;

@Service("pilotService")
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired 
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
//		PilotModel pilot = null;
//		List<PilotModel> allPilot = pilotDb.findAll();
//		System.out.println(licenseNumber);
//		System.out.println(allPilot.get(0).getLicenseNumber());
//		System.out.println(allPilot.get(1).getLicenseNumber());
//		System.out.println(allPilot.size());
//		for (PilotModel i : allPilot) {
//			if (i.getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
//				pilot = i;
//			}
//		}
//		System.out.println(pilot);
//		return pilot;
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
	}
	
	public void updatePilot(String licenseNumber, String nama, int flyHour) {
		System.out.println(nama + " " + flyHour + " license : " + licenseNumber);
		pilotDb.findByLicenseNumber(licenseNumber).setName(nama);
		pilotDb.findByLicenseNumber(licenseNumber).setFlyHour(flyHour);
	}
	

}