package GUI;

import GUI.linkDrawer.drawer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class LoadPlatforme extends JPanel {


    public static ArrayList<linkDrawer> popi = new ArrayList<linkDrawer>();
    public Processor k[][];
    int ligne, collone;
    int x = 50, y = 50, x1 = 50, y1 = 300;
    drawer pop;
    int xloc, yloc;
    Timer videoTimer;


    public LoadPlatforme(int x, int y) {
        // TODO Auto-generated constructor stub
        ligne = x;
        collone = y;
        GridLayout gr = new GridLayout(2 * x - 1, 2 * y - 1, 3, 3);
        this.setLayout(gr);
        k = new Processor[x][y];

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                k[i][j] = new Processor("GP");
                k[i][j].setSize(50, 50);
                k[i][j].setBackground(Color.gray);
            }


    }

    public static int find(int x, int y) {
        int j = 0;
        for (int i = 0; i < popi.size(); i++) {
            if (popi.get(i).x == x && popi.get(i).y == y) {
                j = i;
                break;
            }
        }
        return j;
    }

    public void setProcessor(int x, int y, String type) {
        k[x][y].upl.setText(type);
    }

    public void drawplatform() {

        for (int i = 0; i < ligne * 2 - 1; i++)
            for (int j = 0; j < collone * 2 - 1; j++) {
                if ((i % 2) == 0 && (j % 2) == 0) {//System.out.println("-------"+(int)(j/2)+"----------->"+(int)(i/2));
                    this.add(k[i / 2][j / 2]);
                } else if (!((i % 2) == 1 && (j % 2) == 1)) {
                    if ((i % 2) != 0)
                        popi.add(new linkDrawer(1, i, j));
                    else
                        popi.add(new linkDrawer(2, i, j));
                    this.add(popi.get(popi.size() - 1));
                } else this.add(new JLabel());
            }

    }
		/*public void paint(Graphics g){
		    g.fillOval(xloc, yloc, 15, 15);
		}*/

}