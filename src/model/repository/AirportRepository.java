package model.repository;

import model.objects.Airport;

public interface AirportRepository {

    Airport getAirportById(long id);

}
