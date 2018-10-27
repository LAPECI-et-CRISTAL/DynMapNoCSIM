package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

import java.awt.*;

public class NN_GBHD {

    // variable qui determine si la teche a ete place
    public boolean place;
    // temps de l'execution de l'algorithme
    public int temps_recherche;
    // x,y position du processeur sur lequel la teche pere est place ( processeur courant)
    public int x;
    public int y;

    // Energie consommee lors de la recherche
    public int Energie;


    public NN_GBHD() {

        temps_recherche = 0;
        Energie = 0;
    }

/////////////////////////////////////////////////// NN avec la strategie de packing G,B,H,D	//////////////////////////////

    public void start_GBHD(Tache tachePlacer, int x, int y) throws InterruptedException {
        this.x = x;
        this.y = y;
        place = false;

        Create_NOC.platforme.k[x][y].setBackground(Color.GREEN);

        while (place == false) {

            // recherche a  gauche
            recherche_gauche(tachePlacer, this.x, this.y);

            //recherche en bas
            if (place == false)
                recherche_bas(tachePlacer, this.x, this.y);

            //recherche en haut
            if (place == false)
                recherche_haut(tachePlacer, this.x, this.y);

            // recherche a droite
            if (place == false)
                recherche_droite(tachePlacer, this.x, this.y);


// verifier si la teche a ete place ou non

            if (place == false) {
                int i = 0;
                while (place == false && i <= StaticParametre.Limit_NOC_x - 1) {

                    i++;


                    refaire(tachePlacer, getX(), getY() - i); // refaire la recherche e partir du proc gauche


                    if (place == false)

                        refaire(tachePlacer, getX() + i, getY()); //refaire la recherche e partir du proc bas


                    if (place == false)
                        refaire(tachePlacer, getX() - i, getY()); //refaire la recherche e partir du proc haut


                    if (place == false)
                        refaire(tachePlacer, getX(), getY() + i); //refaire la recherche e partir du proc droit

                    if (place == false && i == StaticParametre.Limit_NOC_x - 1) {
                        int c = 0;
                        place = true;

                        Create_NOC.platforme.k[x][y].setBackground(Color.RED);
                        if (StaticParametre.not_mapped.isEmpty())
                            StaticParametre.not_mapped.add(tachePlacer);
                        else {
                            for (int k = 0; k < StaticParametre.not_mapped.size(); k++) {
                                if (StaticParametre.not_mapped.get(k).equals(tachePlacer)) {
                                    c = 1;
                                    break;
                                }
                            }
                            if (c != 1) StaticParametre.not_mapped.add(tachePlacer);

                        }
                    }

                }
            }


        }


    }

    ///////////////////////
    void changeColor(int x, int y) {
        Color mp = Create_NOC.platforme.k[x][y].getBackground();
        Create_NOC.platforme.k[x][y].setBackground(Color.BLUE);
        try {
            Thread.sleep(Simulator.speed);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        Create_NOC.platforme.k[x][y].setBackground(mp);
    }


    //////////////////////////////////////////
    public void refaire(Tache tachePlacer, int x, int y) throws InterruptedException {

        // recherche a gauche
        recherche_gauche(tachePlacer, x, y);
        //recherche en bas
        if (place == false)
            recherche_bas(tachePlacer, x, y);

        //recherche en haut
        if (place == false)
            recherche_haut(tachePlacer, x, y);

        // recher a droite
        if (place == false)
            recherche_droite(tachePlacer, x, y);


    }


///////////////////////////////////////


    public void lancer(Tache t, int x, int y) throws InterruptedException {


        t.x = x;
        t.y = y;

        t.mapped = true;

        t.debut_execution = Simulator.Tnow + this.temps_recherche;
        t.debut_routage = Simulator.Tnow + this.temps_recherche;


        Simulator.set_temps_recherche(this.temps_recherche);

        Create_NOC.getNOC()[x][y].set_state(false);

        Create_NOC.platforme.k[x][y].setBackground(Color.RED);
//System.out.println("End Green");
        Create_NOC.platforme.k[this.x][this.y].setBackground(Color.RED);

        Simulator.setEnergy(this.Energie);//ajouter l energie consomme lors de la recherche e l energie total consomme par l application


    }


////////////////////////////////


///////////////////////

    public void recherche_gauche(Tache tachePlacer, int x, int y) throws InterruptedException {

        if (y - 1 >= 0 && y <= StaticParametre.Limit_NOC_y - 1 && x >= 0 && x <= StaticParametre.Limit_NOC_x - 1) {
            temps_recherche = temps_recherche + 10;

            this.Energie += 1;

            changeColor(x, y - 1);

            for (int i = 0; i < tachePlacer.getType().size(); i++) //parcourir la liste des types de la tache
            {
                if (Create_NOC.getNOC()[x][y - 1].getType() == tachePlacer.getType().get(i)) {
                    if (Create_NOC.getNOC()[x][y - 1].getFree() == true) {

                        lancer(tachePlacer, x, y - 1);
                        place = true;

                    }
                }
            }

        }
    }


/////////////////////////////////////	

    public void recherche_bas(Tache tachePlacer, int x, int y) throws InterruptedException {


        if (x + 1 <= StaticParametre.Limit_NOC_x - 1 && x >= 0 && y >= 0 && y <= StaticParametre.Limit_NOC_y - 1) {
            temps_recherche = temps_recherche + 10;
            this.Energie += 1;

            changeColor(x + 1, y);
            for (int i = 0; i < tachePlacer.getType().size(); i++) //parcourir la liste des types de la tache
            {
                if (Create_NOC.getNOC()[x + 1][y].getType() == tachePlacer.getType().get(i)) {

                    if (Create_NOC.getNOC()[x + 1][y].getFree() == true) {

                        place = true;
                        lancer(tachePlacer, x + 1, y);


                    }


                }
            }
        }

    }

//////////////////////////////////////////


    public void recherche_haut(Tache tachePlacer, int x, int y) throws InterruptedException {


        if (x - 1 >= 0 && x <= StaticParametre.Limit_NOC_x - 1 && y >= 0 && y <= StaticParametre.Limit_NOC_y - 1) {
            temps_recherche = temps_recherche + 10;
            this.Energie += 1;
            changeColor(x - 1, y);
            for (int i = 0; i < tachePlacer.getType().size(); i++) //parcourir la liste des types de la tache
            {
                if (Create_NOC.getNOC()[x - 1][y].getType() == tachePlacer.getType().get(i)) {


                    if (Create_NOC.getNOC()[x - 1][y].getFree() == true) {

                        lancer(tachePlacer, x - 1, y);
                        place = true;

                    }
                }


            }
        }


    }


//////////////////////////////////


    public void recherche_droite(Tache tachePlacer, int x, int y) throws InterruptedException {


        if (y + 1 <= StaticParametre.Limit_NOC_y - 1 && y >= 0 && x >= 0 && x <= StaticParametre.Limit_NOC_x - 1) {
            temps_recherche = temps_recherche + 10;
            this.Energie += 1;

            changeColor(x, y + 1);

            for (int i = 0; i < tachePlacer.getType().size(); i++) //parcourir la liste des types de la tache
            {
                if (Create_NOC.getNOC()[x][y + 1].getType() == tachePlacer.getType().get(i)) {
                    if (Create_NOC.getNOC()[x][y + 1].getFree() == true) {

                        lancer(tachePlacer, x, y + 1);
                        place = true;
                    }
                }
            }
        }
    }


    public int getY() {
        return this.y;
    }


    public int getX() {
        return this.x;
    }


    public int Energie() {
        return (this.Energie);
    }

}
