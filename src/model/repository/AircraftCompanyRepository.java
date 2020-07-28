package model.repository;

import model.objects.Aircraft;
import model.objects.AircraftCompany;

public interface AircraftCompanyRepository {

    boolean addAircraft(AircraftCompany aircraftCompany, int aircraftID);
    boolean removeAircraft(AircraftCompany aircraftCompany, int aircraftID);
    AircraftCompany getAircraftCompanyById(long id);

}
