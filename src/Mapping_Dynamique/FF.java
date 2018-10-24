package Mapping_Dynamique;

import java.awt.Color;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class FF {
	
	// variable qui détermine si la tâche a été placé
	public boolean placé ;
	// temps de l'execution de l'algorithme
	public int temps_recherche;
	// x,y position du processeur sur lequel la tâche pere est placé ( processeur courant)
	public int x;
	public int y,Saut;
	
	// Energie consommée lors de la recherche
	public int Energie;
	
	
	public FF(){
		
		temps_recherche=0;
		Energie=0;
		
	}





///////////////////////////////////////



public  void lancer(Tache t,int x,int y) throws InterruptedException
{


t.x=x;
t.y=y;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;

Simulator.set_temps_recherche(this.temps_recherche);

Create_NOC.getNOC()[x][y].set_state(false);

Create_NOC.platforme.k[StaticParametre.listApplication.get(t.getIdApplication()).getListTache().get(t.getIdPere()).x][StaticParametre.listApplication.get(t.getIdApplication()).getListTache().get(t.getIdPere()).y].setBackground(Color.RED);

Create_NOC.platforme.k[x][y].setBackground(Color.RED);
Simulator.setEnergy(this.Energie);//ajouter l energie consommé lors de la recherche à l energie total consommé par l application


}








////////////////////////////////



public   void start(Tache tachePlacer) throws InterruptedException 
{
placé=false;															
int Id_Noeud=0;


Create_NOC.platforme.k[StaticParametre.listApplication.get(tachePlacer.getIdApplication()).getListTache().get(tachePlacer.getIdPere()).x][StaticParametre.listApplication.get(tachePlacer.getIdApplication()).getListTache().get(tachePlacer.getIdPere()).y].setBackground(Color.GREEN);
while(placé==false &&  Id_Noeud<StaticParametre.listProcesseur.size())	
{
//recherche d'un proc Libre. 
recherche_proc_libre(tachePlacer,Id_Noeud);
	

	if(placé==false && Id_Noeud==StaticParametre.listProcesseur.size()-1) {
						int c=0;
						placé=true;
						
						Create_NOC.platforme.k[StaticParametre.listApplication.get(tachePlacer.getIdApplication()).getListTache().get(tachePlacer.getIdPere()).x][StaticParametre.listApplication.get(tachePlacer.getIdApplication()).getListTache().get(tachePlacer.getIdPere()).y].setBackground(Color.RED);
						if(StaticParametre.not_mapped.isEmpty())
						StaticParametre.not_mapped.add(tachePlacer);
									else
											{
												for(int k=0;k<StaticParametre.not_mapped.size();k++)
												{
													if(StaticParametre.not_mapped.get(k).equals(tachePlacer))
														{c=1;break;}
												}
											if(c!=1)	StaticParametre.not_mapped.add(tachePlacer);
												
											}
									}
							

																																												


	Id_Noeud++;
	}
		
		


}





 
/////////////////////// 
 
public void recherche_proc_libre(Tache tachePlacer,int Id_proc) throws InterruptedException{
	

	//System.out.println("x   "+x+"   y  "+y+"  Id_proc  "+Id_proc);
	int x=StaticParametre.listProcesseur.get(Id_proc).x;
	int y=StaticParametre.listProcesseur.get(Id_proc).y;
	
	
	if(y>=0 && y<=StaticParametre.Limit_NOC_y-1 && x>=0 && x<=StaticParametre.Limit_NOC_x-1)
	{
		

	temps_recherche=temps_recherche+10;	
	this.Energie+=1;
	changeColor(x,y);
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
	{
		if (Create_NOC.getNOC()[x][y].getFree()==true)
		{

			lancer(tachePlacer,x,y);
			placé=true;
			
		}
	}
	}
	
	}
}


/////////////////////////////////////	

/////////////////////// 
void changeColor(int x,int y)
{
Color mp=Create_NOC.platforme.k[x][y].getBackground();
Create_NOC.platforme.k[x][y].setBackground(Color.BLUE);
try {
Thread.sleep(Simulator.speed);
} catch (InterruptedException e) {
//TODO Auto-generated catch block
e.printStackTrace();
}
Create_NOC.platforme.k[x][y].setBackground(mp);
}


//////////////////////////////////////////

public  int Energie()
	{
	return(this.Energie);
	}

}
