package Routage_Algorithme;

import java.util.LinkedList;

import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Architecture.Paquet;
import GUI.LoadPlatforme;
import Simulation.Simulator;
import Simulation.StaticParametre;


public class MORA_Algorithme {

	private int x_source;
	private int y_source;
	private int x_destination;
	private int y_destination;
	private int temps_attente;
	private int temps_routage;
	private int Energie_routage;
	private int Energie_attente;
	private int donnee;
	private Paquet p;
	private LinkedList<Integer> cheminx= new LinkedList<Integer>();
	private LinkedList<Integer> cheminy= new LinkedList<Integer>();

	
	
	 public MORA_Algorithme(Paquet p)
	   	{
		 x_source=p.x_inter;
		   y_source=p.y_inter;
		  x_destination=p.x_destination;
		   y_destination=p.y_destination;
		   this.temps_attente=0;
		   this.temps_routage=0;
		   this.Energie_attente=0;
		   this.Energie_routage=0;
		   this.p=p;
		   donnee=StaticParametre.DEBIT;
		   
		   
	   	}
	 
	 public void start() throws InterruptedException
{
		
		 if(x_source < x_destination)//////////////////////// de haut vers le bas ///////
		  {
			 haut_vers_bas();
			  	
		  }
		  
		  else 
		  {
			  if(x_source>x_destination)///////////////////////// de bas vers le haut//////////
			  		{
				        bas_vers_haut();
				  			  
			  		}
			  
			  else 		{			//////////////////// sur la meme ligne x_source=x_desstination ////////// public void meme ligne .
	                    
				  if((y_source-y_destination)< 0) // de gauche à droite 

			    	 {
			
					  
					 
					  
			    		 
			    		     if(get_lien(x_source,y_source,x_source,y_source+1)>0) 
			    			 {
			    		    	 set_lien(x_source,y_source,x_source,y_source+1);
			    		    	 attendre(x_source,y_source,x_source,y_source+1);
			    		    	 lancer_envoie(x_source,y_source,x_source,y_source+1,2);
			    			 }
			    		     
			    		     else
			    		    	 {
			    		    	 set_lien(x_source,y_source,x_source,y_source+1);
			    		    	 lancer_envoie(x_source,y_source,x_source,y_source+1,2);
			    		    	 
			    		    	 }
			    		
			    			 cheminx.add(this.x_source);
			    	 	    cheminy.add(this.y_source);
			    	 	   
			    	 }

			    	 else   //de droite à gauche  

			    	 {

			    		 	 
			    		 
			    		 		 
			    		  if (get_lien(x_source,y_source-1,x_source,y_source)>0)
			    				 {
			    		
			    			  
			    		
			    			 
			    			  set_lien(x_source,y_source-1,x_source,y_source);
			    			  attendre(x_source,y_source-1,x_source,y_source);
			    			  lancer_envoie(x_source,y_source-1,x_source,y_source,1);
			    				 }
			    		  
			    		  else
			    			  {
			    			  set_lien(x_source,y_source-1,x_source,y_source);
			    			  lancer_envoie(x_source,y_source-1,x_source,y_source,1);
			    			  }
			    				 
			    		 		cheminx.add(x_source);
				    	 	    cheminy.add(y_source);	 
			    		       }
		 
			    		
			
			    		 		
			    	 }
				  
				  
				      
			  			}
		 
		 
	 }  

	 
///////////// cette methode nous retourne le volume du lien  ////////
	  
public int get_lien(int x,int y,int x1,int y1)
	{
	


	int lien=-1;
		if(x>=0 && x<=7 && y>=0 && y<=7 && x1>=0 && x1<=7 && y1>=0 && y1<=7)	{
		lien=GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
		} 
return lien;
 

	}

//////////////////////////cette methode permet d'ajouter le volume de données echangées sur le lien/////////////

public void set_lien(int x,int y,int x1,int y1)
	{
	if(x>=0 && x<=7 && y>=0 && y<=7 && x1>=0 && x1<=7 && y1>=0 && y1<=7)
    	GenererChannel.getListChannel().get(1).ajout_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId(),donnee);
	}



	
///////////
public void attendre(int x,int y,int x1,int y1) throws InterruptedException
{
	int size_file=get_lien(x,y,x1,y1);
	// ajouter un evt , pour indiquer le debut d un routage et donc la fin de l attente !!
	this.temps_attente=(((size_file-donnee)/StaticParametre.DEBIT)*StaticParametre.Temps_envoie);
	
	
	
	
	Simulator.Add_Event(Simulator.Tnow+temps_attente);
	
	
	this.Energie_attente+=(StaticParametre.Energie_attente_envoie*((size_file-donnee)/StaticParametre.DEBIT));
	
	Simulator.setEnergy(Energie_attente);
	

	
}
///////////
//////////////////////////

