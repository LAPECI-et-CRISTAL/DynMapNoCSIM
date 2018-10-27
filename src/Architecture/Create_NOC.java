/**
 * mapping.traitement
 */

package Architecture;


import GUI.LoadPlatforme;
import Simulation.StaticParametre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * Cette Classe permet de creer le NOC
 * Cette Classe necessite de prendre en parametre un fichier qui definit le type de chaque processeur
 *
 *
 *
 */
public class Create_NOC {

    public static LoadPlatforme platforme;
    // matrice 2D qui represente le NOC
    private static ProcessorElement[][] NOC;


    /**
     *
     *
     *
     * @throws IOException
     */
    public static void creationNoc() throws IOException {
        // matrice de ProcessorElement

        BufferedReader in = new BufferedReader(new FileReader(StaticParametre.Lien_Fichier_architecture));
        String line = null;
        StringTokenizer tok;
        String word = null;
        for (int i = 0; i < 2; i++) {
            line = in.readLine();

            tok = new StringTokenizer(line, " ");
            word = tok.nextToken();
            if (word.equals("Nbr_Ligne")) {
                String val = tok.nextToken();
                StaticParametre.Limit_NOC_x = Integer.parseInt(val);

            }

            if (word.equals("Nbr_Colone")) {
                String val = tok.nextToken();
                StaticParametre.Limit_NOC_y = Integer.parseInt(val);

            }
            System.out.println(" limit noc x " + StaticParametre.Limit_NOC_x + " word  " + word + "   ");

        }

        platforme = new LoadPlatforme(StaticParametre.Limit_NOC_x, StaticParametre.Limit_NOC_y);
        NOC = new ProcessorElement[StaticParametre.Limit_NOC_x][StaticParametre.Limit_NOC_y];

        int compteurLigne = 0;
        int id = 0;
        while (true) {

            line = in.readLine();

            if (line == null)
                break;
            //String word;

            tok = new StringTokenizer(line, ",");
            //	word=tok.nextToken();
            int count_word = tok.countTokens();
            for (int i = 0; i < count_word; i++) {
                //StaticParametre.Limit_NOC_x++;
                // cree un Processor Element avec sa position x,y
                ProcessorElement processeur = new ProcessorElement();
                NOC[compteurLigne][i] = new ProcessorElement(compteurLigne, i);

                word = tok.nextToken();

                //System.out.println("woooord   "+word);
                processeur.setType(Integer.parseInt(word));
                processeur.setId(id);
                processeur.x = compteurLigne;
                processeur.y = i;
                String iop = null;
                if (processeur.getType() == 1) iop = "GP";
                if (processeur.getType() == 2) iop = "FPGA";
                if (processeur.getType() == 3) iop = "ASIC";
                if (processeur.getType() == 4) iop = "DSP";
                platforme.setProcessor(compteurLigne, i, iop);
                NOC[compteurLigne][i] = processeur;
                id++;
            }

            compteurLigne++;
        }

        // remplir la list des processeurs
        remplir_map();
        platforme.drawplatform();
    }

    /**
     * Definir une list de processeur qui permet de facilite l'accee aux information de chaque processeur
     *
     *
     */
    public static void remplir_map() {
        for (int i = 0; i < NOC.length; i++) {
            for (int j = 0; j < NOC.length; j++) {
                StaticParametre.listProcesseur.put(NOC[i][j].getId(), getNOC()[i][j]);
            }
        }
    }

    /**
     *
     * Cette methode  permet de retourner la matrice du NOC
     *
     * @return
     */
    public static ProcessorElement[][] getNOC() {
        return NOC;
    }

}
