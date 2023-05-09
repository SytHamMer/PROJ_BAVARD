import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ThemeFrame extends JFrame {

    private JPanel choicePanel;

    private Bavard bav;

    private ArrayList<String> selectedTheme = new ArrayList<>();
    public ThemeFrame(Bavard bav){
        this.bav = bav;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des themes : "));
        int nbCheck = Theme.values().length;
        int nbCol = nbCheck / 5;
        this.choicePanel = new JPanel(new GridLayout(5,nbCol));
        System.out.println(bav.getThemes());
        for(Theme value : Theme.values()){
            JCheckBox newCB = new JCheckBox(value.name());
            System.out.println(Arrays.asList(bav.getThemes()));
            System.out.println(value.name());
            if(bav.getThemes().contains(value.name())){
                System.out.println("pk ca marche pas");
                newCB.setSelected(true);
            }
            this.choicePanel.add(newCB);
        }
        this.add("Center", choicePanel);
        JPanel buttons = new JPanel(new GridLayout(1,2));
        JButton annuler = new JButton("Annuler");
        JButton valider = new JButton("Valider");
        annuler.addActionListener(e-> annuler());
        valider.addActionListener(e-> validation());
        buttons.add(annuler);
        buttons.add(valider);
        this.add("South", buttons);

        this.setVisible(true);
    }

    public void annuler(){
        this.setVisible(false);
    }

    public void validation(){
        ArrayList<JCheckBox> checkboxes = getAllCheckBoxes(this.choicePanel);

        for (JCheckBox checkbox : checkboxes){
            if(checkbox.isSelected() == true){
                this.selectedTheme.add(checkbox.getText());
            }
        }
        System.out.println(this.selectedTheme);
        bav.setThemes(selectedTheme);
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
