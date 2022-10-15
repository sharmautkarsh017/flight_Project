package com.example.flight.services;

import com.example.flight.dto.ReservationRequest;
import com.example.flight.entities.Flight;
import com.example.flight.entities.Passenger;
import com.example.flight.entities.Reservation;
import com.example.flight.repos.FlightRepository;
import com.example.flight.repos.PassengerRepository;
import com.example.flight.repos.ReservationRepository;
import com.example.flight.util.EmailUtil;
import com.example.flight.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;
    @Override
    public Reservation bookFlight(ReservationRequest request) {
        Long flightId=request.getFlightId();
        Flight flight=flightRepository.fle(flightId);
        Passenger passenger=new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());
        Passenger savedPassenger=passengerRepository.save(passenger);
        Reservation reservation=new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
         Reservation savedReservation=reservationRepository.save(reservation);

         String filePath ="/Users/utkarshsharma/Documents/reservations/reservation"+savedReservation.getId()+".pdf";
                 pdfGenerator.generateItinerary(reservation,filePath);
                 emailUtil.sendItinerary(passenger.getEmail(),filePath);

         return savedReservation;



    }
}
