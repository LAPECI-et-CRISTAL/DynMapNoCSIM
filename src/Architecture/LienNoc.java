package Architecture;

import GUI.LoadPlatforme;
import Simulation.StaticParametre;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * cette classe represente un lien entre  2 processeurs, il est caracteriser par
 * <ul>
 * <li> identificateur du processeur source </li>
 * <li> identificateur du processeur voisin(distant)</li>
 * <li> la charge du lien</li>
 * <li> identificateur du lien </li>
 * </ul>
 */


public class LienNoc {


    /**
     * List des temps de decrementation de la charge du lien
     */
    public ArrayList<Integer> event = new ArrayList<Integer>();
    /**
     * identificateur du processeur source
     *
     * @uml.property name="idSource"
     */
    private int idSource;
    /**
     * identificateur du processeur destination
     *
     * @uml.property name="idDestination"
     */
    private int idDestination;
    /**
     * identificateur du lien
     */
    private int idLien;

    /**
     * File d'attente du lien
     */
    private LinkedList<Integer> File;


    // Constructeur de la classe
    public LienNoc(int idSource, int idDestination, int idLien) {
        this.idSource = idSource;
        this.idDestination = idDestination;
        this.idLien = idLien;
        this.File = new LinkedList<Integer>();
    }

    /**
     * @return idSoource : retourne identificateur du processeur source
     */
    public int getSource() {
        return idSource;
    }

    /**
     * @param idSource : identificateur du processeur source
     */

    public void setSource(int idSource) {
        this.idSource = idSource;
    }

    /**
     * Cette methode retourne  identificateur du processeur destinataire
     */
    public int getVoisin() {
        return idDestination;
    }

    /**
     * Cette methode definit le voisin
     *
     * @param idDestination: identificateur du processeur voisin
     */
    public void setVoisin(int idDestination) {
        this.idDestination = idDestination;
    }

    /**
     * @return the idLien
     * @uml.property name="idLien"
     */
    public int getIdLien() {
        return idLien;
    }

    /**
     * @param idLien the idLien to set
     * @uml.property name="idLien"
     */
    public void setIdLien(int idLien) {
        this.idLien = idLien;
    }

    /**
     * Retourne la charge du lien actuelle
     *
     * @param source
     * @param destination
     * @return
     */

    public int get_size_file(int source, int destination) {
        int somme = 0;
        for (int i = 1; i <= StaticParametre.LEGNHT_CHANNEL; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {
                if (!GenererChannel.getListChannel().get(i).File.isEmpty()) {
                    for (int j = 0; j < GenererChannel.getListChannel().get(i).File.size(); j++) {
                        somme = somme + GenererChannel.getListChannel().get(i).File.get(j);
                    }

                }
            }


        }
        return somme;
    }


    /**
     * Ajoute le nombre de paquet qui doivent transiter par ce lien
     *
     * @param source
     * @param destination
     * @param paquet
     */
    public void ajout_file(int source, int destination, int paquet) {

        for (int i = 1; i <= StaticParametre.LEGNHT_CHANNEL; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {
                GenererChannel.getListChannel().get(i).File.add(paquet);
            }

        }


    }
    ///////////////////////////////////////////////


    /**
     *
     *
     */
    public void set_file() { /// diminuer la valeur de nombre de paquets qui reste a envoyer selon le debit ////


        if (!File.isEmpty()) {


            if (!File.isEmpty()) {
                this.File.set(0, this.File.getFirst() - StaticParametre.DEBIT);
            }


            if (this.File.getFirst() == 0) {
                this.File.remove(0);

            }


            if (File.isEmpty()) {
                int x = StaticParametre.listProcesseur.get(idSource).x;
                int x1 = StaticParametre.listProcesseur.get(idDestination).x;
                int y = StaticParametre.listProcesseur.get(idSource).y;
                int y1 = StaticParametre.listProcesseur.get(idDestination).y;
                if (x == x1) LoadPlatforme.popi.get(LoadPlatforme.find(x * 2, (y + y1))).setVisible(false);
                else if (y == y1) LoadPlatforme.popi.get(LoadPlatforme.find((x + x1), y * 2)).setVisible(false);


            }

        }

    }


///////////////

    /**
     * @param time_event
     * @param source
     * @param destination
     */
    public void event(int time_event, int source, int destination) {
        for (int i = 1; i <= StaticParametre.LEGNHT_CHANNEL; i++) {
            if (GenererChannel.getListChannel().get(i).getSource() == source && GenererChannel.getListChannel().get(i).getVoisin() == destination) {

                GenererChannel.getListChannel().get(i).event.add(time_event);

            }

        }
    }

}