package ui;
import model1.Expense;
import service.ExpenseService;
import charts.ChartRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExpenseTrackerUI extends JFrame {
    private final ExpenseService service;
    private final DefaultTableModel tableModel;
    private final JComboBox<String> monthCombo;
    private final JPanel chartPanelContainer;
    private final JLabel insightsLabel;

    public ExpenseTrackerUI() {
        setTitle("Expense Tracker");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        service = new ExpenseService();

        // Add panel
        JPanel addPanel = new JPanel();
        JTextField titleField = new JTextField(9);
        JTextField amountField = new JTextField(5);
        JComboBox<String> catBox = new JComboBox<>(new String[]{"Food", "Travel", "Bills", "Shopping", "Others"});
        JTextField dateField = new JTextField(9);
        JTextField noteField = new JTextField(8);
        JButton addBtn = new JButton("Add");

        addPanel.add(new JLabel("Title")); addPanel.add(titleField);
        addPanel.add(new JLabel("Amount")); addPanel.add(amountField);
        addPanel.add(new JLabel("Cat.")); addPanel.add(catBox);
        addPanel.add(new JLabel("Date (YYYY-MM-DD)")); addPanel.add(dateField);
        addPanel.add(new JLabel("Note")); addPanel.add(noteField);
        addPanel.add(addBtn);

        add(addPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Title", "Amt", "Cat", "Date", "Note"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Right panel for chart and insights
        JPanel right = new JPanel(new BorderLayout());
        chartPanelContainer = new JPanel();
        monthCombo = new JComboBox<>(getMonthChoices());
        JButton filterBtn = new JButton("Filter by Month");
        JPanel monthPanel = new JPanel(); monthPanel.add(monthCombo); monthPanel.add(filterBtn);
        insightsLabel = new JLabel("<Insights>");

        right.add(monthPanel, BorderLayout.NORTH);
        right.add(chartPanelContainer, BorderLayout.CENTER);
        right.add(insightsLabel, BorderLayout.SOUTH);

        add(right, BorderLayout.EAST);

        // Listeners
        addBtn.addActionListener(e -> {
            try {
                String title = titleField.getText();
                double amt = Double.parseDouble(amountField.getText().trim());
                String cat = String.valueOf(catBox.getSelectedItem());
                LocalDate date = LocalDate.parse(dateField.getText().trim());
                String note = noteField.getText();
                service.addExpense(new Expense(title, amt, cat, date, note));
                updateUI(service.getAll());
                titleField.setText(""); amountField.setText("");
                dateField.setText(""); noteField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        filterBtn.addActionListener(e -> {
            int selMonth = monthCombo.getSelectedIndex() + 1;
            List<Expense> filtered = service.getAll().stream()
                .filter(ex -> ex.getDate().getMonthValue() == selMonth)
                .toList();
            updateUI(filtered);
        });

        // Init
        updateUI(service.getAll());
    }

    private void updateUI(List<Expense> showList) {
        tableModel.setRowCount(0);
        for (Expense e : showList)
            tableModel.addRow(new Object[]{e.getTitle(), e.getAmount(), e.getCategory(), e.getDate(), e.getNote()});

        chartPanelContainer.removeAll();
        Map<String, Double> catTotals = service.getCategorySpending(showList);
        chartPanelContainer.add(ChartRenderer.pieChart(catTotals));
        chartPanelContainer.revalidate();

        String insight = "<html>";
        insight += "Total: <b>" + service.total(showList) + "</b><br>";
        insight += "Most spent category: <b>" + service.mostSpentCategory(showList) + "</b><br>";
        insight += "Avg daily: <b>" + String.format("%.2f", service.averageDaily(showList)) + "</b>";
        insight += "</html>";
        insightsLabel.setText(insight);
    }

    private String[] getMonthChoices() {
        return new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    }
}

