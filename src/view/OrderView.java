package view;

import controller.OrderController;
import controller.SearchController;
import model.objects.Order;
import model.singletons.LoginSingleton;

import java.util.Scanner;
import java.util.Set;

public class OrderView {
    private OrderController orderController;
    public SababaFlightsProgram sababaFlightsProgram;

    public OrderView(SababaFlightsProgram referer) {
        this.orderController= new OrderController();
        this.sababaFlightsProgram = referer;
    }

    public void orderScreen(){
        String op;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Welcome to order page. What would you like to do? ");
            System.out.println("1: Search order by ID ");
            System.out.println("2: Search order by Agent code");
            System.out.println("3: Search order by Passenger ID");
            System.out.println("4: Search order by dates");
            System.out.println("9: Back");
            System.out.println("0: Logout");
            System.out.println("-1: Exit");

            op = scanner.nextLine();
            try {
                Set<Order> orders;
                switch (op) {
                    case "1":
                        System.out.println("Please enter order ID");
                        Order order = orderController.getOrderByID(Long.parseLong(scanner.nextLine()));
                        if (order != null)
                            this.editOrder(order);
                        break;
                    case "2":
                        System.out.println("Please enter agent code");
                        orders = orderController.getOrdersByAgentCode(Long.parseLong(scanner.nextLine()));
                        if (orders != null)
                            System.out.println(orders);
                        break;
                    case "3":
                        System.out.println("Please enter passenger ID");
                        orders = orderController.getOrdersByPassengerID(Long.parseLong(scanner.nextLine()));
                        if (orders != null)
                            System.out.println(orders);
                        break;
                    case "4":
                        System.out.println("Still to be implemented");
                        orderController.searchByDate();
                        break;
                    case "9":
                        sababaFlightsProgram.homePage();
                        break;
                    case "0":
                        LoginSingleton.getInstance().logOut();
                        sababaFlightsProgram.loginScreen();
                        break;
                    case "-1":
                        LoginSingleton.getInstance().logOut();
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (!op.equals("-1"));
    }

    public void editOrder(Order order) {
        String op;
        Scanner scanner = new Scanner((System.in));
        do {
            System.out.println("What would you like to edit?");
            System.out.println("1: View order");
            System.out.println("2: Add a passenger to order");
            System.out.println("3: Remove a passenger from order");
            System.out.println("4: " + (!order.isCanceled() ? "Deactivate " : "Reactivate ") + "Order");
            System.out.println("5: Delete order");
            System.out.println("9: Back");
            System.out.println("0: Logout");
            System.out.println("-1: Exit");
            op = scanner.nextLine();
            try {
                switch (op) {
                    case "1":
                        System.out.println(order.toString());
                        break;
                    case "2":
                        System.out.println("Please enter passenger's ID");
                        orderController.addPassengerToOrder(order, Long.parseLong(scanner.nextLine()));
                        break;
                    case "3":
                        if (order.getOtherPassengersIDs() == null) {
                            System.out.println("This order has no additional passengers - nothing to remove");
                            break;
                        }
                        System.out.println("Please enter passenger's ID");
                        orderController.removePassengerFromOrder(order, Long.parseLong(scanner.nextLine()));
                        break;
                    case "4":
                        if (order.isCanceled()) {
                            orderController.reActivateOrder(order);
                        } else {
                            orderController.cancelOrder(order);
                        }
                        break;
                    case "5":
                        orderController.deleteOrder(order);
                        this.orderScreen();
                        break;
                    case "9":
                        this.orderScreen();
                        break;
                    case "0":
                        LoginSingleton.getInstance().logOut();
                        sababaFlightsProgram.loginScreen();
                        break;
                    case "-1":
                        LoginSingleton.getInstance().logOut();
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!op.equals("-1"));
    }

}
