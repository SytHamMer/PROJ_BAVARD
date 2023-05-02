import java.util.EventObject;

public class OnlineBavardEvent extends EventObject {


    private Bavard b;

    public Bavard getB() {
        return b;
    }

    public void setB(Bavard b) {
        this.b = b;
    }

    public OnlineBavardEvent(Object source, Bavard b){
        super(source);
        this.b =b;
    }
}
