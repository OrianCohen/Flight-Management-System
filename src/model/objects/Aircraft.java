package model.objects;

public class Aircraft {
    private static int aircraftsCount;
    private int id;
    private int aircraftCompanyID;
    private int seatsCount;
    private String model;

    static {
        aircraftsCount = 0;
    }

    public Aircraft() {
        aircraftsCount++;
    }

    public Aircraft(int aircraftCompanyID, int seatsCount, String model) {
        this.id = aircraftsCount++;
        this.aircraftCompanyID = aircraftCompanyID;
        this.seatsCount = seatsCount;
        this.model = model;
    }

    public int getId() { return this.id; }
    public int getAircraftCompanyID() { return this.aircraftCompanyID; }
    public int getSeatsCount() { return this.seatsCount; }
    public String getModel() { return this.model; }

    public void setAircraftCompanyID(int aircraftCompanyID) { this.aircraftCompanyID = aircraftCompanyID; }
    public void setSeatsCount(int seatsCount) { this.seatsCount = seatsCount; }
    public void setModel(String model) { this.model = model; }

    /* TODO: Implement getAircraftByID(List<Aircraft> aircraftList, int id) in Repository */
    /* TODO: Implement getAircraftByCompanyName(List<Aircraft> aircraftList, String companyName) in Repository */
    /* TODO: Implement addFlight(Flight flight, int id) in Repository */
    /* TODO: Implement removeFlight(Flight flight) in Repository */

    @Override
    public String toString() {
        return
                " Aircraft ID: " + id + " ," +
                " Aircraft Company: " + aircraftCompanyID + " ," +
                " Seats Count: " + seatsCount + " ," +
                " Model: " + model;
    }

}
