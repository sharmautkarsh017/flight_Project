package com.example.flight.controllers;

import com.example.flight.dto.ReservationRequest;
import com.example.flight.dto.ReservationUpdateRequest;
import com.example.flight.entities.Flight;
import com.example.flight.entities.Reservation;
import com.example.flight.repos.FlightRepository;
import com.example.flight.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    ReservationService reservationService;
    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap)
    {
        Flight flight= flightRepository.fl(flightId);

        modelMap.addAttribute("flight",flight);
        return "completeReservation";
    }
    @RequestMapping(value="/completeReservation",method= RequestMethod.POST)
    public String completeReservation(ReservationRequest request,ModelMap modelMap)
    {
        Reservation reservation=reservationService.bookFlight(request);
        modelMap.addAttribute("msg","Reservation created successfully and the id is"+reservation.getId());
        return "reservationConfirmation";

    }

}
