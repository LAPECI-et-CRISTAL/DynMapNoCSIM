package Application;

import Simulation.StaticParametre;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Cette classe donne la representation d'une teche avec ces differentes caracteristiques
 */


public class Tache {

    /**
     * le type de la Tache
     */
    public ArrayList<Integer> type = new ArrayList<Integer>();


    /**
     * la taille de la Tache
     */
    public ArrayList<Integer> tailleTache = new ArrayList<Integer>();

    /**
     * le nombrer de cylce necessaire e l'execution d'une teche selon le type
     */
    public ArrayList<Integer> Nbr_Cycle = new ArrayList<Integer>();
    /**
     * la coordonnees x(par rapport a ligne du NOC)
     */
    public int x;
    /**
     * la coordonnees y(par rapport a colone du NOC)
     */
    public int y;
    /**
     * Nombre de messages reeu en provenance des fils (1 message par fils)
     */
    public int nbr_message = 0;
    public int fin;
    /**
     * la variable mapped determine si la teche est place ou non
     */
    public boolean mapped = false;
    /**
     * x1,y1 determine e quel niveau(processeur) se trouve la communication avec mes fils
     */
    public ArrayList<Integer> x1 = new ArrayList<Integer>();
    public ArrayList<Integer> y1 = new ArrayList<Integer>();
    public ArrayList<Integer> communication_de_fils = new ArrayList<Integer>();
    /**
     * Temps du debut et fin d'execution sur un proc
     */
    public int debut_execution, fin_execution;
    /**
     * Temps du debut et fin de routage avec le pere
     */
    public int fin_root_pere = -1;
    public int debut_root_pere = -2;
    /**
     * x2,y2 determine e quel niveau(processeur) se trouve la communication avec mon pere
     */
    public int x2, y2;
    /**
     * Temps du debut de routage
     */
    public int debut_routage;
    /**
     * Variable qui determine si le routage est termine
     */
    public boolean end_root = false;
    public boolean end = false, Launched = false;
    public boolean k = false;
    /**
     * Temps de fin de routage pour chaque fils
     */
    public ArrayList<Integer> fin_routage = new ArrayList<Integer>();
    /**
     * identificateur de l'application de la Tache
     */
    private int idApplication;
    /**
     * identificateur de la Tache
     */
    private int id;
    /**
     * la quantite de donnees e partager
     */
    private LinkedList<Integer> donneePartager = new LinkedList<Integer>();
    /**
     * identificateur du pere
     */
    private int idPere = 0;
    /**
     * successeurs de la Tache (fils)
     */
    private LinkedList<Integer> succ = new LinkedList<Integer>();


    public Tache() {
        // Initialisation des valeurs
        debut_execution = -1;
        fin_execution = -1;
        debut_routage = -1;
        fin = 0;

        x = -1;
        y = -1;
        x2 = -1;
        y2 = -1;

        for (int i = 0; i <= 100; i++) {
            x1.add(-2);
            y1.add(-2);
            fin_routage.add(-1);
            type.add(-1);
            tailleTache.add(-1);
            Nbr_Cycle.add(-1);
        }
    }

    /**
     * renvoyer l'indentificateur de la teche
     */
    public int getId() {
        return this.id;
    }

    /**
     * donner un identificateur a la teche
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * renvoyer  le type de la teche
     */
    public ArrayList<Integer> getType() {
        return type;
    }

    /**
     * Modifier un type a la teche
     */
    public void setType(String typ) {


        type.add(Integer.parseInt(typ), Integer.parseInt(typ));


    }

    /**
     * Modifier la taille de la teche
     */

    public void setTailleTache(String type, String taille) {

        // ajouter la taille de la tache selon le type
        tailleTache.add(Integer.parseInt(type), Integer.parseInt(taille));

    }

    /**
     * Ajouter le nombre de cylce necessaire e l execution de la teche
     */
    public void setCycleTache(String type, String cycles) {
        // ajouter la taille de la tache selon le type
        Nbr_Cycle.add(Integer.parseInt(type), Integer.parseInt(cycles));

    }


    /**
     * renvoyer la taille de la teche selon le type
     */
    public int getTailleTache(int typ) {
        return tailleTache.get(typ);
    }

