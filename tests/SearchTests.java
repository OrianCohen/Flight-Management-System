import controller.SearchController;
import controller.objects.Search;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class SearchTests {


    @Test
    public void failSearchResultsOneDirection(){

        SearchController searchController = new SearchController();
        LocalDate departureDate = LocalDate.now().plusDays(3);
        LocalDate returnDate = LocalDate.now().plusDays(10);

        String destination = "Spain";
        int numberOfPassengers = 2;
        Search search = new Search(departureDate,returnDate, destination,numberOfPassengers, true);
        InputStream in = new ByteArrayInputStream("n".getBytes());
        System.setIn(in);
        assertNull(searchController.searchResultsOneDirection(search));
    }

    @Test
    public void searchResultsOneDirection(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = "02/08/2020";

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        SearchController searchController = new SearchController();

        LocalDate departureDate = localDate;
        LocalDate returnDate = localDate;

        String destination = "Spain";
        int numberOfPassengers = 2;
        Search search = new Search(departureDate,returnDate, destination,numberOfPassengers, true);
        InputStream in = new ByteArrayInputStream("n".getBytes());
        System.setIn(in);
        assertNull(searchController.searchResultsOneDirection(search));
    }

    @Test
    public void notValidDepartDateTest(){
        SearchController searchController = new SearchController();

        boolean depDateValid = searchController.validateDepartDate( LocalDate.of(2019,12,21));
        assertFalse(depDateValid);

    }
    @Test
    public void validReturnDepartDate() {
        SearchController searchController = new SearchController();
        LocalDate depDate = LocalDate.of(2020,9,1);
        LocalDate returnDate = LocalDate.of(2020,9,9);
        boolean datesValidation = searchController.validateReturnDepartDate(returnDate,depDate);
        assertTrue(datesValidation);
    }
    @Test
    public void errorReturnDepartDate() {
        SearchController searchController = new SearchController();
        LocalDate depDate = LocalDate.of(2020,9,1);
        LocalDate returnDate = LocalDate.of(2020,9,9);
        //there is a mistake in purpose
        boolean datesValidation = searchController.validateReturnDepartDate(depDate,returnDate);
        assertFalse(datesValidation);
    }

    @Test
    public void notValidDestinationTest() {
        SearchController searchController = new SearchController();
        boolean validDestination = searchController.validDestination("iran");
        assertFalse(validDestination);
    }

    @Test
    public void validDestinationTest() {
        SearchController searchController = new SearchController();
        boolean validDestination = searchController.validDestination("Israel");
        assertTrue(validDestination);
    }

    @Test
    public void numOfPassengerIsEmpty() {
        SearchController searchController = new SearchController();
        boolean zeroPassenger = searchController.validNumOfPassenger(0);
        assertFalse(zeroPassenger);
    }

}
