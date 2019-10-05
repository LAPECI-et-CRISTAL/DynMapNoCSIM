package Architecture;

import Application.Tache;


public class Paquet {
    public int x_source, y_source, x_destination, y_destination, T_fin, x_inter, y_inter;
    public boolean pere_fils, fils_pere;
    public int Nbr_Paquets = 0;
    public int T_Debut, id;
    public Tache tache_dest, tache_source;

    public Paquet(int x, int y, int x1, int y1, int id, Tache t_source) {
        x_inter = x;
        y_inter = y;
        x_destination = x1;
        y_destination = y1;
        this.id = id;
        tache_dest = t_source;


    }

    public void set_Tfin(int Time) {
        this.T_fin = Time;
    }

    public void set_Tdebut(int Time) {
        this.T_Debut = Time;
    }

    public void set_source(int x, int y) {
        x_source = x;
        y_source = y;
    }

    public void set_Nbr_Paquets(int n) {
        Nbr_Paquets = n;
    }

}
