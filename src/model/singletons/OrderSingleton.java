package model.singletons;

import model.objects.Order;
import java.util.HashSet;
import java.util.Set;

public class OrderSingleton extends Singleton<Order> {

    private static OrderSingleton orderSingletonInstance = null;
    public Set<Order> orderSet;

    private OrderSingleton() {
        super("src/data/orders.json");
        orderSet = this.read(Order.class);
    }

    public static OrderSingleton getInstance() {
        if (orderSingletonInstance == null)
            orderSingletonInstance = new OrderSingleton();

        return orderSingletonInstance;
    }

}
