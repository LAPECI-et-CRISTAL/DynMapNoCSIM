package Application;

import java.util.LinkedList;


/**
 * classe applicaiton qui contient une liste de teche
 */

public class Application {

    public int Time_arrived;
    /**
     * liste des  teches que contient l'application
     */
    private LinkedList<Tache> listTache = new LinkedList<Tache>();
    /**
     * id du cluster sur lequel l'application sera placee
     */
    private int cluster = -1;

    /**
     *  Energie consommee par l'application
     *
     */

    //private int energie;
    /**
     * Id de l'application
     */

    private int id_application;


    public Application() {

        // Initialisation de l'energie

        //	this.energie=0;
    }


    /**
     * retourner la liste de teche
     */
    public LinkedList<Tache> getListTache() {
        return listTache;
    }

    /**
     * ajouter une teche
     */
    public void setListTache(LinkedList<Tache> listTache) {
        this.listTache = listTache;
    }

    /**
     * retourner l'ID du cluster
     */
    public int getIdCluster() {
        return cluster;
    }

    /**
     * definir le cluster de l'appplication
     */
    public void setIdCluster(int idCluster) {
        cluster = idCluster;
    }


    /** recuperer l'energie consommee par l'application  */

//public int  getEnergie(){

//		return this.energie;

    //}

/**
 * modifier l'energie consomee par l'application apres chaque execution d'une tache ou communication
 *
 * @param energie
 */

//public void  setEnergie(int energie)
//{
    //this.energie+=energie;

//}

    /**
     * Recuperer l'id de l'application
     */

    public int getId_appli() {

        return id_application;
    }

    /**
     * Modifier l'ID de l'application
     */

    public void setId_appli(int id) {

        this.id_application = id;
    }

}
