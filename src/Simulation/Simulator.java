package Simulation;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Application.Application;
import Application.Create_Application;
import Architecture.Create_NOC;
import Architecture.CreationCluster;
import Architecture.GenererChannel;
import Architecture.Paquet;
import GUI.MAIN_Frame;
import GUI.StartSim;
import GUI.linkDrawer;
import Proc.Manager.ProcessorMaitre;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;

/**
 * c'est a partir de cette classe que la simulation demmare 
 * 
 * @author Nabil
 *
 *
 */
public class Simulator extends JPanel implements ActionListener{
	/** le processeur Maitre*/
	public static ProcessorMaitre MAITRE;
	public static int speed=0;
	JSlider js;
	public static int Tnow=0,temps_recherche=0,Energy=0,temps_routage=0,Energy_recherche=0;	//Tnow représente le tempc actuel de la simulation
	
	public static JFrame me;
	//public static JPanel main;
	public JPanel westPanel=new JPanel();
	public static DefaultCategoryDataset cd,cd2;
	public static FileWriter fw;
	
	/*
	 *  Liste qui contient les temps des prochains evenements
	 * 
	 */
	public static ArrayList<Integer> T_next_event=new ArrayList<Integer>();
	
	public Simulator() throws IOException, InterruptedException{
		
		JPanel pan=new JPanel();
		pan.setLayout(new FlowLayout());
		JButton bt=new JButton("Lancer Simulation");
		bt.addActionListener(this);
	   js=new JSlider();
		js.setMaximum(5);
		js.setMinimum(1);
		js.addChangeListener(new ChangeListener()

		{public void  stateChanged(javax.swing.event.ChangeEvent evt)
		{
			jSliderState(evt);
		}
			
		});
		//JLabel lab=new JLabel("grrrrrrrrrrrr  ");
		pan.add(bt);
		pan.add(js);
		this.add(pan);	
	
		
	}
		public JPanel start(DefaultCategoryDataset cd,DefaultCategoryDataset cd2,FileWriter fw) throws IOException, InterruptedException
		{
		Container cp =(Container) MAIN_Frame.pe.getSelectedComponent();
		cp.removeAll();
		 //=new JPanel();
	 int xx=50,yy=60, xx1=50, yy1=200, xxloc=xx, yyloc=yy;
	// procedure qui lance le simulateur
	//public void start(FileWriter fw ) throws IOException, InterruptedException{
		
/*****    Configuration du systeme (Architecture,Application)                    **************/

			
		
		
		
		// charger les tache d'application (selon le scenario choisit)
	
		Create_Application.remplirTaches(StaticParametre.Lien_Fichier_XML);
		
		//StaticParametre.listApplication.get(0).getListTache().get(0).get_Max_Fils();
		
		// creation du NOC
		Create_NOC.creationNoc();

this.setLayout(new BorderLayout());

JPanel pan=new JPanel();
pan.setLayout(new FlowLayout());
JButton bt=new JButton("Lancer Simulation");
bt.addActionListener(this);
js=new JSlider();

js.setMaximum(5);
js.setMinimum(1);

js.addChangeListener(new ChangeListener()
{public void  stateChanged(javax.swing.event.ChangeEvent evt)
{
	jSliderState(evt);
}
	
});

//JLabel lab=new JLabel("grrrrrrrrrrrr  ");
pan.add(bt);
pan.add(js);
this.add(pan,BorderLayout.NORTH);	



JPanel plate=new JPanel();
plate.setLayout(new FlowLayout());

//plate.setSize(300,300);
plate.add(Create_NOC.platforme/*,BorderLayout.NORTH*/);
this.add(plate,BorderLayout.CENTER);
westPanel.removeAll();
westPanel.repaint();
westPanel.setLayout(new FlowLayout());
JTextField now=new JTextField("Tnow=0");
JLabel nnn=new JLabel(" TNOW  ");
nnn.setBackground(this.getBackground());
westPanel.add(nnn);
this.add(westPanel,BorderLayout.SOUTH);

//JMenuBar bar=new JMenuBar();


MAIN_Frame.pe.setComponentAt(3,this);


///****************************************/
/****************************************************/

////////////////////////////////



		//me.getContentPane().add(this);
		//me.setVisible(true);
		//me.setSize(800,600);
		//me.setDefaultCloseOperation(me.EXIT_ON_CLOSE);
		// création des clusters

		
		
		
		
		CreationCluster.creation_cluster();
		
		// génerer les lien entre les processeurs		
		GenererChannel gen = new GenererChannel();
		gen.start();
		
		// le processeur maître
		MAITRE  = new ProcessorMaitre() ;
		
		

		
			
		
		
		
		
/*************************  debut de la simuation	********************/	

		StaticParametre.end_application=0;
		//Simulator.T_next_event.clear();
		Simulator.Add_Event(0);
		//Tnow=Simulator.T_next_event.get(0);
		//
		StaticParametre.application_en_cours=new Application[20] ;
		Energy=0;
		temps_recherche=0;
		Energy_recherche=0;
		
		while(StaticParametre.end_application!=StaticParametre.NOMBRE_APPLICATION )// condition d'arret de la simuation
	
		{

			
			Tnow=this.T_next_event.get(0);
			// Affichage du temps actuel
			System.out.println("Tnow   =  "+Tnow);
			
				//javax.swing.JOptionPane.showMessageDialog(this,"Reprendre la simulation","Pause",1);
			
			//now.setText("Tnow= "+Tnow);
			nnn.setText("  TNOW   "+Tnow );
			
				
				cd.addValue(Tnow,""+StaticParametre.ALGORITHME_PLACEMENT+","+StaticParametre.ALGORITHME_ROUTAGE,""); 

				cd2.addValue(Energy,""+StaticParametre.ALGORITHME_PLACEMENT+","+StaticParametre.ALGORITHME_ROUTAGE,""); 
			// Le parcours des taches qui demandes a etre executés
			MAITRE.Mapping(Tnow);
			

			// Le parcours des taches qui nécéssitent le placement de communication
			MAITRE.Routage(Tnow);
			
			// Mise à jour de la plateforme
			MAITRE.Mise_A_Jour(Tnow);
	
		////////  Récupération du temps du prochain evenement /////
 			if(!T_next_event.isEmpty())
			{
 				if(T_next_event.get(0)<Tnow)
 				{
 					System.out.println("erreur");
 					System.exit(0);
 				}
			Tnow=T_next_event.get(0);
			T_next_event.remove(0);
			}
	   ///////////
 	
	}


			/************************ Affichage du Résultat ***********************/		
			
			for(int j=0;j<StaticParametre.listApplication.size();j++)
		 	{
		 		// Affichage du résultat du placement, temps d'execution ainsi que la consommation d'énergie de chaque tache
				
		 				for(int r=0;r<StaticParametre.getListApplication().get(j).getListTache().size();r++)
		 				{
		 				int x=StaticParametre.getListApplication().get(j).getListTache().get(r).x;
		 				int y=StaticParametre.getListApplication().get(j).getListTache().get(r).y;
		 				System.out.println("x   "+x+"  y  "+y+"  fin execution  "+StaticParametre.getListApplication().get(j).getListTache().get(r).fin_execution+ "   Debut execution    "+StaticParametre.getListApplication().get(j).getListTache().get(r).debut_execution+"  id tache  "+r+ " id application  "+j  );
		 					
		 					
		 			}
		 	}
			
			
			
			for(int r=0;r<StaticParametre.Limit_NOC_x;r++)
			{
				for(int j=0;j<StaticParametre.Limit_NOC_y;j++)
				
					System.out.println("etat du proc "+Create_NOC.getNOC()[r][j].getFree()+"   son type  "+Create_NOC.getNOC()[r][j].getType()+"  x   "+r+"    y   "+j+" File memoire   "+Create_NOC.getNOC()[r][j].getMem());
					
			}
			
/***************************************** fin affichage  *************************/
			System.out.println("ecriture du fichier résultat en cours ..... ");
			OutputFileReader.generateOutputFile(Tnow, Energy,temps_recherche,Energy_recherche,fw);


		/*	for(int p=0;p<StaticParametre.List_Paquet.size();p++)
			{Paquet paquet=StaticParametre.List_Paquet.get(p);
				System.out.println("x source  "+paquet.x_source  +"  x_dest  "+paquet.x_destination+"  y source "+paquet.y_source+"  y destination  "+paquet.y_destination+"  T debut   "+paquet.T_Debut+ "  T_fin  "+paquet.T_fin);
			}*/
	System.out.println("fin   "+Tnow+"  "+Energy+"    temps recherche "+temps_recherche/*+"      temps routage     "+temps_routage*/);
	
	
	/*for(int p=0;p<StaticParametre.List_Paquet.size();p++)
	{
		Paquet paquet=StaticParametre.List_Paquet.get(p);
		System.out.println("id p "+paquet.id+ "  TDEBUT  "+ paquet.T_Debut+ "  T_fin  "+paquet.T_fin);
	}*/
		
	
	
	
/************************ Affichage du Résultat ***********************/		

			return(this);
	}

	
////////////////////////////  Procédure qui ajoute et trie les temps des evenements par ordre croissant 	
public static void Add_Event(int evt)
	{
		
	if(!T_next_event.isEmpty()) // Si la liste n'est pas vide, placer l'evenement dans la position appropriée 
	{
	int i;
	 for( i=0;i<T_next_event.size();i++)
	 {
		 if(evt<T_next_event.get(i))
		 { 
		 T_next_event.add(i,evt);
		 break;
		 }
		 
		 else { 
			 if(evt==T_next_event.get(i))
		 	{
			break;
			 
		 	}
		 }
		}
	 
	 if(i==T_next_event.size()) T_next_event.add(evt); //si evt est plus grand que tt les evts qui sont présent dans la liste je l ajoute à la fin
	
	}
	
	
	else // si la liste est vide, ajouter l'evenement dans la lise
		 {
		 T_next_event.add(evt);
		 }
	
	}
////////////////////////////


public static void setEnergy(int energie)
{
	
	Energy+=energie;
}
///////////////

public static void setEnergie_recherche(int energie)
{
	
	Energy_recherche+=energie;
}
////////

public static void set_temps_recherche(int time)
{
	temps_recherche+=time;
}

///////////////

public static void set_temps_routage(int time)
{
temps_routage+=time;
}

///////////////////////////////	
	public static void main(String[] args) throws Throwable
	{

		File file=new File("result_scenario1_3_Final.txt");
		
		FileWriter fw = new FileWriter(file);
	
		me=new JFrame();
		 
		 /********************************/
		 DefaultCategoryDataset cd=new DefaultCategoryDataset();
		 DefaultCategoryDataset cd2=new DefaultCategoryDataset();

		 JFreeChart chart = ChartFactory.createBarChart3D("Temps d'exécution", ""/*,true*/,"",cd,PlotOrientation.VERTICAL, true,true,true );
		 chart.getCategoryPlot().getDomainAxis().setCategoryMargin(0.1);
		 chart.getCategoryPlot().getDomainAxis().setLowerMargin(0.1);

		 chart.getCategoryPlot().getDomainAxis().setUpperMargin(0.4);
		 ChartPanel panel=new ChartPanel(chart);
		 
		 JPanel res=new JPanel();
		 res.setLayout(new FlowLayout());
		 res.add(panel);
		 JFreeChart chart2 = ChartFactory.createBarChart3D("Energie Consomée", ""/*,true*/,"",cd2,PlotOrientation.VERTICAL, true,true,true );
		 chart2.getCategoryPlot().getDomainAxis().setCategoryMargin(0.1);
		 chart2.getCategoryPlot().getDomainAxis().setLowerMargin(0.1);

		 chart2.getCategoryPlot().getDomainAxis().setUpperMargin(0.4);
		 ChartPanel panel2=new ChartPanel(chart2);

		 JFrame frame=new JFrame("Résultats");
		 frame.setSize(1500,500);
		 frame.setVisible(true);
		 res.add(panel2);
		 frame.add(res);

		// lire le fichier des paramètres d'exécution
		new InputFile();
		
		
		
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

			System.out.println("fin   "+Tnow+"  "+Energy+"    ");
			 //Thread.sleep(1000); 
			//me=new JFrame();

			// me.setVisible(true);
			// Thread.sleep(1000);
			Simulator sim = new Simulator();
		sim.start(cd, cd2, fw);
			
			//me.dispose();
			//sim.start(fw);
			
			}
		}
		
		}
		fw.close();
		//System.exit(0);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	StartSim s=new StartSim();
	s.start();
//	s.destroy();
	}
	
	private void get_speed(JSlider j)
	{
		
		switch (j.getValue())
		{
		case 5: speed=5;break;
		case 4: speed=20;break;
		case 3: speed=50;break;
		case 2: speed=70;break;
		case 1: speed=100;break;
		default : break;
		}
		
		
	}
	
private void jSliderState(javax.swing.event.ChangeEvent evt)
{
	switch (js.getValue())
	{
	case 5: speed=0;break;
	case 4: speed=50;break;
	case 3: speed=200;break;
	case 2: speed=500;break;
	case 1: speed=1000;break;
	default : break;
	}
	
}
	
}
