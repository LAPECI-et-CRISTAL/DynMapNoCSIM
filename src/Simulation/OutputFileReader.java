package Simulation;

import Architecture.Create_NOC;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class OutputFileReader {
    //static File file=Simulator.file ;

    //static FileWriter fw=Simulator.fw;

    public OutputFileReader() throws IOException {

    }

    public static void generateOutputFile(int Time, int Energie, int temps_re, int energ_re, FileWriter fw) {


        try {
            //Creation de l'objet

            String str = "*****************************************************************\r\n";
            str += "Resultat de la simulation \r\n";
            str += "Algorithme de placement :  " + StaticParametre.ALGORITHME_PLACEMENT + "\r\n";
            str += "Strategie de recherche :  " + StaticParametre.Strategie_De_Recherche + "\r\n";
            str += "Algorithme de routage :  " + StaticParametre.ALGORITHME_ROUTAGE + "\r\n";
            str += "*****************************************************************\r\n";


            str += "\r\n";

            for (int j = 0; j < StaticParametre.listApplication.size(); j++) {
                // Affichage du resultat du placement, temps d'execution ainsi que la consommation d'energie de chaque tache

                for (int i = 0; i < StaticParametre.getListApplication().get(j).getListTache().size(); i++) {
                    int x = StaticParametre.getListApplication().get(j).getListTache().get(i).x;
                    int y = StaticParametre.getListApplication().get(j).getListTache().get(i).y;
                    str += ("x   " + x + "  y  " + y + "  fin execution  " + StaticParametre.getListApplication().get(j).getListTache().get(i).fin_execution + "   Debut execution    " + StaticParametre.getListApplication().get(j).getListTache().get(i).debut_execution + "  id tache  " + i + " id application  " + j + "\r\n");


                }
            }


            for (int r = 0; r < StaticParametre.Limit_NOC_x; r++) {
                for (int j = 0; j < StaticParametre.Limit_NOC_y; j++)
                    str += ("etat du proc " + Create_NOC.getNOC()[r][j].getFree() + "   son type  " + Create_NOC.getNOC()[r][j].getType() + "  x   " + r + "    y   " + j + " File memoire   " + Create_NOC.getNOC()[r][j].getMem() + "\r\n");

            }
			/*for(int p=0;p<StaticParametre.List_Paquet.size();p++)
			{Paquet paquet=StaticParametre.List_Paquet.get(p);
			str+=("x source  "+paquet.x_source  +"  y source "+paquet.y_source+"  x_dest  "+paquet.x_destination+"  y destination  "+paquet.y_destination+"  T debut   "+paquet.T_Debut+ "  T_fin  "+paquet.T_fin+"  id  "+paquet.id+"\r\n");
			}*/


            str += " TIME:	" + Time + "\r\n";
            str += " TIME recherche :	" + temps_re + "\r\n";
            str += " ENERGIE:	" + Energie + "\r\n";
            str += " ENERGIE  recherche :	" + energ_re + "\r\n";


            str += "\r\n\r\n\r\n";
            //On ecrit la chaene

            fw.write(str);
            //On ferme le flux
//			fw.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
