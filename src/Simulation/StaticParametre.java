package Simulation;

import Application.Application;
import Application.Tache;
import Architecture.Cluster;
import Architecture.Paquet;
import Architecture.ProcessorElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Cette classe stocke toutes les variables Statiques
 */


public class StaticParametre {

    public static int nbrapps_parallel = 0;
    /**
     * liste des processeur
     */
    public static Map<Integer, ProcessorElement> listProcesseur = new TreeMap<Integer, ProcessorElement>();
    /**
     * definir les listes d'application
     */
    public static LinkedList<Application> listApplication = new LinkedList<Application>();
    ;
    public static LinkedList<Application> applicationAttente = new LinkedList<Application>();
    ;
    public static Application[] application_en_cours/*=new Application[20]*/;
    /**
     * la liste des cluster
     */
    public static Cluster[] listClusters = new Cluster[20];
    /**
     * Nombre de cluster
     */
    public static int Nbr_cluster;
    /**
     * le type de la plateforme (Multi/Mono)
     */
    public static String MONO_MULTI = "MONO";
    /**
     * l'algorithme de placements utilise
     */
    public static String ALGORITHME_PLACEMENT;
    /**
     * l'algorithme de routage utilise
     */
    public static ArrayList<String> List_ALGORITHME_ROUTAGE = new ArrayList<String>();
    public static String ALGORITHME_ROUTAGE;
    /**
     * le type de Strategie De Recherche
     **/
    public static String Strategie_De_Recherche;
    /**
     * nombre de lien total dans le NOC
     */
    public static int LEGNHT_CHANNEL;

    /** nombre de processeur par ligne */
    //public static final int LEGNHT_NOC;
    /**
     * Lien du fichier XML en entree
     **/
    public static String Lien_Fichier_XML;
    /**
     * Lien du fichier de l'architecture en entree
     **/
    public static String Lien_Fichier_architecture;
    /**
     * la taille du cluster
     */
    public static int TAILLE_CLUSTER = 3;
    /**
     * nombre d'applications executees
     **/
    public static int end_application = 0;
    /**
     * temps entre arrive
     **/
    public static int between_arrived = 1000;
    /**
     * List des teches non places
     */
    public static ArrayList<Tache> not_mapped = new ArrayList<Tache>();
    /**
     * nombre d'applications
     */
    public static int NOMBRE_APPLICATION;
    /**
     * le debit d'envoi
     */
    public static int DEBIT;
    /**
     * nombre d'instruction execute par une seul unite de temps
     */
    public static int FREQUENCE_FPGA, FREQUENCE_ASIC, FREQUENCE_DSP, FREQUENCE_GP;
    /**
     * le mode de chaque type de proc
     **/
    public static int Mode_GP, Mode_FPGA, Mode_ASIC, Mode_DSP;
    /**
     * nombre d'unite consomme par une seul instruction
     */
    public static int ENERGIE_GP, ENERGIE_FPGA, ENERGIE_ASIC, ENERGIE_DSP;
    /**
     * la memoire d'un processeur logiciel
     */
    public static int MEMOIRE_GP = 900;
    /**
     * la memoire d'un processeur Materiel
     */
    public static int MEMOIRE_HARD = 600;
    /**
     * Energie consommee par un envoi
     */
    public static int Energie_envoi;
    /**
     * Energie consommee pour l attente
     */
    public static int Energie_attente_envoie;
    /**
     * temps d'envoi d un seul paquet
     */
    public static int Temps_envoie;
    public static int Limit_NOC_x = 0, Limit_NOC_y = 0;
    public static String scenario;
    /**      **/
    public static ArrayList<String> List_Algo_Mapping = new ArrayList<String>();
    public static ArrayList<String> List_Strategie = new ArrayList<String>();
    public static ArrayList<Paquet> List_Paquet = new ArrayList<Paquet>();
    /**
     * matrice qui contien les liens entre les processeurs du NOC
     */
    private static int[][] Matrice_channel = new int[200][200];

    /**
     * Cette methode retourn la list d'application
     *
     * @return:
     */
    // recuperer la liste d'application
    public static LinkedList<Application> getListApplication() {

        return listApplication;


    }

    /**
     * retourn la matrice des liens
     *
     * @return la matrice des liens
     */
    public static int[][] getMatriceChannel() {
        return Matrice_channel;

    }


    public static Cluster[] getListCluster() {
        return listClusters;

    }


    public static void setEnd_Application() {
        end_application++;
    }


    ////////////////////// GP SOFT ////////////

    public static int getFrequence_CPU(int Mode) {

        switch (Mode) {
            case 1: {
                FREQUENCE_GP = 20;
            }
            break;
            case 2: {
                FREQUENCE_GP = 45;
            }
            break;
            case 3: {
                FREQUENCE_GP = 70;
            }
            break;
        }


        return (FREQUENCE_GP);
    }

    ///
////////////
    public static int getEnergy_CPU(int Mode) {

        switch (Mode) {
            case 1: {
                ENERGIE_GP = 20;
            }
            break;
            case 2: {
                ENERGIE_GP = 25;
            }
            break;
            case 3: {
                ENERGIE_GP = 30;
            }
            break;
        }


        return (ENERGIE_GP);

    }

    ////////
    public static int getFrequence_FPGA(int Mode) {

        switch (Mode) {
            case 1: {
                FREQUENCE_FPGA = 40;
            }
            break;
            case 2: {
                FREQUENCE_FPGA = 55;
            }
            break;
            case 3: {
                FREQUENCE_FPGA = 85;
            }
            break;
        }


        return (FREQUENCE_FPGA);

    }

    //////
    public static int getEnergy_FPGA(int Mode) {

        switch (Mode) {
            case 1: {
                ENERGIE_FPGA = 10;
            }
            break;
            case 2: {
                ENERGIE_FPGA = 15;
            }
            break;
            case 3: {
                ENERGIE_FPGA = 20;
            }
            break;
        }


        return (ENERGIE_FPGA);

    }

    ////////
    ////// ASIC ////////////////////////////
    public static int getFrequence_ASIC(int Mode) {

        switch (Mode) {
            case 1: {
                FREQUENCE_ASIC = 55;
            }
            break;
            case 2: {
                FREQUENCE_ASIC = 70;
            }
            break;
            case 3: {
                FREQUENCE_ASIC = 100;
            }
            break;
        }


        return (FREQUENCE_ASIC);

    }

    ///
//////
    public static int getEnergy_ASIC(int Mode) {

        switch (Mode) {
            case 1: {
                ENERGIE_ASIC = 5;
            }
            break;
            case 2: {
                ENERGIE_ASIC = 10;
            }
            break;
            case 3: {
                ENERGIE_ASIC = 15;
            }
            break;
        }


        return (ENERGIE_ASIC);

    }

    /////////////////////	DSP 	////////////////////////////////
    public static int getFrequence_DSP(int Mode) {

        switch (Mode) {
            case 1: {
                FREQUENCE_DSP = 35;
            }
            break;
            case 2: {
                FREQUENCE_DSP = 50;
            }
            break;
            case 3: {
                FREQUENCE_DSP = 75;
            }
            break;
        }


        return (FREQUENCE_DSP);

    }

    //////
    public static int getEnergy_DSP(int Mode) {

        switch (Mode) {
            case 1: {
                ENERGIE_DSP = 15;
            }
            break;
            case 2: {
                ENERGIE_DSP = 20;
            }
            break;
            case 3: {
                ENERGIE_DSP = 25;
            }
            break;
        }


        return (ENERGIE_DSP);

    }
    ////////

}
