import java.util.ArrayList;

public class Bavard implements PapotageListener {
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();
    private String username;

    private String password;

    public Bavard(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void addPapotageListener(PapotageListener pl){
        destinataires.add(pl);
    }


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

    }


    @Override
    public void generateMessage(String sujet, String text,String author){
        PapotageEvent message = new PapotageEvent(this, sujet, text,this.getUsername() );
        for (PapotageListener p : destinataires){
            p.newMessage(message);
        }

    }

}
