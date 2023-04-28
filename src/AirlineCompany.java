import java.io.*;
import java.util.*;
import java.time.*;

public class AirlineCompany {

//    static HashSet<User> users = new HashSet<>();

    static HashSet<Client> allClients = new HashSet<>();
    static HashSet<Admin> allAdmins = new HashSet<>();

    static LinkedList<Ticket> allTickets = new LinkedList<>();

    public static void main(String[] args) throws Exception{



        FileOutputStream fos = new FileOutputStream("onlyOnce.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(0);
        oos.close();
        fos.close();



//        startUpOperation();         // IMPORTANT
//        readFiles();                // IMPORTANT


        Seat[] seats = new Seat[20];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat(i, "eco", 77);
        }

        Airport airport = new Airport("EgyptAir", "hurghada", "egypt");

        Route route = new Route(airport, airport);

        Flight flight = new Flight(route, LocalDateTime.now(), LocalDateTime.now(), 10.0, seats, 20);

        Flight.routes.add(route);



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

        System.out.println(Flight.routes);
//        System.out.println(flights);


        System.out.println("done");

        Client client = new Client("Loay Ahmed", "loaiahmed@bue.edu.eg", "1234");
        client.createAccount();

        System.out.print("WORKS FOR NOW");

//        StartUp startUp = new StartUp();        // IMPORTANT

    }
    public static Client getClientWithAccount(String emailAddress, String password){
        for( Client client : allClients){
            if(client.getEmail().equals(emailAddress) && client.getPassword().equals(password)){
                return client;
            }
        }
        return null;
    }
    public static Admin getAdminWithAccount(String emailAddress, String password){
        for( Admin admin : allAdmins){
            if(admin.getEmail().equals(emailAddress) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }

    public static void writeFiles() throws Exception{

        FileOutputStream fos = new FileOutputStream("allClients.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Client client : allClients){
            oos.writeObject(client);
        }
        oos.close();
        fos.close();

        FileOutputStream fos1 = new FileOutputStream("allAdmins.txt");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        for(Admin admin : allAdmins){
            oos1.writeObject(admin);
        }
        oos1.close();
        fos1.close();

        FileOutputStream fos2 = new FileOutputStream("allTickets.txt");
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        for(Ticket ticket : allTickets){
            oos2.writeObject(ticket);
        }
        oos2.close();
        fos2.close();

    }
    public static void readFiles() throws Exception{            // the collections have to be empty to work properly
        FileInputStream fis = new FileInputStream("allClients.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            while (true) {
                Client c  = (Client) ois.readObject();
                allClients.add(c);
                System.out.println(c);
            }
        }catch (Exception ignored) {}
        ois.close();
        fis.close();

        FileInputStream fis1 = new FileInputStream("allAdmins.txt");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        try {
            while (true) {
                Admin a  = (Admin) ois1.readObject();
                allAdmins.add(a);
                System.out.println(a);
            }
        }catch (Exception ignored) {}
        ois1.close();
        fis1.close();

        FileInputStream fis2 = new FileInputStream("allTickets.txt");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        try {
            while (true) {
                Ticket t  = (Ticket) ois2.readObject();
                allTickets.add(t);
                System.out.println(t);
            }
        }catch (Exception ignored) {}
        ois2.close();
        fis2.close();

    }
    public static void startUpOperation() throws Exception {      // happens only once to fill the files with the Collections to read
        FileInputStream fis2 = new FileInputStream("onlyOnce.txt");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        int flag = ois2.readInt();
        ois2.close();
        fis2.close();

        if(flag == 0){
            System.out.println("Entered onlyOnce operation");
            writeFiles();

            FileOutputStream fos = new FileOutputStream("onlyOnce.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(1);
            oos.close();
            fos.close();
        }
    }
}