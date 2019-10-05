package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class linkDrawer extends JComponent {
    public static drawer pop;
    int sens;
    int xloc, yloc;
    Timer videoTimer;
    int x, y;

    public linkDrawer(int sensy, int x, int y) {
        this.setSize(50, 50);
        //this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.x = x;
        this.y = y;
        this.sens = sensy;
        pop = new drawer();
        this.setLayout(new GridLayout(1, 1));
        pop.setSize(50, 50);

        xloc = 23;
        if (sens == 1)
            yloc = 23;
        else yloc = 10;
        pop.setVisible(true);
        this.add(pop);
        this.setVisible(false);
        videoTimer = new Timer(100 / 20, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (sens == 0) yloc = yloc - 10;
                else if (sens == 1) yloc += 10;
                else if (sens == 2) xloc += 10;
                else xloc -= 10;
                xloc = xloc % 50;
                yloc = yloc % 50;
                pop.repaint();

            }
        });
        videoTimer.start();

        // TODO Auto-generated constructor stub
    }

    public class drawer extends JComponent {

        public void paint(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillOval(xloc, yloc, 6, 6);
            // g.setColor(Color.YELLOW);
            // g.fillRect(xloc-10,yloc-10 , 20, 50);
        }
    }

}
