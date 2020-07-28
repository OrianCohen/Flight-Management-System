package model.service;

import model.objects.Flight;
import model.objects.Order;
import model.objects.Passenger;
import model.repository.FlightRepositoryImpl;
import model.repository.OrderRepositoryImpl;
import model.singletons.FlightSingleton;
import model.singletons.OrderSingleton;
import model.singletons.PassengerSingleton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderService {

    private OrderRepositoryImpl orderRepository;
    private OrderSingleton orderSingletonInstance;
    private FlightRepositoryImpl flightRepository;
    private Set<Order> orders;

    public OrderService() {
        orderSingletonInstance = OrderSingleton.getInstance();
        orders = orderSingletonInstance.orderSet;
        orderRepository = new OrderRepositoryImpl();
        flightRepository=new FlightRepositoryImpl();
    }

    public Order getOrderByID(long orderID) {
        return orderRepository.getOrderById(orderID);
    }

    public Set<Order> getOrdersByAgentCode(long agentCode) {
        return orderRepository.getOrdersByAgentCode(agentCode);
    }

    public Set<Order> getOrdersByPassengerID(long passengerID) {
        return orderRepository.getOrdersByPassengerID(passengerID);
    }

    public boolean addPassengerToOrder(Order order, long passengerID) {
        return orderRepository.addPassenger(order, passengerID);
    }

    public boolean removePassengerByID(Order order, long passengerID) {
        return orderRepository.removePassengerByID(order, passengerID);
    }

    public boolean cancelOrder(Order order) {
        return orderRepository.deactivate(order);
    }

    public boolean reActivateOrder(Order order) {
        return orderRepository.activate(order);
    }

    public boolean deleteOrder(Order order) {
        return orderRepository.deleteOrder(order);
    }


    public void makeNewOrder(long agentCode,String creditCard, List<Long> flightToIDs, List<Long> flightFromIDs, int seatsCount,
                             Passenger ownerPassenger, List<Passenger> otherPassengers, boolean isMeal, boolean isSuitcase, int numOfPassegers) {

        // Process order data
        double totalPrice = 0;
        for (long flightID : flightToIDs)
            totalPrice += flightRepository.getFlightPriceById(flightID);
        if (flightFromIDs != null) {
            for (long flightId : flightFromIDs)
                totalPrice += flightRepository.getFlightPriceById(flightId);
        }
        if (isMeal)
            totalPrice += 25;
        if(isSuitcase)
            totalPrice += 25;
        totalPrice *= numOfPassegers;

        long ownerPassengerID = ownerPassenger.getId();
        List<Long> otherPassengersIDs = new ArrayList<>();
        for (Passenger otherPassenger : otherPassengers)
            otherPassengersIDs.add(otherPassenger.getId());

        // Decrease amount of remaining seats
        for (long flightID : flightToIDs)
            flightRepository.decreaseSeats(flightID, seatsCount);
        if (flightFromIDs != null) {
            for (long flightId : flightFromIDs)
                flightRepository.decreaseSeats(flightId, seatsCount);
        }

        // Create the order itself
        System.out.println(agentCode);
        System.out.println(creditCard);
        System.out.println(totalPrice);
        System.out.println(flightToIDs);
        System.out.println(flightFromIDs);
        System.out.println(ownerPassengerID);
        System.out.println(otherPassengersIDs);
        System.out.println(isMeal);
        System.out.println(isSuitcase);
        Order newOrder = new Order(agentCode, creditCard, totalPrice, flightToIDs, flightFromIDs, ownerPassengerID, otherPassengersIDs,isMeal,isSuitcase);
        OrderSingleton.getInstance().orderSet.add(newOrder);

        PassengerSingleton.getInstance().saveSet(PassengerSingleton.getInstance().passengerSet);
        FlightSingleton.getInstance().saveSet(FlightSingleton.getInstance().flightSet);
        OrderSingleton.getInstance().saveSet(OrderSingleton.getInstance().orderSet);

        System.out.println("Order created successfully");

    }

}