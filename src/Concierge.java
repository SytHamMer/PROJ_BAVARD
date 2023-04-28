import java.util.ArrayList;

public class Concierge implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();

    @Override
    public void newMessage(PapotageEvent event) {

    }
}
