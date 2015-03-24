package classes;

import exceptions.DimException;

public class Dimension {

	protected int la; // largeur
	protected int lo; // longeur

	public Dimension(int la, int lo) throws DimException {
		if (lo < la) {
			throw new DimException();
		} else {
			this.la = la;
			this.lo = lo;
		}
	}

	public int getLa() {
		return la;
	}

	public int getLo() {
		return lo;
	}

}
