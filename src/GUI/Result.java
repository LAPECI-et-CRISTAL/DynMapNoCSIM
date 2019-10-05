package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class Result extends JPanel {

    Result() {


        DefaultCategoryDataset cd = new DefaultCategoryDataset();

        JFreeChart chart = ChartFactory.createBarChart3D("test", ""/*,true*/, "", cd, PlotOrientation.VERTICAL, true, true, true);
        chart.getCategoryPlot().getDomainAxis().setCategoryMargin(0.1);
        chart.getCategoryPlot().getDomainAxis().setLowerMargin(0.1);

        chart.getCategoryPlot().getDomainAxis().setUpperMargin(0.6);
        chart.getCategoryPlot().getRangeAxis().setRange(0, 100);
        ChartPanel res = new ChartPanel(chart);
        //res.setLayout(new FlowLayout());
        res.setBounds(800, 1360, 0, 400);
        this.add(res);


    }

}
