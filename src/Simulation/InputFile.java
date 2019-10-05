package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/****************
 *
 *  Classe qui fait la lecture du fichier des parametre d'execution
 */

public class InputFile {


    public InputFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("PARAMETRE.txt"));

        while (true) {
            String line = in.readLine();
            if (line == null) break;

            StringTokenizer tok = new StringTokenizer(line, " ");

            String word = tok.nextToken();


            if (word.equals("Nombre_Application")) {
                String val = tok.nextToken();
                StaticParametre.NOMBRE_APPLICATION = Integer.parseInt(val);

            }

            if (word.equals("Type_de_plateforme")) {
                String val = tok.nextToken();
                StaticParametre.MONO_MULTI = val;
            }

            if (word.equals("ALGO_PLACEMENT")) {
                while (true) {
                    String val = tok.nextToken();
                    if (val.equals(".")) break;
                    else
                        StaticParametre.List_Algo_Mapping.add(val);
                }
            }


            if (word.equals("Strategie_De_Recherche")) {

                while (true) {
                    String val = tok.nextToken();
                    if (val.equals(".")) break;
                    else
                        StaticParametre.List_Strategie.add(val);
                }
            }

            if (word.equals("ALGO_ROUTAGE")) {
                while (true) {

                    String val = tok.nextToken();
                    if (val.equals(".")) break;
                    else
                        StaticParametre.List_ALGORITHME_ROUTAGE.add(val);
                }
            }


            if (word.equals("DEBIT")) {
                String val = tok.nextToken();
                StaticParametre.DEBIT = Integer.parseInt(val);
            }

            if (word.equals("Temps_Envoi")) {
                String val = tok.nextToken();
                StaticParametre.Temps_envoie = Integer.parseInt(val);

            }

            if (word.equals("Energie_Consommee_Envoi")) {
                String val = tok.nextToken();
                StaticParametre.Energie_envoi = Integer.parseInt(val);
                StaticParametre.Energie_attente_envoie = StaticParametre.Energie_envoi / 10;
            }

            if (word.equals("MODE_CPU")) {
                String val = tok.nextToken();
                StaticParametre.Mode_GP = Integer.parseInt(val);
            }

            if (word.equals("MODE_FPGA")) {
                String val = tok.nextToken();
                StaticParametre.Mode_FPGA = Integer.parseInt(val);
            }


            if (word.equals("MODE_ASIC")) {
                String val = tok.nextToken();
                StaticParametre.Mode_ASIC = Integer.parseInt(val);

            }

            if (word.equals("MODE_DSP")) {
                String val = tok.nextToken();
                StaticParametre.Mode_DSP = Integer.parseInt(val);
            }


        }


    }

}
