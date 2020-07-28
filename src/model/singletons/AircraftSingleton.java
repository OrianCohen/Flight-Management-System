package model.singletons;

import model.objects.Aircraft;
import java.util.Set;

public class AircraftSingleton extends Singleton<Aircraft> {

    private static AircraftSingleton aircraftSingletonInstance = null;
    public Set<Aircraft> aircraftSet;

    private AircraftSingleton() {
        super("src/data/aircrafts.json");
        aircraftSet = this.read(Aircraft.class);
    }

    public static AircraftSingleton getInstance() {
        if (aircraftSingletonInstance == null)
            aircraftSingletonInstance = new AircraftSingleton();

        return aircraftSingletonInstance;
    }

}
