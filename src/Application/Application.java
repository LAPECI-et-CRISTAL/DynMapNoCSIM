package Application;

import java.util.LinkedList;


/**
 * classe applicaiton qui contient une liste de t�che 
 * 
 */

public class Application {

	/**
	 * liste des  t�ches que contient l'application
	 */
	private  LinkedList<Tache> listTache = new LinkedList<Tache>();
	
	/**
	 * id du cluster sur lequel l'application sera plac�e
	 *
	 */
	private int cluster=-1;
	
	public int Time_arrived;
		
	/**
	 *  Energie consomm�e par l'application
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
	
	
	/** retourner la liste de t�che */
	public LinkedList<Tache> getListTache() {
		return listTache;
	}

	/** ajouter une t�che*/
	public void setListTache(LinkedList<Tache> listTache) {
		this.listTache = listTache;
	}
	
	
	/**
	 * d�finir le cluster de l'appplication
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
	

	
	/** r�cuperer l'energie consomm�e par l'application  */
	
//public int  getEnergie(){
		
//		return this.energie;
		
	//}

/**
 * modifier l'energie consom�e par l'application apres chaque �xecution d'une tache ou communication 
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


/** R�cuperer l'id de l'application */

public  int getId_appli(){
	
	return id_application;
}

}