public  void lancer_envoie(int x,int y,int x1,int y1,int M) throws InterruptedException
{
			
	

	int xx=x*2;
	int yy=y*2;int xx1=x1*2;
	int yy1=y1*2;
	if (x==x1) LoadPlatforme.popi.get(LoadPlatforme.find(x*2,(y+y1))).setVisible(true);
	else if (y==y1) LoadPlatforme.popi.get(LoadPlatforme.find((x+x1),y*2)).setVisible(true);
	
	//Thread.sleep(Simulator.speed/10);
	
	this.temps_routage=(StaticParametre.Temps_envoie);
	int c=temps_routage+temps_attente+Simulator.Tnow;
	GenererChannel.getListChannel().get(1).event(c,Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());

	p.set_Tdebut(Simulator.Tnow+temps_attente);
	p.set_Tfin(temps_routage+temps_attente+Simulator.Tnow);
	Simulator.set_temps_routage(temps_routage+temps_attente);
	Simulator.Add_Event(c);
	
	if(M==2)
	{	
    	p.x_inter=x1;
    	p.y_inter=y1;
	}
	else
	{

    	p.x_inter=x;
    	p.y_inter=y;
	}
	

	//System.out.println(" id   "+p.id+" aprés   x   "+p.x_inter+"   y  "+p.y_inter+"   t arrivé "+p.T_fin);
	
	this.Energie_routage=(StaticParametre.Energie_envoi);

	Simulator.setEnergy(Energie_routage);
	
	    }


///////////

////////////////////////


///// 
public void haut_vers_bas() throws InterruptedException
{

	if (y_source<y_destination)		////// de gauche à droite //////////
	{ 
//// verifier si le lien vers la droite est moins chargé que celui du bas /////
		  		
	if(get_lien(x_source,y_source,x_source,y_source+1)<get_lien(x_source,y_source,x_source+1,y_source))
		{
		
		    if (get_lien(x_source,y_source,x_source,y_source+1)>0)
		    {
		    	set_lien(x_source,y_source,x_source,y_source+1);
		    	attendre(x_source,y_source,x_source,y_source+1);
		    	lancer_envoie(x_source,y_source,x_source,y_source+1,2);
		    }
			
		    else 
		    {
		    	set_lien(x_source,y_source,x_source,y_source+1);
		    lancer_envoie(x_source,y_source,x_source,y_source+1,2);
  
		    }
		    
		  
			cheminx.add(x_source);
	 	    cheminy.add(y_source);
			     
		}

	else 		{
		// envoyer vers le processeur bas 
		
		if (get_lien(x_source,y_source,x_source+1,y_source)>0)
		{      
			set_lien(x_source,y_source,x_source+1,y_source);
			attendre(x_source,y_source,x_source+1,y_source);
			lancer_envoie(x_source,y_source,x_source+1,y_source,2);
		}
		else
		{
			
		set_lien(x_source,y_source,x_source+1,y_source);
		lancer_envoie(x_source,y_source,x_source+1,y_source,2);
		} 
		
		cheminx.add(x_source);
 	    cheminy.add(y_source);
			}
}

else 

{	
if	(y_source>y_destination)   //////////////// de droite à gauche ///////////
		
{
	
	if(get_lien(x_source,y_source-1,x_source,y_source)<get_lien(x_source,y_source,x_source+1,y_source))
	{
	// envoyer vers le processeur gauche
		
	if (get_lien(x_source,y_source-1,x_source,y_source)>0)
	{
	set_lien(x_source,y_source-1,x_source,y_source);	
	attendre(x_source,y_source-1,x_source,y_source);  
    lancer_envoie(x_source,y_source-1,x_source,y_source,1);   
	}
	else
	{
	set_lien(x_source,y_source-1,x_source,y_source);
	lancer_envoie(x_source,y_source-1,x_source,y_source,1);

	}
	
		cheminx.add(x_source);
 	    cheminy.add(y_source);
		     
	}

	else 		{
	
	// envoyer vers le processeur bas 
	
		if (get_lien(x_source,y_source,x_source+1,y_source)>0)
		{
			set_lien(x_source,y_source,x_source+1,y_source);
			attendre(x_source,y_source,x_source+1,y_source);
			lancer_envoie(x_source,y_source,x_source+1,y_source,2);
		}
		
		else
			 {
			set_lien(x_source,y_source,x_source+1,y_source);
			lancer_envoie(x_source,y_source,x_source+1,y_source,2);
			 }
	
		cheminx.add(x_source);
	    cheminy.add(y_source);
	}

}

else // sur la meme colonne y_source=y_destination 
{
	if (get_lien(x_source,y_source,x_source+1,y_source)>0)
	{
		set_lien(x_source,y_source,x_source+1,y_source);
	  attendre(x_source,y_source,x_source+1,y_source);
	  lancer_envoie(x_source,y_source,x_source+1,y_source,2);
	}
	
	else {
		set_lien(x_source,y_source,x_source+1,y_source);
	lancer_envoie(x_source,y_source,x_source+1,y_source,2);
	}
	
		cheminx.add(x_source);
	    cheminy.add(y_source);
		}


}
}





