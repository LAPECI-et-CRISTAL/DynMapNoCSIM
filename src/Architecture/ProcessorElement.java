package Architecture;


import Application.Tache;
import Simulation.Simulator;
import Simulation.StaticParametre;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Classe qui determine le type et caracteristique de l'element de calcul dans la plateforme
 *
 * @author Nabil
 */


public class ProcessorElement {


    /**
     * les coordonnees x (ligne) du processeur
     */
    public int x;
    /**
     * les coordonnees y (colonne) du processeur
     */
    public int y;
    /**
     * La liste des taches qui sont entrain de s'executer sur le proc
     */
    public LinkedList<Tache> File = new LinkedList<Tache>();
    public ArrayList<Integer> com_y_source = new ArrayList<Integer>();
    public ArrayList<Integer> com_x_source = new ArrayList<Integer>();
    public ArrayList<Integer> nbr_paquet = new ArrayList<Integer>();
    public ArrayList<Tache> tache_source = new ArrayList<Tache>();
    public ArrayList<Tache> tache_dest = new ArrayList<Tache>();
    public ArrayList<Integer> nbr_paquet_reeu = new ArrayList<Integer>();
    public int fin_routage;
    public ArrayList<Paquet> List_Paquet = new ArrayList<Paquet>();
    /**
     * le type de processeur
     */
    protected int type;
    /**
     * id du proc
     */
    protected int id;
    /**
     * etat du proc
     */
    protected boolean etat = true;
    /**
     * memoire du processeur
     */
    protected int memoire = 2100;
    /**
     * le cluster corrspondant(uniquement pour le spiral)
     */
    protected int idCluster;
    int fin_exec = 0;
    /**
     * Frequence d'execution
     */
    private int Frequence;
    /**
     * Energie consommee lors de l'execution d'une instruction
     */
    private int Energie;

    public ProcessorElement() {


    }

    /**
     * @param x position par rapport a ligne
     * @param y position par rapport a colonne
     */
    public ProcessorElement(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * @return etat du processeur
     */
    public boolean getFree() {
        return this.etat;
    }

    /**
     * definir l'etat du processeur
     */
    public void set_state(boolean etat) {
        this.etat = etat;
    }

    /**
     * @return le type du processeur
     */
    public int getType() {
        return type;
    }

    /**
     * definir l'etat du processeur
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Modifier la taille de la memoire
     *
     * @param mem la taille de la memoire
     */
    public void setMem(int taille_tache, int n) {
        if (n == 1)
            this.memoire -= taille_tache; // lors du placement de la tache

        else this.memoire += taille_tache;// lors de la fin de la tache, liberer l'espace memoire
    }

    /**
     * @return la taille de la memoire
     */
    public int getMem() {
        return memoire;
    }

    /**
     * @return identificateur du processeur
     */
    public int getId() {
        return id;
    }

    /**
     * @param id identificateur du processeur
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idCluster
     */
    public int getIdCluster() {
        return idCluster;
    }

    /**
     * @param idCluster the idCluster to set
     */
    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }


    public void ajoutTache(Tache tache) throws InterruptedException {

        switch (this.type) {    //determiner la frequence et l energie selon le type
            case 0: {
                Frequence = StaticParametre.getFrequence_CPU(StaticParametre.Mode_GP);
                Energie = StaticParametre.getEnergy_CPU(StaticParametre.Mode_GP);

            }
            break;

            case 1: {
                Frequence = StaticParametre.getFrequence_CPU(StaticParametre.Mode_GP);
                Energie = StaticParametre.getEnergy_CPU(StaticParametre.Mode_GP);

            }
            break;

            case 2: {
                Frequence = StaticParametre.getFrequence_FPGA(StaticParametre.Mode_FPGA);
                Energie = StaticParametre.getEnergy_FPGA(StaticParametre.Mode_FPGA);
            }
            break;

            case 3: {
                Frequence = StaticParametre.getFrequence_ASIC(StaticParametre.Mode_ASIC);
                Energie = StaticParametre.getEnergy_ASIC(StaticParametre.Mode_ASIC);

            }
            break;

            case 4: {
                Frequence = StaticParametre.getFrequence_DSP(StaticParametre.Mode_DSP);
                Energie = StaticParametre.getEnergy_DSP(StaticParametre.Mode_DSP);
            }
            break;
        }


        Strategy_Switching(tache); // lancement de l'execution


        //	StaticParametre.listApplication.get(tache.getIdApplication()).setEnergie(this.Energie);

        Simulator.setEnergy(Energie);


    }


    private void Strategy_Switching(Tache tache) {
        // FIFO
		
	/*	int fin_exec=Simulator.Tnow;

				if(!tache.getSucc().isEmpty())
				{
					
					tache.fin_execution=((tache.getTailleTache(type)*Frequence)/2)+fin_exec;
					Energie=(tache.getTailleTache(type)*Energie)/2;
				}
			
				else
			
				{
					tache.fin_execution=(tache.getTailleTache(type)*Frequence)+fin_exec;
					Energie=(tache.getTailleTache(type)*Energie);
				}
				fin_exec=tache.fin_execution;
		*/


        if (fin_exec == 0 && Simulator.Tnow != 0 || StaticParametre.MONO_MULTI.equals("MONO") || Simulator.Tnow >= fin_exec)
            fin_exec = Simulator.Tnow;
		
		/*		
		if(!tache.getSucc().isEmpty())
		{
			
			tache.fin_execution=((tache.getTailleTache(type)*Frequence)/2)+fin_exec;
			Energie=(tache.getTailleTache(type)*Energie)/2;
		}
	
		else
	
		{
			tache.fin_execution=(tache.getTailleTache(type)*Frequence)+fin_exec;
			Energie=(tache.getTailleTache(type)*Energie);
		}*/
        if (!tache.getSucc().isEmpty()) {

            tache.fin_execution = ((tache.getCyclesTache(type) / Frequence) / 2) + fin_exec;
            Energie = (tache.getTailleTache(type) * Energie) / 2;
        } else {
            //System.exit(0);
            if (tache.end == false) {

                tache.fin_execution = (tache.getCyclesTache(type) / Frequence) + fin_exec;
                Energie = (tache.getTailleTache(type) * Energie);
            }
        }


        System.out.print(" debut    " + fin_exec);
        fin_exec = tache.fin_execution;

        System.out.println("fiiiin  " + fin_exec + "   tache  " + tache.getId() + "   apps   " + tache.getIdApplication());
    }

    ///////////////////////
    public boolean Nbr_Paquet_reeu(int x, int y, Tache t_source, Tache t_dest) {
        int n = 0;
        for (int i = 0; i < this.com_x_source.size(); i++) {


            if (com_x_source.get(i) == x && com_y_source.get(i) == y && nbr_paquet_reeu.get(i).equals(nbr_paquet.get(i)) && tache_source.get(i).equals(t_source) && tache_dest.get(i).equals(t_dest)) {
                n = 1;
                break;
            }

        }

        if (n == 0)
            return false;
        else
            return true;
        //return n;
    }


    public void set_Nbr_Paquet_reeu(int x, int y, Tache t_source, Tache t_dest) {
        for (int i = 0; i < this.com_x_source.size(); i++) {

            if (com_x_source.get(i) == x && com_y_source.get(i) == y && tache_source.get(i).equals(t_source) && tache_dest.get(i).equals(t_dest)) {
                this.nbr_paquet_reeu.set(i, (nbr_paquet_reeu.get(i) + 1));


            }
        }

    }


}
	

