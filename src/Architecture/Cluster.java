package Architecture;

/**
 * Cette classe definit le cluster qui sera utiliser pour placer l'application, chaque application place sa teche initial
 * au milieu
 */

public class Cluster {

    /**
     * identificateur du cluster
     */
    public int id;

    /**
     * les coordonne du centre du cluster
     */
    public int xCentre;
    public int yCentre;
    /**
     * cluster libre ou pas
     */
    public boolean Free = true;
    /**
     * Les limites du cluster
     */
    private int xDebut;
    private int yDebut;
    private int xFin;
    private int yFin;

    public void setCenter(int x_debut, int x_fin, int y_debut, int y_fin) {
        this.xCentre = (x_fin + x_debut) / 2;
        this.yCentre = (y_fin + y_debut) / 2;
    }


    public void setId(int id) {
        this.id = id;

    }

    public void setCoordonneesDebut(int xDebut, int yDebut) {
        this.xDebut = xDebut;
        this.yDebut = yDebut;
    }

    public void setCoordonneesFin(int xFin, int yFin) {
        this.xFin = xFin;
        this.yFin = yFin;
    }


    public int getxDebut() {
        return xDebut;
    }


    public int getyDebut() {
        return yDebut;
    }


    public int getxFin() {
        return xFin;
    }


    public int getyFin() {
        return yFin;
    }


////

    ////
    public synchronized boolean get_Free() {
        return Free;

    }

    public synchronized void set_Free(boolean F) {
        Free = F;

    }

}
