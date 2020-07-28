package model.repository;

import model.objects.Aircraft;
import model.singletons.AircraftSingleton;

import java.util.Set;

public class AircraftRepositoryImpl implements AircraftRepository{

    private Set<Aircraft> aircraftSet;

    public AircraftRepositoryImpl() {
        this.aircraftSet = AircraftSingleton.getInstance().aircraftSet;
    }

    @Override
    public Aircraft getAircraftById(long id) {
        for (Aircraft aircraft : this.aircraftSet) {
            if (aircraft.getId() == id) {
                return aircraft;
            }
        }
        System.out.println("Could not find an aircraft with this id");
        return null;
    }

}
