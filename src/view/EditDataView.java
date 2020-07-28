package view;

import controller.AuthenticationController;
import controller.DataController;
import model.objects.AircraftCompany;
import model.objects.Flight;
import model.objects.Passenger;
import model.singletons.*;

import java.util.Scanner;

public class EditDataView {

    private SababaFlightsProgram sababaFlightsProgram;
    private DataController dataController;
    private AuthenticationController authenticationController;

    EditDataView(SababaFlightsProgram referer) {
        this.sababaFlightsProgram = referer;
        this.dataController = new DataController();
    }

    public void showData() {
        String op;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the relevant file to edit: ");
        System.out.println("1: Agents ");
        System.out.println("2: Flights ");
        System.out.println("-1: Return to home page");
        op = scanner.nextLine();
        switch (op) {
            case "1":
                this.manipulateAgents();
                break;
            case "2":
                this.manipulateFlights();
                break;
            case "-1":
                sababaFlightsProgram.homePage();
                break;
            default:
                sababaFlightsProgram.homePage();
        }
    }

    private void manipulateAgents() {
        String op;
        long id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the action you would like to perform: ");
        System.out.println("1: Remove an agent ");
        System.out.println("2: Change agent's permission level ");
        System.out.println("3: Change agent's email address");
        System.out.println("-1: Return to home page");
        op = scanner.nextLine();
        switch (op) {
            case "1":
                System.out.println("Enter the ID of the agent you would like to remove from the system");
                id = scanner.nextLong();
                if (dataController.removeAgent(id))
                    System.out.println("Removed successfully" + "\n");
                this.manipulateAgents();
                break;
            case "2":
                System.out.println("Enter the ID of the agent you would like to change the permission level to");
                id = scanner.nextLong();
                if (dataController.changePermissionLevelAgent(id))
                    System.out.println("Changed successfully" + "\n");
                this.manipulateAgents();
                break;
            case "3":
                System.out.println("Enter the ID of the agent you would like to change the email address to");
                id = scanner.nextLong();
                System.out.println("Enter the new email address");
                op = scanner.nextLine();
                authenticationController = new AuthenticationController();
                if (authenticationController.isValidEmail(op)) {
                    if (dataController.changeEmailAddressAgent(id, op))
                        System.out.println("Changed successfully" + "\n");
                }
                this.manipulateAgents();
                break;
            case "-1":
                sababaFlightsProgram.homePage();
                break;
            default:
                sababaFlightsProgram.homePage();
        }
    }

    private void manipulateFlights() {
        String op;
        long id;
        int idF;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the action you would like to perform: ");
        System.out.println("1: Add a flight ");
        System.out.println("2: Remove a flight ");
        System.out.println("3: Change flight's departure date");
        System.out.println("4: Change flight's arrival date");
        System.out.println("-1: Return to home page");
        op = scanner.nextLine();
        switch (op) {
            case "1":
                Flight flight;
                flight = this.addAFlight();
                dataController.addAFlight(flight);
                System.out.println("Added successfully" + "\n");
                this.manipulateFlights();
                break;
            case "2":
                System.out.println("Enter the ID of the flight you would like to remove from the system");
                idF = scanner.nextInt();
                if (dataController.removeAFlight(idF))
                    System.out.println("Removed successfully" + "\n");
                this.manipulateFlights();
                break;
            case "3":
                System.out.println("Enter the ID of the flight you would like to change the departure date to");
                id = scanner.nextLong();
                scanner = new Scanner(System.in);
                System.out.println("Enter the new departure date in dd/MM/yyyy format");
                op = scanner.nextLine();
                if (dataController.changeDepartureDate(id, op))
                    System.out.println("Changed successfully" + "\n");
                this.manipulateFlights();
                break;
            case "4":
                System.out.println("Enter the ID of the flight you would like to change the arrival date to");
                id = scanner.nextLong();
                scanner = new Scanner(System.in);
                System.out.println("Enter the new arrival date in dd/MM/yyyy format");
                op = scanner.nextLine();
                if (dataController.changeArrivalDate(id, op))
                    System.out.println("Changed successfully" + "\n");
                this.manipulateFlights();
                break;
            case "-1":
                sababaFlightsProgram.homePage();
                break;
            default:
                sababaFlightsProgram.homePage();
        }
    }

    private Flight addAFlight() {
        Flight flight;
        String op;
        String destination;
        String departureDate;
        String arrivalDate;
        double flightPrie;
        boolean direct = false;
        int seatsLeft;
        int aircraftID;
        long departureAirportID;
        long destinationAirtportID;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Flight destination: ");
        destination = scanner.nextLine();
        System.out.print("Departure date: ");
        departureDate = scanner.nextLine();
        System.out.print("Arrival date: ");
        arrivalDate = scanner.nextLine();
        scanner = new Scanner(System.in);
        System.out.print("Flight price:");
        flightPrie = scanner.nextDouble();
        scanner = new Scanner(System.in);
        System.out.print("Aircraft ID:");
        aircraftID = scanner.nextInt();
        System.out.print("Departure airport ID: ");
        departureAirportID = scanner.nextLong();
        System.out.print("Destination airport ID: ");
        destinationAirtportID = scanner.nextLong();
        System.out.print("Seats left: ");
        seatsLeft = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.print("Direct flight? Y/N: " );
        op = scanner.nextLine();
        if (op.toLowerCase().equals("y"))
            direct = true;

        flight = new Flight(aircraftID, departureAirportID, destinationAirtportID, departureDate, arrivalDate, flightPrie, direct, destination, seatsLeft);
        return flight;
    }

}


