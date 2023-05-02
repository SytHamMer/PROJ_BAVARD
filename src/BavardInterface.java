import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BavardInterface extends JFrame {
    private Bavard bav;

    public BavardInterface(Bavard bav){
        this.bav = bav;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);

        JLabel title = new JLabel("Interface Concierge");
        this.add(title);
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
        this.add(messList);



        this.setVisible(true);
    }
}
