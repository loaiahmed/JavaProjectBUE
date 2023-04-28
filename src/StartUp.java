import javax.swing.*;

public class StartUp extends JFrame{
//    private JTable table1;
    private JPanel rootPanel;
    private JButton logInButton;
    private JButton signUpButton;
    private JButton button3;
    private JTextPane textPane;

    public StartUp(){


        this.setContentPane(rootPanel);
        this.setSize(600, 400);
//        this.pack();
        this.setTitle("Start up window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        logInButton.addActionListener(e -> new Login());
        signUpButton.addActionListener(e -> new SignUp());
    }
}
