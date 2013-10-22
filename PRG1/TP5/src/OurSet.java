
/**Classe OurSet
 * 
 * @author Hector Labanca, Augustin Bardou
 *
 */
public class OurSet extends List<SubSet> {
	
	private static final int MAX_RANG = 128;
	private static final SubSet FLAG_VALUE = new SubSet(MAX_RANG, new SmallSet());
	public OurSet(){
		super();
		setFlag(FLAG_VALUE);
	}
	
	/**Vérifie que notre set contient un nombre compris entre
	 * 0 et la valeur max.
	 * @param nb nombre recherché.
	 * @param it itérateur de notre set.
	 * @return true si nb trouvé, sinon false.
	 */
	public boolean containment(int nb, Iterator<SubSet> it) {
		//vérifions que nb se trouve dans l'intervalle de valeurs
		if(nb < 0 || nb > (MAX_RANG-1)*256)	return false;
		//parcours des subset
		it.restart();
		while(!it.isOnFlag() && it.getValue().rank != nb/256) {
			//recherche du subset pouvant contenir nb.
			it.goForward();
		}
		if(it.isOnFlag())
			return false;
		//Si le subset de rang adéquat est présent, test s'il contient nb.
		return it.getValue().set.contains(nb%256);
	}
	
	/**Retire un nombre de notre ensemble.
	 * 
	 * @param nb nombre à retirer
	 * @return true si le nombre a été retiré, false s'il n'était pas présent.
	 */
	public boolean remove(int nb) {
		//1.Recherche du nombre
		Iterator<SubSet> it = newIterator();
		if(containment(nb, it))	{
			//2.Si trouvé, suppression dans le subset concerné.
			it.getValue().set.remove(nb%256);
			return true;
		}
		return false;
	}
	
	/**Effectuer l'intersection entre this et En2, stocker le résultat dans this.
	 * 
	 * @param En2 ensemble avec lequel effectuer l'intersection.
	 * @return this.
	 */
	public OurSet intersection(OurSet En2) {
		Iterator<SubSet> it1 = newIterator();
		Iterator<SubSet> it2 = En2.newIterator();
		//Parcours de this et En2
		do {
			//Si l'itérateur de En2 est "devant" celui de En1
			if(it2.isOnFlag() || it1.getValue().rank < it2.getValue().rank)
				it1.remove();
			else {
				//Si this et En2 ont des subsets de même rang, on effectue leur intersection
				if(it1.getValue().rank == it2.getValue().rank) {
					it1.getValue().set.intersection(it2.getValue().set);
					it1.goForward();
					it2.goForward();
				}
				//Si it1 est "devant" it2
				else
					it2.goForward();
			}
		} while(!it1.isOnFlag());
		return this;
	}
	
	

}
