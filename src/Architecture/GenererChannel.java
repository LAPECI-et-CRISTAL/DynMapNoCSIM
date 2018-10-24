package Architecture;

import java.util.Map;
import java.util.TreeMap;

import Simulation.StaticParametre;


/**
 * cette classe permet de g�n�rer les lien entre les processeurs 
 *
 */


public class GenererChannel {

	//La liste des liens
	
	private static Map<Integer,LienNoc> listChannel =null;
	
	public GenererChannel(){
		listChannel = new TreeMap<Integer, LienNoc>();
	}
	
	
	// ceation des liens
	public void start(){
		int id_channel=0;
		int nbrNode = StaticParametre.Limit_NOC_x*StaticParametre.Limit_NOC_y;
		
		for(int i=0;i < nbrNode; i++){
			LienNoc lien = null;
			int id= StaticParametre.listProcesseur.get(i).getId();
			int taille_NOC_x = StaticParametre.Limit_NOC_x; 
			int taille_NOC_y = StaticParametre.Limit_NOC_y;
			// 1 ligne
			if(StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y== 0){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1, id_channel);
				listChannel.put(id_channel,lien);
				
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y,  id_channel);
				listChannel.put(id_channel,lien);
			// 1 ligne au colonne milieu
			}else if(StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y>0 && StaticParametre.listProcesseur.get(i).y < taille_NOC_y-1){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1, id_channel);
				listChannel.put(id_channel,lien);
				
				id_channel++;							
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y,id_channel);
				listChannel.put(id_channel,lien);
			// 1 ligne derni�re colonne
			}else if(StaticParametre.listProcesseur.get(i).x == 0 && StaticParametre.listProcesseur.get(i).y==taille_NOC_y-1){
				id_channel++;			
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y,id_channel);
				listChannel.put(id_channel,lien);
			
			// ligne milieu + 1 colonne
			}else if(StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x<taille_NOC_x-1 && StaticParametre.listProcesseur.get(i).y==0){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1,id_channel);
				listChannel.put(id_channel,lien);
				
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y, id_channel);
				listChannel.put(id_channel,lien);

			// ligne milieu + colonne milieu	
			}else if(StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x<taille_NOC_x-1 && StaticParametre.listProcesseur.get(i).y > 0 && StaticParametre.listProcesseur.get(i).y<taille_NOC_y-1){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1, id_channel);
				listChannel.put(id_channel,lien);
											
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y,id_channel);
				listChannel.put(id_channel,lien);
			// ligne milieu + derni�re colonne
			}else if(StaticParametre.listProcesseur.get(i).x > 0 && StaticParametre.listProcesseur.get(i).x<taille_NOC_x-1 && StaticParametre.listProcesseur.get(i).y == taille_NOC_y-1){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+taille_NOC_y] = id_channel;
				StaticParametre.getMatriceChannel()[id+taille_NOC_y][id] = id_channel;
				lien = new LienNoc(id, id+taille_NOC_y, id_channel);
				listChannel.put(id_channel,lien);
			// derni�re ligne 1 colonne
			}else if(StaticParametre.listProcesseur.get(i).x== taille_NOC_x-1 && StaticParametre.listProcesseur.get(i).y == 0){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1,  id_channel);
				listChannel.put(id_channel,lien);
								
			// derni�re ligne colonne milieu
			}else if(StaticParametre.listProcesseur.get(i).x== taille_NOC_x-1 && StaticParametre.listProcesseur.get(i).y>0 &&StaticParametre.listProcesseur.get(i).y< taille_NOC_y-1 ){
				id_channel++;
				StaticParametre.getMatriceChannel()[id][id+1] = id_channel;
				StaticParametre.getMatriceChannel()[id+1][id] = id_channel;
				lien = new LienNoc(id, id+1,id_channel);
				listChannel .put(id_channel,lien);
								
			}
			
			
		}
		StaticParametre.LEGNHT_CHANNEL=listChannel.size();
		
	}
	
	/**
	 * Cette methode retourn la list des lien (entre les processeur) ou chaque lien est repr�sent� par un identificateur unique
	 * 
	 * @return listChannel 
	 */
	public  static Map<Integer,LienNoc> getListChannel() {
		
		return listChannel;
	}
	
}
