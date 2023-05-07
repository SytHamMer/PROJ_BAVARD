import javax.swing.*;
import java.awt.*;

public class ConnexionInterface extends JFrame {
    private Batiment batiment;
    private JTextField loginTf;
    private JPasswordField pwdTf;
    public ConnexionInterface(Batiment batiment){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));
        this.setSize(500, 320);
        this.batiment = batiment;
        JLabel loginLabel = new JLabel("login : ");
        this.loginTf = new JTextField(20);
        JLabel pwdLabel = new JLabel("Password : ");
        this.pwdTf = new JPasswordField(20);
        JButton signIn = new JButton("Sign in");
        JButton signUp = new JButton("Sign up");


        signIn.addActionListener(e-> connectBavard());
        signUp.addActionListener(e -> createBavard());

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


    /* Fonction qui s'execute lorsqu'on appuie sur le bouton "signIn" elle verifie l'identité de l'utilisateur
    Différence si c'est le concierge, ainsi que les mots de passes. Messages d'erreurs differents en cas de mauvaise
    saisie
     */
    public void connectBavard(){
        String login = loginTf.getText();
        String password = pwdTf.getText();

        //Modifier cas plusieurs concierges
        if (login.equals("Concierge")){
            if (password.equals(batiment.getConcierge().getPassword())){
                //Se connecter à la page concierge
                System.out.println("Concierge connecté");
                ConciergeInterface conciergeInterface = new ConciergeInterface(batiment);
                conciergeInterface.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Ce n'est pas le mot de passe du concierge");
            }
        }
        else{
            if (batiment.getBavard(login) != null){
                if(batiment.getBavard(login).getPassword().equals(password)){
                    //Se connecter
                    //Renvoyer à la fenêtre de tous ces messages
                    batiment.getBavard(login).setConnected(true);
                    batiment.sendOnlineNotification(batiment.getBavard(login));
                    batiment.addOnlineBavard(batiment.getBavard(login));
                    System.out.println(login + " est connecté");
                    System.out.println(batiment.getOnlineBavard());
                    BavardInterface bavardInterface = new BavardInterface(batiment.getBavard(login),true,batiment);
                    bavardInterface.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ce mot de passe n'est pas bon");
                }

            }
            else{
                JOptionPane.showMessageDialog(null,"Ce login n'existe pas, réessayez");

            }
        }
    }

    /* Fonction appelé lors de l'appui sur le bouton "signUp" verifie si le nom d'utilisateur est déjà pris ou non
    et créé un utilisateur et le connecte sinon
     */
    public void createBavard(){

        String login = loginTf.getText();
        String password = pwdTf.getText();
        System.out.println(login);
        if( batiment.isLastAddBavardTry()){
            if(batiment.getBavard(login) == null){
                batiment.addBavards(login,password);
                //Appel de la fonction connectBavard() uniquement si ce dernier n'existait pas avant
                connectBavard();
            }
            else{
                JOptionPane.showMessageDialog(null,"Ce login existe déjà, sois original, appelle toi autrement, des bisous");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Ce login existe déjà, sois original, appelle toi autrement, des bisous");
        }


    }



}
