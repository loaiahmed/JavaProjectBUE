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
    private final int flightNumber;
    private Route route;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private LinkedList<Ticket> tickets = new LinkedList<>();
    private int numOfSeats;
    private Seat[] seats;
    static int count = 0;

    static HashSet<Route> routes = new HashSet<>();


    public Flight(Route route, LocalDateTime arrivalTime,
                  LocalDateTime departureTime, double estimatedDuration, Seat[] seats, int numOfSeats) {
        this.flightNumber = count;
        this.route = route;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
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
        System.out.println("seat is not available");
        return false;
    }

    public void reserveSeats(LinkedList<Seat> seats1){
        for(int i = 0; i < seats1.size(); i++){
            if(seats1.get(i).getSeatNumber() == seats[i].getSeatNumber()){
                seats[i].setAvailable(false);
            }
        }
    }
    public void updateReservation(int oldSeatNum, int newSeatNum){
        for(Seat seat : seats){
            if(seat.getSeatNumber() == oldSeatNum){
                seat.setAvailable(true);
            }
            if(seat.getSeatNumber() == newSeatNum){
                seat.setAvailable(false);
            }
        }
        System.out.println("Done");
    }
    public boolean isSeatReserved(int seatNum){
        for(Seat seat: getAvailableSeats()){
            if(seat.getSeatNumber() == seatNum){
                return false;
            }
        }
        return true;
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

    public LinkedList<Seat> getAvailableSeats(){
        LinkedList<Seat> seats1 = new LinkedList<>();
        for(Seat seat : seats){
            if(seat.isAvailable()){
                seats1.add(seat);
            }
        }
        return seats1;
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
//                ", seats=" + Arrays.toString(seats) +
                ", numOfSeats=" + numOfSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber && numOfSeats == flight.numOfSeats && Objects.equals(route, flight.route) && Objects.equals(arrivalTime, flight.arrivalTime) && Objects.equals(departureTime, flight.departureTime) && Objects.equals(tickets, flight.tickets) && Arrays.equals(seats, flight.seats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(flightNumber, route, arrivalTime, departureTime, tickets, numOfSeats);
        result = 31 * result + Arrays.hashCode(seats);
        return result;
    }
}
