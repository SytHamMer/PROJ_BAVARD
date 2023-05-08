import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConciergeInterface extends JFrame {

    // attributs
    private Batiment bat;
    private Concierge concierge;
    public ConciergeInterface(Batiment bat){

        // Initialisation de la fenetre
        this.bat = bat;
        this.concierge = bat.getConcierge();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 320);
        this.setLayout(new BorderLayout());

        // Titre de l'interface
        JLabel title = new JLabel("Interface Concierge");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        this.add("North",title);

        // Liste de messages
        ArrayList<HashMap<String,String>> messages = concierge.getMessageReceived();
        JList messList = new JList<>(concierge.getMessageListModel());
        JScrollPane messageSP = new JScrollPane(messList);
        this.add("Center", messageSP);

        this.setVisible(true);
    }
}
