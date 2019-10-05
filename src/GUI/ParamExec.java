package GUI;

import Simulation.StaticParametre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class ParamExec extends JPanel implements ActionListener, MouseListener {
    public static JFrame me;
    JTextField[] op;
    private JCheckBox bn;
    private JCheckBox nn;
    private JCheckBox ff;
    private JCheckBox xy;
    private JCheckBox mora;
    private JComboBox plat;
    private FileWriter fstream;
    private BufferedWriter out;

    public ParamExec() {

        GridLayout gr = new GridLayout(14, 2, 10, 10);
        setLayout(gr);
        JLabel tp = new JLabel("			  Type de platforme");
        JLabel alg = new JLabel("			  Algorithme de placement");
        JLabel algr = new JLabel("		  Algorithme de routage");
        Vector<String> po = new Vector<String>();

        po.add("MONO");
        po.add("MULTI");
        plat = new JComboBox(po);
        add(tp);
        add(plat);
        bn = new JCheckBox("BN");
        nn = new JCheckBox("NN");
        ff = new JCheckBox("FF");
        JPanel algoplacement = new JPanel();
        algoplacement.setLayout(new FlowLayout());
        algoplacement.add(bn);
        algoplacement.add(nn);
        algoplacement.add(ff);
        add(alg);
        add(algoplacement);
        xy = new JCheckBox("XY");
        mora = new JCheckBox("MORA");
        JPanel algorootage = new JPanel();
        algorootage.setLayout(new FlowLayout());
        algorootage.add(xy);
        algorootage.add(mora);
        add(algr);
        add(algorootage);


        op = new JTextField[9];
        JLabel[] opi = new JLabel[9];
        for (int i = 0; i < 9; i++) {
            opi[i] = new JLabel();
            op[i] = new JTextField("");
            add(opi[i]);
            add(op[i]);
        }
        op[0].setText("10");
        op[1].setText("1000");
        op[2].setText("20");
        op[3].setText("3");
        op[4].setText("3");
        op[5].setText("3");
        op[6].setText("3");
        op[7].setText("arch.txt");
        StaticParametre.Lien_Fichier_architecture = "arch.txt";
        op[8].setText("scenario1.XML");
        StaticParametre.Lien_Fichier_XML = "scenario1.XML";
        opi[0].setText("  		 Nombre d'application");
        opi[1].setText("   		 Temps d'envoie");
        opi[2].setText("   		 L'energie Consommee/envoie");
        opi[3].setText("   		 Mode GP");
        opi[4].setText("   		 Mode FPGA");
        opi[5].setText("   		 Mode DSP");
        opi[6].setText("  		 Mode Asic");
        opi[7].setText("   		 Architecture");
        opi[8].setText("   		 Application");
        op[7].addMouseListener(this);
        op[8].addMouseListener(this);
        JButton gen = new JButton("Suivant");
        gen.addActionListener(this);
        JPanel nab = new JPanel();
        nab.setLayout(new FlowLayout());
        nab.add(gen);
        add(nab);


    }

    public static void main(String args[]) {
        JFrame me = new JFrame();
        me.getContentPane().add(new ParamExec());
        me.setSize(600, 600);
        me.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        //	MAIN_Frame.pe.setSelectedIndex(3);

        try {
            fstream = new FileWriter("PARAMETRE.txt");
            out = new BufferedWriter(fstream);

            out.write("Nombre_Application " + Integer.parseInt(op[0].getText()) + "\n");
            out.write("Type_de_plateforme " + plat.getSelectedItem() + "\n");
            out.write("ALGO_PLACEMENT ");
            if (bn.getSelectedObjects() != null)
                out.write("BN ");
            if (nn.getSelectedObjects() != null)
                out.write("NN ");
            if (ff.getSelectedObjects() != null)
                out.write("FF ");
            out.write(". \n");
            out.write("Strategie_De_Recherche GBHD ." + "\n");
            out.write("ALGO_ROUTAGE ");
            if (xy.getSelectedObjects() != null)
                out.write("XY ");
            if (mora.getSelectedObjects() != null)
                out.write("MORA ");
            out.write("." + "\n");
            out.write("DEBIT 5" + "\n");
            out.write("Temps_Envoi " + Integer.parseInt(op[1].getText()) + "\n");
            out.write("Energie_Consommee_Envoi " + Integer.parseInt(op[2].getText()) + "\n");
            out.write("MODE_CPU " + Integer.parseInt(op[3].getText()) + "\n");
            out.write("MODE_FPGA " + Integer.parseInt(op[4].getText()) + "\n");
            out.write("MODE_ASIC " + Integer.parseInt(op[5].getText()) + "\n");
            out.write("MODE_DSP " + Integer.parseInt(op[6].getText()) + "\n");


        } catch (java.lang.NumberFormatException ep) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur: Veuillez saisir correctement tous les champs", "Erreur", 2);
            // TODO: handle exception
        } catch (IOException epp) {
            // TODO Auto-generated catch block
            epp.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Lancement de la simulation !!
        //MAIN_Frame.fra.setVisible(false);

        new Arrived();
					
		/*try {
			
			//MAIN_Frame.pe.setComponentAt(3, new Simulator());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			*/


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        filechoose fc = new filechoose(new JFrame());
        if (e.getSource().equals(op[8])) {

            op[8].setText(fc.Path);
            StaticParametre.Lien_Fichier_XML = fc.Path;
            // TODO Auto-generated method stub
        }

        if (e.getSource().equals(op[7])) {

            op[7].setText(fc.Path);
            StaticParametre.Lien_Fichier_architecture = fc.Path;
            // TODO Auto-generated method stub
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
	

