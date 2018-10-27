package Mapping_Dynamique;


import Application.Tache;
import Architecture.ProcessorElement;
import Simulation.StaticParametre;

/**
 * Cette classe lance la methode de placement dynamique cite dans le fichier Input
 */

public class Methode_Placement {

    Tache tache_fils;
    private Tache tache;
    private ProcessorElement procCourant;
    private NN_GBHD near = new NN_GBHD();
    //private NN_MANHATAN_GBHD MANHATAN_GBHD=new NN_MANHATAN_GBHD();
    //private NN_MANHATAN_Circ MANHATAN_Circ=new NN_MANHATAN_Circ();
    //private BN_MANHATAN_Circ BN_MANHATAN_Circ=new BN_MANHATAN_Circ();
//	private NN_MANHATAN_Circ_Haut MANHATAN_Circ_HAUT=new NN_MANHATAN_Circ_Haut();
    //private BN_MANHATAN_GBHD BN_MANHATAN_GBHD=new BN_MANHATAN_GBHD();
    //private BN_MANHATAN_Circ_Haut BN_MANHATAN_Circ_HAUT=new BN_MANHATAN_Circ_Haut();
    private NN_Multi near_Multi = new NN_Multi();
    private BN_GBHD Best_GBHD = new BN_GBHD();
    private BN_GBHD_Multi Best_GBHD_Multi = new BN_GBHD_Multi();
    //private NN_MANHATAN_GBHD_MULTI MANHATAN_GBHD_MULTI=new NN_MANHATAN_GBHD_MULTI();
    //private NN_MANHATAN_Circ_MULTI MANHATAN_Circ_MULTI=new NN_MANHATAN_Circ_MULTI();
    //private NN_MANHATAN_Circ_Haut_MULTI MANHATAN_Circ_HAUT_MULTI=new NN_MANHATAN_Circ_Haut_MULTI();
    //private BN_MANHATAN_Circ_Haut_MULTI BN_MANHATAN_Circ_Haut_MULTI=new BN_MANHATAN_Circ_Haut_MULTI();
    //private BN_MANHATAN_Circ_MULTI BN_MANHATAN_Circ_MULTI=new BN_MANHATAN_Circ_MULTI();
    //private BN_MANHATAN_GBHD_MULTI BN_MANHATAN_GBHD_MULTI=new BN_MANHATAN_GBHD_MULTI();
    private FF FF = new FF();
    private FF_MULTI FF_MULTI = new FF_MULTI();


    public Methode_Placement(Tache tache, ProcessorElement procCourant) {

        this.procCourant = procCourant; // processeur sur lequel la tache pere a ete place

        this.tache = tache; // tache e place

        this.run();

    }

    public void run() {


        ////////////// lancement du placement des taches fils ////////////////
        if (StaticParametre.MONO_MULTI.equals("MONO")) //Methodes de placement MONO
        {

            try {


                if (StaticParametre.ALGORITHME_PLACEMENT.equals("NN")) {
                    if (StaticParametre.Strategie_De_Recherche.equals("GBHD"))
                        near.start_GBHD(tache, procCourant.x, procCourant.y);
					
					
					
				/*
				else 
				{
					if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_Ciruclaire"))					
						MANHATAN_Circ.start_MANHATAN(tache,procCourant.x,procCourant.y);
					else
					{
						if(StaticParametre.Strategie_De_Recherche.equals("MANHATAN_GBHD"))
							MANHATAN_GBHD.start_MANHATAN(tache,procCourant.x,procCourant.y);
						else
						{
							if (StaticParametre.Strategie_De_Recherche.equals("MANHATAN_Circ_HAUT"))
							MANHATAN_Circ_HAUT.start_MANHATAN(tache,procCourant.x,procCourant.y);
						
							else 
							{
								System.out.println("Veuillez saisir correctement le nom de la strategie de recherche" +
										" (GBHD MANHATAN_Ciruclaire MANHATAN_GBHD MANHATAN_Circ_HAUT ");
								System.exit(0);
								
								
							}
						
						}
					}
					}
				}*/
                } else {
                    if (StaticParametre.ALGORITHME_PLACEMENT.equals("BN")) {
                        if (StaticParametre.Strategie_De_Recherche.equals("GBHD"))
                            Best_GBHD.start(tache, procCourant.x, procCourant.y);


                    } else FF.start(tache);


                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        /****************************** MULTI ****************************/
        else        //Methodes de placement Multi
        {
            try {


                if (StaticParametre.ALGORITHME_PLACEMENT.equals("NN")) {
                    if (StaticParametre.Strategie_De_Recherche.equals("GBHD"))
                        near_Multi.start_GBHD(tache, procCourant.x, procCourant.y);


                } else /******* BN  ****/ {
                    if (StaticParametre.ALGORITHME_PLACEMENT.equals("BN")) {
                        if (StaticParametre.Strategie_De_Recherche.equals("GBHD"))
                            Best_GBHD_Multi.start(tache, procCourant.x, procCourant.y);


                    } else
                        FF_MULTI.start(tache);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }


}
	
	
	



