public class Main {
    public static void main(String[] args) {

        Batiment polytech = new Batiment();
        polytech.setConcierge("Concierge","pdw");
        polytech.addBavards("test","test",false);
        polytech.addBavards("Lila","Lila_mdp",true);
        Bavard mathys = new Bavard("Mathys","lpb",true);
        polytech.addBavards(mathys);
        Bavard lila = polytech.getBavard("Lila");
        polytech.addBavards("Sorana_Cimpan","sorana",true);
        lila.generateMessage("Test","Ceci est mon message",lila.getUsername());
        mathys.generateMessage("Chiant", "quoicoube", mathys.getUsername());

        ConnexionInterface mainFrame = new ConnexionInterface(polytech);


        //ConciergeInterface mainFrame = new ConciergeInterface(polytech);
        //BavardInterface bavardFrame = new BavardInterface(lila);
    }
}