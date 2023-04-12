import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author omarw
 */


public class Flight {
    private int flightNumber;
    private Route route;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private double estimatedDuration;
    private LinkedList<Ticket> tickets = new LinkedList<>();
    private Seat[] seats;
    private int numOfSeats;
    static int count = 0;

    static HashSet<Route> routes = new HashSet<>();


    public Flight(Route route, LocalDateTime arrivalTime,
                  LocalDateTime departureTime, double estimatedDuration, Seat[] seats, int numOfSeats) {
        this.flightNumber = count;
        this.route = route;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.estimatedDuration = estimatedDuration;
        this.seats = seats;
        this.numOfSeats = numOfSeats;
        count++;
    }

    public Flight() {
        this.flightNumber = count;
        count++;
    }


    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(LinkedList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean addInTickets(Ticket ticket){
        System.out.println("checking if seat is available...");
        for(Seat seat : seats) {

            if(ticket.getSeatNum() == seat.getSeatNumber() && seat.isAvailable()) {
                this.tickets.add(ticket);

                System.out.println("seat is available");
                seat.setAvailable(false);

                return true;
            }
        }
        return false;
    }

    public boolean removeOfTickets(Ticket ticketToRemove){

        for(Seat seat : seats) {

            if(ticketToRemove.getSeatNum() == seat.getSeatNumber()) {
                if(!(seat.isAvailable())) {
                    System.out.println("seat found!!");
                    this.tickets.remove(ticketToRemove);

                    System.out.println("seat removed");
                    seat.setAvailable(true);

                    return true;
                }
                else {
                    System.out.println("seat is already available");
                }
            }
        }
        return false;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", route=" + route +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", estimatedDuration=" + estimatedDuration +
//                ", seats=" + Arrays.toString(seats) +
                ", numOfSeats=" + numOfSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber && Double.compare(flight.estimatedDuration, estimatedDuration) == 0 && numOfSeats == flight.numOfSeats && Objects.equals(route, flight.route) && Objects.equals(arrivalTime, flight.arrivalTime) && Objects.equals(departureTime, flight.departureTime) && Arrays.equals(seats, flight.seats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(flightNumber, route, arrivalTime, departureTime, estimatedDuration, numOfSeats);
        result = 31 * result + Arrays.hashCode(seats);
        return result;
    }
}
