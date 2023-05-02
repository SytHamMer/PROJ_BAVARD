import java.util.HashMap;

public interface PapotageListener{

    public void newMessage(PapotageEvent event);

    public HashMap<String,String> saveMessage(PapotageEvent event);
    


    //message généré par le concierge qui donne l'information sur le bavard aussi
    void generateMessage(String sujet, String text,String author);


}
