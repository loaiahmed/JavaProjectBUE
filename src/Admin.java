import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;

public class Admin extends User {


    @Override
    public String toString() {
        return "Admin{" +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void addFlight(PriorityQueue<Flight> flights, HashSet<Route> routes) {
        Scanner scan = new Scanner(System.in);
        Airport airport = new Airport();
        Route route = new Route();
        Seat[] seats;
        Flight flight = new Flight();
        String input1;
        double input2;
        int input3;

        System.out.print("         Lets add a flight together\n");
        System.out.print("-----------------------------------------------\n");
        System.out.print("Route: Destination: \n");
        System.out.print("                  Airport Code: ");
        input3 = scan.nextInt();
        airport.setAirportCode(input3);
        System.out.print("                  Airport Name: ");
        input1 = scan.next();
        airport.setName(input1);
        System.out.print("                  Airport City: ");
        input1 = scan.next();
        airport.setCity(input1);
        System.out.print("                  Airport Country: ");
        input1 = scan.next();
        airport.setCountry(input1);
        route.setDeparture_airport(airport);

        System.out.print("Route: Arrival: \n");
        System.out.print("                  Airport Code: ");
        input3 = scan.nextInt();
        airport.setAirportCode(input3);
        System.out.print("                  Airport Name: ");
        input1 = scan.next();
        airport.setName(input1);
        System.out.print("                  Airport City: ");
        input1 = scan.next();
        airport.setCity(input1);
        System.out.print("                  Airport Country: ");
        input1 = scan.next();
        airport.setCountry(input1);
        route.setArrival_airport(airport);

        routes.add(route);
        

        System.out.print("Route: Distance: ");
        input2 = scan.nextDouble();
        route.setDistance(input2);
        flight.setRoute(route);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
//        String time1 = "2019-03-27 10:15:30 AM";
//        LocalDateTime localTimeObj1 = LocalDateTime.parse(time1, formatter);

        System.out.print("Flight: Arrival Time: yyyy-MM-ddThh:mm:ss : ");
        input1 = scan.next();
        LocalDateTime aTime = LocalDateTime.parse(input1);
        flight.setArrivalTime(aTime);

        System.out.print("Flight: Departure Time: yyyy-MM-ddThh:mm:ss : ");
        input1 = scan.next();
        LocalDateTime dTime = LocalDateTime.parse(input1);
        flight.setDepartureTime(dTime);

        System.out.print("Flight: Time till arrival: ");
        input2 = scan.nextDouble();
        flight.setEstimatedDuration(input2);

        System.out.print("Flight: Seats: number of seats: ");
        int seatsSize = scan.nextInt();
        seats = new Seat[seatsSize];
        System.out.print("Flight: Seats: number of VIP seats: ");
        input3 = scan.nextInt();
        System.out.print("Flight: Seats: price of VIP seats: ");
        input2 = scan.nextDouble();

        int i = 0;
        while(i < input3 && i < seatsSize){
            seats[i] = new Seat(i, "VIP", input2);
            i++;
        }

        System.out.print("Flight: Seats: number of ECO seats: ");
        input3 = scan.nextInt();
        System.out.print("Flight: Seats: price of ECO seats: ");
        input2 = scan.nextDouble();

        while(i < input3 && i < seatsSize){
            seats[i] = new Seat(i, "ECO", input2);
            i++;
        }

        System.out.print("Flight: Seats: number of BUSINESS seats: ");
        input3 = scan.nextInt();
        System.out.print("Flight: Seats: price of BUSINESS seats: ");
        input2 = scan.nextDouble();

        while(i < input3 && i < seatsSize){
            seats[i] = new Seat(i, "BUSINESS", input2);
            i++;
        }
        flight.setSeats(seats);

        flights.add(flight);
    }
    public void addFlight(Flight flight, PriorityQueue<Flight> flights){
        System.out.println("adding flight...");
        flights.add(flight);
        System.out.println("flight added.");
    }

    public void removeFlight(Flight flight, PriorityQueue<Flight> flights) {
        System.out.println("removing flight...");
        if(!flights.remove(flight)){
            System.out.println("couldn't remove flight...");
            return;
        }
        System.out.println("flight removed.");
    }
    public void updateFlight(Flight flight, PriorityQueue<Flight> flights) {
        Scanner scan = new Scanner(System.in);
        char input;

        if (flights.contains(flight)){
            System.out.println("flight found");

            System.out.print("remove flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                removeFlight(flight, flights);

            System.out.print("edit flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y') {
                removeFlight(flight, flights);
                addFlight(flight, flights);
            }
        }
        else {
            System.out.println("flight not found");

            System.out.print("Add flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                addFlight(flight, flights);
        }
        System.out.println("to show flight: type 1");
        System.out.println("to exit flight: type 2");
        input = scan.next().charAt(0);
        switch (input){
            case '1':
                System.out.println(flight);
                break;
            case '2':
                return;
        }
    }
    public boolean generateReport(Route route, HashSet<Route> routes, LinkedList<Ticket> tickets) {
        System.out.println("searching for route...");

        if(!(routes.contains(route))){
            System.out.println("route not found.");
            return false;
        }
        System.out.println("route found.");
        System.out.println("Searching for tickets for flights with specified route...");

        double fare = 0;
        int numOfBookings = 0;
        for (Ticket ticket : tickets) {

            if (route.equals(ticket.getFlight().getRoute())) {
                fare += ticket.getPrice();
                numOfBookings++;
            }
        }
        System.out.println("tickets found...fare calculated ");
        System.out.println("     fare : " + fare);
        System.out.println("     number of bookings : " + numOfBookings);
        return true;
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
    public Admin() {
        this.userID = count;
        count++;}
}
