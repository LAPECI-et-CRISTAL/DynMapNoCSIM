package Proc.Manager;

import Application.Tache;
import Architecture.Create_NOC;
import Architecture.ProcessorElement;
import Mapping_Dynamique.Methode_Placement;
import Simulation.Simulator;
import Simulation.StaticParametre;

import java.awt.*;
import java.util.ArrayList;

//import Architecture.ProcessorGeneral;

/**
 * Cette classe gere le placement set evenement statique des taches initiales et le placement dynamique des autres taches
 *
 * @author Nabil
 */
public class Placement {
    Tache tache_fils;

    public Placement(int Tnow) throws InterruptedException {
        if (StaticParametre.NOMBRE_APPLICATION >= StaticParametre.Nbr_cluster)
            StaticParametre.nbrapps_parallel = StaticParametre.Nbr_cluster;

        else
            StaticParametre.nbrapps_parallel = StaticParametre.NOMBRE_APPLICATION;
		 

		 
		 
	  /*   if (Tnow==0)
	    	 //placer les taches initiales
	     	
	     {
	    	
	    	 for (int i=0;i<StaticParametre.nbrapps_parallel;i++)// placement des taches initiales,selon le nombre de cluster ///
	    	 	{
	    		StaticParametre.application_en_cours[i]=StaticParametre.applicationAttente.getFirst();
	    		
	    		//ajouter cette application e la liste des apps en cours d'execution
	    		 PlacementStatic(i); 
				
	    	 	}   	 
	    	
	   
	   		
	    
	    	}*/

        for (int i = 0; i < StaticParametre.nbrapps_parallel; i++)// placement des taches initiales,selon le nombre de cluster ///
        {

            if (!StaticParametre.applicationAttente.isEmpty()) {
                if (StaticParametre.applicationAttente.getFirst().Time_arrived == Tnow) {
                    int k = 0;
                    for (k = 0; k < StaticParametre.application_en_cours.length; k++)
                        if (StaticParametre.application_en_cours[k] == null)
                            break;


                    if (k < StaticParametre.nbrapps_parallel) {

                        StaticParametre.application_en_cours[k] = StaticParametre.applicationAttente.getFirst();

                        //ajouter cette application e la liste des apps en cours d'execution
                        PlacementStatic(k);
                    }
                }
            }
        }


        // Placement des taches non initiales qui demandent a etre place, en parcourant la list des taches par application en cours d'execution

        for (int j = 0; j < StaticParametre.nbrapps_parallel; j++) {

            int c;

            if (StaticParametre.application_en_cours[j] != null) {
                for (int i = 0; i < StaticParametre.application_en_cours[j].getListTache().size(); i++) {


                    Tache tache = new Tache();
                    tache = StaticParametre.application_en_cours[j].getListTache().get(i);
                    if (Tnow == (tache.fin_execution) && tache.end == false)    //lancement du placement des fils de la tache
                    {
                        //////*************************************////////

                        //	tache.get_Max_Fils();
                        if (!tache.getSucc().isEmpty())// parcours de la list des fils, afin de lancer leur placement
                        {
                            System.out.println("placement des fils  de la tache  " + i + " de l application  " + j);


                            ArrayList<Integer> List_fils = tache.get_Max_Fils();
	    			 	    			 		
	    			 	    			 		
	    			 	    			 			/*for(int k=0;k<tache.getSucc().size();k++){
	    			 	    			 			
	    			 	    			 				//tache.get_Max_Fils();
	    			 	    			 				c=tache.getSucc().get(k);*/
                            for (int k = 0; k < List_fils.size(); k++) {
                                c = List_fils.get(k);

                                tache_fils = StaticParametre.application_en_cours[j].getListTache().get(c);
                                tache_fils.setIdPere(i);


                                // lancement du placement du fils avec comme parametres: la tache fils e placer et le processeur sur le quel la tache pere est place
                                new Methode_Placement(tache_fils, Create_NOC.getNOC()[tache.x][tache.y]);


                                if (tache_fils.debut_execution != -1)  // Fils place
                                {
                                    Simulator.Add_Event(tache_fils.debut_routage); // ajout de l'evenement debut de routage

                                    tache_fils.x2 = tache_fils.x;
                                    tache_fils.y2 = tache_fils.y;
                                    tache.x1.set(k, tache.x);
                                    tache.y1.set(k, tache.y);

                                }


                            }

                        }


                    }


                }
            }
        }


    }


////////////	 methode qui permet de placer statiquement une application au milieu d un cluster ////

    public static void PlacementStatic(int id_cluster) throws InterruptedException {

        StaticParametre.listClusters[id_cluster].Free = false;// rendre le cluster  occupe

        StaticParametre.applicationAttente.getFirst().setIdCluster(id_cluster);//definir le cluster de l'appplication


        StaticParametre.application_en_cours[id_cluster] = StaticParametre.applicationAttente.getFirst();

        Tache tacheInit = new Tache();

        tacheInit = StaticParametre.applicationAttente.getFirst().getListTache().get(0);

        tacheInit.x = StaticParametre.listClusters[id_cluster].xCentre;// tache.x= x du proc du centre de cluster

        tacheInit.y = StaticParametre.listClusters[id_cluster].yCentre;// tache.y=y du proc du centre de clust

        Create_NOC.platforme.k[tacheInit.x][tacheInit.y].setBackground(Color.cyan);
        Thread.sleep(Simulator.speed);
        Create_NOC.platforme.k[tacheInit.x][tacheInit.y].setBackground(Color.red);
        ProcessorElement processeurInit = Create_NOC.getNOC()[tacheInit.x][tacheInit.y];
        // temps debut execution de la tache initiale=TNOW
        tacheInit.debut_execution = Simulator.Tnow;

        if (StaticParametre.MONO_MULTI.equals("MONO"))
            processeurInit.set_state(false);// MONO, rendre le processeur occupe

        else
            processeurInit.setMem(tacheInit.getTailleTache(processeurInit.getType()), 1);//MULTI; decrementer le memoire du proc

        if (processeurInit.getType() != 1 && processeurInit.getType() != 0) {
            System.out.println("Milieu du cluster n'est pas une ressource logicielle");
            System.exit(0);
        }
        processeurInit.ajoutTache(tacheInit);// ajouter la tache initiale sur le proc adequat


        //ajouter le temps de fin d'execution de la tache e la liste des evenements

        Simulator.Add_Event(tacheInit.fin_execution);


        for (int k = 0; k <= tacheInit.getSucc().size(); k++) {

            tacheInit.x1.set(k, tacheInit.x);
            tacheInit.y1.set(k, tacheInit.y);
        }


        StaticParametre.applicationAttente.removeFirst();

    }


////////////////	methode qui est appelee lorsque une application a fini de s'executer //////////

    public static void launch_new_application(int idcluster) throws InterruptedException {

        if (!StaticParametre.applicationAttente.isEmpty()) {
            for (int i = 0; i < StaticParametre.Nbr_cluster; i++) {

                if (StaticParametre.application_en_cours[i].getIdCluster() == idcluster) {

                    PlacementStatic(i);

                }
            }
        }


    }

}
