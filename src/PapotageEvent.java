import java.util.EventObject;

public class PapotageEvent extends EventObject {

    private String sujet;
    private String text;

    private String bavard;

    public String getSujet() {
        return sujet;
    }

    public String getText() {
        return text;
    }

    public String getBavard() {
        return bavard;
    }

    public PapotageEvent(Object source, String sujet, String text,String b){
        super(source);
        this.sujet=sujet;
        this.text=text;
        this.bavard =b;

    }
}
