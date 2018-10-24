package Architecture;

import Simulation.StaticParametre;

/**
 * la classe CreationCluster permet de définir les limite de chaque cluster 
 * 
 * 
 *
 */


public class CreationCluster {
	/**
	 *  name="id_cluster"
	 */
	//private int id_cluster;
	private static boolean[] etat_cluster ;

	public CreationCluster(){
		etat_cluster=null;
	}

	
	public static void creation_cluster() {
		// récuperer la taille du cluster via la classe Static_Paramettre
		int tailleCluster = StaticParametre.TAILLE_CLUSTER;
		// variable id qui donne identificateur unique a chaque cluster  
		int id=0;
		int r =0;
		// parcourir les lignes du NOC
		for(int i=0; i< StaticParametre.Limit_NOC_x; i=i+tailleCluster){
			// parcourir les colonnes du NOC
			for(int j=0; j< StaticParametre.Limit_NOC_y; j=j+tailleCluster){

				if(i+tailleCluster<=StaticParametre.Limit_NOC_x && j+tailleCluster<=StaticParametre.Limit_NOC_y){

					Cluster c = new Cluster();
					c.setCoordonneesDebut(i,j);
					c.setCoordonneesFin(i+2,j+2);
					c.setCenter(i, i+2, j, j+2);
					c.setId(id);
					StaticParametre.listClusters[r] =c;
				}else if(i+tailleCluster >StaticParametre.Limit_NOC_x && j+tailleCluster <=StaticParametre.Limit_NOC_y){
					int dif = tailleCluster - (StaticParametre.Limit_NOC_x-i);
					Cluster c = new Cluster();
					c.setCoordonneesDebut(i-dif,j);
					c.setCoordonneesFin((i-dif)+2,j+2);
					c.setCenter(i-dif, (i-dif)+2, j, j+2);
					c.setId(id);
					StaticParametre.listClusters[r] =c;
				}else if(j+tailleCluster>StaticParametre.Limit_NOC_x && i+tailleCluster <=StaticParametre.Limit_NOC_y){
					int dif = tailleCluster - (StaticParametre.Limit_NOC_y-j);
					Cluster c = new Cluster();
					c.setCoordonneesDebut(i,(j-dif));
					c.setCoordonneesFin(i+2,(j-dif)+2);
					c.setCenter(i,i+2, (j-dif), (j-dif)+2);
					c.setId(id);
					StaticParametre.listClusters[r] =c;
				}else if(j+tailleCluster>StaticParametre.Limit_NOC_x && i+tailleCluster >StaticParametre.Limit_NOC_y){
					int dif = tailleCluster - (StaticParametre.Limit_NOC_y-j);
					int dif1 = tailleCluster - (StaticParametre.Limit_NOC_x-i);
					Cluster c = new Cluster();
					c.setCoordonneesDebut((i-dif1),(j-dif));
					c.setCoordonneesFin((i-dif1)+2,(j-dif+2));
					c.setId(id);
					c.setCenter(i-dif1,(i-dif1)+2, (j-dif), (j-dif)+2);
					StaticParametre.listClusters[r] =c;		
				}
				r++;
				id++;
			}
			
		}
		StaticParametre.Nbr_cluster=r;
		etat_cluster = new boolean[StaticParametre.listClusters.length];
	}
	public static void setEtatCluster(int id_cluster, boolean etat){

		etat_cluster[id_cluster] = etat;

	}
	// charger le cluster
	public static boolean[] getEtat_Cluster(){

		return etat_cluster;

	}

	
}