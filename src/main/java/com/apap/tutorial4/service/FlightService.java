package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.FlightModel;

@Service
public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(long id);
	FlightModel findFlight(long id);
	void updateFlight(long id, String flightNumber, String origin, String destination, Date time);
	List<FlightModel> findAllFlight();

}
