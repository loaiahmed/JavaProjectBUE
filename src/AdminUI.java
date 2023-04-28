import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class AdminUI extends JFrame implements ActionListener{
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton logOutButton;
    private JTextPane welcomeMasterTextPane;
    private JTable table1;
    private JButton removeFlightButton;
    private JButton addFlightButton;
    private JButton updateFlightButton;
    private JComboBox comboBox1;
    private JButton updateButton;
    private JLabel generateReportLabel;

    private final Admin admin;

    AdminUI(Admin admin) {
        this.admin = admin;

        this.setContentPane(rootPanel);
        this.setSize(1000, 700);
//        this.pack();
        this.setTitle("Admin UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        updateButton.addActionListener(this::actionPerformed);
        removeFlightButton.addActionListener(this::actionPerformed);
        comboBox1.addActionListener(this::actionPerformed);
        addFlightButton.addActionListener(this::actionPerformed);
        updateFlightButton.addActionListener(this::actionPerformed);

        createTable();
        createComboBox(comboBox1);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logOut();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    AirlineCompany.writeFiles();
                } catch (Exception ex) {
                    System.out.println("Writing to files Failed!!");
                }
            }
        });
    }

    public void logOut(){
        try {
            AirlineCompany.writeFiles();
            System.out.println("Writing to files Successful!!");
        } catch (Exception ex) {
            System.out.println("Writing to files Failed!!");
        }
        this.dispose();
        new StartUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateButton) {
            clearTable();
            createTable();
        }
        if(e.getSource() == removeFlightButton){
            admin.removeFlight((Flight) getSelectedRow(table1));
            JOptionPane.showMessageDialog(rootPanel, "Removed");
        }
        if(e.getSource() == comboBox1){
            if(comboBox1.getSelectedItem() == "All Routes"){
                clearTable();
                createTable();
                generateReportLabel.setText("Choose Route From Combo Box");
            }
            else {
                clearTable();
                Route route = (Route) comboBox1.getSelectedItem();
                System.out.println(route);
                createTable(route);
                generateReportLabel.setText(Arrays.toString(admin.generateReport(route)));
            }
        }
        if (e.getSource() == addFlightButton){
            FlightManager flightManager = new FlightManager(admin, new Flight());
        }
        if (e.getSource() == updateFlightButton){
            FlightManager flightManager = new FlightManager(admin, (Flight) getSelectedRow(table1), new Flight());
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
    public void clearTable(){
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); // notifies the JTable that the model has changed
    }
    public void createTable(){
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
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"Flight Number", "Arrival Time", "Departure Time", "Number of Seats", "Route", "Flight"}
        ));
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
    public void createTable(Route route){
        LinkedList<Flight> flights = admin.getFlightsFromRoute(route);
        Object[][] data = new Object[flights.size()][6];

        for(int j = 0; j < flights.size(); j++){
            data[j][0] = flights.get(j).getFlightNumber();
            data[j][1] = flights.get(j).getArrivalTime();
            data[j][2] = flights.get(j).getDepartureTime();
            data[j][3] = flights.get(j).getNumOfSeats();
            data[j][4] = flights.get(j).getRoute();
            data[j][5] = flights.get(j);
        }
        table1.setModel(new DefaultTableModel(
                data,
                new String[] {"Flight Number", "Arrival Time", "Departure Time", "Number of Seats", "Route", "Flight"}
        ));
//        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(5).setMaxWidth(0);
        table1.getColumnModel().getColumn(5).setMinWidth(0);
    }
}
