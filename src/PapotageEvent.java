import java.util.EventObject;

public class PapotageEvent extends EventObject {

    private Bavard bavard;

    private String sujet;
    private String text;




    public PapotageEvent(Object source,Bavard bavard,String sujet,String text){
        super(source);
        this.bavard =bavard;
        this.sujet=sujet;
        this.text=text;

    }
}
