import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConciergeInterface extends JFrame {
    private Batiment bat;
    private Concierge concierge;
    public ConciergeInterface(Batiment bat){
        this.bat = bat;
        this.concierge = bat.getConcierge();
        System.out.println("e");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);

        JLabel title = new JLabel("Interface Concierge");
        this.add(title);
        ArrayList<HashMap<String,String>> messages = concierge.getMessageReceived();
        ArrayList<String> modeleList = new ArrayList<>();
        for(HashMap<String,String> m : messages){
            String sujet = m.get("sujet");
            String auteur = m.get("auteur");
            String texte = m.get("text");
            modeleList.add("[" + sujet + "] " + auteur + " : " + texte);
        }
        JList messList = new JList<>(modeleList.toArray());
        this.add(messList);



        this.setVisible(true);
    }
}
