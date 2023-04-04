import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.*;

public class Client extends User{

    LinkedList<Ticket> tickets = new LinkedList<>();

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

    public void bookTicket(int seatNum, Flight flight, double baggageWeight, LocalDateTime reservationDate, LinkedList<Ticket> bigTickets){
        Ticket ticket = new Ticket();
        double price = flight.getSeats()[seatNum].getPrice();

        ticket.setSeatNum(seatNum);
        ticket.setFlight(flight);
        ticket.setPassenger(this);
        ticket.setPrice(price);
        ticket.setBaggageWeight(baggageWeight);
        ticket.setReservationDate(reservationDate);

        System.out.println("adding ticket in tickets...");
        tickets.add(ticket);
        System.out.println("adding ticket in big ticket...");
        bigTickets.add(ticket);
        System.out.println("added ticket.");
    }

    public void viewTicket(Ticket ticket) {
        if(!(this.tickets.contains(ticket))){
            System.out.println("Ticket not found.");
            return;
        }
        for(Ticket eachTicket : this.tickets){
            System.out.println(ticket.toString());
        }
    }
    public void searchTicket(Ticket ticket) {
        if(!(this.tickets.contains(ticket))){
            System.out.println("Ticket not found.");
            return;
        }
        for(Ticket eachTicket : this.tickets){
            if(eachTicket.equals(ticket)) {
                System.out.println(ticket.toString());
            }
        }
    }

    public void CancelBooking(LinkedList<Ticket> bigTickets, Ticket ticketToCancel) {
        System.out.println("checking if ticket exist in collections...");
        if(!(this.tickets.contains(ticketToCancel) && bigTickets.contains(ticketToCancel))){
            System.out.println("Ticket not found.");
            return;
        }
        System.out.println("Ticket found");
        System.out.println("Removing ticket from collections...");

        this.tickets.remove(ticketToCancel);
        System.out.println("removed ticket from passenger ticket collection...");

        bigTickets.remove(ticketToCancel);
        System.out.println("removed ticket from big ticket collection.");
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
                CancelBooking(bigTickets, ticketToUpdate);
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

    public void manageAcc(){
        Scanner sc = new Scanner (System.in);
        int x;
        String pass2, user2, email2;
        String pass1, user1;
        System.out.println("Enter user name. "); user1 = sc.next();
        System.out.println("Enter password. "); pass1 = sc.next();

        if(pass1.equals(this.getPassword()) && user1.equals(this.getUsername())){
            do{
                System.out.println("Enter 1 to change username, 2 to change password, 3 to change email, 4 to exit.");
                x = sc.nextInt();

                if( x == 1){ System.out.println("Enter new Username.");
                    user2 = sc.next();
                    this.setUsername(user2); }

                else if( x == 2){ System.out.println("Enter new password");
                    pass2 = sc.next();
                    this.setUsername(pass2); }

                else if( x == 3){ System.out.println("Enter new email");;
                    email2 = sc.next();
                    this.setUsername(email2); }

                else{System.out.println("Enter a valid number.");}
            }while(x != 4);
        }
        else{ System.out.println("Invalid info."); }
    }
}
