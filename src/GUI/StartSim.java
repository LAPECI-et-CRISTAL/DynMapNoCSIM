package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Simulation.InputFile;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class StartSim extends Thread{
	
	
	
	public void StartSim()
	
	
	{
		
		this.run();
		
		
		
	}
	
	
	public void run()
	
	{
		JFrame me=new JFrame();
		File file=new File("result_scenario1_3_Final.txt");
				
				FileWriter fw=null;
				try {
					fw = new FileWriter(file);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				 
				 /********************************/
				 DefaultCategoryDataset cd=new DefaultCategoryDataset();
				 DefaultCategoryDataset cd2=new DefaultCategoryDataset();

				 JFreeChart chart = ChartFactory.createBarChart3D("Temps d'exécution", ""/*,true*/,"",cd,PlotOrientation.VERTICAL, true,true,true );
				 chart.getCategoryPlot().getDomainAxis().setCategoryMargin(0.1);
				 chart.getCategoryPlot().getDomainAxis().setLowerMargin(0.1);

				 chart.getCategoryPlot().getDomainAxis().setUpperMargin(0.4);
				 ChartPanel panel=new ChartPanel(chart);
				 
				 JPanel res=new JPanel();
				 res.setLayout(new 	BorderLayout());
				 res.add(panel,BorderLayout.WEST);
				 JFreeChart chart2 = ChartFactory.createBarChart3D("Energie Consomée", ""/*,true*/,"",cd2,PlotOrientation.VERTICAL, true,true,true );
				 chart2.getCategoryPlot().getDomainAxis().setCategoryMargin(0.1);
				 chart2.getCategoryPlot().getDomainAxis().setLowerMargin(0.1);

				 chart2.getCategoryPlot().getDomainAxis().setUpperMargin(0.4);
				 ChartPanel panel2=new ChartPanel(chart2);

				 JFrame frame=new JFrame("Résultats");
				 frame.setSize(1500,500);
				 frame.setVisible(true);
				 res.add(panel2,BorderLayout.EAST);
				 frame.add(res);
                  if(!StaticParametre.List_Algo_Mapping.isEmpty())
                	  {
                	  StaticParametre.List_Algo_Mapping.clear();
                	  StaticParametre.List_ALGORITHME_ROUTAGE.clear();
                	  StaticParametre.List_Stratégie.clear();
                	  ApplicationInterface.task.clear();
                	  }
                  	
				// System.out.println(StaticParametre.List_Algo_Mapping.size());
				// lire le fichier des paramètres d'exécution
				try {
					new InputFile();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				System.out.println("lllllllll     "+StaticParametre.List_Algo_Mapping.size());
				
					for(int i=0;i<StaticParametre.List_Algo_Mapping.size();i++)
					{
						
					
					StaticParametre.ALGORITHME_PLACEMENT=StaticParametre.List_Algo_Mapping.get(i);
				

					for(int j=0;j<StaticParametre.List_ALGORITHME_ROUTAGE.size();j++)
						{
						StaticParametre.ALGORITHME_ROUTAGE=StaticParametre.List_ALGORITHME_ROUTAGE.get(j);
						
					for(int b=0;b<StaticParametre.List_Stratégie.size();b++)
					{
						
					StaticParametre.Strategie_De_Recherche =StaticParametre.List_Stratégie.get(b);
					System.out.println("Algo    =  "+StaticParametre.ALGORITHME_PLACEMENT+"   Stratégie "+StaticParametre.List_Stratégie.get(b));

					System.out.println("fin   "+Simulator.Tnow+"  "+Simulator.Energy+"    ");
					 //Thread.sleep(1000); 
					//me=new JFrame();

					// me.setVisible(true);
					// Thread.sleep(1000);
					try {
						Simulator sim=new Simulator();
						
						sim.start(cd, cd2, fw);
						
						LoadPlatforme.popi.clear();
						
						//Container cp=(Container) MAIN_Frame.pe.getSelectedComponent();
						//cp.removeAll();
				        //	JPanel pe=	(JPanel) MAIN_Frame.pe.getComponent(4);
				        //	pe.add(new Gantt());
					
						MAIN_Frame.pe.setComponentAt(5,new Gantt());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//me.dispose();
					//sim.start(fw);
					
					}
				}
				
				}
				try {
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//System.exit(0);
			}
		
		
		
		
		
		
	
	

}
