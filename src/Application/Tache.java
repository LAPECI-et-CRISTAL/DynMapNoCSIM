package Application;

import java.util.ArrayList;
import java.util.LinkedList;
import Simulation.StaticParametre;


/**
 * 
 * Cette classe donne la représentation d'une tâche avec ces différentes caractéristiques
 *
 */



public class Tache {
	
	/**
	 * le type de la Tache
	 */
	public 	ArrayList<Integer> type=new ArrayList<Integer>();

	
	/**
	 * la taille de la Tache
	 */
	public 	ArrayList<Integer> tailleTache=new ArrayList<Integer>();
	
	/**
	 * le nombrer de cylce nécessaire à l'exécution d'une tâche selon le type
	 */
	public 	ArrayList<Integer> Nbr_Cycle=new ArrayList<Integer>();

	
	
	/**
	 * identificateur de l'application de la Tache
	 */
	private int idApplication;
	
	/**
	 * identificateur de la Tache
	 */
	private int id;
	
	/**
	 * la coordonnées x(par rapport a ligne du NOC)
	 */
	public int x;
	
	/**
	 * la coordonnées y(par rapport a colone du NOC)
	 */
	public int y;
	
	
	/**
	 *  Nombre de messages reçu en provenance des fils (1 message par fils)
	 * 
	 */
	public int nbr_message=0;
	
	
	
	public int fin;
	
	/**
	 * la quantité de données à partager
	 */
	private LinkedList <Integer> donneePartager=new LinkedList<Integer>();
	
	/**
	 * identificateur du père
	 */
	private int idPere=0;
	
	/**
	 * successeurs de la Tache (fils)
	 */
	private LinkedList<Integer> succ = new LinkedList<Integer>();
	

	/**
	 *  la variable mapped détermine si la tâche est placé ou non 
	 */
	public boolean mapped = false;
	
	/**
	 *  x1,y1 détermine à quel niveau(processeur) se trouve la communication avec mes fils
	 */
	public ArrayList<Integer> x1=new ArrayList<Integer>();
	
	public ArrayList<Integer> y1=new ArrayList<Integer>();
	
	
	
	public ArrayList<Integer> communication_de_fils=new ArrayList<Integer>();
	
	/**
	 *  Temps du debut et fin d'execution sur un proc
	 * 
	 */
	public int debut_execution,fin_execution;
	
	/**
	 *  Temps du début et fin de routage avec le pere
	 */
	public int fin_root_pere=-1;
	public int debut_root_pere=-2;
	
	/**
	 *  x2,y2 détermine à quel niveau(processeur) se trouve la communication avec mon pere
	 */
	public int x2,y2;
	
	
	/**
	 *  Temps du debut de routage
	 * 
	 */
	public int debut_routage;
	
	
	/**
	 * 
	 *  Variable qui détermine si le routage est terminé
	 */
	public boolean end_root=false;
	
	public boolean end=false,Launched=false;
	
	public boolean k=false;
	
	/**
	 *  Temps de fin de routage pour chaque fils
	 * 
	 */
	public ArrayList<Integer> fin_routage=new ArrayList<Integer>();
	
	
	
	public Tache(){
		// Initialisation des valeurs
		debut_execution=-1;
		fin_execution=-1;
		debut_routage=-1;
		fin=0;
		
		x=-1;
		y=-1;
		x2=-1;
		y2=-1;
	
		for(int i=0;i<=100;i++)
		{
			x1.add(-2);
			y1.add(-2);
			fin_routage.add(-1);
			type.add(-1);
			tailleTache.add(-1);
			Nbr_Cycle.add(-1);
		}
	}
	
	/**
	 * donner un identificateur a la tâche
	 * 
	 */
	public void setId(int id){
		this.id= id;
	}
	
	/**
	 * renvoyer l'indentificateur de la tâche
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Modifier un type a la tâche
	 */
	public void setType(String typ){
		
		
		type.add(Integer.parseInt(typ),Integer.parseInt(typ));
		
		
	}
	

	
	
	
	/**
	 * renvoyer  le type de la tâche
	 */
	public ArrayList<Integer> getType() {
		return type;
	}
	
	/**
	 * Modifier la taille de la tâche
	 */
	
		public void setTailleTache(String type,String taille){
			
			// ajouter la taille de la tache selon le type
			tailleTache.add(Integer.parseInt(type),Integer.parseInt(taille));
			
		}
		
