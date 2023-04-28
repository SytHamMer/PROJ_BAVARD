public class Main {
    public static void main(String[] args) {
        Concierge c = new Concierge();
        Bavard b = new Bavard("bzzzz", "jsp");
        Bavard b2 = new Bavard("Maya","pdm");
        c.addPapotageListener(b);
        c.addPapotageListener(b2);
        b2.addPapotageListener(c);
        b.addPapotageListener(c);
        b.generateMessage("Test", "marche pitie");
        b2.generateMessage("Le caca","Le caca c'est comme le pipi mais en solide.");
    }
}