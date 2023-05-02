import java.util.ArrayList;
import java.util.HashMap;

public class Bavard implements PapotageListener, OnlineBavardListener {
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    private ArrayList<OnlineBavardListener> onlineBavards = new ArrayList<>();

    private String username;

    private String password;

    private boolean isConnected;

    private ArrayList<HashMap<String,String>> messageReceived = new ArrayList<>();

    public Bavard(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public ArrayList<HashMap<String, String>> getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(ArrayList<HashMap<String, String>> messageReceived) {
        this.messageReceived = messageReceived;
    }

    public void addMessageReceived(HashMap<String,String> messageReceived){
        this.messageReceived.add(messageReceived);
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

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }

    public void addOnlineBavardListener(OnlineBavardListener obl){onlineBavards.add(obl);}


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
    public void newMessage(PapotageEvent event) {
        System.out.println(this.username + " a bien recu le message.");
        System.out.println(event.getSujet());
        System.out.println(event.getText());
        System.out.println("Ecrit par : "+ event.getBavard());
        String text = event.getText();
        String sujet = event.getSujet();
        String author = event.getBavard();
        HashMap<String,String> message = new HashMap<>();
        message.put("sujet",sujet);
        message.put("text",text);
        message.put("auteur",author);
        this.addMessageReceived(message);


    }

    @Override
    public HashMap<String, String> saveMessage(PapotageEvent event) {
        return null;
    }


    @Override
    public void generateMessage(String sujet, String text,String author){
        PapotageEvent message = new PapotageEvent(this, sujet, text,this.getUsername() );
        for (PapotageListener p : destinataires){
            p.saveMessage(message);
        }

    }

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
}

