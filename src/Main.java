public class Main {
    public static void main(String[] args) {
        Concierge c = new Concierge();
        Bavard b = new Bavard("bzzzz", "jsp");
        c.addPapotageListener(b);
        b.addPapotageListener(c);
        b.generateMessage("Test", "marche pitie");
    }
}