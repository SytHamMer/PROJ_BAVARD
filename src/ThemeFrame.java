import javax.swing.*;
import java.awt.*;

public class ThemeFrame extends JFrame {

    public ThemeFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des themes : "));
        int nbCheck = Theme.values().length;
        int nbCol = nbCheck / 5;
        JPanel choicePanel = new JPanel(new GridLayout(5,nbCol));
        for(Theme value : Theme.values()){
            choicePanel.add(new JCheckBox(value.name()));
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
