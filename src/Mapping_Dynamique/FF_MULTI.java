package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class FF_MULTI {

    // variable qui determine si la teche a ete place
    public boolean place;
    // temps de l'execution de l'algorithme
    public int temps_recherche;
    // x,y position du processeur sur lequel la teche pere est place ( processeur courant)
    public int x;
    public int y, Saut;

    // Energie consommee lors de la recherche
    public int Energie;


    public FF_MULTI() {

        temps_recherche = 0;
        Energie = 0;

    }


///////////////////////////////////////


    public void lancer(Tache t, int x, int y) {


        t.x = x;
        t.y = y;

        t.mapped = true;

        t.debut_execution = Simulator.Tnow + this.temps_recherche;
        t.debut_routage = Simulator.Tnow + this.temps_recherche;


        System.out.println(temps_recherche + "  apps  " + t.getId());

        Create_NOC.getNOC()[x][y].setMem(t.getTailleTache(Create_NOC.getNOC()[x][y].getType()), 1);
        Create_NOC.getNOC()[x][y].File.add(t);

        Simulator.set_temps_recherche(temps_recherche);
        Simulator.setEnergy(Energie);


    }


////////////////////////////////


    public void start(Tache tachePlacer) throws InterruptedException {
        place = false;
        int Id_Noeud = 0;
        while (place == false && Id_Noeud < StaticParametre.listProcesseur.size()) {
//recherche d'un proc Libre. 
            recherche_proc_libre(tachePlacer, Id_Noeud);


            if (place == false && Id_Noeud == StaticParametre.listProcesseur.size() - 1) {
                int c = 0;
                place = true;

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


            Id_Noeud++;
        }


    }


///////////////////////

    public void recherche_proc_libre(Tache tachePlacer, int Id_proc) {


        int x = StaticParametre.listProcesseur.get(Id_proc).x;
        int y = StaticParametre.listProcesseur.get(Id_proc).y;


        if (y >= 0 && y <= StaticParametre.Limit_NOC_y - 1 && x >= 0 && x <= StaticParametre.Limit_NOC_x - 1) {


            temps_recherche = temps_recherche + 100;
            this.Energie += 10;
            for (int i = 0; i < tachePlacer.getType().size(); i++) //parcourir la liste des types de la tache
            {
                if (Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i)) {
                    if (Create_NOC.getNOC()[x][y].getMem() >= tachePlacer.getTailleTache(tachePlacer.getType().get(i))) {

                        lancer(tachePlacer, x, y);
                        place = true;

                    }
                }
            }

        }
    }


/////////////////////////////////////	


    public int Energie() {
        return (this.Energie);
    }

}
