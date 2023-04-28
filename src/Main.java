public class Main {
    public static void main(String[] args) {

        Batiment polytech = new Batiment();
        polytech.setConcierge("Concierge");
        polytech.addBavards("Lila","Lila_mdp");
        Bavard mathys = new Bavard("Mathys","dr(sd-ftèyg_huiojp");
        polytech.addBavards(mathys);
        Bavard lila = polytech.getBavard("Lila");
        lila.generateMessage("Test","Mathys je te trouve très gentil mais franchement t pa bo");

    }
}