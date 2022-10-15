package com.example.flight.repos;

import com.example.flight.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
    List<Flight> findFlights(String departureCity, String arrivalCity, Date dateOfDeparture);
    @Query("from Flight where id=:fli")
    Flight fl(Long fli);
    @Query("from Flight where id=:fli")
    Flight fle(Long fli);

}
