
import javax.swing.*;
        import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserFrame extends JFrame {

    private JPanel choicePanel;

    private Bavard bav;

    private ArrayList<String> selectedUsers = new ArrayList<>();


    public UserFrame(Batiment bat, Bavard bav){
        this.bav = bav;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(200, 280);
        this.add("North", new JLabel("Choix des utilisateurs : "));
        ArrayList<Bavard> values = bat.getBavards();
        int nbCheck = values.size();
        int nbCol = nbCheck / 5;
        this.choicePanel = new JPanel(new GridLayout(5,nbCol));
        for(Bavard value : values){
            JCheckBox newCB = new JCheckBox(value.getUsername());
            if(bav.getUsers().contains(value.getUsername())){
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

    public ArrayList<String> getSelectedUsers() {
        return selectedUsers;
    }

    public void annuler(){
        this.setVisible(false);
    }

    //Fonction appelé lorsque que l'on valide les thèmes sélectionnés
    public void validation(){
        ArrayList<JCheckBox> checkboxes = getAllCheckBoxes(this.choicePanel);

        for(JCheckBox checkbox : checkboxes){
            if(checkbox.isSelected()){
                this.selectedUsers.add(checkbox.getText());
            }
        }
        bav.setUsers(selectedUsers);
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

