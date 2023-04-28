import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class FlightManager extends JFrame{
    private JTextField arrivalTimeField;
    private JTextField departureField;
    private JTextField numOfSeatsField;
    private JTextField numOfVipField;
    private JTextField airportNameField;
    private JTextField airportCityField;
    private JTextField airportCountryField;
    private JTextField airportNameField1;
    private JTextField airportCityField1;
    private JTextField airportCountryField1;
    private JPanel rootPanel;
    private JTextField ecoPriceField;
    private JTextField vipPriceField;
    private JComboBox comboBox1;
    private JButton addFlightWithExistingButton;
    private JButton addFlightWithNewButton;
    private Flight flight;
    private Flight oldFlight;
    private Admin admin;

    FlightManager(Admin admin, Flight flight){                  // Sorry in advance for repeating myself, I was just so damn sleepy
        this.flight = flight;
        this.admin = admin;

        this.setContentPane(rootPanel);
        this.setSize(900, 500);
//        this.pack();
        this.setTitle("Flight Manager UI");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);


        createComboBox();
        addFlightWithNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                LocalDateTime arrivalTime;
                LocalDateTime departureTime;
                int numOfSeats;
                int numOfVip;
                int ecoPrice;
                int vipPrice;
                try {
                    arrivalTime = LocalDateTime.parse(arrivalTimeField.getText());
                    departureTime = LocalDateTime.parse(departureField.getText());
                    numOfSeats = Integer.parseInt( numOfSeatsField.getText());
                    numOfVip = Integer.parseInt( numOfVipField.getText());
                    ecoPrice = Integer.parseInt( ecoPriceField.getText());
                    vipPrice = Integer.parseInt( vipPriceField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Error: given Time or Numbers can't be parsed!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(numOfVip > numOfSeats){
                    JOptionPane.showMessageDialog(rootPanel, "Error numOfVip is greater than numOfSeats!! ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                flight.setArrivalTime(arrivalTime);
                flight.setDepartureTime(departureTime);

                Seat[] seats = new Seat[numOfSeats];
                flight.setSeats(seats);
                for(int i = 0; i < numOfSeats; i++){
                    if(i < numOfVip) {
                        seats[i] = new Seat(i, "VIP", vipPrice);
                    }
                    else {
                        seats[i] = new Seat(i, "ECO", ecoPrice);
                    }
                }

                if (actionEvent.getSource() == addFlightWithNewButton) {
                    Route route = new Route(new Airport(airportNameField.getText(), airportCityField.getText(), airportCountryField.getText()),
                            new Airport(airportNameField1.getText(), airportCityField1.getText(), airportCountryField1.getText()));

                    flight.setRoute(route);
                    admin.addFlight(flight);
                    JOptionPane.showMessageDialog(rootPanel, "Added", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (actionEvent.getSource() == addFlightWithExistingButton) {
                    createComboBox();
                    flight.setRoute((Route) comboBox1.getSelectedItem());
                    admin.addFlight(flight);
                    JOptionPane.showMessageDialog(rootPanel, "Added", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    FlightManager(Admin admin, Flight oldFlight, Flight flight){
        this.flight = flight;
        this.admin = admin;
        this.oldFlight = oldFlight;

        this.setContentPane(rootPanel);
        this.setSize(900, 500);
//        this.pack();
        this.setTitle("Flight Manager UI");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.addFlightWithNewButton.setText("Update Flight with new Route (Text Fields)");
        this.addFlightWithExistingButton.setText("Update Flight with existing Route (ComboBox)");

        createComboBox();
        addFlightWithNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LocalDateTime arrivalTime;
                LocalDateTime departureTime;
                int numOfSeats;
                int numOfVip;
                int ecoPrice;
                int vipPrice;
                try {
                    arrivalTime = LocalDateTime.parse(arrivalTimeField.getText());
                    departureTime = LocalDateTime.parse(departureField.getText());
                    numOfSeats = Integer.parseInt( numOfSeatsField.getText());
                    numOfVip = Integer.parseInt( numOfVipField.getText());
                    ecoPrice = Integer.parseInt( ecoPriceField.getText());
                    vipPrice = Integer.parseInt( vipPriceField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Error: given Time or Numbers can't be parsed!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(numOfVip > numOfSeats){
                    JOptionPane.showMessageDialog(rootPanel, "Error numOfVip is greater than numOfSeats!! ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                flight.setArrivalTime(arrivalTime);
                flight.setDepartureTime(departureTime);

                Seat[] seats = new Seat[numOfSeats];
                flight.setSeats(seats);
                for(int i = 0; i < numOfSeats; i++){
                    if(i < numOfVip) {
                        seats[i] = new Seat(i, "VIP", vipPrice);
                    }
                    else {
                        seats[i] = new Seat(i, "ECO", ecoPrice);
                    }
                }

                if (actionEvent.getSource() == addFlightWithNewButton) {
                    Route route = new Route(new Airport(airportNameField.getText(), airportCityField.getText(), airportCountryField.getText()),
                            new Airport(airportNameField1.getText(), airportCityField1.getText(), airportCountryField1.getText()));

                    flight.setRoute(route);
                    admin.updateFlight1(oldFlight, flight);
                    JOptionPane.showMessageDialog(rootPanel, "Updated", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (actionEvent.getSource() == addFlightWithExistingButton) {
                    createComboBox();
                    flight.setRoute((Route) comboBox1.getSelectedItem());
                    admin.updateFlight1(oldFlight, flight);
                    JOptionPane.showMessageDialog(rootPanel, "Updated", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void createComboBox(){
        Route[] routes = Flight.routes.toArray(new Route[0]);
        for (Route route : routes) {
            comboBox1.addItem(route);
        }
    }

    public Flight getFlight() {
        return flight;
    }
}
