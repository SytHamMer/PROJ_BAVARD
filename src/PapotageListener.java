public interface PapotageListener{

    public void newMessage(PapotageEvent event);


    //message généré par le concierge qui donne l'information sur le bavard aussi
    void generateMessage(String sujet, String text,String author);


}
