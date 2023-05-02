import java.util.EventObject;

public class OfflineBavardEvent extends EventObject {

    private Bavard b;

    public Bavard getB() {
        return b;
    }

    public void setB(Bavard b) {
        this.b = b;
    }

    public OfflineBavardEvent(Object source, Bavard b){
        super(source);
        this.b =b;
    }
}
