import java.util.ArrayList;

public class Concierge implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }

    @Override
    public void newMessage(PapotageEvent event) {
        String sujet = event.getSujet();
        String text = event.getText();
        System.out.println("Le concierge a bien recu le message.");
        System.out.println(sujet);
        System.out.println(text);
        this.generateMessage(sujet, text);
    }

    @Override
    public void generateMessage(String sujet, String text) {
        PapotageEvent message = new PapotageEvent(this, sujet, text);
        for (PapotageListener p : destinataires) {
            p.newMessage(message);

        }
    }
}
