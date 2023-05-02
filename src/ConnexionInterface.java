import javax.swing.*;
import java.awt.*;

public class ConnexionInterface extends JFrame {

    public ConnexionInterface(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));
        this.setSize(500, 320);
        JLabel loginLabel = new JLabel("login : ");
        JTextField loginTf = new JTextField(20);
        JLabel pwdLabel = new JLabel("Password : ");
        JPasswordField pwdTf = new JPasswordField(20);
        JButton signIn = new JButton("Sign in");
        JButton signUp = new JButton("Sign up");

        JPanel loginPanel = new JPanel();
        loginPanel.add(loginLabel);
        loginPanel.add(loginTf);

        JPanel pwdPanel = new JPanel();
        pwdPanel.add(pwdLabel);
        pwdPanel.add(pwdTf);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signIn);
        buttonPanel.add(signUp);

        this.add(loginPanel);
        this.add(pwdPanel);
        this.add(buttonPanel);

        this.setVisible(true);



    }
}
