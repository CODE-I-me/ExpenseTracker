package charts;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChartRenderer {
    public static JPanel pieChart(Map<String, Double> categoryTotals) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> e : categoryTotals.entrySet())
            dataset.setValue(e.getKey(), e.getValue());
        JFreeChart chart = ChartFactory.createPieChart("Category-wise Spending", dataset, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(400,280));
        return panel;
    }
}
