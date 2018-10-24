package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;

import flanagan.io.FileChooser;
public class InterfaceArchitecture extends JPanel implements ActionListener {

	int ligne,collone;
Processor k[][];
JPanel pan=new JPanel();
JTextField tex;
JPanel est;
JPanel e=new JPanel();
JPanel ee =new JPanel();
JPanel eee=new JPanel();

	public InterfaceArchitecture(/*int x,int y*/) {
//	ligne=x;
	//collone=y;
	//GridLayout gr=new GridLayout(x,y,30,30);
	//this.setLayout(gr);
	this.setLayout(new FlowLayout());
	//pan.setLayout(gr); 
	//k=new Processor[x][y];
	 
	/*for (int i=0;i<x;i++)
		for (int j=0;j<y;j++)
		{
			k[i][j]=new Processor("GP");
			k[i][j].setSize(50,50);
			k[i][j].setBackground(Color.gray);
			k[i][j].setToolTipText("Frequence=50 \n"+"consommantion d'energie");
			//this.add(k[i][j]);
			pan.add(k[i][j]);
	//		this.add(new JLabel("proc"));
		}
	
	this.add(pan,BorderLayout.WEST);
	*/
	 this.add(e);
	 this.add(ee);
	this.add(eee);
	
	est=new JPanel();
	GridLayout g2=new GridLayout(1,1,30,10);
	est.setLayout(g2);
	JLabel jl=new JLabel ("Nombre de ligne et de colone");
	tex=new JTextField("");
	JButton ok=new JButton("ok");
	ok.addActionListener(this);
	est.add(jl);
	est.add(tex);
	est.add(ok);
	
	this.add(est);
//this.add(bt);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		JButton pol=(JButton)(e.getSource());
		if(pol.getText().equals("ok"))
		{
		pan.removeAll();
		pan.repaint();
		this.add(Archi(Integer.parseInt(tex.getText()),Integer.parseInt(tex.getText())),BorderLayout.WEST);
		System.out.println(tex.getText());
		this.add(this.e);
		this.add(ee);
		this.add(eee);
		this.add(est);
		} else
			try {
				generateFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	
	
	
	void generateFile() throws IOException{
		
		filechoose fc=new filechoose(new JFrame());
		
		
		
File file=new File(fc.Path);
		
		FileWriter fw = new FileWriter(file+".txt");
		fw.write("Nbr_Ligne "+tex.getText()+"\r\n");
		fw.write("Nbr_Colone "+tex.getText()+"\r\n");
		int j;
		for (int i=0;i<Integer.parseInt(tex.getText());i++)
		{
					for (j=0;j<Integer.parseInt(tex.getText())-1;j++)
			{
				if (k[i][j].upl.getText().equals("FPG")){
					fw.write("2,");
				}
				else if (k[i][j].upl.getText().equals("DSP")){
					fw.write("3,");
				}
				else if (k[i][j].upl.getText().equals("GPP")){
					fw.write("1,");
				}
				else if (k[i][j].upl.getText().equals("ASI")){
					fw.write("4,");
				}
			}
					if (k[i][j].upl.getText().equals("FPG")){
						fw.write("2");
					}
					else if (k[i][j].upl.getText().equals("DSP")){
						fw.write("3");
					}
					else if (k[i][j].upl.getText().equals("GPP")){
						fw.write("1");
					}
					else if (k[i][j].upl.getText().equals("ASI")){
						fw.write("4");
					}
					

					fw.write("\r\n");
		}
		fw.close();
		
		
			
	}
	
	
	public JPanel Archi (int x,int y){
		ligne=x;
		collone=y;
		JPanel nab=new JPanel();

		JPanel nab2=new JPanel();
		
		pan.setLayout(new BorderLayout());
		GridLayout gr=new GridLayout(x,y,30,30);
		nab.setLayout(gr);
		nab2.setLayout(new FlowLayout());
		k=new Processor[x][y];
		 
		for (int i=0;i<x;i++)
			for (int j=0;j<y;j++)
			{
				k[i][j]=new Processor("GP");
				k[i][j].setSize(100,100);
				k[i][j].setBackground(Color.gray);
				k[i][j].setToolTipText("Frequence=50 \n"+"consommantion d'energie");
				//this.add(k[i][j]);
				nab.add(k[i][j]);
		//		this.add(new JLabel("proc"));
			}
		pan.add(nab,BorderLayout.NORTH);

		JButton bt=new JButton("Enregistrer");
		bt.addActionListener(this);
		nab2.add(bt);
		pan.add(nab2,BorderLayout.SOUTH);
		return(pan);
		
		
	}

}
