import java.util.ArrayList;
import java.util.HashMap;

public class Concierge implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();
    private String pseudo;



    public Concierge(String pseudo){
        this.pseudo=pseudo;
    }

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }

    public void removePapotageListener(PapotageListener pl){
        if(this.destinataires.contains(pl)){
            this.destinataires.remove(pl);
            System.out.println("Bavard bien retiré du concierge");
        }
        else{
            System.out.println("Ce bavard n'est pas connecté");
        }
    }

    @Override
    public void newMessage(PapotageEvent event) {
    }

    @Override
    public HashMap<String,String> saveMessage(PapotageEvent event) {
        //MODIFIER ICI
        HashMap<String,String> bavardage = new HashMap<>();
        String sujet = event.getSujet();
        String text = event.getText();
        String author = event.getBavard();
        bavardage.put("sujet",sujet);
        bavardage.put("text",text);
        bavardage.put("auteur",author);

        System.out.println("Le concierge a bien recu le message.");
        System.out.println(sujet);
        System.out.println(text);
        System.out.println("FROM:"+author);


        for (PapotageListener p : destinataires) {
            p.newMessage(event);

        }
        return bavardage;
    }

    @Override
    public void generateMessage(String sujet, String text,String author) {
        PapotageEvent message = new PapotageEvent(this, sujet, text,author);
        for (PapotageListener p : destinataires) {
            p.newMessage(message);

        }
    }
}
