import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.net.URL;
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
        messList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    //Traitement lorsque que l'on selectionne une élément de la liste
                    String selectedMessage = (String) messList.getSelectedValue();
                    System.out.println(selectedMessage);
                    String selectedSujet = selectedMessage.substring(selectedMessage.indexOf("[")+1,selectedMessage.indexOf("]"));
                    System.out.println(selectedSujet);
                    for (HashMap<String,String> m:messages){
                        if(m.get("sujet").equals(selectedSujet)){
                            String message = "Sujet :" + m.get("sujet") + "\n" + m.get("text") + "\n\n Ecrit par :" + m.get("auteur");

                            JOptionPane.showMessageDialog(null,message);

                        }
                    }
                }
            }
        });

        this.add(messList);



        this.setVisible(true);
    }
}
