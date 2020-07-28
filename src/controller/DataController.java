package controller;

import model.objects.Flight;
import model.service.DataService;

public class DataController {

    private DataService dataService;

   public DataController(){
        this.dataService=new DataService();
    }

    public boolean removeAgent(long id)
    {
        return dataService.removeAgent(id);
    }

    public boolean changePermissionLevelAgent(long id){
       return dataService.changePermissionLevelAgent(id);
   }
   public boolean changeEmailAddressAgent(long id,String email){return dataService.changeEmailAddressAgent(id,email);}
   public void addAFlight(Flight flight){ dataService.addAFlight(flight);}
   public boolean removeAFlight(int flightID){return dataService.removeAFlight(flightID);}
   public boolean changeDepartureDate(long flightID,String newDate){return dataService.changeDepartureDate(flightID,newDate);}
    public boolean changeArrivalDate(long flightID,String newDate){return dataService.changeArrivalDate(flightID,newDate);}

}
