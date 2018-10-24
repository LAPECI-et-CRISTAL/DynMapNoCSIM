package Application;

import java.util.LinkedList;


/**
 * classe applicaiton qui contient une liste de tâche 
 * 
 */

public class Application {

	/**
	 * liste des  tâches que contient l'application
	 */
	private  LinkedList<Tache> listTache = new LinkedList<Tache>();
	
	/**
	 * id du cluster sur lequel l'application sera placée
	 *
	 */
	private int cluster=-1;
	
	public int Time_arrived;
		
	/**
	 *  Energie consommée par l'application
	 * 
	 */
	
	//private int energie;
	
	/**
	 * 
	 *  Id de l'application
	 * 
	 */
	
	private  int id_application;
	
	
	public 	Application(){
		
		// Initialisation de l'energie
		
	//	this.energie=0;
	}
	
	
	/** retourner la liste de tâche */
	public LinkedList<Tache> getListTache() {
		return listTache;
	}

	/** ajouter une tâche*/
	public void setListTache(LinkedList<Tache> listTache) {
		this.listTache = listTache;
	}
	
	
	/**
	 * définir le cluster de l'appplication
	 * 
	 */
	public void setIdCluster(int idCluster) {
		cluster = idCluster;
	}
	
	/**
	 * retourner l'ID du cluster
	 *
	 */
	public int getIdCluster() {
		return cluster;
	}
	

	
	/** récuperer l'energie consommée par l'application  */
	
//public int  getEnergie(){
		
//		return this.energie;
		
	//}

/**
 * modifier l'energie consomée par l'application apres chaque éxecution d'une tache ou communication 
 * 
 * @param energie
 */

//public void  setEnergie(int energie)
//{
	//this.energie+=energie;

//}


/** Modifier l'ID de l'application */

public void setId_appli(int id){
		
		this.id_application=id;
	}


/** Récuperer l'id de l'application */

public  int getId_appli(){
	
	return id_application;
}

}
