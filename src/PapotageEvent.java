import java.util.EventObject;

public class PapotageEvent extends EventObject {

    private String sujet;
    private String text;

    public String getSujet() {
        return sujet;
    }

    public String getText() {
        return text;
    }

    public PapotageEvent(Object source, String sujet, String text){
        super(source);
        this.sujet=sujet;
        this.text=text;

    }
}
