package algo;

import java.util.ArrayList;

import parseur.*;
import classes.*;

public abstract class Algo {

	private double price;
	private int reject;
	private int x, y;
	private int num_planche;

	ArrayList<Commande> rejet = new ArrayList<Commande>();

	protected Writer w = new Writer();
	protected WriterSvg ws = new WriterSvg();

	public Algo() {
		price = 0;
		reject = 0;
		x = 0;
		y = 0;
		num_planche = 0;
	}

	/* Getters */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getPrice() {
		return price;
	}

	public int getReject() {
		return reject;
	}

	public int getNum_planche() {
		return num_planche;
	}

	/* Setters */
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void priceUp(double prix) {
		this.price = this.price + prix;
	}

	public void rejectUp(Commande commande) {
		rejet.add(commande);
		this.reject++;
	}

	public void plancheUp() {
		this.num_planche++;
	}

	/*****************************************************************************/
	/** ********** Opérations éffectuées sur les fichiers XML et SVG ********** **/
	/*****************************************************************************/
	public void nextPlanche(Fournisseurs fourni) {
		priceUp(fourni.getPrix());
		plancheUp();
		setY(0);
		setX(0);

		try {
			w.XmlPlanche(getNum_planche());
		} catch (Exception e) {
			System.out.println("Erreur du parseur xml :" + e.getMessage());
		}
		try {
			ws.SvgPlanche(fourni, getNum_planche());
		} catch (Exception e) {
			System.out.println("Erreur du parseur svg :" + e.getMessage());
		}

	}

	public void init(Fournisseurs fourni) {
		try {
			w.XmlInit(fourni, this);
		} catch (Exception e) {
			System.out.println("Erreur du parseur xml :" + e.getMessage());
		}
		try {
			ws.SvgInit(fourni, this);
		} catch (Exception e) {
			System.out.println("Erreur du parseur svg :" + e.getMessage());
		}
	}

	public void finalise() {
		try {
			w.XmlFinal();
		} catch (Exception e) {
			System.out.println("Erreur du parseur xml :" + e.getMessage());
		}
		try {
			ws.SvgFinal();
		} catch (Exception e) {
			System.out.println("Erreur du parseur svg :" + e.getMessage());
		}
	}

}
