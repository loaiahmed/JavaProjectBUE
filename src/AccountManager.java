import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AccountManager extends JFrame {
    private JTextField oldPasswField;
    private JTextField newPasswField;
    private JTextField newUserNameField;
    private JTextField oldEmailField;
    private JTextField newEmailField;
    private JButton changePasswordButton;
    private JButton changeUsernameButton;
    private JButton changeEmailButton;
    private JScrollBar scrollBar1;
    private JPanel rootPanel;
    private JTextField confPasswField;

    public AccountManager(Client client) {

    this.setContentPane(rootPanel);
//    this.setSize(1000, 700);
        this.pack();
    this.setTitle("Account Manager UI");
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    changePasswordButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(Objects.equals(client.getPassword(), oldPasswField.getText())){
                if(Objects.equals(newPasswField.getText(), confPasswField.getText())){
                    client.setPassword(confPasswField.getText());
                    JOptionPane.showMessageDialog(rootPanel, "Password Set");
                }
                JOptionPane.showMessageDialog(rootPanel, "new Password and confirm Password does not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(rootPanel, "old Password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
    changeUsernameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            client.setUsername(newUserNameField.getText());
        }
    });
    changeEmailButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(Objects.equals(client.getEmail(), oldEmailField.getText())){
                client.setEmail(newEmailField.getText());
            }
            JOptionPane.showMessageDialog(rootPanel, "old Email is wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
}
}
