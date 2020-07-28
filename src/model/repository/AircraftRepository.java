package model.repository;

import model.objects.Aircraft;

public interface AircraftRepository {

    Aircraft getAircraftById(long id);

}
