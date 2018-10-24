package Mapping_Dynamique;


import java.awt.Color;

import Application.Tache;
import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class BN_GBHD {
	
	private boolean placé ;
	private int temps_recherche,Energie;
	private int x;
	private int y;
	public int temps_execution;
	private int [] charge= new  int[4]; 
	private int c=-1;
	

	
	
	
	public BN_GBHD(){
		
		temps_recherche=0;
		temps_execution=0;
		Energie=0;
	}

	/////////////////////////////////////////////////////////////////
public void start(Tache tachePlacer,int x,int y) throws InterruptedException
	{
	this.x=x;
	this.y=y;
	//System.out.println("\n"+x+" Green "+y);
	Create_NOC.platforme.k[x][y].setBackground(Color.GREEN);
	
	for(int i=0;i<=3;i++) this.charge[i]=Integer.MAX_VALUE;
	
	placé=false;
while(placé==false)
{
	// recherche à gauche 
	recherche_gauche(tachePlacer,this.x,this.y);
	//recherche en bas
	recherche_bas(tachePlacer,this.x,this.y);
	//recherche en haut
	recherche_haut(tachePlacer,this.x,this.y);
	// recherche a droite
	recherche_droite(tachePlacer,this.x,this.y);

	
	
	if(placé==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=3;i++)
			{
		
			if(this.charge[i] < min) {min=this.charge[i];c=i;}
			}
		
		lancer(tachePlacer,c,this.x,this.y);
		
		
		
		}
	else {
       int i=0;
		while (placé==false && i<=StaticParametre.Limit_NOC_x-1)

		{
			i++;


		  	//temps_recherche=temps_recherche+10;
		  	
		  refaire(tachePlacer,x,y-i); // refaire la recherche à partir du proc gauche
		  

		 
		  if(placé==false)		  
				  
	      refaire(tachePlacer,x+i,y); //refaire la recherche à partir du proc bas
		  
		  
		  
		  
		  if(placé==false)
		      refaire(tachePlacer,x-i,y); //refaire la recherche à partir du proc haut
		  
		  
		if (placé==false)
		
			  refaire(tachePlacer,x,y+i); //refaire la recherche à partir du proc droit
			

		if(placé==false && i==StaticParametre.Limit_NOC_y-1) {
			int c=0;
			placé=true;
			Create_NOC.platforme.k[x][y].setBackground(Color.RED);
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
		
		
			}
	
	}

	
}	

	}


/////////////////////// 
void changeColor(int x,int y)
{
	Color mp=Create_NOC.platforme.k[x][y].getBackground();
	  Create_NOC.platforme.k[x][y].setBackground(Color.BLUE);
	  try {
		Thread.sleep(Simulator.speed);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Create_NOC.platforme.k[x][y].setBackground(mp);
}

//////////////////////////
public  void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{


if(y-1>=0 && y<=StaticParametre.Limit_NOC_y-1 && x>=0 && x<=StaticParametre.Limit_NOC_x-1)
	{
	  this.temps_recherche=this.temps_recherche+10;
	  Energie+=1;
changeColor(x, y-1);
		  for(int i=0;i<tachePlacer.getType().size();i++)
		  {
			  
		  if(Create_NOC.getNOC()[x][y-1].getType() == tachePlacer.getType().get(i))
			{
				if (Create_NOC.getNOC()[x][y-1].getFree()==true)
				{
					
					this.charge[0]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y-1].getId(),Create_NOC.getNOC()[x][y].getId());
	                placé=true;
	
				}
			
				//else System.out.println("youupi gauche "+Mesh2dNOC.getNOC()[x][y-1].getType());
	
			}
		  }
	}
}

/////////////////////////////////////	
	
	
public  void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x+1<=StaticParametre.Limit_NOC_x-1 && x>=0 && y>=0 && y<=StaticParametre.Limit_NOC_y-1)
	  {
		this.temps_recherche=this.temps_recherche+10;
		 Energie+=1;
		changeColor(x+1, y);
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		  {
		 
	        if(Create_NOC.getNOC()[x+1][y].getType() == tachePlacer.getType().get(i))
				{
						if (Create_NOC.getNOC()[x+1][y].getFree()==true)
						{
							 			               
							this.charge[1]=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x+1][y].getId());
							placé=true;
		
						}
						
				}
	       
		  }
     }
	
																			}

//////////////////////////////////////////	
	
	

public  void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	
	if(x-1>=0 && x<=StaticParametre.Limit_NOC_x-1 && y>=0 && y<=StaticParametre.Limit_NOC_y-1)
	{
		this.temps_recherche=this.temps_recherche+10;
		 Energie+=1;
changeColor(x-1, y);
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	 		{
			 if(Create_NOC.getNOC()[x-1][y].getType() == tachePlacer.getType().get(i)) 
			  {
				  
				 
				if (Create_NOC.getNOC()[x-1][y].getFree()==true)
				{
				
					
		            this.charge[2]= GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x-1][y].getId(), Create_NOC.getNOC()[x][y].getId());
		    		placé=true;
					
				}
				
			  }
		
	   }
	}	

	
	

}


//////////////////////////////////
   	
   
public  void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
		
	
	if (y+1<=StaticParametre.Limit_NOC_y-1 && y>=0 && x>=0 && x<=StaticParametre.Limit_NOC_x-1)
	{	this.temps_recherche=this.temps_recherche+10;
	 Energie+=1;
changeColor(x, y+1);
		 for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
		 {
			if (Create_NOC.getNOC()[x][y+1].getType() == tachePlacer.getType().get(i))
		    {
			if (Create_NOC.getNOC()[x][y+1].getFree()==true)
			{
				
	            this.charge[3]= GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x][y+1].getId());
				
	            placé=true;
				}
				    }	
		 }
	}
	
}


////////////////////////////////////////////

public  void refaire(Tache tachePlacer,int x,int y) throws InterruptedException
{

		// recherche a gauche
		recherche_gauche(tachePlacer,x,y);
		//recherche en bas
		recherche_bas(tachePlacer,x,y);
		
		//recherche en haut
		recherche_haut(tachePlacer,x,y);
		
		// recher a droite
		recherche_droite(tachePlacer,x,y);	
		
		//temps_recherche=temps_recherche+40;
		
		if(placé==true)
		{ int min=Integer.MAX_VALUE;
		for(int i=0;i<=3;i++)
			{            
			
			if(this.charge[i] < min) {min=this.charge[i];c=i;}
			
			}
		
      
		 lancer(tachePlacer,c,x,y);
		 
		
		
		
		}
	
	
	
}
//////////////////////////


////////////////////////////////////////

public  void lancer(Tache t,int K,int x,int y) throws InterruptedException
{

int x1=-1,y1=-1;
	
	switch (K)
	{ 
	case 0 : 	{x1=x;y1=y-1;}break;
	case 1 :	{x1=x+1;y1=y;}break;
	case 2 :	{x1=x-1;y1=y;}break;
	case 3 :	{x1=x;y1=y+1;}break;
	
	}
	
	
	
	
	
t.x=x1;
t.y=y1;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;


Simulator.set_temps_recherche(this.temps_recherche);

Create_NOC.getNOC()[x1][y1].set_state(false);
Create_NOC.platforme.k[x1][y1].setBackground(Color.RED);
//System.out.println("End Green");
Create_NOC.platforme.k[this.x][this.y].setBackground(Color.RED);
Simulator.setEnergy(Energie);

}






}
