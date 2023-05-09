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




    /* Cette interface est double il y a "deux fenêtre" pour réaliser le switch entre les deux on utilise un bouton
    et la variable isDefaultInterface cette dernière est modifié à chaque changement de fenetre car c'est seulement un
    switch entre deux fenêtre
     */
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

    // Fenetre par defaut qui s'affiche quand un utilisateur se connecte
    public void defaultInterface(Bavard bav){

        // Initialisation de la fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        // Titre de l'interface
        JLabel title = new JLabel("Bavard : " + bav.getUsername());
        title.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        this.add("North",title);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainPanelTop = new JPanel();
        JButton themeBtn = new JButton("Theme");
        JButton userBtn = new JButton("Utilisateur");
        mainPanelTop.add(themeBtn);
        mainPanelTop.add(userBtn);
        mainPanel.add("North", mainPanelTop);

        themeBtn.addActionListener(e -> showThemeFrame());
        userBtn.addActionListener(e -> showUserFrame());
        // Apperçu des messages reçus sous forme de liste, avec possibilité de
        // faire defiler les messages s'ils sont trop nombreux
        ArrayList<HashMap<String,String>> messages = bav.getMessageReceived();
        JList messList = new JList<>(bav.getMessageListModel());
        JScrollPane messageSP = new JScrollPane(messList);
        mainPanel.add("Center", messageSP);
        this.add("Center", mainPanel);

        //Lecture des messages après appui dessus dans la liste
        messList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    //Traitement lorsque que l'on selectionne une élément de la liste
                    String selectedMessage = (String) messList.getSelectedValue();
                    System.out.println(selectedMessage);
                    String selectedSujet = selectedMessage.substring(selectedMessage.indexOf("[")+1,selectedMessage.indexOf("]"));
                    String selectedBavard = selectedMessage.substring(selectedMessage.indexOf("]")+2,selectedMessage.indexOf(":")-1);
                    String selectedText = selectedMessage.substring(selectedMessage.indexOf(":")+2,selectedMessage.length());
                    System.out.println(selectedText);
                    System.out.println(selectedBavard);
                    System.out.println(selectedSujet);
                    for (HashMap<String,String> m:messages){
                        //récupérer les premiers caractères du text pour comparer au premier caractère du text papotage selectionné
                        String text;
                        if (m.get("text").length()>=15) {
                            text = m.get("text").substring(0, 14) + "...";
                        }
                        else{
                            text = m.get("text");
                        }
                        if(m.get("sujet").equals(selectedSujet) && m.get("auteur").equals(selectedBavard) && text.equals(selectedText)){
                            String message = "Sujet :" + m.get("sujet") + "\n" + m.get("text") + "\n\n Ecrit par :" + m.get("auteur");

                            JOptionPane.showMessageDialog(null,message);

                        }
                    }
                }
            }
        });

        // Panel des boutons (deconnexion et ecriture)
        JPanel buttons = new JPanel();
        JButton disconnectBtn = new JButton("Deconnexion");
        JButton writeBtn = new JButton("Ecrire message"); //Bouton opur passer à l'interface d'ecriture
        buttons.add(disconnectBtn);
        buttons.add(writeBtn);

        // Affichage des bavards connectés sous forme de liste, avec possibilité de
        // faire defiler s'ils sont trop nombreux
        JList onlineList = new JList<>(batiment.getOnlineListModel());
        JScrollPane onlineSP = new JScrollPane(onlineList);
        JPanel onlinePanel = new JPanel();
        onlinePanel.setLayout(new BorderLayout());
        onlinePanel.add("North", new JLabel("Bavards connectés :"));
        onlinePanel.add("Center", onlineSP);

        // Panel de la partie droite de l'interface (boutons + liste bavards connectés)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add("North", buttons);
        rightPanel.add("Center", onlinePanel);
        this.add("East", rightPanel);

        // Actions des 2 boutons
        disconnectBtn.addActionListener(e-> disconnect());
        writeBtn.addActionListener(e -> changePage());

        this.setVisible(true);
    }

    //Fonction appelée lors de l'appui du bouton qui déconnecte l'utilisateur et donc envoie la notification
    public void disconnect(){
        //Déconnecter le bavard VOIR SI FAIRE AVEC event
        batiment.sendOfflineNotification(bav);
        batiment.removeOnlineBavard(bav);
        this.dispose();
    }
    /*Fonction permettant le changement de fenêtre lors de l'appui d'un bouton.
    On vérifie quelle fenêtre et ouverte et ou switch sur l'autre
     */
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

    /* Fonction qui ajoute le message renseigné lorsque l'on appui sur envoyer
    La fonction doit aussi changer la page et retourner à celle ou se trouve la liste de messages
     */
    public void addMessage(JTextField sujet,JTextArea texte,JComboBox menu){
        Theme theme = getTheme(menu);
        String strSujet = sujet.getText();
        String strTexte = texte.getText();
        if (strSujet.equals("") || strTexte.equals("")){
            JOptionPane.showMessageDialog(null,"Le contenu du sujet ou du texte est vide");
        }
        else{
            this.bav.generateMessage(strSujet,strTexte,this.bav.getUsername(),theme);
            //Retourner à la page de liste des messages à la fin de l'ajout
            changePage();
        }
    }

    /* Fenetre d'ecriture de message, qui s'affiche uniquement si l'utilisateur a cliqué
    sur le bouton writeBtn
     */
    public void writeMessageInterface(Bavard bav){

        // Initialisation de la fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        // Barre en haut de l'interface
        JPanel topBar = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel choicePanel = new JPanel(new GridLayout(2,2));
        JTextField sujetTf = new JTextField(20);
        JButton backBtn = new JButton("Retour"); //Bouton retour à l'interface principale
        JButton sendBtn = new JButton("Envoyer"); //Bouton retour + envoi message
        JComboBox menu = new JComboBox<>(Theme.values());
        topBar.add(backBtn);
        choicePanel.add(new JLabel("Sujet : "));
        choicePanel.add(sujetTf);
        choicePanel.add(new JLabel("Theme : "));
        choicePanel.add(menu);
        topBar.add(choicePanel);
        topBar.add(sendBtn);
        menu.addActionListener(e -> getTheme(menu));
        this.add("North", topBar);

        // Zone de texte pour l'ecriture du message
        JTextArea text = new JTextArea();
        this.add("Center", text);

        // Actions des boutons
        backBtn.addActionListener(e-> changePage());
        sendBtn.addActionListener(e-> addMessage(sujetTf,text,menu));

        this.setVisible(true);
    }

    public Theme getTheme(JComboBox menu){
        Theme theme = (Theme) menu.getSelectedItem();
        return theme;
    }

    public void showThemeFrame(){
        ThemeFrame tf = new ThemeFrame(bav);
    }

    public void showUserFrame(){
        UserFrame uf = new UserFrame(batiment, bav);
    }



}
