import java.util.ArrayList;

public class Batiment {
    //faire une version où les bavards peuvent choisir de recevoir ou non les messages du batiment
    private ArrayList<Bavard> bavards = new ArrayList<>();

    private String nom;
    private Concierge concierge;


    private ArrayList<Bavard> OnlineBavard = new ArrayList<>();
    //Permet de récupérer l'état du dernier essai
    private boolean lastAddBavardTry;

    public void Batiment(String nom, Concierge c){
        this.nom = nom;
        this.concierge = c;
    }

    public ArrayList<Bavard> getOnlineBavard() {
        return OnlineBavard;
    }

    public void addOnlineBavard(Bavard b){
        this.getOnlineBavard().add(b);
    }
    public boolean isLastAddBavardTry() {
        return lastAddBavardTry;
    }

    public void setLastAddBavardTry(boolean lastAddBavardTry) {
        this.lastAddBavardTry = lastAddBavardTry;
    }

    public String getNom() {
        return nom;
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public void setConcierge(String pseudo,String password){
        this.concierge= new Concierge(pseudo,password);

    }

    //renvoi la liste des bavards
    public ArrayList<Bavard> getBavards() {
        return bavards;
    }


    //Renvoie le bavard correspondant au pseudo si il est présent dans ce batiment
    public Bavard getBavard(String pseudo) {
        int cpt = 0;
        while (cpt<bavards.size()){
            if(this.bavards.get(cpt).getUsername().equals(pseudo)){
                return this.bavards.get(cpt);
            }
            else{
                cpt=cpt+1;
            }

        }
        System.out.println("Ce bavard("+ pseudo + ") n'est pas présent dans le batiment");
        return null;
    }


    //Renvoie le bavard corresponant au bavard b SI il est présent dans le batiment
    public Bavard getBavard(Bavard b){
        int cpt = 0;
        while (cpt<bavards.size()){
            if(this.bavards.get(cpt)==b){
                return this.bavards.get(cpt);
            }
            else{
                cpt=cpt+1;
            }

        }
        System.out.println("Ce bavard("+ b.getUsername() + ") n'est pas présent dans le batiment");
        return null;

    }

    //Ajout d'un Bavard déjà éxistant
    public void addBavards(Bavard b){
        //Vérification que le login n'exista pas déjà
        if(this.getBavard(b.getUsername())==null){
            this.bavards.add(b);
            this.concierge.addPapotageListener(b);
            this.getBavard(b).addPapotageListener(this.concierge);
            this.lastAddBavardTry =true;

        }
        else{
            this.lastAddBavardTry=false;
        }

    }

    //Création d'un nouveau bavard directement dans ce batiment
    public void addBavards(String username, String password, boolean connected){
        //Vérification qu'un login similaire n'existe pas
        if(this.getBavard(username)==null){
            Bavard b = new Bavard(username, password,connected);
            this.bavards.add(b);
            this.concierge.addPapotageListener(b);
            this.getBavard(b).addPapotageListener(this.concierge);
            System.out.println("Bavard :" + username + " ajouté");
            this.lastAddBavardTry = true;
        }
        else{
            this.lastAddBavardTry = false;
        }
    }

    //Suppresion d'un bavard de la liste du batiment
    public void removeBavards(Bavard b){
        if (this.bavards.contains(b)) {
            this.bavards.remove(b);
            this.concierge.removePapotageListener(b);
            b.removePapotageListener(this.concierge);
        }else{
            System.out.println("Ce bavard n'est pas présent dans ce batiment");

        }
    }
}
