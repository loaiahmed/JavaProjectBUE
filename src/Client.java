import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.*;
import java.util.regex.Pattern;

public class Client extends User implements Serializable {

    private LinkedList<Ticket> tickets = new LinkedList<>();

    public Client(String username, String email, String password) {
        super(username, email, password);
    }

    public Client() {
        this.userID = count;
        count++;
    }

    public Ticket getTicket(int index){
        return tickets.get(index);
    }
    public Ticket getTicket(Ticket ticket){
        return tickets.get(tickets.indexOf(ticket));
    }

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(LinkedList<Ticket> tickets) {
        this.tickets = tickets;
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

        AirlineCompany.allClients.add(this);

        return true;
    }

    public boolean bookTicket(int seatNum, Flight flight, double baggageWeight, LocalDateTime reservationDate, LinkedList<Ticket> allTickets){
        Ticket ticket = new Ticket();
        double price = flight.getSeats()[seatNum].getPrice();

        ticket.setSeatNum(seatNum);
        ticket.setFlight(flight);
        ticket.setPassenger(this);
        ticket.setPrice(price);
        ticket.setBaggageWeight(baggageWeight);
        ticket.setReservationDate(reservationDate);


        System.out.println("adding ticket in flight tickets collection...");
        if(!flight.addInTickets(ticket)){
            System.out.println("ticket not added, terminating...");
            return false;
        }
        System.out.println("ticket added...");

        System.out.println("adding ticket in tickets...");
        tickets.add(ticket);
        System.out.println("adding ticket in big ticket...");
        allTickets.add(ticket);
        System.out.println("added ticket.");

        return true;
    }
    public boolean bookTicket(Ticket ticket){
        System.out.println("adding ticket in flight tickets collection...");
        if(!ticket.getFlight().addInTickets(ticket)){
            System.out.println("ticket not added, terminating...");
            return false;
        }
        System.out.println("ticket added...");

        System.out.println("adding ticket in tickets...");
        tickets.add(ticket);
        System.out.println("adding ticket in big ticket...");
        AirlineCompany.allTickets.add(ticket);
        System.out.println("added ticket.");

        return true;
    }
    public void bookTicket(LinkedList<Seat> seats, Flight flight, int baggageWeight){
        for(Seat seat : seats){
            Ticket ticket = new Ticket(seat.getSeatNumber(), flight, this, baggageWeight);
            tickets.add(ticket);
            AirlineCompany.allTickets.add(ticket);

            flight.reserveSeats(seats);
        }
        System.out.println("Tickets Booked");
    }


    public void viewTickets() {
        if(this.tickets.isEmpty()){
            System.out.println("tickets empty");
            return;
        }
        for(Ticket eachTicket : this.tickets){
            System.out.println(eachTicket.toString());
        }
    }
    public boolean searchTicket(Ticket ticket) {
        if(!(this.tickets.contains(ticket))){
            System.out.println("Ticket not found.");
            return false;
        }
        for(Ticket eachTicket : this.tickets){
            if(eachTicket.equals(ticket)) {
                System.out.println(ticket);
                return true;
            }
        }
        return false;
    }

    public void CancelBooking(Ticket ticketToCancel) {
        System.out.println("checking if ticket exist in collections...");
        if(!(this.tickets.contains(ticketToCancel) && AirlineCompany.allTickets.contains(ticketToCancel))){
            System.out.println("Ticket not found.");
            return;
        }
        System.out.println("Ticket found");
        System.out.println("Removing ticket from collections...");

        this.tickets.remove(ticketToCancel);
        System.out.println("removed ticket from passenger ticket collection...");

        AirlineCompany.allTickets.remove(ticketToCancel);
        System.out.println("removed ticket from big ticket collection.");

        ticketToCancel.getFlight().removeOfTickets(ticketToCancel);
        System.out.println("done");

    }

    public void updateBooking(LinkedList<Ticket> bigTickets, Ticket ticketToUpdate, Ticket newTicket) {

        if (bigTickets.contains(ticketToUpdate)) {

            if (newTicket.getPassenger().equals(ticketToUpdate.getPassenger()) && newTicket.getFlight().equals(ticketToUpdate.getFlight())) {
                int index = ((List<Ticket>) bigTickets).indexOf(ticketToUpdate);
                ((List<Ticket>) bigTickets).set(index, newTicket);
                System.out.println("Ticket updated successfully!");
            }

            else {
                CancelBooking(ticketToUpdate);
                int s = ticketToUpdate.getSeatNum();
                Flight f =  newTicket.getFlight();
                double bw =  newTicket. getBaggageWeight();
                LocalDateTime rd = newTicket.getReservationDate();
                bookTicket(s , f , bw , rd , bigTickets);
            }
        }
        else {
            System.out.println("Ticket to update not found in booked collection!");
        }
    }

    public void updateBooking(Ticket ticketToUpdate,Ticket newTicket) {
        Scanner scan = new Scanner(System.in);
        char input;

        if (tickets.contains(ticketToUpdate)){
            System.out.println("Ticket found");

            System.out.print("remove Ticket? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                CancelBooking(ticketToUpdate);

            System.out.print("edit ticket? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y') {
                CancelBooking(ticketToUpdate);
                bookTicket(newTicket);
            }
        }
    }
    public boolean updateTicket(Ticket ticket, int baggageWeight, int seatNum){
        if(tickets.contains(ticket)){
            if(ticket.getFlight().isSeatReserved(seatNum)){
                System.out.println("Seat already Reserved");
                return false;
            }
            ticket.getFlight().updateReservation(ticket.getSeatNum(), seatNum);
            ticket.setBaggageWeight(baggageWeight);
            ticket.setSeatNum(seatNum);
            return true;
        }
        return false;
    }

    public void manageAcc(){
        Scanner sc = new Scanner (System.in);
        int x;
        String pass2, user2, email2;

        if(AirlineCompany.allClients.contains(this)){
            do{
                System.out.println("Enter 1 to change username, 2 to change password, 3 to change email, 4 to exit.");
                x = sc.nextInt();

                if( x == 1){ System.out.println("Enter new Username.");
                    user2 = sc.next();
                    this.setUsername(user2); }

                else if( x == 2){ System.out.println("Enter new password");
                    pass2 = sc.next();
                    this.setUsername(pass2); }

                else if( x == 3){ System.out.println("Enter new email");
                    email2 = sc.next();
                    this.setUsername(email2); }

                else{System.out.println("Enter a valid number.");}
            }while(x != 4);
        }
        else{ System.out.println("Invalid info."); }
    }
    public LinkedList<Ticket> getTicketsFromRoute(Route route){
        LinkedList<Ticket> tickets1 = new LinkedList<>();
        for(Ticket ticket: tickets){
            if(ticket.getFlight().getRoute().equals(route)){
                tickets1.add(ticket);
            }
        }
        return tickets1;
    }

    @Override
    public String toString() {
        return "Client{" +
                "tickets=" + tickets +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(tickets, client.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tickets);
    }
}
