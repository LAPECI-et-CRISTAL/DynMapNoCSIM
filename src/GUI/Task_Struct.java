package GUI;

import java.util.ArrayList;

public class Task_Struct {
    int id;
    ArrayList<Integer> Type = new ArrayList<Integer>();

    ArrayList<Integer> Taille = new ArrayList<Integer>();

    ArrayList<Integer> Cycles = new ArrayList<Integer>();

    ArrayList<Integer> Id_Fils = new ArrayList<Integer>();

    ArrayList<Integer> donnee_fils = new ArrayList<Integer>();

    public Task_Struct(int ID, ArrayList<Integer> type, ArrayList<Integer> taille,
                       ArrayList<Integer> cycle) {
        this.id = ID;
        this.Type = type;
        this.Taille = taille;
        this.Cycles = cycle;

    }


}
