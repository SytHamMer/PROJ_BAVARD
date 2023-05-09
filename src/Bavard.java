import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Bavard implements PapotageListener, OnlineBavardListener, OfflineBavardListener {

    // Attributs
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();
    private ArrayList<OnlineBavardListener> onlineBavards = new ArrayList<>();
    private ArrayList<OfflineBavardListener> offlineBavards = new ArrayList<>();
    private String username;
    private String password;
    private boolean isConnected;
    private ArrayList<HashMap<String,String>> messageReceived = new ArrayList<>();
    private DefaultListModel<String> messageListModel = new DefaultListModel<>();

    private ArrayList<String> themes = new ArrayList<>();

    // Constructeur
    public Bavard(String username, String password) {
        this.username = username;
        this.password = password;

    }

    // getters & setters
    public String getPassword() {
        return password;
    }

    public DefaultListModel<String> getMessageListModel() {
        return messageListModel;
    }


    public ArrayList<HashMap<String, String>> getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(ArrayList<HashMap<String, String>> messageReceived) {
        this.messageReceived = messageReceived;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getThemes() {
        return themes;
    }

    public void setThemes() {
        for(Theme theme : Theme.values()){
            this.themes.add(theme.name());
        }
    }

    /* Fonction qui permet d'ajouter le message reçu à la liste de hashmap messageReceived,
        ainsi que sous forme de string dans la defaultModelList messageListModel, utilisee pour
        afficher les messages dans l'interface
         */
    public void addMessageReceived(HashMap<String,String> m){
        this.messageReceived.add(m);
        System.out.println(m);
        String sujet = m.get("sujet");
        String auteur = m.get("auteur");
        String texte = m.get("text");
        if (texte.length()>=15) {
            texte = texte.substring(0, 14) + "...";
        }
        this.messageListModel.addElement("[" + sujet + "] " + auteur + " : " + texte);
    }

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }

    public void addOnlineBavardListener(OnlineBavardListener obl){onlineBavards.add(obl);}
    public void addOfflineBavardListener(OfflineBavardListener obl){offlineBavards.add(obl);}



    public void removePapotageListener(PapotageListener pl){
        if(this.destinataires.contains(pl)){
            this.destinataires.remove(pl);
            System.out.println("Concierge bien retiré de ce bavard");
        }
        else{
            System.out.println("Concierge n'est pas connecté");
        }
    }
    @Override
    /* Récupère le message et l'affiche dans la console et l'ajoute aux messages recus
    Avant de récupérer on regarde si le thème du message est présent dans la liste des thèmes
    autorisé par le bavard

     */
    public void newMessage(PapotageEvent event) {

        for (String bavTheme : themes){
            String theme = event.getTheme().name();
            if(bavTheme == theme){
                System.out.println(this.username + " a bien recu le message.");
                System.out.println(event.getSujet());
                System.out.println(event.getText());
                System.out.println(event.getTheme().name());
                System.out.println("Ecrit par : "+ event.getBavard());
                String text = event.getText();
                String sujet = event.getSujet();
                String author = event.getBavard();
                HashMap<String,String> message = new HashMap<>();
                message.put("sujet",sujet);
                message.put("text",text);
                message.put("auteur",author);
                message.put("theme",theme);
                this.addMessageReceived(message);
                break;

            }
        }



    }
    /* Génère un message pour le PapotageEvent et récupère les message avec saveMessage

     */
    @Override
    public HashMap<String, String> saveMessage(PapotageEvent event) {
        return null;
    }


    @Override
    public void generateMessage(String sujet, String text,String author,Theme theme){
        PapotageEvent message = new PapotageEvent(this, sujet, text,this.getUsername(),theme);
        for (PapotageListener p : destinataires){
            p.saveMessage(message);
        }

    }


    /* Que ce soit OnlineBavard ou OfflineBavard ces fonctions fonctionne de manière similaire
    le but est d'avertir tous les autres Bavard de la connexion ou deconnexion et d'être avertis en cas de connexion
    ou déconnexion de tout autre bavard
     */
    @Override
    public void newOnlineBavard(OnlineBavardEvent obl) {
        Bavard b = obl.getB();
        String nom = b.getUsername();
        System.out.println("Le bavard : " + nom + " s'est connecté.");
        }

    public void generateNewOnlineBavard(Bavard b) {
        OnlineBavardEvent online = new OnlineBavardEvent(this, b);
        for (OnlineBavardListener obl : onlineBavards) {
            obl.newOnlineBavard(online);
        }
    }

    @Override
    public void newOfflineBavard(OfflineBavardEvent obl) {
        Bavard b = obl.getB();
        String nom = b.getUsername();
        System.out.println("Le bavard : " + nom + " s'est déconnecté.");
    }

    public void generateNewOfflineBavard(Bavard b) {
        OfflineBavardEvent offline = new OfflineBavardEvent(this, b);
        for (OfflineBavardListener obl : offlineBavards) {
            obl.newOfflineBavard(offline);
        }
    }
}

