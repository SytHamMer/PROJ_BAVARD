import java.util.ArrayList;
import java.util.HashMap;

public class Concierge implements PapotageListener, OnlineBavardListener, OfflineBavardListener {
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    private ArrayList<OnlineBavardListener> onlineBavards = new ArrayList<>();
    private ArrayList<OfflineBavardListener> offlineBavards = new ArrayList<>();
    private String pseudo;

    private String password;
    private ArrayList<HashMap<String, String>> messageReceived = new ArrayList<>();

    public Concierge(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public ArrayList<OnlineBavardListener> getOnlineBavards() {
        return onlineBavards;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<HashMap<String, String>> getMessageReceived() {
        return messageReceived;
    }

    public void addMessageReceived(HashMap<String, String> messageReceived) {
        this.messageReceived.add(messageReceived);
    }

    public void addPapotageListener(PapotageListener pl) {
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl) {
        if (this.destinataires.contains(pl)) {
            this.destinataires.remove(pl);
            System.out.println("Bavard bien retiré du concierge");
        } else {
            System.out.println("Ce bavard n'est pas connecté");
        }
    }

    public void addOnlineBavardListener(OnlineBavardListener obl){onlineBavards.add(obl);}
    public void addOfflineBavardListener(OfflineBavardListener obl){offlineBavards.add(obl);}



    @Override
    public void newMessage(PapotageEvent event) {
    }

    @Override
    /* Le concierge sauvegarde sous la forme d'un dictionnaire toutes les informations relatives
    au message recu et l'affiche dans la console
     */
    public HashMap<String, String> saveMessage(PapotageEvent event) {
        //MODIFIER ICI
        HashMap<String, String> bavardage = new HashMap<>();
        String sujet = event.getSujet();
        String text = event.getText();
        String author = event.getBavard();
        bavardage.put("sujet", sujet);
        bavardage.put("text", text);
        bavardage.put("auteur", author);

        System.out.println("Le concierge a bien recu le message.");
        System.out.println(sujet);
        System.out.println(text);
        System.out.println("FROM:" + author);


        for (PapotageListener p : destinataires) {
            p.newMessage(event);

        }

        this.addMessageReceived(bavardage);
        return bavardage;
    }

    @Override
    public void generateMessage(String sujet, String text, String author) {
        PapotageEvent message = new PapotageEvent(this, sujet, text, author);
        for (PapotageListener p : destinataires) {
            p.newMessage(message);

        }
    }


    @Override
    public void newOnlineBavard(OnlineBavardEvent obl) {
        Bavard b = obl.getB();
        String nom = b.getUsername();
        System.out.println("Le bavard : " + nom + " s'est connecté.");
        OnlineBavardEvent online = new OnlineBavardEvent(this, b);
        for (OnlineBavardListener onlineOne : onlineBavards) {
            onlineOne.newOnlineBavard(online);
        }

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
        OfflineBavardEvent offline = new OfflineBavardEvent(this, b);
        for (OfflineBavardListener offlineOne : offlineBavards) {
            offlineOne.newOfflineBavard(offline);
        }

    }
}
