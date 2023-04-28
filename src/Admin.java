import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Admin extends User {


    static PriorityQueue<Flight> flights = new PriorityQueue<>(5, new FlightComparator());

    @Override
    public String toString() {
        return "Admin{" +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public boolean createAccount() {
//        Scanner scan = new Scanner(System.in);
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//
//        Pattern pat = Pattern.compile(emailRegex);
//
//        System.out.print("Username : ");
//        this.username = scan.next();
//
//        do {
//            System.out.print("Email : ");
//            this.email = scan.next();
//        } while (!(pat.matcher(email).matches()));
//
//        System.out.print("Password : ");
//        this.password = scan.next();

        AirlineCompany.allAdmins.add(this);

        return true;
    }

    public void addFlight() {
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
        System.out.print("                  Airport Name: ");
        input1 = scan.next();
        airport.setName(input1);
        System.out.print("                  Airport City: ");
        input1 = scan.next();
        airport.setCity(input1);
        System.out.print("                  Airport Country: ");
        input1 = scan.next();
        airport.setCountry(input1);
        route.setDestinationAirport(airport);

        System.out.print("Route: Arrival: \n");
        System.out.print("                  Airport Name: ");
        input1 = scan.next();
        airport.setName(input1);
        System.out.print("                  Airport City: ");
        input1 = scan.next();
        airport.setCity(input1);
        System.out.print("                  Airport Country: ");
        input1 = scan.next();
        airport.setCountry(input1);
        route.setOriginAirport(airport);

        Flight.routes.add(route);
        

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
    public void addFlight(Flight flight){
        System.out.println("adding flight...");
        flights.add(flight);
        System.out.println("flight added.");

        System.out.println("adding Route...");
        Flight.routes.add(flight.getRoute());
        System.out.println("Route added.");
    }

    public void removeFlight(Flight flight) {
        System.out.println("removing flight...");
        if(!flights.remove(flight)){
            System.out.println("couldn't remove flight...");
            return;
        }
        System.out.println("flight removed.");
    }
    public void updateFlight(Flight flight, Flight newFlight) {
        Scanner scan = new Scanner(System.in);
        char input;

        if (flights.contains(flight)){
            System.out.println("flight found");

            System.out.print("remove flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                removeFlight(flight);

            System.out.print("edit flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y') {
                removeFlight(flight);
                addFlight(newFlight);
            }
        }
        else {
            System.out.println("flight not found");

            System.out.print("Add flight? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                addFlight(flight);
        }
        System.out.println("to show flight: type 1");
        System.out.println("to exit flight: type 2");
        input = scan.next().charAt(0);
        switch (input){
            case '1':
                System.out.println(flight);
                break;
            case '2':
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }
    }

    public void updateFlight1(Flight flight, Flight newFlight){

        if (!(flights.contains(flight))){
            System.out.println("Flight not found");
            return;
        }
        flights.remove(flight);
        flights.add(newFlight);

        System.out.println("removed old Flight and added new Flight!!");
    }
    public double[] generateReport(Route route) {
        System.out.println("searching for route...");

        if(!(Flight.routes.contains(route))){
            System.out.println("route not found.");
            return null;
        }
        System.out.println("route found.");
        System.out.println("Searching for tickets for flights with specified route...");

        double fare = 0;
        double numOfBookings = 0;
        for (Ticket ticket : AirlineCompany.allTickets) {

            if (route.equals(ticket.getFlight().getRoute())) {
                fare += ticket.getPrice();
                numOfBookings++;
            }
        }
        System.out.println("tickets found...fare calculated ");
        System.out.println("     fare : " + fare);
        System.out.println("     number of bookings : " + numOfBookings);
        return new double[]{fare, numOfBookings};
    }

    public static LinkedList<Flight> getFlightsFromRoute(Route route){
//        LinkedList<Flight> flights = new LinkedList<>(Admin.flights);
        LinkedList<Flight> flights1 = new LinkedList<>();
        for(Flight flight: Admin.flights){
            if(flight.getRoute().equals(route)){
                flights1.add(flight);
            }
        }
        return flights1;
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
    public Admin() {
        this.userID = count;
        count++;}
}
