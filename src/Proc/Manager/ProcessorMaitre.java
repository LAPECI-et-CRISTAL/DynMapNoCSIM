package Proc.Manager;

import Architecture.ProcessorElement;
import Simulation.StaticParametre;

/**
 * Cette classe represente le maître
 * 
 * @author Nabil Rekkab
 *
 */



public class ProcessorMaitre extends ProcessorElement {

	
	

	public ProcessorMaitre() {

		x=StaticParametre.Limit_NOC_x/2;
		y = StaticParametre.Limit_NOC_y/2;
	}
	
	
///////// Placement des taches ///////////////	
	public void Mapping(int Tnow) throws InterruptedException
	{
		// Le parcours des taches qui demandes a etre placés
			new Placement(Tnow);
	}
	///////////////// Routage des communication ///////////////
	public void Routage(int Tnow) throws InterruptedException
	{
		// Le parcours des taches qui nécéssitent le placement de communication
		//new routage(Tnow);
		//new routage2(Tnow);
		new Routage(Tnow);
	}

	
	
	//////////// Mise a jour de la platefrome ////////////////
	public void Mise_A_Jour(int Tnow) throws InterruptedException
	{
			
		//new action_et_mise_a_jour(Tnow);
		//new Mise_a_jour(Tnow);
		new Action_Mise_a_jour_(Tnow);
		
	}
	
}