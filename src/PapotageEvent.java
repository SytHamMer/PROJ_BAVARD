import java.util.EventObject;

public class PapotageEvent extends EventObject {
    //Attributs
    private String sujet;
    private String text;

    private String bavard;

    private Theme theme;

    //Constructeur
    public PapotageEvent(Object source, String sujet, String text,String b,Theme theme){
        super(source);
        this.sujet=sujet;
        this.text=text;
        this.bavard =b;
        this.theme = theme;

    }

    //getters and setters
    public String getSujet() {
        return sujet;
    }

    public String getText() {
        return text;
    }

    public Theme getTheme() {
        return theme;
    }

    public String getBavard() {
        return bavard;
    }


}
