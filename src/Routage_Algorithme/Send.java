package Routage_Algorithme;

import Architecture.Paquet;
import Simulation.Simulator;
import Simulation.StaticParametre;


/**
 * Cette classe represente l'entite qui charge de d'effectue le routage entre les teches
 */


public class Send {

    private int x_source;
    private int y_source;
    private int x_destination;
    private int y_destination;
    private MORA_Algorithme mora;
    private Paquet p;

    public Send(Paquet p) throws InterruptedException {


        this.x_destination = p.x_destination;
        this.y_destination = p.y_destination;
        this.x_source = p.x_inter;
        this.y_source = p.y_inter;
        this.p = p;


        if (x_source == x_destination && y_source == y_destination)   // dans le cas du multi pas besoin de lancer le routage

        {
            p.set_Tdebut(Simulator.Tnow);
            p.set_Tfin(Simulator.Tnow + 2);
            Simulator.Add_Event(Simulator.Tnow + 2);

        } else {
            /////////////////
            ///	MONO
            this.run();
            /////////////////
        }
    }

    /*********************************************/ // Send packet
    public void run() throws InterruptedException {

        if (StaticParametre.ALGORITHME_ROUTAGE.equals("XY")) {
            XY_Algorithme rout = new XY_Algorithme(p);
            rout.start();

        } else {
            if (StaticParametre.ALGORITHME_ROUTAGE.equals("MORA")) {
                mora = new MORA_Algorithme(p);
                mora.start();


            } else {
                System.out.println("Veuillez saisir correctement le nom d'un des algorithmes de routage XY ou MORA ");
                System.exit(0);

            }
        }

    }


}

