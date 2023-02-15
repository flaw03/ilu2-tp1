package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche[] marche;
	
	
	private static class Marche{
		
		private static  Etal[] etals;
		private static int nbrEtal = 0;
		
		private Marche(int nbrEtal) {
			etals = new Etal[nbrEtal];
			this.nbrEtal = nbrEtal;
			for(int i = 0 ; i<nbrEtal ;i++) {
				etals[i].occuperEtal(null, null, 0);
				etals[i].libererEtal();
			}
		}
		
		
		private static void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit,int nbProduit) {
			Etal etal = etals[nbrEtal];
			etal.occuperEtal(vendeur, produit, nbProduit);
		}
	
		private static int trouverEtalLibre() {
			int i = 0;
			while (i<nbrEtal) {
				i++;
				if (etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private static Etal[] touverEtals(String produit) {
			Etal[] etalProduit = new Etal[nbrEtal];
			int i1=0;
			for(int i = 0 ; i<nbrEtal ; i++) {
				if (etals[i].contientProduit(produit)) {
					etalProduit[i1] = etals[i];
					i1++;
				}
			}
			return etalProduit;
		}	
		
		private static Etal touverVendeur(Gaulois gaulois) {
			Etal etalTrouver = null;
			int i = 0;
			while(etals[i].getVendeur() == gaulois) {
				i++;
			}
				return  etalTrouver;
		}
		
		private static String afficherMarche() {
			String texte = "Les etal du marcher :";
			for (int i = 0 ; i<nbrEtal;i++) {
				texte = texte +"/n" +etals[i].afficherEtal();
			}
			return texte;
		}
		
	}

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum ) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche[nbEtalsMaximum];
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur,String produit, int nbProduit) {
		marche.t
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}