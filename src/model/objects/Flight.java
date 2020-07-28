package model.objects;

import model.adapter.FlightCurrencyAdapterImpl;
import model.singletons.FlightSingleton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight {

    static int staticFlightID;
    private int id;
    private String destination;
    private int aircraftID;
    private long departureAirportID, destinationAirportID;
    private String departureDate, arrivalDate;
    private double flightPrice;
    private boolean direct;
    private int seatsLeft;


    static { staticFlightID = 0; }

    public Flight() { staticFlightID++; }

    public Flight(int aircraftID, long departureAirportID, long destinationAirportID,
                  String departureDate, String arrivalDate, double flightPrice, boolean direct,String destination, int seatsLeft) {
        this.id = staticFlightID++;
        this.aircraftID = aircraftID;
        this.departureAirportID = departureAirportID;
        this.destinationAirportID = destinationAirportID;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.flightPrice = flightPrice;
        this.direct = direct;
        this.destination=destination;
        this.seatsLeft = seatsLeft;
    }

    public int getAircraftID() { return this.aircraftID; }
    public int getId() { return id; }
    public long getDepartureAirportID() { return this.departureAirportID; }
    public long getDestinationAirportID() { return this.destinationAirportID; }
    public String getDepartureDate() { return this.departureDate; }
    public String getArrivalDate() { return this.arrivalDate; }
    public double getFlightPrice() { return this.flightPrice; }
    public boolean isDirect() { return this.direct; }
    public String getDestination() { return destination; }
    public int getSeatsLeft() { return seatsLeft; }

    public void setAircraftID(int aircraftID) { this.aircraftID = aircraftID; }
    public void setDepartureAirport(long departureAirportID) { this.departureAirportID = departureAirportID; }
    public void setDestinationAirport(long destinationAirportID) { this.destinationAirportID = destinationAirportID; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    public void setFlightPrice(double flightPrice) { this.flightPrice = flightPrice; }
    public void setDirect(boolean direct) { this.direct = direct; }
    public void setId(int id) { this.id = id; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setSeatsLeft(int seatsLeft) { this.seatsLeft = seatsLeft; }

    public LocalDate convertToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter.parse(date);
        return (LocalDate.parse(departureDate, formatter));
    }
    public LocalDate convertToLocalDateArrival(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter.parse(date);
        return (LocalDate.parse(arrivalDate, formatter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        int currency = FlightSingleton.getInstance().getCurrency();
        if (currency == 1) {
            FlightCurrencyAdapterImpl currencyAdapter = new FlightCurrencyAdapterImpl(this);
            return
                    " Flight ID: " + id + "\n"+
                    " Seats left: " +seatsLeft +"\n"+
                    " Aircraft: " + aircraftID + "\n" +
                    " Destination: "+ destination +"\n"+
                    " Departure airport: " + departureAirportID + "\n" +
                    " Destination airport: " + destinationAirportID + "\n" +
                    " Departure date: " + departureDate + "\n" +
                    " Arrival date: " + arrivalDate + "\n" +
                    " Flight price: " + currencyAdapter.getFlightPrice() + "\n" +
                    " Is direct: " + direct + "\n" ;
        } else {
            return
                    " Flight ID: " + id + "\n"+
                    " Seats left: "+seatsLeft +"\n"+
                    " Aircraft: " + aircraftID + "\n" +
                    " Destination: "+ destination +"\n"+
                    " Departure airport: " + departureAirportID + "\n" +
                    " Destination airport: " + destinationAirportID + "\n" +
                    " Departure date: " + departureDate + "\n" +
                    " Arrival date: " + arrivalDate + "\n" +
                    " Flight price: " + flightPrice + "\n" +
                    " Is direct: " + direct + "\n" ;
        }
    }
}
