package Proc.Manager;

import Application.Tache;
import Architecture.Create_NOC;
import Architecture.Paquet;
import Routage_Algorithme.Send;
import Simulation.StaticParametre;

public class Routage {
    Tache tache_fils, tache_pere;

    public Routage(int Tnow) throws InterruptedException {
        for (int j = 0; j < StaticParametre.nbrapps_parallel; j++)// parcour la list des tache qui necissent la communication
        {
            int c;

            if (StaticParametre.application_en_cours[j] != null) {
                for (int i = 0; i < StaticParametre.application_en_cours[j].getListTache().size(); i++) {
                    tache_pere = StaticParametre.application_en_cours[j].getListTache().get(i);


                    for (int k = 0; k < tache_pere.getSucc().size(); k++) {


                        c = tache_pere.getSucc().get(k);
                        tache_fils = StaticParametre.application_en_cours[j].getListTache().get(c);

                        {
                            if (tache_fils.debut_execution == Tnow) {// lancer le routage entre le pere et le fils
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].com_x_source.add(tache_pere.x);
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].com_y_source.add(tache_pere.y);
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].nbr_paquet.add(tache_pere.getDonneePartager(k) / StaticParametre.DEBIT);
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].nbr_paquet_reeu.add(0);
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].tache_source.add(tache_pere);
                                Create_NOC.getNOC()[tache_fils.x][tache_fils.y].tache_dest.add(tache_fils);


                                for (int r = 1; r <= tache_pere.getDonneePartager(k) / StaticParametre.DEBIT; r++) {// creation de paquets !!
                                    Paquet p = new Paquet(tache_pere.x, tache_pere.y, tache_fils.x, tache_fils.y, StaticParametre.List_Paquet.size() + 1, tache_fils);

                                    p.pere_fils = true;// communication pere fils

                                    p.set_source(tache_pere.x, tache_pere.y);

                                    p.tache_dest = tache_fils;
                                    p.tache_source = tache_pere;
                                    StaticParametre.List_Paquet.add(p);
                                    new Send(p);


                                }


                            }

                        }

                    }
                }
            }
        }
    }
}
