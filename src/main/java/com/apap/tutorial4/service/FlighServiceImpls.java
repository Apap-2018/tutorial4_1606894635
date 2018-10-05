package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDb;

@Service("flightService")
@Transactional
public class FlighServiceImpls implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	public void deleteFlight(long id) {
//		flightDb.delete(flight);
		flightDb.deleteById(id);
	}
	
	public FlightModel findFlight(long id) {
		return flightDb.findById(id).get();	
	}
	
	public void updateFlight(long id, String flightNumber, String origin, String destination, Date time) {
		FlightModel flightModel = flightDb.findById(id).get();
		flightModel.setFlightNumber(flightNumber);
		flightModel.setOrigin(origin);
		flightModel.setDestination(destination);
		flightModel.setTime(time);
	}
	
	public List<FlightModel> findAllFlight(){
		return flightDb.findAll();
	}

}