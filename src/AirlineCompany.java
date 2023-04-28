import java.util.*;
import java.time.*;

public class AirlineCompany {

//    static HashSet<User> users = new HashSet<>();

    static HashSet<Client> allClients = new HashSet<>();
    static HashSet<Admin> allAdmins = new HashSet<>();

    static LinkedList<Ticket> allTickets = new LinkedList<>();

    public static void main(String[] args) {

        Seat[] seats = new Seat[20];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat(i, "eco", 77);
        }

        Airport airport = new Airport("EgyptAir", "hurghada", "egypt");

        Route route = new Route(airport, airport);

        Flight flight = new Flight(route, LocalDateTime.now(), LocalDateTime.now(), 10.0, seats, 20);

        Flight.routes.add(route);

//        HashSet<User> users = new HashSet<>();

        LinkedList<Ticket> allTickets = new LinkedList<>();



        Admin admin = new Admin();
        admin.createAccount();

        admin.addFlight(flight);

//        admin.removeFlight(flights.peek(), flights);

        //admin.updateFlight(flights.peek(), flights);

        System.out.println(Flight.routes);
//        System.out.println(flights);

//        assert flights.peek() != null;
        admin.generateReport(flight.getRoute());

        System.out.println("--------------------------------");

//        Client client = new Client();
//        client.createAccount();
//        System.out.println(users);


//        client.bookTicket(1, flight, 10, LocalDateTime.now(), allTickets );
//
//        Ticket ticket = new Ticket(2, flight, client, 100, LocalDateTime.now());
//        Ticket ticket1 = new Ticket(2, flight, client, 100, LocalDateTime.now());
//        allTickets.add(ticket1);
//        allTickets.add(ticket);
//
//        client.CancelBooking(allTickets, client.getTicket(0));
//
//        client.updateBooking(allTickets, ticket1, ticket);
//
//        System.out.println("----------------------");
//
//        client.manageAcc();
//
//        client.searchTicket(ticket);
//
//        System.out.println(client);

        System.out.println(Flight.routes);
//        System.out.println(flights);

        Client client = new Client("Loay Ahmed", "loaiahmed@bue.edu.eg", "1234");
        client.createAccount();

        System.out.print("WORKS FOR NOW");
        new ClientUI(client);
        new AdminUI(admin);
    }

    public static boolean isClientWithAccount(String emailAddress, String password){
        for( Client client : allClients){
            if(client.getEmail().equals(emailAddress) && client.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public static boolean isAdminWithAccount(String emailAddress, String password){
        for( Admin admin : allAdmins){
            if(admin.getEmail().equals(emailAddress) && admin.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}