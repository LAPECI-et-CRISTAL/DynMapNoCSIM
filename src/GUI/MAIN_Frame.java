package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MAIN_Frame {

	public static JFrame fra=new JFrame();
	public static JTabbedPane pe;
	MAIN_Frame(){
		
		pe=new JTabbedPane();
		InterfaceArchitecture archi=new InterfaceArchitecture(/*8,8*/);
		archi.setSize(100, 100);
		//JPanel appli=new JPanel();
		ParamExec param=new ParamExec();
		JPanel sim=new JPanel();
		Gantt gant=new Gantt();
		Result res=new Result();
		pe.add("Paramètres",param);
		pe.add("Architecture",archi);
		pe.add("Application",new ApplicationInterface());
		pe.add("Simulation",sim);
		/************************************/
		
		//res.setSize(300, 300);
		//me.add(panel,BorderLayout.EAST);
		//panel.setSize(300, 300);
		pe.add("Résultats",res);		
		/******************************************/
		
	
		

		pe.add("Gantt",gant);
		fra.add(pe);
		fra.setSize(1200,720);
		fra.setVisible(true);
		fra.setDefaultCloseOperation(fra.EXIT_ON_CLOSE);
		
	}
	
	public static void main (String [] args)
	{
		
		  try {
	          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	              if ("Nimbus".equals(info.getName())) {
	                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                  break;
	              }
	          }
	      } catch (ClassNotFoundException ex) {
	          java.util.logging.Logger.getLogger(MAIN_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	      } catch (InstantiationException ex) {
	          java.util.logging.Logger.getLogger(MAIN_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	      } catch (IllegalAccessException ex) {
	          java.util.logging.Logger.getLogger(MAIN_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	          java.util.logging.Logger.getLogger(MAIN_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	      }
		new MAIN_Frame();
		
	}

}
