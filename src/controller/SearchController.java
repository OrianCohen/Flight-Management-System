/*
 * 1. Make Singleton
 * 2. Can contain as many services as it needs
 */

package controller;

import controller.objects.Search;
import model.objects.Airport;
import model.objects.Flight;
import model.service.SearchService;
import model.singletons.AirportSingleton;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchController {

    private SearchService searchService = new SearchService();

    public boolean validDestination(String destination) {
        Set<Airport> airportSet = AirportSingleton.getInstance().airportSet;
        for (Airport airport : airportSet) {
            if (airport.getCountry().toLowerCase().equals(destination.toLowerCase()))
                return true;
        }
        System.out.println("Destination not found");
        return false;
    }

    //Validate if number of passenger is between 1 to 10
    public boolean validNumOfPassenger(int passengers) {
        return passengers >= 1 && passengers <= 10;
    }

    //Validate if depart date year is from year.now until year.now+1, id depart date mount is not smaller then mount now, if depart date day is not before day.now
    public boolean validateDepartDate(LocalDate departDate) {
        int dt = departDate.getYear();
        if (departDate.isBefore(LocalDate.now())) {
            System.out.println("Depart date must be later than current date!");
            return false;
        }
        if(dt> (Calendar.getInstance().get(Calendar.YEAR)+1)){
         System.out.println("Depart date must be within one year ");
            return  false;
        }
        return true;
    }

    //Validate if return date is not before depart date, return date year is not smaller then year now or bigger then year +1
    public boolean validateReturnDepartDate(LocalDate returnDate,LocalDate departDate){
        int dt = returnDate.getYear();
        if(dt> (Calendar.getInstance().get(Calendar.YEAR)+1)){
            System.out.println("Return date must be within one year ");
            return  false;
        }
        if(returnDate.isBefore(departDate)|| departDate.isAfter(returnDate)){
            System.out.println("Return date must be after Departure date!");
            return false;
        }
        return true;
    }

    public List<Long> searchResultsOneDirection(Search search)
    {
        List<Long> res;
        res = searchService.validateSearchOneDirection(search);
        return res;
    }

    public List<Long> searchResultsRoundTrip(Search search)  {
        List<Long> res;
        res=searchService.validateSearchRoundTrip(search);
        return res;
    }

}

