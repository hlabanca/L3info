
public class SubSet implements SuperT {
	
	public int rank;
	public SmallSet set;

	public SubSet(int rank, SmallSet smallSet) {
		this.rank = rank;
		set = smallSet;
	}

	@Override
	public SuperT copy() {
		return new SubSet(rank, set.clone());
	}

}
