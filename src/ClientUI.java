import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

public class ClientUI extends JFrame implements ActionListener {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JTextPane welcomeMRTextPane;
    private JTable flightsTable;
    private JComboBox comboBox1;
    private JButton bookFlightButton;
    private JButton updateButton;
    private JTable ticketsTable;
    private JButton cancelTicketButton;
    private JButton logOutButton;
    private JButton manageAccountButton;
    private JButton updateTicketButton;
    private JComboBox comboBox2;
    private JButton updateButton1;

    private Client client;

    ClientUI(Client client){
        this.client = client;

        this.setContentPane(rootPanel);
        this.setSize(1000, 700);
//        this.pack();
        this.setTitle("Client UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        createComboBox(comboBox1);
        createComboBox(comboBox2);

        createFlightsTable();
        createTicketsTable();

        comboBox1.addActionListener(this::actionPerformed);
        comboBox2.addActionListener(this::actionPerformed);
        updateButton.addActionListener(this::actionPerformed);
        updateButton1.addActionListener(this::actionPerformed);
        cancelTicketButton.addActionListener(this::actionPerformed);
        bookFlightButton.addActionListener(this::actionPerformed);
        manageAccountButton.addActionListener(this::actionPerformed);
        updateTicketButton.addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == comboBox1){

            if(comboBox1.getSelectedItem() == "All Routes"){
                clearTable(flightsTable);
                createFlightsTable();
            }
            else {
                clearTable(flightsTable);
                Route route = (Route) comboBox1.getSelectedItem();
                System.out.println(route);
                createFlightsTable(route);
            }
        }
        if(actionEvent.getSource() == comboBox2){

            if(comboBox1.getSelectedItem() == "All Routes"){
                clearTable(ticketsTable);
                createTicketsTable();
            }
            else {
                clearTable(ticketsTable);
                Route route = (Route) comboBox1.getSelectedItem();
                System.out.println(route);
                createTicketsTable(route);
            }
        }
        if(actionEvent.getSource() == updateButton){
            clearTable(flightsTable);
            createFlightsTable();
        }
        if(actionEvent.getSource() == updateButton1){
            clearTable(ticketsTable);
            createTicketsTable();
        }
        if(actionEvent.getSource() == cancelTicketButton){
            client.CancelBooking((Ticket) getSelectedRow(ticketsTable));
            JOptionPane.showMessageDialog(rootPanel, "Booking Canceled", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        if(actionEvent.getSource() == bookFlightButton){
            if(getSelectedRow(flightsTable) != null) {
                new FlightBooker(this.client, (Flight) getSelectedRow(flightsTable));
            }
            else{
                JOptionPane.showMessageDialog(rootPanel, "Error: Choose a Row", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(actionEvent.getSource() == manageAccountButton){
            new AccountManager(client);
        }
        if(actionEvent.getSource() == updateTicketButton){
            if(getSelectedRow(ticketsTable) != null) {
                new TicketEditor((Ticket) getSelectedRow(ticketsTable));
            }
            else{
                JOptionPane.showMessageDialog(rootPanel, "Error: Choose a Row", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void createComboBox(JComboBox comboBox1){
        comboBox1.addItem("All Routes");
        for (Route route : Flight.routes) {
            comboBox1.addItem(route);
        }
    }
    public Object getSelectedRow(JTable table){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        Vector<Vector> data = dm.getDataVector();
        for(int i = 0; i < data.size(); i++){
            if(i == table.getSelectedRow()) {
                return data.get(i).lastElement();
            }
        }
        return null;
    }
    public void clearTable(JTable table1){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
    public void createFlightsTable(){
        LinkedList<Flight> flights = new LinkedList<>(Admin.flights);
        Object[][] data = new Object[flights.size()][6];

        for(int j = 0; j < flights.size(); j++){
            data[j][0] = flights.get(j).getFlightNumber();
            data[j][1] = flights.get(j).getArrivalTime();
            data[j][2] = flights.get(j).getDepartureTime();
            data[j][3] = flights.get(j).getNumOfSeats();
            data[j][4] = flights.get(j).getRoute();
            data[j][5] = flights.get(j);
        }
        flightsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Flight Number", "Arrival Time", "Departure Time", "Number of Seats", "Route", "Flight"}
        ));
//        flightsTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        flightsTable.getColumnModel().getColumn(5).setMaxWidth(0);
        flightsTable.getColumnModel().getColumn(5).setMinWidth(0);
    }
    public void createFlightsTable(Route route){
        LinkedList<Flight> flights = Admin.getFlightsFromRoute(route);
        Object[][] data = new Object[flights.size()][6];

        for(int j = 0; j < flights.size(); j++){
            data[j][0] = flights.get(j).getFlightNumber();
            data[j][1] = flights.get(j).getArrivalTime();
            data[j][2] = flights.get(j).getDepartureTime();
            data[j][3] = flights.get(j).getNumOfSeats();
            data[j][4] = flights.get(j).getRoute();
            data[j][5] = flights.get(j);
        }
        flightsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Flight Number", "Arrival Time", "Departure Time", "Number of Seats", "Route", "Flight"}
        ));
//        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        flightsTable.getColumnModel().getColumn(5).setMaxWidth(0);
        flightsTable.getColumnModel().getColumn(5).setMinWidth(0);
    }public void createTicketsTable(){
        Object[][] data = new Object[client.getTickets().size()][7];

        for(int j = 0; j < client.getTickets().size(); j++){
            data[j][0] = client.getTickets().get(j).getTicketNumber();
            data[j][1] = client.getTickets().get(j).getSeatNum();
            data[j][2] = client.getTickets().get(j).getFlight();
            data[j][3] = client.getTickets().get(j).getBaggageWeight();
            data[j][4] = client.getTickets().get(j).getPrice();
            data[j][5] = client.getTickets().get(j).getReservationDate();
            data[j][6] = client.getTickets().get(j);
        }
        ticketsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Ticket Number", "Seat Num", "Flight", "Baggage Weight", "Price", "Reservation Date", "Ticket"}
        ));
//        flightsTable.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        ticketsTable.getColumnModel().getColumn(6).setMaxWidth(0);
        ticketsTable.getColumnModel().getColumn(6).setMinWidth(0);
    }
    public void createTicketsTable(Route route){
        LinkedList<Ticket> tickets = client.getTicketsFromRoute(route);
        Object[][] data = new Object[tickets.size()][7];

        for(int j = 0; j < tickets.size(); j++){
            data[j][0] = tickets.get(j).getTicketNumber();
            data[j][1] = tickets.get(j).getSeatNum();
            data[j][2] = tickets.get(j).getFlight();
            data[j][3] = tickets.get(j).getBaggageWeight();
            data[j][4] = tickets.get(j).getPrice();
            data[j][5] = tickets.get(j).getReservationDate();
            data[j][6] = tickets.get(j);
        }
        ticketsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Ticket Number", "Seat Num", "Flight", "Baggage Weight", "Price", "Reservation Date", "Ticket"}
        ));
//        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        ticketsTable.getColumnModel().getColumn(6).setMaxWidth(0);
        ticketsTable.getColumnModel().getColumn(6).setMinWidth(0);
    }
}
