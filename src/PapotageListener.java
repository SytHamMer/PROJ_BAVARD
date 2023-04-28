public interface PapotageListener {

    public void newMessage(PapotageEvent event);

    void generateMessage(String sujet, String text);

}
