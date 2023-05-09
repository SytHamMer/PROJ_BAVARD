
import javax.swing.*;
        import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserFrame extends JFrame {

    private JPanel choicePanel;
    private ArrayList<String> selectedUsers = new ArrayList<>();


    public UserFrame(Batiment bat){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des utilisateurs : "));
        ArrayList<Bavard> values = bat.getBavards();
        int nbCheck = values.size();
        int nbCol = nbCheck / 5;
        System.out.println(nbCol);
        this.choicePanel = new JPanel(new GridLayout(5,nbCol));
        for(Bavard value : values){
            this.choicePanel.add(new JCheckBox(value.getUsername()));
        }
        this.add("Center", choicePanel);
        JButton valider = new JButton("Valider");
        valider.addActionListener(e-> validation());
        this.add("South", valider);

        this.setVisible(true);
    }

    public ArrayList<String> getSelectedUsers() {
        return selectedUsers;
    }

    //Fonction appelé lorsque que l'on valide les thèmes sélectionnés
    public void validation(){
        ArrayList<JCheckBox> checkboxes = getAllCheckBoxes(this.choicePanel);

        for(JCheckBox checkbox : checkboxes){
            if(checkbox.isSelected()){
                this.selectedUsers.add(checkbox.getText());
            }
        }
        System.out.println(this.selectedUsers);
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

