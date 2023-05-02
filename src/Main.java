public class Main {
    public static void main(String[] args) {

        Batiment polytech = new Batiment();
        polytech.setConcierge("Concierge");
        polytech.addBavards("Lila","Lila_mdp",true);
        Bavard mathys = new Bavard("Mathys","dr(sd-ftèyg_huiojp",true);
        polytech.addBavards(mathys);
        Bavard lila = polytech.getBavard("Lila");
        polytech.addBavards("Sorana_Cimpan","uigyvuftèygui",true);
        lila.generateMessage("Test","Ceci est mon message",lila.getUsername());
        mathys.generateMessage("Chiant", "quoicoube", mathys.getUsername());

        //ConnexionInterface mainFrame = new ConnexionInterface();

        System.out.println(polytech.getConcierge().getMessageReceived());

        ConciergeInterface mainFrame = new ConciergeInterface(polytech);
    }
}