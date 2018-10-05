package com.apap.tutorial4.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		System.out.println("license number url:" + licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{licenseNumber}/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "licenseNumber") String licenseNumber,@PathVariable(value="id") long id, Model model) {
//		FlightModel flight = null;
//		for (FlightModel loopFlight: pilotService.getPilotDetailByLicenseNumber(licenseNumber).getPilotFlight()) {
//			if (loopFlight.getId() == id) {
//				System.out.println(loopFlight.getId() + " " + id);
//				flight = loopFlight;
//			}
//		}
//		System.out.println(flight.getId() +" "+ flight.getPilot().getName());
		flightService.deleteFlight(id);
		return "delete";
	
	}
	
	@RequestMapping(value = "/flight/update/{licenseNumber}/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "licenseNumber") String licenseNumber,@PathVariable(value="id") long id, Model model) {
		FlightModel flight= flightService.findFlight(id);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "flight/update", method = RequestMethod.POST)
	private String updateFlightDB(@RequestParam(value = "id") long id, @RequestParam(value = "flightNumber") String flightNumber, @RequestParam(value = "origin") String origin, @RequestParam(value = "destination") String destination, @RequestParam(value = "time") Date time) {
		flightService.updateFlight(id, flightNumber, origin, destination, time);
		return "update";
	}
	
	@RequestMapping(value = "flight/view")
	private String viewFlight(Model model) {
		model.addAttribute("listOfFlight", flightService.findAllFlight());
		return "viewFlight";
	}
	
	@RequestMapping(value = "flight/view/detail/{licenseNumber}")
	private String viewDetailFlight(Model model, @PathVariable(value = "licenseNumber") String licenseNumber) {
		model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber));
		return "viewDetailFlight";
	}
}
