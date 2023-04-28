import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login extends JFrame{
    private JButton login;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel loginPanel;
    private JRadioButton clientRadioButton;
    private JRadioButton adminRadioButton;

    public Login() {

        this.setContentPane(loginPanel);
//        this.setSize(400, 400);
        this.pack();
        this.setTitle("Log In window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(login, "button been pressed");

                String password = String.valueOf(passwordField1.getPassword());
                if(clientRadioButton.isSelected()) {
                    try {
                        new ClientUI(Objects.requireNonNull(AirlineCompany.getClientWithAccount(textField1.getText(), password)));
                        JOptionPane.showMessageDialog(login, "account found");
                    } catch (NullPointerException NPE){
                        JOptionPane.showMessageDialog(login, "account not found");
                    }
                }
                if(adminRadioButton.isSelected()) {
                    try {
                        new AdminUI(Objects.requireNonNull(AirlineCompany.getAdminWithAccount(textField1.getText(), password)));
                        JOptionPane.showMessageDialog(login, "account found");
                    } catch (NullPointerException NPE){
                        JOptionPane.showMessageDialog(login, "account not found");
                    }
                }
            }
        });
    }
}
