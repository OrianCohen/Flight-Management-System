package model.objects;

import model.adapter.OrderCurrencyAdapterImpl;
import model.repository.FlightRepository;
import model.repository.FlightRepositoryImpl;
import model.repository.OrderRepositoryImpl;
import model.singletons.FlightSingleton;

import java.util.List;
import java.util.Set;

public class Order {

    static int ordersCount;
    long agentCode;
    boolean roundTrip;
    long id;
    double totalCost;
    String creditCard;
    List<Long> flightToIDs;
    List<Long> flightFromIDs;
    long ownerPassengerID;
    List<Long> otherPassengersIDs;
    boolean canceled, isMeals,isSuitcase;

    static { ordersCount = 0; }

    public Order() { ordersCount++; }

    public Order(long agentCode, String creditCard, double totalCost, List<Long> flightToIDs, List<Long> flightFromIDs,
                 long ownerPassengersID, List<Long> otherPassengersIDs,boolean isMeals, boolean isSuitcase) {
        this.id = ordersCount++;
        this.agentCode = agentCode;
        this.creditCard = creditCard;
        this.totalCost = totalCost;
        this.flightToIDs = flightToIDs;
        this.flightFromIDs = flightFromIDs;
        if (flightToIDs.size() > 0) {
            if (flightFromIDs != null && flightFromIDs.size() != 0)
                this.roundTrip = true;
        }
        this.ownerPassengerID = ownerPassengersID;
        this.otherPassengersIDs = otherPassengersIDs;
        this.isSuitcase=isSuitcase;
        this.isMeals=isMeals;
    }

    public Order(long agentCode, int flightCompanyID, boolean roundTrip, double totalCost, String creditCard,
                 List<Long> flightToIDs, List<Long> flightFromIDs, int ownerPassengerID, List<Long> otherPassengersIDs) {
        this.agentCode = agentCode;
        this.roundTrip = roundTrip;
        this.id = ordersCount++;
        this.totalCost = totalCost;
        this.creditCard = creditCard;
        this.flightToIDs = flightToIDs;
        this.flightFromIDs = flightFromIDs;
        this.ownerPassengerID = ownerPassengerID;
        this.otherPassengersIDs = otherPassengersIDs;
        this.canceled = false;
    }

    // Getters
    public long getAgentCode() { return this.agentCode; }
    public boolean isRoundTrip() { return this.roundTrip; }
    public long getId() { return this.id; }
    public double getTotalCost() { return this.totalCost; }
    public String getCreditCard() { return this.creditCard; }
    public List<Long> getFlightTo() { return this.flightToIDs; }
    public List<Long> getFlightFrom() { return this.flightFromIDs; }
    public long getOwnerPassengerID() { return this.ownerPassengerID; }
    public List<Long> getOtherPassengersIDs() { return this.otherPassengersIDs; }
    public boolean isCanceled() { return canceled; }
    public boolean isMeals() { return isMeals; }
    public boolean isSuitcase() { return isSuitcase; }

    // Setters
    public void setAgentCode(int agentCode) { this.agentCode = agentCode; }
    public void setRoundTrip(boolean roundTrip) { this.roundTrip = roundTrip; }
    public void setId(long id) { this.id = id; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
    public void setFlightTo(List<Long> flightToIDs) { this.flightToIDs = flightToIDs; }
    public void setFlightFrom(List<Long> flightFromIDs) { this.flightFromIDs = flightFromIDs; }
    public void setOwnerPassengerID(int ownerPassengerID) { this.ownerPassengerID = ownerPassengerID; }
    public void setOtherPassengersIDs(List<Long> otherPassengersIDs) { this.otherPassengersIDs = otherPassengersIDs; }
    public void setCanceled(boolean isCanceled) { this.canceled = isCanceled; }
    public void setSuitcase(boolean suitcase) { isSuitcase = suitcase; }
    public void setMeals(boolean meals) { isMeals = meals; }

    public boolean addPassenger(Passenger newPassenger) {
        return new OrderRepositoryImpl().addPassenger(this, newPassenger);
    }

    public boolean removePassengerByID(long id) {
        return new OrderRepositoryImpl().removePassengerByID(this, id);
    }

    public void deleteOrder() {
        new OrderRepositoryImpl().deleteOrder(this);
    }

    public void cancelOrder() {
        new OrderRepositoryImpl().deactivate(this);
    }

    @Override
    public String toString() {
        int currency = FlightSingleton.getInstance().getCurrency();
        if (currency == 1) {
            OrderCurrencyAdapterImpl orderCurrencyAdapter = new OrderCurrencyAdapterImpl(this);
            return  " Order details" +"\n" +
                    " Round trip: " + roundTrip +"\n" +
                    " Order ID: " + id +"\n" +
                    " Total cost: " + orderCurrencyAdapter.getTotalCost() +
                    " Credit card number: " + creditCard +"\n" +
                    " Departure flight ID: "+ flightToIDs +"\n" +
                    " Return flight ID: " + flightFromIDs +"\n" +
                    " Main passenger ID: " + ownerPassengerID +"\n" +
                    " Other passengers ID's: " + otherPassengersIDs +"\n" +
                    " Meal:  " + isMeals +"\n" +
                    " Suitcase: " + isSuitcase;

        }
        return  " Order details" +"\n" +
                " Round trip: " + roundTrip +"\n" +
                " Order ID: " + id +"\n" +
                " Total cost: " + totalCost +"\n" +
                " Credit card number: " + creditCard +"\n" +
                " Departure flight ID: "+ flightToIDs +"\n" +
                " Return flight ID: " + flightFromIDs +"\n" +
                " Main passenger ID: " + ownerPassengerID +"\n" +
                " Other passengers ID's: " + otherPassengersIDs +"\n" +
                " Meal:  " + isMeals +"\n" +
                " Suitcase: " + isSuitcase;
    }
}
