package Proc.Manager;

import java.awt.Color;

import Mapping_Dynamique.Methode_Placement;
import Routage_Algorithme.Send;
import Simulation.Simulator;
import Simulation.StaticParametre;
import Application.Tache;
import Architecture.Create_NOC;
import Architecture.Paquet;
import Architecture.ProcessorElement;
import Architecture.GenererChannel;

public class Action_Mise_a_jour_ {
	
	Tache tache_fils,tache,tache_fils1,tache_pere;

	
	 public Action_Mise_a_jour_(int Tnow) throws InterruptedException
	  
	  {
		 
		 
		// parcours de la liste des liens GenererChannnel.listChannel.size
		 for(int i=1;i<= GenererChannel.getListChannel().size();i++)
		 {	 for(int j=0;j<GenererChannel.getListChannel().get(i).event.size();j++)
		 		{
		 // tester si le Tnow correspond à un evenement de décrementation de la charge d'un lien
		 			if(GenererChannel.getListChannel().get(i).event.get(j)==Tnow)
		 				{
		 			
		 				GenererChannel.getListChannel().get(i).set_file();	// diminuer le nombre de paquets qui rest à envoyer pour ce lien   
		 				
		 				}
		 			
		 		}

		 }					 
		 	/***************************************/
		 
		 
		 
		 	 for (int k=0;k<StaticParametre.List_Paquet.size();k++) //parcourir la liste des paquets
				 {
					
			 Paquet p=StaticParametre.List_Paquet.get(k);
						 
						
			 if(p.T_fin==Tnow)
				 {				
				 
				 		 
						 if(p.x_inter==p.x_destination && p.y_inter==p.y_destination)// Paquet arrivé à Destination
						 	{
									 // incrémenter le nombre de paquets reçu
									 	Create_NOC.getNOC()[p.x_destination][p.y_destination].set_Nbr_Paquet_reçu(p.x_source,p.y_source,p.tache_source,p.tache_dest);
									 		
									 	

							 			
											 
												// tester si tous les paquets ont été reçu 
									 	
											 if(Create_NOC.getNOC()[p.x_destination][p.y_destination].Nbr_Paquet_reçu(p.x_source,p.y_source,p.tache_source,p.tache_dest) )// toutes les données sont arrivées
											 	{	 
												 	
							 						
												 if(p.pere_fils) // paquet pere_fils
													 { 					
																

																			 
														 		Launch(p.tache_dest);
														 			 		
														 			
													 }
														 
														 else
														 {
															 if(p.fils_pere)
															 {
																 		 // incrémenter le nombre de communication reçu venant du fils
																		 p.tache_dest.set_communication_reçu();
																			 
																	
																	
																}
															 
														 }
														 	
												
														 	
														}
							
						 	}
						 else //Communication pas encore fini (sur un proc intermédiaire)
						 {
								 new Send(p);
						 }
								 	 	
						 
				 }//fin P.Tnow
						

								
				} 
		 		 
		 		 /********************************/
//////////////////////////////////mise a jour des liens //////////
				 
				 
					
							
							 
							 
						
				 
		 /************************/
		 // mise a jour des noeuds 
		 
		 for(int j=0;j<StaticParametre.getListApplication().size();j++)
 	 	{
 	 	int c;	
 	 				for(int i=0;i<StaticParametre.getListApplication().get(j).getListTache().size();i++)
 	 				{

 	 					tache=StaticParametre.getListApplication().get(j).getListTache().get(i);
 	 	
 	 					
 	 				
 	 					

 	 					
 	 					////////////////////
 	 					
 	 					/**
 	 					 *  Libération d'un processeur
 	 					 * 
 	 					 */
 	 					
 	 					
 	 					
 	 				if((Tnow==tache.fin_execution && tache.getSucc().size()==0 && tache.end==false) || (tache.nbr_message==tache.getSucc().size() && !tache.getSucc().isEmpty() && tache.end==false))   // à compléter: lors de la fin d'execution on ne fait ke rendre le processeur libre et verifier si on peut les taches not mapped
 	 					{
 	 					
 	 					
 	 					tache.end=true;
 	 					Launch(tache);
 	 						}
 	 				
 	 				
 	 				if(tache.fin_execution==Tnow && tache.end)
 	 				{
 	 					System.out.println("fin   execution     "+tache.fin_execution+"   x  "+tache.x+"   y   "+tache.y);
 	 	 				
 	 	 	 					
 	 					//////MONO///////
 	 					if(StaticParametre.MONO_MULTI.equals("MONO"))
 	 					{
 	 						Create_NOC.getNOC()[tache.x][tache.y].set_state(true); // rendre l'etat du processeur à libre
 	 						Create_NOC.platforme.k[tache.x][tache.y].setBackground(Color.CYAN);
 	 						Thread.sleep(2);
 	 						Create_NOC.platforme.k[tache.x][tache.y].setBackground(Color.GRAY);
 	 						 	 						
 	 					}
 	 					

 	 					
 	 					
 	 					////////////////////////////
 	 				else{////////	Multi ///////////
 	 					 	 					
 	 						// Libérer l'espace mémoire occupé par la tache
 	 						Create_NOC.getNOC()[tache.x][tache.y].setMem(tache.getTailleTache(Create_NOC.getNOC()[tache.x][tache.y].getType()),2);	 						
 	 						
 						for(int o=0;o<Create_NOC.getNOC()[tache.x][tache.y].File.size();o++)
 							{
 							if (Create_NOC.getNOC()[tache.x][tache.y].File.get(o)==tache)
 								//enlever la tache de la liste des tache en execution sur ce processeur
 	 							Create_NOC.getNOC()[tache.x][tache.y].File.remove(o);
 							
 							
 							}
 	 					}
 	 					
 	 					////////
 	 					
 	 					/** Modifier le temps de fin d'execution global de la tache */
 	 				
 	 				
 	 					tache.fin_execution=Tnow;
 	 					
 	 						 					
 	 					
 	 					/**fin de la tache initiale, donc fin de l'application*/
 	 					if (tache.getId()==0) 
 	 					{
 	 						StaticParametre.setEnd_Application();//incrementer le nombre d'application executées
 	 					
 	 				//	Simulator.setEnergy(StaticParametre.listApplication.get(tache.getIdApplication()).getEnergie());// ajout de l'energie consommée par l'application à l energie globale
 	 					
 	 					/**Lancement d'une nouvelle application sur le cluster */
 	 					
 	 					Placement.launch_new_application(StaticParametre.getListApplication().get(tache.getIdApplication()).getIdCluster());
 	 					

 	 					
 	 					
 	 					}
 	 					
 	 				 	 				
 	 					
 	 					// i représente l id d'une tache	
 	 					if(i!=0)
 	 					// lancement du routage du fils vers le pere
 	 						{
 	 						//récuperer le pere
 	 						tache_pere=StaticParametre.listApplication.get(j).getListTache().get(tache.getIdPere());
 	 						Paquet p=new Paquet(tache.x,tache.y,tache_pere.x,tache_pere.y,StaticParametre.List_Paquet.size()+1,tache_pere);
 	 						
 	 						p.fils_pere=true;
 	 						

 	 					
 	 						p.tache_dest=tache_pere;
							p.tache_source=tache;
 	 						
 	 						p.set_source(tache.x,tache.y);
								
								
								
								StaticParametre.List_Paquet.add(p);
							
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].com_x_source.add(tache.x);
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].com_y_source.add(tache.y);
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].nbr_paquet.add(1);								
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].nbr_paquet_reçu.add(0);
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].tache_source.add(tache);
								Create_NOC.getNOC()[tache_pere.x][tache_pere.y].tache_dest.add(tache_pere);
								
								
								
