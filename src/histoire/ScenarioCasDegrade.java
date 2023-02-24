package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;

public class ScenarioCasDegrade {
	
    public static void main(String[] args) {
        
        Gaulois obelix = new Gaulois("Obélix", 25);
        Gaulois bonemine = new Gaulois("Bonemine", 7);

        Etal etalFleur = new Etal();
        etalFleur.occuperEtal(bonemine, "banane", 10);
        try {
            System.out.println( etalFleur.acheterProduit(4, obelix));
        } catch (IllegalArgumentException e) {
            System.out.println("La quantiter acherter : doit etre superieur a 1\n");
        }catch(IllegalStateException e){
            System.out.println("L'etal doit etre occuper\n");
        }
       

        System.out.println("FIN du test\n");


    }
}
