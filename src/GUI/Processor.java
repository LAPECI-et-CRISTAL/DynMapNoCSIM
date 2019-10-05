package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Processor extends JPanel implements MouseListener {
    String typeProces;
    JLabel upl;
    String processorTypes[] = {"GPP", "FPG", "DSP", "ASI"};
    Color couleur[] = {Color.GRAY, Color.RED, Color.BLUE, Color.GREEN};
    // ajouter un tableau de couleurs
    int nbrclick = 0;
    public Processor(String type) {
        this.setLayout(new FlowLayout());
        upl = new JLabel(processorTypes[0]);
        this.add(upl);
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(this);
        //this.setSize(40, 40);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        nbrclick = (nbrclick + 1) % couleur.length;
        this.setBackground(couleur[nbrclick]);
        upl.setText(processorTypes[nbrclick]);

        //System.out.pr
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
