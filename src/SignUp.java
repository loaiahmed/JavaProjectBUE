import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SignUp extends JFrame{
    private JTextField userNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField checkPasswordField;
    private JButton signUpButton;
    private JRadioButton adminRadioButton;
    private JRadioButton clientRadioButton;
    private JPanel rootPanel;

    public SignUp() {

//        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize((int)size.getWidth()/3, (int)size.getHeight()/4);
        this.setContentPane(rootPanel);
        this.pack();
        this.setTitle("Sign Up window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(passwordField.getPassword());

                if(userNameField.getText().trim().isEmpty() || password.trim().isEmpty()){
                    JOptionPane.showMessageDialog(rootPanel, "a field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!checkEmail()){
                    JOptionPane.showMessageDialog(rootPanel, "Email is not right", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!(Arrays.equals(passwordField.getPassword(), checkPasswordField.getPassword()))){
                    JOptionPane.showMessageDialog(rootPanel, "Password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    JOptionPane.showConfirmDialog(rootPanel, "Confirm SignUp?");
                }

                if(adminRadioButton.isSelected()){
                    Admin admin = new Admin( userNameField.getText(), emailField.getText(),  password);
                    AirlineCompany.allAdmins.add(admin);

                    JOptionPane.showMessageDialog(rootPanel, "Account Created, return to setup window and log in");
                }
                else if(clientRadioButton.isSelected()){
                    Client client = new Client( userNameField.getText(), emailField.getText(), password);
                    client.createAccount();

                    JOptionPane.showMessageDialog(rootPanel, "Account Created, return to setup window and log in");
                }
                else {
                    JOptionPane.showMessageDialog(rootPanel, "SignUp failed: Select a checkBox", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public boolean checkEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(emailField.getText()).matches();
    }
}

/*
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        (pat.matcher(email).matches())
 */
