package GUI;

import Simulation.StaticParametre;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Gantt extends JPanel {

    ArrayList<TaskSeries> ts = new ArrayList<TaskSeries>();
    TaskSeriesCollection tsc = new TaskSeriesCollection();

    Gantt() {
        this.setAutoscrolls(true);
        //this.setLayout(new FlowLayout());
        for (int i = 0; i < StaticParametre.listApplication.size(); i++)
            ts.add(new TaskSeries("Application  " + i));

//	int nbr=StaticParametre.List_Algo_Mapping.size()*StaticParametre.List_ALGORITHME_ROUTAGE.size();


        for (int i = 0; i < StaticParametre.listApplication.size(); i++) {
            Date d = new Date();
            d.setHours(0);
            d.setMinutes(0);
            d.setDate(0);
            d.setMonth(0);
            d.setSeconds(0);
            d.setYear(0);

            Date d2 = new Date();
            d2.setHours(0);
            d2.setMinutes(0);
            d2.setDate(0);
            d2.setMonth(0);
            d2.setSeconds(0);
            d2.setYear(0);

            d.setTime((long) StaticParametre.listApplication.get(i).getListTache().get(0).debut_execution);
            //StaticParametre.listApplication.get(i).getListTache().get(0).fin_execution=10000+(i*4);
            //d.setTime(0);

            d2.setTime((long) StaticParametre.listApplication.get(i).getListTache().get(0).fin_execution);
            //d2.setTime(10);
            Task t = new Task("Application  " + i, d, d2);


            ts.get(i).add(t);

            tsc.add(ts.get(i));

        }

        GanttCategoryDataset gcd = new SlidingGanttCategoryDataset(tsc, 0, StaticParametre.listApplication.size());


        JFreeChart chart = ChartFactory.createGanttChart(" Applications ", "  Application  ", " Temps  ", gcd, false, true, true);

        chart.getCategoryPlot().getDomainAxis().setCategoryMargin(0.000001);
        chart.getCategoryPlot().getDomainAxis().setLowerMargin(0.00001);
        chart.getCategoryPlot().getDomainAxis().setUpperMargin(0.00000005);

        GanttRenderer render = (GanttRenderer) chart.getCategoryPlot().getRenderer();
        render.setItemMargin(0);
		
	/*DateAxis dateAxis=(DateAxis) chart.getCategoryPlot().getRangeAxis() ;
	DateFormat dateFormat=new SimpleDateFormat("dd") ;
	dateAxis.setDateFormatOverride(dateFormat) ;
	dateAxis.setTickUnit(new DateTickUnit(DateTickUnit.MILLISECOND, 1));
	dateAxis.setVisible(true);
	*/

        //HorizontalIntervalBarRenderer l=new HorizontalIntervalBarRenderer();
        ChartPanel pan = new ChartPanel(chart);
        pan.setSize(800, 600);
        this.add(pan);
        this.setSize(800, 600);

    }


}
