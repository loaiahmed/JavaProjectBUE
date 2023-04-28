import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Serializable {
    private int ticketNumber;
    private int seatNum;
    private Flight flight;
    private Client passenger;
    private double price;
    private double baggageWeight;
    private LocalDateTime reservationDate;
    static int count = 0;

    public Ticket(int seatNum, Flight flight, Client passenger, double baggageWeight, LocalDateTime reservationDate) {
        this.ticketNumber = count;
        this.seatNum = seatNum;
        this.flight = flight;
        this.passenger = passenger;
        this.price = flight.getSeats()[seatNum].getPrice();
        this.baggageWeight = baggageWeight;
        this.reservationDate = reservationDate;
        count++;
    }public Ticket(int seatNum, Flight flight, Client passenger, double baggageWeight) {
        this.ticketNumber = count;
        this.seatNum = seatNum;
        this.flight = flight;
        this.passenger = passenger;
        this.price = flight.getSeats()[seatNum].getPrice();
        this.baggageWeight = baggageWeight;
        this.reservationDate = LocalDateTime.now();
        count++;
    }
    public Ticket() {
        this.ticketNumber = count;
        count++;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Client getPassenger() {
        return passenger;
    }

    public void setPassenger(Client passenger) {
        this.passenger = passenger;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber=" + ticketNumber +
                ", seatNum=" + seatNum +
                ", flight=" + flight +
//                ", passenger=" + passenger +
                ", price=" + price +
                ", baggageWeight=" + baggageWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketNumber == ticket.ticketNumber && seatNum == ticket.seatNum && Double.compare(ticket.price, price) == 0 && Double.compare(ticket.baggageWeight, baggageWeight) == 0 && Objects.equals(flight, ticket.flight) && Objects.equals(passenger, ticket.passenger) && Objects.equals(reservationDate, ticket.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNumber, seatNum, flight, price, baggageWeight, reservationDate);
    }
}