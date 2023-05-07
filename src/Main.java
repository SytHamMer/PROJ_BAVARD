public class Main {
    public static void main(String[] args) {

        Batiment polytech = new Batiment();
        polytech.setConcierge("Concierge","pdw");
        polytech.addBavards("test","test");
        polytech.addBavards("Lila","Lila_mdp");
        Bavard mathys = new Bavard("Mathys","lpb");
        polytech.addBavards(mathys);
        Bavard lila = polytech.getBavard("Lila");
        polytech.addBavards("Sorana_Cimpan","sorana");
        lila.generateMessage("Test","Ceci est mon message",lila.getUsername());
        mathys.generateMessage("Chiant", "quoicoube", mathys.getUsername());
        for(int i=0; i<15;i++) {
            mathys.generateMessage("Chiant", "quoicoube" + Integer.toString(i), mathys.getUsername());
        }

        ConnexionInterface mainFrame = new ConnexionInterface(polytech);


        //ConciergeInterface mainFrame = new ConciergeInterface(polytech);
        //BavardInterface bavardFrame = new BavardInterface(lila);
    }
}