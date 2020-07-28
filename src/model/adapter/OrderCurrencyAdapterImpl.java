package model.adapter;

import model.objects.Order;

public class OrderCurrencyAdapterImpl implements OrderCurrencyAdapter {

    private Order order;

    public OrderCurrencyAdapterImpl(Order order) {
        this.order = order;
    }

    @Override
    public double getTotalCost() {
        return this.convertUSDtoILS(this.order.getTotalCost());
    }

    private double convertUSDtoILS(double usd) {
        return usd * 3.46;
    }

}
