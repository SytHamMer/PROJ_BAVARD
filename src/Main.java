public class Main {
    public static void main(String[] args) {

        Batiment polytech = new Batiment();
        polytech.setConcierge("Concierge","pdw");
        ConnexionInterface mainFrame = new ConnexionInterface(polytech);
        System.out.println(polytech.getBavards());

        //ConciergeInterface mainFrame = new ConciergeInterface(polytech);
        //BavardInterface bavardFrame = new BavardInterface(lila);
    }
}