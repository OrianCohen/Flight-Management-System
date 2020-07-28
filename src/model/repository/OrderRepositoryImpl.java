package model.repository;

import model.objects.Order;
import model.objects.Passenger;
import model.singletons.OrderSingleton;

import java.util.*;

public class OrderRepositoryImpl implements OrderRepository {

    private Map<Integer, Order> allOrders;
    private Set<Order> orderSet;

    public OrderRepositoryImpl(){
        orderSet = OrderSingleton.getInstance().orderSet;
    }

    @Override
    public Order getOrderById(long id) {
        for (Order order : this.orderSet) {
            if (order.getId() == id)
                return order;
        }
        System.out.println("Could not find an order with this id");
        return null;
    }

    @Override
    public Set<Order> getOrdersByAgentCode(long agentCode) {
        Set<Order> ordersByAgent = new HashSet<>();
        for (Order order : this.orderSet) {
            if (order.getAgentCode() == agentCode) {
                ordersByAgent.add(order);
            }
        }
        if (ordersByAgent.size() == 0)
            System.out.println("Could not find orders created by provided agent");

        return ordersByAgent;
    }

    @Override
    public Set<Order> getOrdersByPassengerID(long passengerID) {
        Set<Order> ordersByPassenger = new HashSet<>();
        for (Order order : this.orderSet) {
            if (order.getOwnerPassengerID() == passengerID) {
                ordersByPassenger.add(order);
            }
        }
        if (ordersByPassenger.size() == 0)
            System.out.println("Could not find orders associated with provided passenger");

        return ordersByPassenger;
    }

    @Override
    public boolean addPassenger(Order order, long passengerID) {
        if (order.getOwnerPassengerID() == passengerID) {
            System.out.println("The provided passenger is the order's owner");
            return false;
        } else {
            if (order.getOtherPassengersIDs() == null) {
                List<Long> passengersIDs = new ArrayList<>();
                passengersIDs.add(passengerID);
                order.setOtherPassengersIDs(passengersIDs);
                System.out.println("Successfully added passenger to order");
                return true;
            } else {
                for (long pID : order.getOtherPassengersIDs()) {
                    if (pID == passengerID) {
                        System.out.println("Passenger is already in order");
                        return false;
                    }
                }
                List<Long> newPassengersList = order.getOtherPassengersIDs();
                newPassengersList.add(passengerID);
                order.setOtherPassengersIDs(newPassengersList);
                return true;
            }
        }
    }

    @Override
    public boolean addPassenger(Order order, Passenger newPassenger) {
        return this.addPassenger(order, newPassenger.getId());
    }

    @Override
    public boolean removePassengerByID(Order order, long passengerToRemove) {
        boolean successfullyRemoved = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Sure? (Y / N)");
        String userInput = input.nextLine();
        if (userInput.equals("Y") || userInput.equals("y")) {
            List<Long> p = new ArrayList<>();
            for (Long passengerIDs : order.getOtherPassengersIDs()) {
                if (passengerIDs == passengerToRemove) {
                    successfullyRemoved = true;
                    continue;
                }
                p.add(passengerIDs);
            }
            if (successfullyRemoved) {
                order.setOtherPassengersIDs(p);
                System.out.println("Successfully removed passenger from order");
                return true;
            } else {
                System.out.println("Could not find a passenger with the given ID in provided order");
                return false;
            }
        }
        System.out.println("Done nothing");
        return false;
    }

    @Override
    public boolean deleteOrder(Order orderToDelete) {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to delete the order? (Y / N)");
        String userInput = input.nextLine();
        if (userInput.equals("Y") || userInput.equals("y")) {
            Iterator<Order> iterator = orderSet.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                if (order.getId() == orderToDelete.getId()) {
                    iterator.remove();
                    System.out.println("Order has been deleted");
                    return true;
                }
            }
            System.out.println("There is no order with the provided ID");
            return false;
        }
        System.out.println("Done nothing");
        return false;
    }

    @Override
    public boolean deactivate(Order orderToCancel) {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to cancel the order? (Y / N)");
        String userInput = input.nextLine();
        if (userInput.equals("Y") || userInput.equals("y")) {
            for (Order order : this.orderSet) {
                if (order.getId() == orderToCancel.getId()) {
                    order.setCanceled(true);
                    System.out.println("Order has been deactivated");
                    return true;
                }
            }
            System.out.println("There is no order with the provided ID");
            return false;
        }
        System.out.println("Done nothing");
        return false;
    }

    @Override
    public boolean activate(Order orderToReopen) {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to re-open the order? (Y / N)");
        String userInput = input.nextLine();
        if (userInput.equals("Y") || userInput.equals("y")) {
            for (Order order : this.orderSet) {
                if (order.getId() == orderToReopen.getId()) {
                    order.setCanceled(false);
                    System.out.println("Order has been canceled");
                    return true;
                }
            }
            System.out.println("There is no order with the provided ID");
            return false;
        }
        System.out.println("Done nothing");
        return false;
    }
}
