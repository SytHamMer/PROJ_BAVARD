import javax.swing.*;
import java.awt.*;

public class ConnexionInterface extends JFrame {

    // Attributs
    private Batiment batiment;
    private JTextField loginTf;
    private JPasswordField pwdTf;
    public ConnexionInterface(Batiment batiment){

        // Initialisation de la fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));
        this.setSize(500, 320);
        this.batiment = batiment;

        // Panel login (Label + textfield)
        JLabel loginLabel = new JLabel("Login : ");
        this.loginTf = new JTextField(20);
        JPanel loginPanel = new JPanel();
        loginPanel.add(loginLabel);
        loginPanel.add(loginTf);

        // Password panel (label + textfield)
        JLabel pwdLabel = new JLabel("Password : ");
        this.pwdTf = new JPasswordField(20);
        JPanel pwdPanel = new JPanel();
        pwdPanel.add(pwdLabel);
        pwdPanel.add(pwdTf);

        // Buttons panel
        JButton signIn = new JButton("Sign in");
        JButton signUp = new JButton("Sign up");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(signIn);
        buttonPanel.add(signUp);

        // actions des boutons
        signIn.addActionListener(e-> connectBavard());
        signUp.addActionListener(e -> createBavard());

        // ajout des panels a la fenetre principale
        this.add(loginPanel);
        this.add(pwdPanel);
        this.add(buttonPanel);

        // affichage de la fenetre
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

    /* Fonction appelée lors de l'appui sur le bouton "signUp" verifie si le nom d'utilisateur est déjà pris ou non
    et créé un utilisateur et le connecte sinon
     */
    public void createBavard(){

        String login = loginTf.getText();
        String password = pwdTf.getText();
        if(batiment.getBavard(login) == null){
                batiment.addBavards(login,password);
                //Appel de la fonction connectBavard() uniquement si ce dernier n'existait pas avant
                connectBavard();
            }
            else{
                JOptionPane.showMessageDialog(null,"Ce login existe déjà, sois original, appelle toi autrement, des bisous");
            }
    }



}
