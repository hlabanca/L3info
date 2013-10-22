/** TP4
 * @author Augustin Bardou, Hector Labanca
 * 
 */
public class SmallSet{
	private boolean [] tab = new boolean [256];
	
	public SmallSet(){
		for (int i = 0; i < 256; i++){
			tab[i] = false;
		}
	}
	public SmallSet(boolean [] t){
		for (int i = 0; i < 256; i++){
			tab[i] = t[i];
		}
	}
	/**
	 * Methode qui retourne le nombre de valeurs appartenant à l'ensemble.
	 * On parcours le tableau tab et a chaque fois qu'on a une valeur a true on incremente le compteur.
	 * @return cpt sachant que cpt vaut la taille du tableau
	 */
	public int size(){
		int cpt = 0;
		if (isEmpty())
			return cpt;
		for (int i = 0; i < 256; i++){
			if (tab[i])
				cpt++;
		}
		return cpt;
	}
	/**
	 * Methode qui dit si la valeur passee en parametre appartient a l'ensemble.
	 * On demande si a l'emplacement x dans le tableau tab on a un true.
	 * @param x int
	 * @return boolean vrai si la valeur du parametre appartient a l'ensemble et faux sinon
	 */
	public boolean contains (int x){
		if (tab[x])
			return true;
		return false;
	}
	/**
	 * Methode qui dit si l'ensemble est vide ou non.
	 * On parcours le tableau tab et si on trouve une valeur a true c'est que le tableau n'est pas vide.
	 * @return boolean vrai si vide et faux sinon
	 */
	public boolean isEmpty(){
		for (int i = 0; i < 256; i++){
			if (tab[i])
				return false;
		}
		return true;
	}
	/**
	 * Methode qui ajoute a l'ensemble l'element passe en parametre.
	 * On se place sur l'emplacement x passe en parametre et on change sa valeur a true.
	 * @return ne retourne rien
	 */
	public void add(int x){
		tab[x] = true;
	}
	/**
	 * Methode qui enleve a l'ensemble l'element passe en parametre.
	 * On se place sur l'emplacement x passe en parametre et on change sa valeur a false.
	 * @param x int
	 */
	public void remove(int x){
		tab[x] = false;
	}
	/**
	 * Methode qui ajoute a l'ensemble tous les elements compris dans l'intervalle donne.
	 * On parcours le tableau tab et on passe a true toutes les valeurs comprises dans l'intervalle passe en parametre.
	 * @param int deb, int fin
	 */
	public void addInterval(int deb, int fin){
		int i;
		for (i = deb; i <= fin; i++){
			tab[i] = true;
		}
	}
	/**
	 * Methode qui supprime dans l'ensemble tous les elements compris dans l'interval donne.
	 * On parcours le tableau tab et on passe a false toutes les valeurs comprises dans l'intervalle passe en parametre.
	 * @param deb int
	 * @param fin int
	 */
	public void removeInterval(int deb, int fin){
		int i;
		for (i = deb; i <= fin; i++){
			tab[i] = false;
		}
	}
	/**
	 * Methode qui fait l'union de deux ensembles.
	 * On parcours le tableau de l'ensemble passe en parametre et des qu'on obtient un true, on passe la valeur du tableau tab a true.
	 * @param f SmallSet
	 */
	public void union(SmallSet f){
		if (! f.isEmpty()){
			for (int i = 0; i < 256; i++){
				if (f.tab[i]){
					tab[i] = true;
				}
			}
		}
	}
	/**
	 * Methode qui fait l'intersection de deux ensembles.
	 * On parcours le tableau de l'ensemble passe en parametre et on verifie que si la valeur de ce tableau vaut false
	 * et que la valeur dans tab vaut true alors on passe la valeur de tab a false.
	 * @param f SmallSet
	 */
	public void intersection(SmallSet f){
		if (! f.isEmpty()){
			for (int i = 0; i < 256; i++){
				if (!f.tab[i])
					tab[i] = false;
			}
		}
	}
	/**
	 * Methode qui fait this \ f
	 * @param f SmallSet
	 */
	public void difference(SmallSet f){
		if(!f.isEmpty()){
			for (int i = 0; i < 256; i++){
				if(f.tab[i])
					tab[i] = false;
			}
		}
	}
	/**
	 * Methode qui utilise l'union, l'intersection et la difference sur deux ensembles.
	 * @param f SmallSet
	 */
	public void symmetricDifference(SmallSet f){
		SmallSet tmp = this.clone();
		union(f);
		tmp.intersection(f);
		difference(tmp);
	}
	/**
	 * Methode qui va construire le complement de l'ensemble.
	 * On parcours le tableau tab et on pour chaque emplacement, on change la valeur, si on a true on met false et inversement.
	 */
	public void complement(){
		for (int i = 0; i < 256; i++){
			if (tab[i])
				tab[i] = false;
			else
				tab[i] = true;
		}
	}
	/**
	 * Methode qui vide l'ensemble.
	 * On parcours le tableau tab et on passe toutes les valeurs a false.
	 */
	public void clear(){
		for (int i = 0; i < 256; i++){
			tab[i] = false;
		}
	}
	/**
	 * Methode qui dit si un ensemble f est inclu dans l'ensemble que l'on etudie.
	 * On parcours les deux tableaux, si une valeur du tableau de f est a true
	 * et qu'a l'emplacement correspondant dans le tableau tab on a false, alors on retourne faux.
	 * @param f SmallSet
	 * @return boolean vrai si f est inclu dans l'ensemble et faux s'il n'est pas inclu
	 */
	public boolean isIncludedIn(SmallSet f){
		for (int i = 0; i < 256; i++){
			if (tab[i] && !f.tab[i])
				return false;
		}
		return true;
	}
	/**
	 * Redefinition de la methode qui determine si deux ensembles sont egaux.
	 * On verifie que l'objet passe en parametre est bien du type SmallSet.
	 * On parcours les tableaux des deux ensembles et on compare les valeurs.
	 * @param o SmallSet
	 * @return retourne vrai si les deux ensemles sont egaux et faux sinon
	 */
	@Override public boolean equals(Object o){
		if ( o instanceof SmallSet){
			SmallSet obj = (SmallSet)o;
			for (int i = 0; i < 256; i++){
				if (tab[i] != obj.tab[i])
					return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * Redefinition de la methode qui duplique un ensemble.
	 * @return newSmallSet(tab)
	 */
	@Override public SmallSet clone(){
		return new SmallSet(tab);
	}
	/**
	 * Redefinition de la methode qui affiche un objet.
	 * @return String s avec s representant l'ensemble des valeurs contenu dans l'ensemble
	 */
	@Override public String toString(){
		if(isEmpty())
			return "Le set est vide.";
		else {
			String s = "éléments présents : ";
			for (int i = 0; i < 256; i++){
				if(tab[i])
					s = s + i +" ";
			}
			return s;
		}
	}
}
	