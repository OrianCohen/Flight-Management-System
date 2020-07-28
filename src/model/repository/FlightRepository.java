package model.repository;

import model.objects.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlightRepository {
     Map<Integer, List<Flight>> flightResultsRoundTrip(String destination, int numOfPassengers, LocalDate departD, LocalDate returnD, boolean direct);

     List<Flight> flightResultOneDirection(String destination, int numOfPassengers, LocalDate departD, boolean direct);

     Flight getFlightByID(long id);

     boolean deleteFlightFromFile(int id);

     void addFlightToFile(Flight flight);

     double getFlightPriceById(long flightID);

     void decreaseSeats(long flightID, int seatsAmount);

     boolean changeDepartureDate(long flightID, String newDate);

      boolean changeArrivalDate(long id, String newDate);
}