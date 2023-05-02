import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BavardInterface extends JFrame {
    private Bavard bav;

    public BavardInterface(Bavard bav){
        this.bav = bav;
        int i=15;
        if (i==0) defaultInterface(bav);
        if (i!=0) writeMessageInterface(bav);
    }

    public void defaultInterface(Bavard bav){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        this.add("North", new JLabel("Bavard : " + bav.getUsername()));
        //JLabel title = new JLabel("Interface Concierge");

        ArrayList<HashMap<String,String>> messages = bav.getMessageReceived();
        ArrayList<String> modeleList = new ArrayList<>();
        for(HashMap<String,String> m : messages){
            System.out.println(m);
            String sujet = m.get("sujet");
            String auteur = m.get("auteur");
            String texte = m.get("text");
            if (texte.length()>=15) {
                texte = texte.substring(0, 14) + "...";
            }
            modeleList.add("[" + sujet + "] " + auteur + " : " + texte);
        }
        JList messList = new JList<>(modeleList.toArray());
        this.add("West", messList);

        JButton writeBtn = new JButton("Ecrire message"); //Bouton opur passer à l'interface d'ecriture
        this.add("East", writeBtn);


        this.setVisible(true);
    }

    public void writeMessageInterface(Bavard bav){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        JPanel topBar = new JPanel();
        JTextField sujetTf = new JTextField(20);
        JButton backBtn = new JButton("Retour"); //Bouton retour à l'interface principale
        JButton sendBtn = new JButton("Envoyer"); //Bouton retour + envoi message
        topBar.add(backBtn);
        topBar.add(new JLabel("Sujet : "));
        topBar.add(sujetTf);
        topBar.add(sendBtn);
        this.add("North", topBar);
        JTextArea text = new JTextArea();
        this.add("Center", text);
        this.setVisible(true);
    }
}
