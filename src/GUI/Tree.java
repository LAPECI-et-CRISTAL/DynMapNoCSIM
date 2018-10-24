
package GUI;


import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Tree {

	ArrayList<Tree> Node;
	Component composant;
	
	// construire un noeud avec identifiant par défaut égal à 0
	int id;

	// Construire un noeud avec un identifiant égal à v
	Tree (int v){
		Node= new ArrayList<Tree>();
		id=v;
		
	}
	// ajouter un fils à un noeud
	public void addSon(Tree m){
		Node.add(m);
	}
	public void addSon(int m){
		Node.add(new Tree(m));
	}
	// modifier un fils
	public void setSon(int i, Tree m)
	{
		Node.set(i,m);
	}
	// effacer un fils
	public void removeSon(int i)
	{
		Node.remove(i);
	}
	
	public void removeSon(Tree m)
	{
		Node.remove(m);
	}
	// changer l'identifiant
	public void setid(int v){
		this.id=v;
	}
	// récuperer l'idée
	public int getid(){return this.id;}
	public Tree getSon(int i){
		return(Node.get(i));
	}
	ArrayList<triplet> p=new ArrayList<triplet>();
	ArrayList<doublet> pop=new ArrayList<doublet>();
	ArrayList<String> drst=new ArrayList<String>();
	public int search(Tree T)
	{
		int j=0;
		 for (int i=0;i<p.size();i++)
			 if (p.get(i).t[0]==T.getid()) {j=i;break;}
		 return j;
	}
	
	public Tree search(int T, Tree ol)
	{
		Tree me=ol;
		int l=0;
		if (ol.getNumberSons()!=0) {
		for (int i=0;i<ol.Node.size();i++){
			System.out.println("OOOP----"+ol.getid()+"---8---"+ol.Node.get(i).getid());
			 if (ol.Node.get(i).getid()==T) {System.out.println("entered");me=ol.Node.get(i);l=1;break;}
	}
		if (l!=1)
		for (int i=0;i<ol.getNumberSons();i++)
		
		me=search(T,ol.Node.get(i));
		
		}
		System.out.println(me.id);
		 return me;
	}
	
	public void Printing(Tree T,int start,int larg,int h)
	{
		if (T.getNumberSons()!=0) {
		//	System.out.println("un appel");
			int l=larg/(T.getNumberSons());
		int pos=start+(int)(l/2);
		for (int i=0;i<T.getNumberSons();i++)
		{
		triplet posi=new triplet();
		posi.t[0]=T.getSon(i).getid();posi.t[1]=pos;posi.t[2]=h;
		drst.add(T.getSon(i).id+"");
		p.add(posi);
		doublet op=new doublet();
		op.t[0]=search(T);
		//System.out.println(T.getid());
		//System.out.println(op.t[0]);
		op.t[1]=p.size()-1;
		pop.add(op);
		pos+=l;	}
		for (int i=0;i<T.getNumberSons();i++)
		{
			//System.out.println("avant appel"+T.getSon(i).id);
			Printing(T.getSon(i),start+i*l,l,h+100);
		}
		
		}
	}
	
	public void Print(int y)
	{	 p=new ArrayList<triplet>();
	pop=new ArrayList<doublet>();
	 drst=new ArrayList<String>();
		
		triplet lop= new triplet();
		lop.t[0]=this.getid();
		drst.add(/*this.composant.setPr()*/"0");
		lop.t[1]=y/2;
		lop.t[2]=50;
		p.add(lop);
		Printing(this,0,y,100);
	}
	public int getNumberSons()
	{
		return Node.size();
		}
       
        
        
	public static void main(String args[]){
            
		Tree root=new Tree(0); //la racine 
		root.addSon(1);
		root.addSon(2);
		root.getSon(0).addSon(3);
		root.getSon(0).getSon(0).addSon(4);
		root.getSon(0).getSon(0).getSon(0).addSon(5);
		Tree om=root.search(1,root);
		//mroot.getSon(0).addSon(new Tree(4));root.getSon(0).addSon(new Tree(5));
		//mroot.setSon(0,new Tree(313));
		//root.getSon(0).getSon(1).addSon(new Tree(55));
		//root.getSon(0).getSon(1).addSon(new Tree(77));
		//root.getSon(0).getSon(1).addSon(new Tree(69));
		//root.getSon(0).getSon(1).addSon(new Tree(60));	
		//mroot.getSon(1).addSon(new Tree(99));root.getSon(1).addSon(new Tree(7));//root.getSon(1).addSon(new Tree(8));
		//mroot.getSon(2).addSon(new Tree(9));root.getSon(2).addSon(new Tree(10));
		//root.getSon(0).getSon(0).addSon(new Tree(78));
		//root.getSon(0).getSon(0).addSon(new Tree(60));
		//root.getSon(0).getSon(1).getSon(1).addSon(new Tree(99));
		//mroot.addSon(103);
		
		//mTree root2= new Tree(105);
		//mroot2.addSon(1144);root2.addSon(107);root2.addSon(108);root2.addSon(1119);
		//mroot2.Print(800);
		//mroot.getSon(3).addSon(104);root.getSon(3).addSon(1084);root.getSon(3).addSon(1074);
		//mroot.getSon(3).getSon(0).addSon(root2);
	//root.removeSon(root2);
		om.Print(800);
		System.out.println("Size"+om.getNumberSons());
		
		//for (int i=0;i<root.p.size();i++) 
	//		System.out.println(root.p.get(i).t[0]+","+root.p.get(i).t[1]+","+root.p.get(i).t[2]);
	
	      /* JFrame l=new JFrame();
	       l.getContentPane().add(new TreeDrawer(om));
	       l.setVisible(true);
	      l.setSize(600,600);
      l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         */
            
            
            
////            mena order
////      
////int M[][]={{1,2,0,1},{1,2,0,0},{0,0,1,1},{2,1,0,2},{1,0,1,0}};            
////   int h []={0,1,2,3};    
////  int c=2;
////int p[];
////
////
//////for(int i=0;i<=m.length;i++){
//////           
//////            for(int j=0;j<t.length;j++)
//////                {  
//////            if(j!=c)   
//////           if(  m[i][t[j]]==0)
////
////ArrayList<Integer> b;
////
//////chaque colonne les nbr de oui et non
////ordre f =new ordre(M,h,c);  
////
////    p =  f.nbryn(M, 1, 1);   
////    
////    System.out.println("yes-------> "+p[0]);    
////      System.out.println("non-----> "+p[1]);
////      
////// pour voire lees eliment de colonne      
////ordre q =new ordre(M,h,c); 	
////  b = q.nbrelement(M, 2)  ;  
// System.out.println(b);
 
 
}}
