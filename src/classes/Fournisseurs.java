package classes;

import exceptions.DimException;

public class Fournisseurs extends Dimension {

	private int id;
	private double prix;
	
	public Fournisseurs(int la, int lo, int id, double prix) throws DimException {
		super(la, lo);
		this.id = id;
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public double getPrix() {
		return prix;
	}
	
}