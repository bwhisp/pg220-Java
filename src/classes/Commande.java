package classes;

import exceptions.DimException;

public class Commande extends Dimension {

	private int id;
	private int qte; // quantité

	public Commande(int la, int lo, int id, int qte) throws DimException {
			super(la, lo);
			this.id = id;
			this.qte = qte;
	}

	public Commande() throws DimException {
		super(0, 0);
		id = 0;
		qte = 0;
	}

	public int getId() {
		return id;
	}

	public int getQte() {
		return qte;
	}

	public int compareLo(Commande other) {
		if (super.getLo() > other.getLo()) {
			return 1;
		}
		if (super.getLo() < other.getLo()) {
			return -1;
		}
		if (super.getLo() == other.getLo()) {
			if (super.getLa() > other.getLa()) {
				return 1;
			}
			if (super.getLa() < other.getLa()) {
				return -1;
			} else
				return 0;
		}

		return 0;
	}

	public int compareLa(Commande other) {
		if (super.getLa() > other.getLa()) {
			return 1;
		}
		if (super.getLa() < other.getLa()) {
			return -1;
		}
		if (super.getLa() == other.getLa()) {
			if (super.getLo() > other.getLo()) {
				return 1;
			}
			if (super.getLo() < other.getLo()) {
				return -1;
			} else
				return 0;
		}

		return 0;
	}
}
