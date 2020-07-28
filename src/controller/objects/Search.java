package controller.objects;

import java.time.LocalDate;

public class Search {
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String destination;
    private int numberOfPassengers;
    private boolean directFlight;


    public Search()
    {
    }
    public Search(LocalDate departureDate, LocalDate returnDate, String destination, int numberOfPassengers,boolean directFlight) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.destination = destination;
        this.numberOfPassengers = numberOfPassengers;
        this.directFlight = directFlight;
    }


    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public LocalDate getReturnDate() { return returnDate; }
    public String getDestination() {
        return destination;
    }
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public boolean isDirectFlight() {
        return directFlight;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
    public void setDirectFlight(boolean directFlight) {
        this.directFlight = directFlight;
    }

}
