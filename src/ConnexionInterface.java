import javax.swing.*;
import java.awt.*;

public class ConnexionInterface extends JFrame {
    private Batiment batiment;
    public ConnexionInterface(Batiment batiment){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));
        this.setSize(500, 320);
        JLabel loginLabel = new JLabel("login : ");
        JTextField loginTf = new JTextField(20);
        JLabel pwdLabel = new JLabel("Password : ");
        JPasswordField pwdTf = new JPasswordField(20);
        JButton signIn = new JButton("Sign in");
        JButton signUp = new JButton("Sign up");


        signIn.addActionListener(e-> {
            String login = loginTf.getText();

            if (batiment.getBavard(login) != null){
                //Se connecter
                //Renvyer à la fenêtre de tous ces messages
                batiment.getBavard(login).setConnected(true);
                System.out.println(login + " est connecté");
            }
            else{
                JOptionPane.showMessageDialog(null,"Ce login n'existe pas, réessayez");

            }
        });
        signUp.addActionListener(e -> {

            String login = loginTf.getText();
            String password = pwdTf.getText();
            if( batiment.isLastAddBavardTry()){
                batiment.addBavards(login,password,true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Ce login existe déjà, sois original, appelle toi autrement, des bisous");
            }


        });

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
