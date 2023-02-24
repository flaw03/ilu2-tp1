package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	
	private static class Marche{
		
		private Etal[] etals;
		private int nbrEtal = 0;
		
		private  Marche(int nbrEtal) {
			this.nbrEtal = nbrEtal;
			etals = new Etal[nbrEtal];
			for(int i = 0 ; i<nbrEtal ;i++) {
				etals[i] = new Etal();
			}
		}
		
		
		private  void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit,int nbProduit) {
			Etal etal = etals[indiceEtal];
			etal.occuperEtal(vendeur, produit, nbProduit);
		}
	
		private  int trouverEtalLibre() {

			for(int i = 0; i<nbrEtal;i++){
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		private  Etal[] touverEtals(String produit) {
			Etal[] etalProduit = new Etal[nbrEtal];
			int ie = 0;
			for(int i = 0 ; i<nbrEtal ; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalProduit[ie] = etals[i];
					ie ++;
				}
			}
			return etalProduit;
		}	
		
		private  Etal touverVendeur(Gaulois gaulois) {
			for(int i = 0 ;i<nbrEtal ; i++)
				if (etals[i].getVendeur() == gaulois){
					return etals[i];
				}
			return  null;
		}
		
		private  String afficherMarche() {
			String texte = "Les etal du marcher :\n";
			for (int i = 0 ; i<nbrEtal;i++) {
				texte = texte +"   -" +etals[i].afficherEtal();
			}
			return texte;
		}
		
	}

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum ) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMaximum);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null){
			throw new VillageSansChefException("village sans chef");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
				chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
				chaine.append("le village n'a pas de chef");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
				}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur,String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+"cherche en endroit pour vendre"+nbProduit+" "+produit+".\n");
		int ietal = marche.trouverEtalLibre();
		if (ietal==-1) {
			chaine.append( "Il n y'a pas de place plus de place pour le vendeur "+vendeur.getNom()+" dans le marcher \n");
		}
		else {
			marche.utiliserEtal(ietal, vendeur, produit, nbProduit);
			chaine.append( "Le vendeur " + vendeur.getNom() + " vend des "+produit+" à l'étal n°"+ietal+"\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etalProduit  = marche.touverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeur qui proposent des fleurs sont "+produit+" : \n");
		for(int i = 0; i<etalProduit.length ;i++){
			if (etalProduit[i] != null){
				Gaulois vendeur = etalProduit[i].getVendeur();
				chaine.append("   -"+vendeur.getNom()+"\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return  marche.touverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return marche.touverVendeur(vendeur).libererEtal();
	}

	public String afficherMarche() {
		return marche.afficherMarche();
	}
}