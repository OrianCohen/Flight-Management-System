package model.objects;

import model.repository.AircraftCompanyRepositoryImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AircraftCompany {

    static int aircraftCompaniesCount;
    private int id;
    private String companyName;
    private String companyClass;                // Regular flight, low-cost, etc.
    private Set<Integer> aircrafts;

    static { aircraftCompaniesCount = 0; }

    public AircraftCompany() {
        this.id =   aircraftCompaniesCount++;
    }

    public AircraftCompany(String companyName) {
        this.id = aircraftCompaniesCount++;
        this.companyName = companyName;
        this.companyClass = "regular";
        this.aircrafts = new HashSet<>();
    }

    public AircraftCompany(String companyName, String companyClass) {
        this.id = aircraftCompaniesCount++;
        this.companyName = companyName;
        this.companyClass = companyClass;
        this.aircrafts = new HashSet<>();
    }

    public int getId() { return this.id ;}
    public String getCompanyName() { return this.companyName; }
    public String getCompanyClass() { return this.companyClass; }
    public Set<Integer> getAircrafts() { return aircrafts; }

    public void setId(int id) { this.id = id; }
    public void setAircrafts(Set<Integer> aircrafts) { this.aircrafts = aircrafts; }

    public boolean addAircraftByID(int id) {
        AircraftCompanyRepositoryImpl aircraftCompanyRepository = new AircraftCompanyRepositoryImpl();
        return aircraftCompanyRepository.addAircraft(this, id);
    }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCompanyClass(String companyClass) { this.companyClass = companyClass; }

    @Override
    public String toString() {
        return
                "Aircraft ID: " + id + "\n" +
                "Company Name='" + companyName + "\n" +
                "Company Class='" + companyClass + "\n" +
                "Aircrafts: " + aircrafts;
    }

}
