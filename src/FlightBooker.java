import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Vector;

public class FlightBooker extends JFrame{
    private JTable table1;
    private JButton bookFlightButton;
    private JTextField baggageWeightField;
    private JPanel rootPanel;
    private JLabel time;

    private Flight flight;

    FlightBooker(Client client, Flight flight){
        this.flight = flight;

        this.setContentPane(rootPanel);
        this.setSize(900, 600);
//        this.pack();
        this.setTitle("Client UI");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        time.setText(LocalDateTime.now().toString());
        createTable();
        bookFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int baggageWeight;
                try {
                    baggageWeight = Integer.parseInt( baggageWeightField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Error: Baggage Weight entry can't be parsed!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                client.bookTicket(getChosenSeats(), flight, baggageWeight);
                JOptionPane.showMessageDialog(rootPanel, "tickets Booked", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    public LinkedList<Seat> getChosenSeats(){
        LinkedList<Seat> seats = new LinkedList<>();
        DefaultTableModel dm = (DefaultTableModel)table1.getModel();

        Vector<Vector> data = dm.getDataVector();

        for (Vector datum : data) {
            if ((Boolean) datum.get(3)) {
                seats.add((Seat) datum.lastElement());
            }
        }
        return seats;
    }


    public void createTable(){
        LinkedList<Seat> seats = flight.getAvailableSeats();
        Object[][] data = new Object[seats.size()][5];

        boolean book = false;

        for(int j = 0; j < seats.size(); j++){
            data[j][0] = seats.get(j).getSeatNumber();
            data[j][1] = seats.get(j).getSeatType();
            data[j][2] = seats.get(j).getPrice();
            data[j][3] = book;
            data[j][4] = seats.get(j);
        }
        table1.setModel(new DefaultTableModel(
                data, new Object[] {"Seat Number", "Seat Type", "Seat Price", "book?", "Seat"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        });
        table1.setAutoCreateRowSorter(true); // sorting of the rows on a particular column
        table1.getColumnModel().getColumn(4).setMaxWidth(0);
        table1.getColumnModel().getColumn(4).setMinWidth(0);
    }
}