    /**
     * retourner le nombre de cycles de la teche selon le type
     */
    public int getCyclesTache(int typ) {
        return Nbr_Cycle.get(typ);
    }

    /**
     * renvoyer l'indentificateur de l'identificateur de l'application de la teche
     */
    public int getIdApplication() {
        return idApplication;
    }

    /**
     * donner l'indentificateur de l'application de la teche
     */
    public void setIdApplication(int id_application) {
        this.idApplication = id_application;
    }

    /**
     * retourner la taille des donne a partage
     */
    public int getDonneePartager(int k) {
        return donneePartager.get(k);
    }

    /**
     * definir le nombre de donnes a partager
     */
    public void setDonneePartager(LinkedList<Integer> donnePartage) {
        this.donneePartager = donnePartage;
    }

    /**
     * retourner l'identificateur du pere
     */
    public int getIdPere() {
        return idPere;
    }

    /**
     * definir l'identificateur du pere
     */
    public void setIdPere(int idPere) {
        this.idPere = idPere;
    }

    /**
     * @return : listes des successeur
     */
    public LinkedList<Integer> getSucc() {
        return succ;
    }

    /**
     * definir les successeur
     */
    public void setSucc(LinkedList<Integer> succ) {
        this.succ = succ;
    }

    /**
     * increment le nombre de message reeu en provenance des fils
     */
    public void set_communication_reeu() {
        nbr_message++;
    }

    /**
     * retourne le nbr de message reeu
     */
    public int get_nbr_message() {
        return this.nbr_message;

    }

    /**
     * temps du debut de routage
     */
    public void debut_routage(int Time) {
        debut_routage = Time;
    }


    /**
     * modifie le temps du debut de routage avec mon pere
     *
     * @param Time
     */
    public void debut_root_pere(int Time) {
        debut_root_pere = Time;
    }

    /**
     * Modifie le temps de fin de routage
     *
     * @param Time
     * @param x1
     * @param y1
     * @param j
     */
    public void fin_routage(int Time, int x1, int y1, int j) {

        fin_routage.set(j, Time);
        this.x1.set(j, x1);
        this.y1.set(j, y1);

    }

    /**
     * Modifie le temps de fin de routage avec mon pere
     *
     * @param Time
     * @param x1
     * @param y1
     */
    public void fin_root_pere(int Time, int x1, int y1) {
        fin_root_pere = Time;
        StaticParametre.getListApplication().get(idApplication).getListTache().get(idPere).communication_de_fils.add(fin_root_pere);
        x2 = x1;
        y2 = y1;
    }

    /**
     *
     *
     */

    public void set_fin() {
        this.fin++;

    }

    /**
     * @param Time
     * @return
     */
    public boolean verify_comm_fils(int Time) // une procedure qui verifie si le tnow correspond e la fin du routage du fils vers le pere


    {
        boolean t = false;
        for (int i = 0; i < communication_de_fils.size(); i++) {
            if (Time == communication_de_fils.get(i)) {
                t = true;
                break;
            }
        }

        return (t);

    }

    /**** Retourne la liste des taches fils trie par ordre croissant selon les donnees partagees **/

    public ArrayList<Integer> get_Max_Fils() {
        int max;
        int idd;

        ArrayList id = new ArrayList<Integer>();

        if (!this.succ.isEmpty()) {
            idd = -1;
            max = 0;
            id.add(0);
            boolean brek;
            //id=this.donneePartager.get(0);
            while (id.size() != succ.size() + 1) {
                max = 0;

                for (int i = 0; i < this.succ.size(); i++) {

                    brek = false;
                    if (this.donneePartager.get(i) > max) {
                        for (int j = 0; j < id.size(); j++) {
                            if (id.get(j) == succ.get(i))
                                brek = true;
                        }

                        if (!brek) {
                            max = this.donneePartager.get(i);
                            idd = succ.get(i);
                            //System.out.println("    suuuccc  "+idd);
                        }
                    }
                }


                //System.out.println("idddd  "+idd);
                id.add(idd);
            }

        }
        id.remove(0);

        return id;
    }


}


