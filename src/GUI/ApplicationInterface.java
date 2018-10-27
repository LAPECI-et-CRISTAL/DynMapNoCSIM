package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationInterface extends JPanel implements ActionListener {


    /**
     * @param args
     */
    public static ArrayList<Task_Struct> task = new ArrayList<Task_Struct>();
    public static int id = 0;
    Tree root;
    JPanel west;
    TreeDrawer pm;
    public ApplicationInterface() {
        // TODO Auto-generated constructor stub
        west = new JPanel();
        setLayout(new BorderLayout());
        west.setLayout(new FlowLayout());
        JButton bt = new JButton("Add");
        JButton enr = new JButton("Enregistrer");
        bt.addActionListener(this);
        enr.addActionListener(this);
        west.add(bt);
        west.add(enr);
        root = new Tree(0);
        root.Print(600);
        pm = new TreeDrawer(root);
        this.add(west, BorderLayout.SOUTH);
        this.add(pm, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JFrame pol = new JFrame();
        pol.getContentPane().add(new ApplicationInterface());
        pol.setVisible(true);
        pol.setSize(800, 800);

    }


    @Override
    public void actionPerformed(ActionEvent arg0) {

        JButton pol = (JButton) (arg0.getSource());
        if (pol.getText().equals("Add")) {
            id++;
            Tree ol = root.search(TreeDrawer.selectedcomponent, root);
            System.out.println("alla" + pm.toDraw.size());
            ol.addSon(pm.toDraw.size());
            this.remove(pm);
            root.Print(600);
            //	this.repaint();
            //	Container cp=(Container) this.getComponent(1);
            //	cp.removeAll();
            pm = new TreeDrawer(root);
//t;is.removeAll();
//this.setLayout(new BorderLayout());

            //this.add(west,BorderLayout.WEST);
            this.add(pm, BorderLayout.CENTER);
	/*   JFrame l=new JFrame();
       l.getContentPane().add(new TreeDrawer(root));
       l.setVisible(true);
      l.setSize(600,600);
  l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		*/
            new TaskData();
        } else
            try {
                generateFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }


    void generateFile() throws IOException {

        filechoose fc = new filechoose(new JFrame());


        File file = new File(fc.Path);

        FileWriter fw = new FileWriter(file + ".XML");
        fw.write("<?xml version=" + "1.0" + " encoding=" + "iso-8859-1" + " ?>" + "\r\n");
        fw.write("<list_applicatio>");
        for (int i = 0; i < this.task.size(); i++) {
            System.out.println(" id  " + task.get(i).id);
            for (int j = 0; j < this.task.get(i).Type.size(); j++) {
                System.out.println(" type  " + task.get(i).Type.get(j) + " cyle  " + task.get(i).Cycles.get(j) + " taille  " + task.get(i).Taille.get(j));
            }
        }

        fw.close();


    }

}