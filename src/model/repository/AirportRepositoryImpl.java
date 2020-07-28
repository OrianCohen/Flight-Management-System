package model.repository;

import model.objects.Airport;
import model.singletons.AirportSingleton;

import java.util.Set;

public class AirportRepositoryImpl implements AirportRepository {

    private Set<Airport> airportSet;

    public AirportRepositoryImpl() {
        this.airportSet = AirportSingleton.getInstance().airportSet;
    }

    public Airport getAirportById(long id) {
        for (Airport airport : this.airportSet) {
            if (airport.getId() == id) {
                return airport;
            }
        }
        System.out.println("Could not find an airport with this id");
        return null;
    }

}
