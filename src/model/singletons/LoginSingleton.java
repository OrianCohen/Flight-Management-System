package model.singletons;

import model.objects.Agent;
import model.objects.Aircraft;
import model.objects.Flight;
import model.objects.Passenger;

public class LoginSingleton {

    private static LoginSingleton loginSingletonInstance = null;
    public boolean isLoggedIn;
    public Agent loggedInAgent;

    private LoginSingleton() {
        this.isLoggedIn = false;
    }

    public static LoginSingleton getInstance() {
        if (loginSingletonInstance == null)
            loginSingletonInstance = new LoginSingleton();

        return loginSingletonInstance;
    }

    public Agent getLoggedInAgent() {
        return loggedInAgent;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void logIn(Agent agent){
        this.loggedInAgent = agent;
        this.isLoggedIn = true;
    }

    public void logOut() {
        this.loggedInAgent = null;
        this.isLoggedIn = false;
        this.saveData();
        System.out.println("Logged out successfully.");
    }

    private void saveData() {
        AgentSingleton.getInstance().saveSet(AgentSingleton.getInstance().agentSet);
        AircraftCompanySingleton.getInstance().saveSet(AircraftCompanySingleton.getInstance().aircraftCompanySet);
        AircraftSingleton.getInstance().saveSet(AircraftSingleton.getInstance().aircraftSet);
        AirportSingleton.getInstance().saveSet(AirportSingleton.getInstance().airportSet);
        FlightSingleton.getInstance().saveSet(FlightSingleton.getInstance().flightSet);
        OrderSingleton.getInstance().saveSet(OrderSingleton.getInstance().orderSet);
        PassengerSingleton.getInstance().saveSet(PassengerSingleton.getInstance().passengerSet);
    }

}
