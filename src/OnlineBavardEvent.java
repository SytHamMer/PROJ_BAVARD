import java.util.EventObject;

public class OnlineBavardEvent extends EventObject {

    // Attributs
    private Bavard b;


    //Constructeur
    public OnlineBavardEvent(Object source, Bavard b){
        super(source);
        this.b =b;
    }
    //getters and setters
    public Bavard getB() {
        return b;
    }

    public void setB(Bavard b) {
        this.b = b;
    }
}
