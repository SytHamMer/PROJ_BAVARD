import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BavardInterface extends JFrame {
    private Bavard bav;

    private boolean isDefaultInterface;

    public BavardInterface(Bavard bav, boolean defaultInterface){
        this.bav = bav;
        this.isDefaultInterface = defaultInterface;
        if(isDefaultInterface){
            defaultInterface(bav);
        }else{
            writeMessageInterface(bav);
        }
    }



    public boolean isDefaultInterface() {
        return isDefaultInterface;
    }

    public void setDefaultInterface(boolean defaultInterface) {
        isDefaultInterface = defaultInterface;
    }

    public Bavard getBav() {
        return bav;
    }

    public void setBav(Bavard bav) {
        this.bav = bav;
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

        //Lecture des messages après appui dessus dans la liste

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







        JButton writeBtn = new JButton("Ecrire message"); //Bouton opur passer à l'interface d'ecriture
        this.add("East", writeBtn);

        writeBtn.addActionListener(e -> changePage());


        this.setVisible(true);
    }

    public void changePage(){
        System.out.println("changePage");
        boolean defaultInterface;
        if (this.isDefaultInterface){
            System.out.println("Going to add subject");
            this.setDefaultInterface(false);
            defaultInterface = false;
        }
        else{
            System.out.println("Going to read subject");
            this.setDefaultInterface(true);
            defaultInterface = true;
        }
        BavardInterface bi = new BavardInterface(this.bav,defaultInterface);
        this.setVisible(false);
        bi.setVisible(true);
    }

    public void addMessage(JTextField sujet,JTextArea texte){
        String strSujet = sujet.getText();
        String strTexte = texte.getText();

        this.bav.generateMessage(strSujet,strTexte,this.bav.getUsername());

        //Retourner à la page de liste des messages à la fin de l'ajout
        changePage();
    }
    public void writeMessageInterface(Bavard bav){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        JPanel topBar = new JPanel();
        JTextField sujetTf = new JTextField(20);
        JButton backBtn = new JButton("Retour"); //Bouton retour à l'interface principale
        JButton sendBtn = new JButton("Envoyer"); //Bouton retour + envoi message
        backBtn.addActionListener(e-> changePage());
        topBar.add(backBtn);
        topBar.add(new JLabel("Sujet : "));
        topBar.add(sujetTf);
        topBar.add(sendBtn);
        this.add("North", topBar);
        JTextArea text = new JTextArea();
        this.add("Center", text);
        sendBtn.addActionListener(e-> addMessage(sujetTf,text));
        this.setVisible(true);
    }
}
