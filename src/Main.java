import java.util.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {

        Seat[] seats = new Seat[20];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat(i, "eco", 77);
        }

        Airport airport = new Airport(1, "EgyptAir", "hurghada", "egypt");

        Route route = new Route(airport, airport, 100.0);

        Flight flight = new Flight(route, LocalDateTime.now(), LocalDateTime.now(), 10.0, seats, 20);

        Flight.routes.add(route);

        HashSet<User> users = new HashSet<>();

        LinkedList<Ticket> allTickets = new LinkedList<>();



        Admin admin = new Admin();
        admin.createAccount(users);

        admin.addFlight(flight);

//        admin.removeFlight(flights.peek(), flights);

        //admin.updateFlight(flights.peek(), flights);

        System.out.println(Flight.routes);
//        System.out.println(flights);

//        assert flights.peek() != null;
        admin.generateReport(flight.getRoute(), allTickets );

        System.out.println("--------------------------------");

        Client client = new Client();
        client.createAccount(users);
        System.out.println(users);


        client.bookTicket(1, flight, 10, LocalDateTime.now(), allTickets );

        Ticket ticket = new Ticket(2, flight, client, 100, LocalDateTime.now());
        Ticket ticket1 = new Ticket(2, flight, client, 100, LocalDateTime.now());
        allTickets.add(ticket1);
        allTickets.add(ticket);

        client.cancelBooking(allTickets, client.getTicket(0));

        //client.updateBooking(allTickets, ticket1, ticket);

        System.out.println("----------------------");

        client.manageAcc(users);

        client.searchTicket(ticket);

        System.out.println(client);

        System.out.println(Flight.routes);
//        System.out.println(flights);


        System.out.print("WORKS FOR NOW");


    }
}