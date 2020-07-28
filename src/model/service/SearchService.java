package model.service;

import controller.objects.Search;
import model.objects.Flight;
import model.repository.FlightRepository;
import model.repository.FlightRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchService {
    private FlightRepository flightRepo= new FlightRepositoryImpl();
    private OrderService orderService=new OrderService();

    public List<Long> validateSearchRoundTrip(Search search) {
    Map<Integer, List<Flight>> mappingFlights;
    mappingFlights = flightRepo.flightResultsRoundTrip(search.getDestination(), search.getNumberOfPassengers(), search.getDepartureDate(), search.getReturnDate(),search.isDirectFlight());
    if (mappingFlights == null) {
        System.out.println("No flights suits your search, good-bye! " + "\n");
        return null;
    }
    else {
       return showResultRoundTrip(mappingFlights);
       }
    }
    public List<Long> validateSearchOneDirection(Search search) {
        List<Flight> flights;
        flights = flightRepo.flightResultOneDirection(search.getDestination(), search.getNumberOfPassengers(), search.getDepartureDate(),search.isDirectFlight());
        if (flights == null) {
            System.out.println("No flights suits your search, good-bye! " + "\n");
            return null;
        }
        else {
           return showResultOneWay(flights);
        }
    }

public List<Long> showResultOneWay(List<Flight> oneWay) {
    int i=1;
    String op;
    int idDeparture;
    List<Long> flightTo = new ArrayList<>();
    Scanner scanner=new Scanner(System.in);
    System.out.println("\n" + "Departure flights result" + "\n");
    for(Flight flights: oneWay)
    {
        System.out.println("("+i+") "+ oneWay.toString());
        i++;
    }
    System.out.println("Would you like to order? Y/N");
    op=scanner.nextLine();
    if(op.toLowerCase().equals("y"))
    {
        System.out.println("Please enter the departure flight ID you would like to book");
        flightTo.add(scanner.nextLong());
        return flightTo;
    }
    else
        return null;
}

    public List<Long> showResultRoundTrip(Map<Integer,List<Flight>> mappingResults){
        int i=1, j=1;
        List<Long> flightIDs = new ArrayList<>();

        String op;
        Scanner scanner =new Scanner(System.in);
        for(Map.Entry<Integer,List<Flight>> e :mappingResults.entrySet())
        {
            System.out.println("\n" + "Departure flights result:" + "\n\n"
                    +"("+i+")"+flightRepo.getFlightByID(e.getKey()).toString());
            System.out.println("Would you like to order? Y/N");
            op=scanner.nextLine();
            if(op.toLowerCase().equals("y"))
            {
                System.out.println("Please enter the departure flight ID you would like to book");
                flightIDs.add(scanner.nextLong());
            }
            else {
                return null;
            }
            System.out.println("\n"+"Return flights result: " +"\n");
            for(Flight flights: e.getValue())
            {
                System.out.println("("+i+"."+j+")" + flights.toString()+"\n");
                j++;
            }
            i++;
            j=1;
            System.out.println("Please enter the return flight ID you would like to book");
            flightIDs.add(scanner.nextLong());
        }
        return flightIDs;
    }
}
