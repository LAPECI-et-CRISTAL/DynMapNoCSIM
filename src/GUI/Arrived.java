package GUI;

import Simulation.Simulator;
import Simulation.StaticParametre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arrived extends JFrame implements ActionListener {

    JTextField time = new JTextField("	");
    JLabel T = new JLabel("Temps entre arrivee    ");
    JLabel T2 = new JLabel("								                 						                                                                                                                                                              							");

    public Arrived() {
        this.setSize(400, 150);
        JPanel Pan3 = new JPanel();

        Pan3.setLayout(new FlowLayout());
        Pan3.add(T2);
        Pan3.add(T);
        Pan3.add(time);
        Pan3.add(T2);
        JButton bt = new JButton(" Suivant  ");
        bt.addActionListener(this);
        Pan3.add(bt);
        this.add(Pan3);
        this.setLocation(300, 300);
        this.setVisible(true);

    }


    public static void main(String[] args) {

        Arrived ar = new Arrived();
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        this.dispose();
        MAIN_Frame.pe.setSelectedIndex(3);
        StaticParametre.between_arrived = Integer.parseInt(time.getText().trim());
        MAIN_Frame.pe.setComponentAt(3, new Simulator());


    }
}