//////////////////////////////////

public void bas_vers_haut() throws InterruptedException
			{
	
	if (y_source<y_destination)		////// de gauche à droite //////////
  	{ 
  		//// verifier si le lien vers la droite est moins chargé celui vers le haut /////
  				  		
  			if(get_lien(x_source,y_source,x_source,y_source+1)<get_lien(x_source-1,y_source,x_source,y_source))
	  			{
  				// envoyer vers le processeur gauche
  					
  				if (get_lien(x_source,y_source,x_source,y_source+1)>0)
  				{
  					set_lien(x_source,y_source,x_source,y_source+1);
  					attendre(x_source,y_source,x_source,y_source+1);
  					lancer_envoie(x_source,y_source,x_source,y_source+1,2);
  				}
  				else
  				{
  					set_lien(x_source,y_source,x_source,y_source+1);
  					lancer_envoie(x_source,y_source,x_source,y_source+1,2);
  				}
  				
  				cheminx.add(x_source);
	    	 	    cheminy.add(y_source);
		  			     
	  			}
	  
  			else 		{
  				// envoyer vers le processeur haut 
  				if (get_lien(x_source-1,y_source,x_source,y_source)>0)
  				{
  					set_lien(x_source-1,y_source,x_source,y_source);
                      attendre(x_source-1,y_source,x_source,y_source);
                      lancer_envoie(x_source-1,y_source,x_source,y_source,1);
          	  			  				}
  				else {
  					set_lien(x_source-1,y_source,x_source,y_source);
  				lancer_envoie(x_source-1,y_source,x_source,y_source,1);
  		
  				}
		      		cheminx.add(x_source);
	    	 	    cheminy.add(y_source);
	  				}
  	}
  	
  	else 
  		
  	{	
  		if	(y_source>y_destination)   //////////////// de droite à gauche ///////////
  				
  		{
  			
  			if(get_lien(x_source,y_source-1,x_source,y_source)<get_lien(x_source-1,y_source,x_source,y_source))
  			{
				// envoyer vers le processeur gauche
					
				if (get_lien(x_source,y_source-1,x_source,y_source)>0)
				
					{
					set_lien(x_source,y_source-1,x_source,y_source);
					attendre(x_source,y_source-1,x_source,y_source);  
					lancer_envoie(x_source,y_source-1,x_source,y_source,1);
					}
           
				else {
					set_lien(x_source,y_source-1,x_source,y_source);
					lancer_envoie(x_source,y_source-1,x_source,y_source,1);
				}
	      
					
					cheminx.add(x_source);
    	 	    cheminy.add(y_source);
	  			     
  			}
  
			else 		{
				
				// envoyer vers le processeur haut 
				
				if (get_lien(x_source-1,y_source,x_source,y_source)>0)
				{
				    set_lien(x_source-1,y_source,x_source,y_source);                
				attendre(x_source-1,y_source,x_source,y_source);
				lancer_envoie(x_source-1,y_source,x_source,y_source,1);
				
				}
				else {
					set_lien(x_source-1,y_source,x_source,y_source);
				lancer_envoie(x_source-1,y_source,x_source,y_source,1);
				}
				
	      	cheminx.add(x_source);
    	    cheminy.add(y_source);
  				}
	
	
	
	
	
			}
  		
else 		{   /// sur la meme colonne y_source=y_destination
				
				// envoyer vers le processeur haut 
				
				if (get_lien(x_source-1,y_source,x_source,y_source)>0)
				{  
					set_lien(x_source-1,y_source,x_source,y_source);
					attendre(x_source-1,y_source,x_source,y_source);
					lancer_envoie(x_source-1,y_source,x_source,y_source,1);
				}
				else {
					
					set_lien(x_source-1,y_source,x_source,y_source);
				lancer_envoie(x_source-1,y_source,x_source,y_source,1);
				}

	      		cheminx.add(x_source);
    	 	    cheminy.add(y_source);
  				}
  		
  		
		
  	}
}
}
