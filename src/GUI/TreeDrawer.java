package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TreeDrawer extends JPanel implements MouseListener {
    public static int selectedcomponent = -1;
    ArrayList<triplet> toDraw;
    ArrayList<doublet> todranwline;
    Tree po;

    public TreeDrawer(Tree t) {

        this.addMouseListener(this);
        toDraw = t.p;
        this.todranwline = t.pop;
        po = t;
	/*	for (int i=0;i<t.pop.size();i++)
		{
			System.out.println(t.pop.get(i).t[0]+"--"+t.pop.get(i).t[1]);
		}
	*/
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < toDraw.size(); i++) {
            g.setColor(Color.gray);
            if (i == selectedcomponent) g.setColor(Color.RED);
            g.fillOval(toDraw.get(i).t[1] - 20, toDraw.get(i).t[2] - 20, 40, 40);
            g.setColor(Color.BLACK);
            g.drawString(po.drst.get(i), toDraw.get(i).t[1], toDraw.get(i).t[2]);
        }
        for (int i = 0; i < todranwline.size(); i++) {
            g.drawLine(toDraw.get(todranwline.get(i).t[0]).t[1], toDraw.get(todranwline.get(i).t[0]).t[2],
                    toDraw.get(todranwline.get(i).t[1]).t[1], toDraw.get(todranwline.get(i).t[1]).t[2]);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("lol");

        for (int i = 0; i < toDraw.size(); i++) {
            if (e.getPoint().x > (toDraw.get(i).t[1] - 15) && e.getPoint().x < (toDraw.get(i).t[1] + 15)) {
                if (e.getPoint().y > (toDraw.get(i).t[2] - 15) && e.getPoint().y < (toDraw.get(i).t[2] + 15)) {
                    //System.out.println("\n"+i+"----");
                    selectedcomponent = i;
                    this.repaint();
                }

            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}