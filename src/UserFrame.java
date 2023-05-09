
import javax.swing.*;
        import java.awt.*;
import java.util.ArrayList;

public class UserFrame extends JFrame {

    public UserFrame(Batiment bat){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des utilisateurs : "));
        ArrayList<Bavard> values = bat.getBavards();
        int nbCheck = values.size();
        int nbCol = nbCheck / 5;
        System.out.println(nbCol);
        JPanel choicePanel = new JPanel(new GridLayout(5,nbCol));
        System.out.println("mathys a fait de la merde :" + values);
        for(Bavard value : values){
            choicePanel.add(new JCheckBox(value.getUsername()));
        }
        this.add("Center", choicePanel);
        JButton valider = new JButton("Valider");
        valider.addActionListener(e-> validation());
        this.add("South", valider);

        this.setVisible(true);
    }

    public void validation(){
        this.setVisible(false);
    }
}

