import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BavardInterface extends JFrame {
    private Bavard bav;

    private boolean isDefaultInterface;

    private Batiment batiment;

    public BavardInterface(Bavard bav, boolean defaultInterface,Batiment bat){
        this.bav = bav;
        this.batiment = bat;
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

        JLabel title = new JLabel("Bavard : " + bav.getUsername());
        title.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        this.add("North",title);

        ArrayList<HashMap<String,String>> messages = bav.getMessageReceived();
        JList messList = new JList<>(bav.getMessageListModel());
        JScrollPane messageSP = new JScrollPane(messList);
        this.add("Center", messageSP);

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





        JPanel rightPanel = new JPanel();
        JPanel buttons = new JPanel();
        JButton disconnectBtn = new JButton("Deconnexion");
        JButton writeBtn = new JButton("Ecrire message"); //Bouton opur passer à l'interface d'ecriture
        buttons.add(disconnectBtn);
        buttons.add(writeBtn);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add("North", buttons);
        JList onlineList = new JList<>(batiment.getOnlineListModel());
        JScrollPane onlineSP = new JScrollPane(onlineList);
        JPanel onlinePanel = new JPanel();
        onlinePanel.setLayout(new BorderLayout());
        onlinePanel.add("North", new JLabel("Bavards connectés :"));
        onlinePanel.add("Center", onlineSP);
        rightPanel.add("Center", onlinePanel);
        this.add("East", rightPanel);
        disconnectBtn.addActionListener(e-> disconnect());
        writeBtn.addActionListener(e -> changePage());


        this.setVisible(true);
    }

    public void disconnect(){
        //Déconnecter le bavard VOIR SI FAIRE AVEC event
        batiment.sendOfflineNotification(bav);
        this.dispose();
    }
    public void changePage(){

        boolean defaultInterface;
        if (this.isDefaultInterface){
            this.setDefaultInterface(false);
            defaultInterface = false;
        }
        else{
            this.setDefaultInterface(true);
            defaultInterface = true;
        }
        BavardInterface bi = new BavardInterface(this.bav,defaultInterface,batiment);
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