								new Send(p);
								
 	 					}
 	 						
 	 					
 	 					
 	 					
 	 				System.out.println("end  de la tache  "+i+ " id app  "+j+   "    !!!!!!!!!!!!!!!!!!!!!!!!!     Tnow   "+Tnow+"                  fin execution    "+tache.fin_execution);
 	 				//////////////////////
 	 				
 	 				
 	 						/** apres la fin d'execution d'une tache, on parcours la liste des taches qui n'ont pas pu placer leurs fils auparavant */
					 	 				
 	 				if(!StaticParametre.not_mapped.isEmpty()) // la list not mapped contient les taches qui n ont pas pu etre placé avant
					 					{		
					 							for(int k=0;k<StaticParametre.not_mapped.size();k++)
					 							{
					 								
					 								
					 								
					 								int r;
					 								
					 								
					 								tache_fils=StaticParametre.not_mapped.get(k); 	 								
					 								
					 								
					 								for( r=0;r<StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).getSucc().size();r++)
					 	 							{
					 		 	 						c=StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).getSucc().get(r);
					 		    			 			tache_fils1=StaticParametre.listApplication.get(tache_fils.getIdApplication()).getListTache().get(c);
					 		    			 			
					 		    			 			
					 		    			 			
					 		    			 			if(tache_fils1.equals(tache_fils))
					 		    			 				{
					 		    			 				break;}
					 	 							}
					 								
					 								new Methode_Placement(tache_fils1,Create_NOC.getNOC()[StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x][StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y]);
					 								
					 								
					 								if(tache_fils.debut_execution!=-1)  
					 									{
							 								

					 										Simulator.Add_Event(tache_fils.debut_execution);
					 										
					 										StaticParametre.not_mapped.remove(k);
					 										tache_fils.y2=tache_fils.y;
					 										tache_fils.x2=tache_fils.x;
					 										
					 										StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x1.set(r,StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).x);
		    			 	    			 				StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y1.set(r,StaticParametre.getListApplication().get(tache_fils.getIdApplication()).getListTache().get(tache_fils.getIdPere()).y);
		    			 	    			 				
		    			 	    			 			
		    			 	    			 				
					 										
					 									}
					 								
					 							}
					 			 			
					 			 		}
					 					
 	 				//////////////////
 	 					}
 	 				
 	 				
 	 				}
 	 				}
 	 	}
		 
		
	 	 	
	 	 	
	 	 	
	 	 	
	
	  
	
	 


	 /**
	  * Lance la tache sur le processeur afin de l executer
	  * 
	  * @param t
	  * @throws InterruptedException
	  */
	 public void Launch(Tache t) throws InterruptedException 
	 {
	ProcessorElement processeur= Create_NOC.getNOC()[t.x][t.y];


	processeur.ajoutTache(t);
	
	if(t.fin_execution!=Simulator.Tnow)	
			Simulator.Add_Event(t.fin_execution);
		
		}
//////////


}
	 
