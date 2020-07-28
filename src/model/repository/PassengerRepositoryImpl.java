package model.repository;

import model.objects.Passenger;
import model.singletons.PassengerSingleton;

import java.util.Set;

public class PassengerRepositoryImpl implements PassengerRepository {

    private Set<Passenger> passengerSet;
    private PassengerSingleton passengerSingleton;

    public PassengerRepositoryImpl() { }

    public Passenger getPassengerByID(long passengerID) {
        passengerSet=PassengerSingleton.getInstance().passengerSet;
        for (Passenger passenger : this.passengerSet) {
            if (passenger.getId() == passengerID) {
                System.out.println("Passenger already exists");
                return passenger;
            }
        }
        return null;
    }


}
