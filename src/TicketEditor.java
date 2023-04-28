import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketEditor extends JFrame{
    private JButton saveButton;
    private JTextField baggageWeightField;
    private JTextField seatNumField;
    private JPanel rootPanel;

    public TicketEditor(Ticket ticket) {

    this.setContentPane(rootPanel);
    this.setSize(800, 400);
//        this.pack();
    this.setTitle("Client UI");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int baggageWeight;
            int seatNum;
            try {
                baggageWeight = Integer.parseInt(baggageWeightField.getText());
                seatNum = Integer.parseInt(seatNumField.getText());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(rootPanel, "Error: numbers can't be parsed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!(ticket.getPassenger().updateTicket(ticket, baggageWeight, seatNum))) {
                JOptionPane.showMessageDialog(rootPanel, "Update Failed, Seat is Already Reserved!!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Ticket Updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
}
}
