package model.repository;

import model.objects.Order;
import model.objects.Passenger;

import java.util.Set;

public interface OrderRepository {

    Order getOrderById(long id);
    Set<Order> getOrdersByAgentCode(long agentCode);
    Set<Order> getOrdersByPassengerID(long passengerID);
    boolean addPassenger(Order order, long passengerID);
    boolean addPassenger(Order order, Passenger newPassenger);
    boolean removePassengerByID(Order order, long passengerToRemove);
    boolean deleteOrder(Order orderToDelete);
    boolean deactivate(Order orderToCancel);
    boolean activate(Order orderToReopen);

}
