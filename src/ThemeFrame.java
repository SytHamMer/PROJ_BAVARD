import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ThemeFrame extends JFrame {

    private JPanel choicePanel;

    private ArrayList<String> selectedTheme = new ArrayList<>();
    public ThemeFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des themes : "));
        int nbCheck = Theme.values().length;
        int nbCol = nbCheck / 5;
        this.choicePanel = new JPanel(new GridLayout(5,nbCol));
        for(Theme value : Theme.values()){
            this.choicePanel.add(new JCheckBox(value.name()));
        }
        this.add("Center", choicePanel);
        JButton annuler = new JButton("Annuler");
        JButton valider = new JButton("Valider");
        valider.addActionListener(e-> validation());
        this.add("South", valider);

        this.setVisible(true);
    }


    public void validation(){
        ArrayList<JCheckBox> checkboxes = getAllCheckBoxes(this.choicePanel);

        for (JCheckBox checkbox : checkboxes){
            if(checkbox.isSelected() == true){
                this.selectedTheme.add(checkbox.getText());
            }
        }
        System.out.println(this.selectedTheme);
        this.setVisible(false);

    }
    //Fonction qui récupère dans une liste l'ensemble des JCheckbox du panel
    public static ArrayList<JCheckBox> getAllCheckBoxes(Container container) {
        Component[] components = container.getComponents();
        ArrayList<JCheckBox> result = new ArrayList<>();

        for (Component component : components) {
            if (component instanceof JCheckBox) {
                result.add((JCheckBox) component);
            } else if (component instanceof Container) {
                result.addAll(getAllCheckBoxes((Container) component));
            }
        }
        return result;
    }
}
