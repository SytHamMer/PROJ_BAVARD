import java.util.EventObject;

public class OfflineBavardEvent extends EventObject {
    // Attributs
    private Bavard b;

    public Bavard getB() {
        return b;
    }


    //Constructeur
    public OfflineBavardEvent(Object source, Bavard b){
        super(source);
        this.b =b;
    }

    //getters and setters
    public void setB(Bavard b) {
        this.b = b;
    }
}
