import ui.ExpenseTrackerUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerUI().setVisible(true));
    }
}
