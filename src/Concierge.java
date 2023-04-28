import java.util.ArrayList;

public class Concierge implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }

    @Override
    public void newMessage(PapotageEvent event) {
        System.out.println("Le concierge a bien recu le message.");
        System.out.println(event.getSujet());
        System.out.println(event.getText());
    }

    @Override
    public void generateMessage(String sujet, String text) {

    }
}
