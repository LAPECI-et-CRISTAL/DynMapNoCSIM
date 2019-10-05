package Routage_Algorithme;

import Architecture.Create_NOC;
import Architecture.GenererChannel;
import Architecture.Paquet;
import GUI.LoadPlatforme;
import Simulation.Simulator;
import Simulation.StaticParametre;

import java.util.LinkedList;


public class XY_Algorithme {
    private int x_source;
    private int y_source;
    private int x_destination;
    private int y_destination;
    private int temps_attente;
    private int temps_routage;
    private int donnee;
    private int Energie_routage;
    private int Energie_attente;
    private Paquet p;

    private LinkedList<Integer> cheminx = new LinkedList<Integer>();
    private LinkedList<Integer> cheminy = new LinkedList<Integer>();

    public XY_Algorithme(Paquet p) {
        x_source = p.x_inter;
        y_source = p.y_inter;
        x_destination = p.x_destination;
        y_destination = p.y_destination;
        this.temps_attente = 0;
        this.temps_routage = 0;
        this.Energie_attente = 0;
        this.Energie_routage = 0;
        this.p = p;
        donnee = StaticParametre.DEBIT;

    }

    public void start() {

        int saut_x = Math.abs(this.x_source - this.x_destination);


        if (saut_x > 0) // pas sur la meme ligne
        {
            if ((this.x_source - this.x_destination) < 0) // de haut en bas

            {

                //retourne la charge du lien
                if (verifier_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source) > 0) {
                    set_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    attendre(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    lancer_envoie(this.x_source, this.y_source, this.x_source + 1, this.y_source, 2);


                } else {

                    set_lien(this.x_source, this.y_source, this.x_source + 1, this.y_source);
                    lancer_envoie(this.x_source, this.y_source, this.x_source + 1, this.y_source, 2);

                }

                cheminx.add(this.x_source + 1);
                cheminy.add(this.y_source);

            } else   //de bas vers le haut

            {

                if (verifier_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source) > 0) {
                    set_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    attendre(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    lancer_envoie(this.x_source - 1, this.y_source, this.x_source, this.y_source, 1);
                } else {
                    set_lien(this.x_source - 1, this.y_source, this.x_source, this.y_source);
                    lancer_envoie(this.x_source - 1, this.y_source, this.x_source, this.y_source, 1);
                }

                //this.x_source--;

                cheminx.add(this.x_source - 1);
                cheminy.add(this.y_source);


            }


        }


        ///////////////////////////// deuxieme etape du routage y -> y -> y ///////////////
        else {
            if ((this.y_source - this.y_destination) < 0) // de gauche e droite

            {


                if (verifier_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1) > 0) {
                    set_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    attendre(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    lancer_envoie(this.x_source, this.y_source, this.x_source, this.y_source + 1, 2);
                } else {
                    set_lien(this.x_source, this.y_source, this.x_source, this.y_source + 1);
                    lancer_envoie(this.x_source, this.y_source, this.x_source, this.y_source + 1, 2);

                }

                cheminx.add(this.x_source);
                cheminy.add(this.y_source + 1);
            } else   //de droite e gauche

            {

                if (verifier_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source) > 0) {
                    set_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    attendre(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    lancer_envoie(this.x_source, this.y_source - 1, this.x_source, this.y_source, 1);
                } else {
                    set_lien(this.x_source, this.y_source - 1, this.x_source, this.y_source);
                    lancer_envoie(this.x_source, this.y_source - 1, this.x_source, this.y_source, 1);
                }

                cheminx.add(this.x_source);
                cheminy.add(this.y_source - 1);

            }


        }


    }


    ///////////// cette methode nous retourne le volume de la file d'attente du lien   ////////

    public int verifier_lien(int x, int y, int x1, int y1) {


        int lien = -1;
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7) {
            lien = GenererChannel.getListChannel().get(1).get_size_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());

        }
        return lien;
    }

////////////////////////// cette methode permet d'ajouter le nombre de paquet e envoyer sur la file d'attente du lien  /////////////

    public void set_lien(int x, int y, int x1, int y1) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7)
            GenererChannel.getListChannel().get(1).ajout_file(Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId(), donnee);

    }

///////////////////////////// cette methode permet de rendre le lien libre ///////////


///////////////////////////////

    public void attendre(int x, int y, int x1, int y1) {
        int size_file = verifier_lien(x, y, x1, y1);
        // ajouter un evt , pour indiquer le debut d un routage et donc la fin de l attente !!
        this.temps_attente = (((size_file - donnee) / StaticParametre.DEBIT) * StaticParametre.Temps_envoie);


        Simulator.Add_Event(Simulator.Tnow + temps_attente);


        this.Energie_attente += (StaticParametre.Energie_attente_envoie * ((size_file - donnee) / StaticParametre.DEBIT));


        Simulator.setEnergy(Energie_attente);

    }
//////////////////////////    

    public void lancer_envoie(int x, int y, int x1, int y1, int M) {
        //	Create_NOC.platforme.k[x][y].getWidth()/2
        int xx = x * 2;
        int yy = y * 2;
        int xx1 = x1 * 2;
        int yy1 = y1 * 2;
        if (x == x1) LoadPlatforme.popi.get(LoadPlatforme.find(x * 2, (y + y1))).setVisible(true);
        else if (y == y1) LoadPlatforme.popi.get(LoadPlatforme.find((x + x1), y * 2)).setVisible(true);

        //Thread.sleep(Simulator.speed/10);

        this.temps_routage = (StaticParametre.Temps_envoie);
        int c = temps_routage + temps_attente + Simulator.Tnow;
        GenererChannel.getListChannel().get(1).event(c, Create_NOC.getNOC()[x][y].getId(), Create_NOC.getNOC()[x1][y1].getId());
        Simulator.set_temps_routage(temps_routage + temps_attente);
        p.set_Tdebut(Simulator.Tnow + temps_attente);
        p.set_Tfin(c);
        Simulator.Add_Event(c);


        if (M == 2) {
            p.x_inter = x1;
            p.y_inter = y1;
        } else {

            p.x_inter = x;
            p.y_inter = y;
        }


        this.Energie_routage = (StaticParametre.Energie_envoi * (donnee / StaticParametre.DEBIT));

        Simulator.setEnergy(Energie_routage);
    }

    //////

    public int getTime_routage() {
        return (this.temps_routage + this.temps_attente);

    }

}