		/**
		 * 
		 * Ajouter le nombre de cylce nécessaire à l execution de la tâche
     	 * 
		 * 
		 */
public void setCycleTache(String type,String cycles){
				// ajouter la taille de la tache selon le type
			Nbr_Cycle.add(Integer.parseInt(type),Integer.parseInt(cycles));
			
		}
		
		
	
	
	/**
	 * renvoyer la taille de la tâche selon le type
	 */
	public int getTailleTache(int typ) {
		return tailleTache.get(typ); 
	}

	/**
	 * retourner le nombre de cycles de la tâche selon le type
	 */
	public int getCyclesTache(int typ) {
		return Nbr_Cycle.get(typ); 
	}
	
	/**
	 * donner l'indentificateur de l'application de la tâche
	 */ 
	public void setIdApplication(int id_application) {
		this.idApplication = id_application;
	}
	
	/**
	 * renvoyer l'indentificateur de l'identificateur de l'application de la tâche
	 */
	public int getIdApplication() {
		return idApplication;
	}	

	/**
	 * retourner la taille des donné a partagé
	 */ 
	public int  getDonneePartager(int k) 
		{
		return donneePartager.get(k);
		}	
	
	/**
	 * définir le nombre de donnés a partager
	 */
	public void setDonneePartager(LinkedList<Integer> donnePartage) {
	this.donneePartager=donnePartage;
	}	
	
	/**
	 * retourner l'identificateur du père
	 */
	public int getIdPere() {
		return idPere;
	}

	/**
	 * définir l'identificateur du père
	 */ 
	public void setIdPere(int idPere) {
		this.idPere = idPere;
	}

	/** définir les successeur	*/
	public void setSucc(LinkedList<Integer> succ) {
		this.succ = succ;
	}

	/** 
	 * 
	 * @return : listes des successeur
	 */
	public LinkedList<Integer> getSucc() {
		return succ;
	}

	
	/**
	 *  incrément le nombre de message reçu en provenance des fils	
	 */
	public void set_communication_reçu()
	{
		nbr_message++;
	}

/**
 * 
 * retourne le nbr de message reçu
 */
public int get_nbr_message()
	{
	return this.nbr_message;
	
	}

/**
 *  temps du début de routage
 */
public void debut_routage(int Time)
	{
	debut_routage=Time;
	}


/**
 *  modifie le temps du début de routage avec mon pere
 * @param Time
 */
public void debut_root_pere(int Time)
	{
	debut_root_pere=Time;
	}
	
/**
 * Modifie le temps de fin de routage
 * 
 * @param Time
 * @param x1
 * @param y1
 * @param j
 */
public void fin_routage(int Time,int x1,int y1,int j)

	{
	
	fin_routage.set(j,Time);
	this.x1.set(j,x1);
	this.y1.set(j,y1);
	
	}

/**
 *  
 *  Modifie le temps de fin de routage avec mon pere
 * 
 * @param Time
 * @param x1
 * @param y1
 */
public void fin_root_pere(int Time,int x1,int y1)

{
	fin_root_pere=Time;
	StaticParametre.getListApplication().get(idApplication).getListTache().get(idPere).communication_de_fils.add(fin_root_pere);
	x2=x1;
	y2=y1;
}

/**
 * 
 * 
 */

public void set_fin()
{
	this.fin++;
	
}

/**
 * 
 * @param Time
 * @return
 */
public boolean verify_comm_fils(int Time) // une procedure qui verifie si le tnow correspond à la fin du routage du fils vers le pere


{
	boolean t=false;
	for(int i=0;i<communication_de_fils.size();i++)
	{
		if(Time ==communication_de_fils.get(i) )
			{t=true;break;}
	}
	
	return(t);
	
}

/**** Retourne la liste des taches fils trié par ordre croissant selon les données partagées **/

public ArrayList<Integer> get_Max_Fils()
{
	int max;
	int idd;
	
	ArrayList id=new ArrayList<Integer>();
	
	if(!this.succ.isEmpty())
	{
		idd=-1;
		max=0;
		id.add(0);
		boolean brek;
		//id=this.donneePartager.get(0);
		while(id.size()!=succ.size()+1)
		{max=0;
			 
				for(int i=0;i<this.succ.size();i++)
			 {

					brek=false;		
					if(this.donneePartager.get(i)>max)
					{
						for(int j=0;j<id.size();j++)
							 {if(id.get(j)==succ.get(i))
								brek=true;
							 }
						
					if(!brek)
					{
					max=this.donneePartager.get(i);
					idd=succ.get(i);
					//System.out.println("    suuuccc  "+idd);
					}
					}
				 }
				

				//System.out.println("idddd  "+idd);
			 id.add(idd);
		}
	
	}
	id.remove(0);
	
return	id;
}



}


