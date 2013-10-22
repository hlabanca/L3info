/**TP4
 * 
 * @author Hector Labanca, Augustin Bardou
 *
 */
public class TestSmallSet {
	
	private SmallSet[] E = new SmallSet[5];
	
	public TestSmallSet() {
		for(int i=0 ; i<5 ; i++) {
			E[i] = new SmallSet();
		}
	}
	
	public static int lireIntSafe() {
		boolean succes = false;
		int nb = 0;
		while(!succes) {
			try {
				nb = Lecture.lireInt();
				succes = true;
			} catch(NumberFormatException e) {
				System.out.println("Vous devez entrer un nombre entier et rien d'autre." +
						" Merci de recommencer.");
				succes = false;
			}
		}
		return nb;
	}
	
	public SmallSet choixSet() {
		while(true) {
			System.out.println("Sur quel set allez-vous effectuer une opération ? choisissez de 1 à 5.");
			int choix = lireIntSafe();
			if(choix>=1 && choix<=5) {
				System.out.println("Vous avez choisi le set "+choix+" :");
				System.out.println(E[choix-1]);
				return E[choix-1];
			}
		}
	}
	
	public static void afficherMenu() {
		System.out.println("Choisissez une opération.");
		System.out.println("1.Taille");
		System.out.println("2.Contient un entier i ?");
		System.out.println("3.Est vide ?");
		System.out.println("4.Ajouter un entier i");
		System.out.println("5.Supprimer un entier i");
		System.out.println("6.Ajouter un intervalle [i,j]");
		System.out.println("7.Supprimer un intervalle [i,j]");
		System.out.println("8.Union avec un autre set");
		System.out.println("9.Intersection avec un autre set");
		System.out.println("10.Différence avec un autre set");
		System.out.println("11.Différence symétrique");
		System.out.println("12.Complément du set");
		System.out.println("13.Suppression de toutes les valeurs");
		System.out.println("14.Est inclus dans un autre set");
		System.out.println("15.Est égal à un autre set");
		System.out.println("Entrez le nombre lui correspondant : (choisissez un autre nombre pour annuler)");
	}
	
	public void effectuerOperation(SmallSet setActif) {
		afficherMenu();
		int choix = lireIntSafe();
		switch(choix) {
		case 1:
			System.out.println("Taille du set :");
			System.out.println(setActif.size());
			break;
		case 2:
			System.out.println("Entrer un entier :");
			if(setActif.contains(lireIntSafe()))	System.out.println("Le nombre est contenu dans le set.");
			else									System.out.println("Le nombre n'est pas contenu dans le set");
			break;
		case 3:
			if(setActif.isEmpty())	System.out.println("Le set est vide.");
			else					System.out.println("Le set n'est pas vide.");
			break;
		case 4:
			System.out.println("Entrer l'entier à ajouter au set :");
			setActif.add(lireIntSafe());
			break;
		case 5:
			System.out.println("Entrer l'entier à retirer du set :");
			setActif.remove(lireIntSafe());
			break;
		case 6:
			int i, j;
			while(true) {
				System.out.println("Entrer un intervalle de 2 entiers à ajouter :");
				i = lireIntSafe();
				j = lireIntSafe();
				if(i >= 0 && j <= 255 && i < j)	break;
				else
					System.out.println("Erreur, vous devez entrer i < j tels que i>=0 et i<=255.");
			}
			setActif.addInterval(i, j);
			break;
		case 7:
			while(true) {
				System.out.println("Entrer un intervalle de 2 entiers à retirer :");
				i = lireIntSafe();
				j = lireIntSafe();
				if(i >= 0 && j <= 255 && i < j)	break;
				else
					System.out.println("Erreur, vous devez entrer i < j tels que i>=0 et i<=255.");
			}
			setActif.removeInterval(i, j);
			break;
		case 8:
			System.out.println("Spécifiez le set avec lequel effectuer l'union :");
			setActif.union(choixSet());
			System.out.println("Résultat de l'union :");
			System.out.println(setActif);
			break;
		case 9:
			System.out.println("Spécifiez le set avec lequel effectuer l'intersection :");
			setActif.intersection(choixSet());
			System.out.println("Résultat de l'intersection :");
			System.out.println(setActif);
			break;
		case 10:
			System.out.println("Spécifiez le set avec lequel effectuer la différence :");
			setActif.difference(choixSet());
			System.out.println("Résultat de la différence :");
			System.out.println(setActif);
			break;
		case 11:
			System.out.println("Spécifiez le set avec lequel effectuer la différence symétrique :");
			setActif.symmetricDifference(choixSet());
			System.out.println("Résultat de la différence symétrique :");
			System.out.println(setActif);
			break;
		case 12:
			setActif.complement();
			System.out.println("Résultat du complément :");
			System.out.println(setActif);
			break;
		case 13:
			setActif.clear();
			System.out.println("Le set a été vidé.");
			break;
		case 14:
			System.out.println("Entrez le set avec lequel effectuer le test d'inclusion :");
			if(setActif.isIncludedIn(choixSet()))	System.out.println("Notre set est inclus dans le set spécifié.");
			else									System.out.println("Notre set n'est pas inclus dans le set spécifié.");
			break;
		case 15:
			System.out.println("Entrez le set avec lequel effectuer le test d'égalité :");
			if(setActif.equals(choixSet()))	System.out.println("Notre set est égal au set spécifié.");
			else							System.out.println("Notre set est différent du set spécifié.");
			break;
		default:
			System.out.println("Votre saisie ne correspond à aucun des choix possibles.");
		}
	}

	
	public static void main(String[] args) {
		TestSmallSet test = new TestSmallSet();
		boolean continuer = true;
		while(continuer){
			test.effectuerOperation(test.choixSet());
			System.out.println("Effectuer une autre opération ? 0 pour non, 1 pour oui.");
			int choix = lireIntSafe();
			while(!(choix==0 || choix==1)) {
				System.out.println("Vous devez entrer 0 ou 1. Recommencez.");
				choix = lireIntSafe();
			}
			if(choix==0)	continuer = false;
			else			continuer = true;
		}
	}


}
