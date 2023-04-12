import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

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
    public boolean bookTicket(Ticket ticket, LinkedList<Ticket> allTickets){
        System.out.println("adding ticket in flight tickets collection...");
        if(!ticket.getFlight().addInTickets(ticket)){
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


    public boolean bookTicket(){
        Scanner scan = new Scanner(System.in);
        int seatNo;

        Flight flight = new Flight();
        Ticket ticket = new Ticket();
        System.out.println("Let's book a ticket..");

        System.out.println("Enter the booked seat number: ");
        seatNo = scan.nextInt();

        System.out.println("Enter the booked seat number: ");

        flight.addInTickets()
//
    }

    public void updateBooking(Ticket ticketToUpdate,Ticket newTicket) {
        Scanner scan = new Scanner(System.in);
        char input;

        if (tickets.contains(ticketToUpdate)){
            System.out.println("Ticket found");

            System.out.print("remove Ticket? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                cancelBooking(tickets,ti);

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

    public void cancelBooking(LinkedList<Ticket> allTickets, Ticket ticketToCancel) {
        System.out.println("checking if ticket exist in collections...");
        if(!(this.tickets.contains(ticketToCancel) && allTickets.contains(ticketToCancel))){
            System.out.println("Ticket not found.");
            return;
        }
        System.out.println("Ticket found");
        System.out.println("Removing ticket from collections...");

        this.tickets.remove(ticketToCancel);
        System.out.println("removed ticket from passenger ticket collection...");

        allTickets.remove(ticketToCancel);
        System.out.println("removed ticket from big ticket collection.");

        ticketToCancel.getFlight().removeOfTickets(ticketToCancel);
        System.out.println("done");

    }

    /*public void updateBooking(LinkedList<Ticket> bigTickets, Ticket ticketToUpdate, Ticket newTicket) {

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
     */

    public void updateBooking(Ticket ticketToUpdate,Ticket newTicket, LinkedList<Ticket> allTicket) {
        Scanner scan = new Scanner(System.in);
        char input;

        if (tickets.contains(ticketToUpdate)){
            System.out.println("Ticket found");

            System.out.print("remove Ticket? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y')
                CancelBooking(allTicket, ticketToUpdate);

            System.out.print("edit ticket? (y/n): ");
            input = scan.next().charAt(0);
            if(input == 'y' || input == 'Y') {
                CancelBooking(allTicket, ticketToUpdate);
                bookTicket(newTicket, allTicket);
            }
        }
    }




    public void manageAcc(HashSet<User> users){
        Scanner sc = new Scanner (System.in);
        int x;
        String pass2, user2, email2;

        if(users.contains(this)){
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

}
