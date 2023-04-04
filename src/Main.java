import java.util.*;
import java.time.*;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Seat[] seats = new Seat[20];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat(i, "eco", 77);
        }

        Airport airport = new Airport(1, "EgyptAir", "hurghada", "egypt");

        Route route = new Route(airport, airport, 100.0);

        Flight flight = new Flight(route, LocalDateTime.now(), LocalDateTime.now(), 10.0, seats, 20);

        HashSet<Route> routes = new HashSet<>();
        routes.add(route);

        HashSet<User> users = new HashSet<>();

        LinkedList<Ticket> tickets = new LinkedList<>();

        PriorityQueue<Flight> flights = new PriorityQueue<Flight>(5, new FlightComparator());



        Admin admin = new Admin();
        admin.createAccount(users);

        admin.addFlight(flight, flights);

//        admin.removeFlight(flights.peek(), flights);

        admin.updateFlight(flights.peek(), flights);

        System.out.println(routes);
        System.out.println(flights);

        assert flights.peek() != null;
        admin.generateReport(flight.getRoute(), routes, tickets );

        System.out.println("--------------------------------");

        Client client = new Client();
        client.createAccount(users);
        System.out.println(users);


        client.bookTicket(1, flight, 10, LocalDateTime.now(), tickets );

        Ticket ticket = new Ticket(2, flight, client, 100, LocalDateTime.now());
        Ticket ticket1 = new Ticket(2, flight, client, 100, LocalDateTime.now());
        tickets.add(ticket1);
        tickets.add(ticket);

        client.CancelBooking(tickets, client.getTicket(0));

        client.updateBooking(tickets, ticket1, ticket);

        System.out.println("----------------------");

        client.manageAcc();

        client.searchTicket(ticket);

        System.out.println(client);

        System.out.println(routes);
        System.out.println(flights);


        System.out.print("WORKS FOR NOW");


    }
}