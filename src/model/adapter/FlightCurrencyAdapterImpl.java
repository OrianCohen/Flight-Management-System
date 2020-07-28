package model.adapter;

import model.objects.Flight;

public class FlightCurrencyAdapterImpl implements FlightCurrencyAdapter {
    private Flight flight;

    public FlightCurrencyAdapterImpl(Flight flight) {
        super();
        this.flight = flight;
    }

    @Override
    public double getFlightPrice() {
        return this.convertUSDtoILS(this.flight.getFlightPrice());
    }

    private double convertUSDtoILS(double usd) {
        return usd * 3.46;
    }
}
