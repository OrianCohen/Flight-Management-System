package model.service;

import controller.DataController;
import model.objects.Flight;
import model.repository.AgentRepository;
import model.repository.AgentRepositoryImpl;
import model.repository.FlightRepository;
import model.repository.FlightRepositoryImpl;

public class DataService {
    private AgentRepository agentRepository;
    private FlightRepository flightRepository;

    public DataService(){
        this.agentRepository=new AgentRepositoryImpl();
        this.flightRepository=new FlightRepositoryImpl();
    }
    public boolean removeAgent(long id)
    {
        return agentRepository.removeAgent(id);
    }
    public boolean changePermissionLevelAgent(long id){
        return agentRepository.changePermissionLevel(id);
    }
    public boolean changeEmailAddressAgent(long id,String email){return agentRepository.changeEmailAddress(id,email);}
    public boolean removeAFlight(int flightID ){return flightRepository.deleteFlightFromFile(flightID);}
    public void addAFlight(Flight flight){ flightRepository.addFlightToFile(flight);}
    public boolean changeDepartureDate(long flightID, String newDate){return flightRepository.changeDepartureDate(flightID,newDate);}
    public boolean changeArrivalDate(long flightID, String newDate){return flightRepository.changeArrivalDate(flightID,newDate);}

}
