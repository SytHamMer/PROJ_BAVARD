import java.util.ArrayList;

public class Bavard implements PapotageListener{
    private ArrayList<PapotageListener> destinataires = new ArrayList<PapotageListener>();
    private String username;

    private String password;

    @Override
    public void newMessage(PapotageEvent event){



    }

}
