import java.util.ArrayList;

public class Bavard implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();
    private String username;

    private String password;

    public Bavard(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }
    @Override
    public void newMessage(PapotageEvent event) {
        System.out.println(this.username + " a bien recu le message.");
        System.out.println(event.getSujet());
        System.out.println(event.getText());

    }

    @Override
    public void generateMessage(String sujet, String text){
        PapotageEvent message = new PapotageEvent(this, sujet, text );
        for (PapotageListener p : destinataires){
            p.newMessage(message);
        }

    }

}
