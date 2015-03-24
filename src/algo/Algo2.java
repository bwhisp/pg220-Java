package algo;

import java.util.ArrayList;

import classes.Commande;
import classes.Decoupe;
import classes.Fournisseurs;
import exceptions.DimException;

public class Algo2 extends Algo {

	public Algo2() {
		super();
	}

	/*****************************************************************************/
	/** ******************* Methode principale de découpe ********************* **/
	/*****************************************************************************/

	public ArrayList<Decoupe> decouper(ArrayList<Commande> commande,
			Fournisseurs fourni) {

		super.init(fourni);

		int x_max = fourni.getLa();
		int y_max = fourni.getLo();
		int next_y;
		ArrayList<Decoupe> decoupes = new ArrayList<Decoupe>();

		makeRejects(commande, fourni);

		super.nextPlanche(fourni);

		while (true) {
			next_y = parcLa(commande, decoupes, x_max, y_max);
			if (next_y != 0) {
				nextLine(next_y);
			} else if (super.getY() != 0 && commande.size() > 0) {
				super.nextPlanche(fourni);
			} else {
				break;
			}
		}
		super.w.endElement();
		super.finalise();

		return decoupes;
	}

	/*****************************************************************************/
	/** ****** Methodes auxiliaires appelées dans la methode principale ******* **/
	/*****************************************************************************/

	// Parcourir la planche en largeur et placer des découpes
	public int parcLa(ArrayList<Commande> commande,
			ArrayList<Decoupe> decoupes, int x_max, int y_max) {

		int k = 0;
		int lastLo = y_max; // pour s'assurer que la première plache rentre
		int next_y = super.getY();
		Decoupe dec = new Decoupe();
		Commande cur;
		try {
			cur = new Commande();
		} catch (DimException e1) {
		}

		super.setX(0);

		while (k < commande.size()) {
			cur = commande.get(k);

			boolean condY = cur.getLo() < y_max - super.getY();
			boolean condX = cur.getLa() < x_max - super.getX();
			boolean condLo = cur.getLo() <= lastLo;

			if (condY && condX && condLo) {
				dec = new Decoupe();
				dec.setX(super.getX());
				dec.setY(super.getY());
				dec.setId(cur.getId());
				decoupes.add(dec);

				try {
					super.w.XmlDecoupe(dec);
				} catch (Exception e) {
					System.out.println("Erreur du parseur xml:"
							+ e.getMessage());
				}
				try {
					super.ws.SvgDecoupe(dec, cur, super.getNum_planche(), y_max);
				} catch (Exception e) {
					System.out.println("Erreur du parseur svg:"
							+ e.getMessage());
				}

				lastLo = cur.getLo();
				if (next_y == super.getY()) {
					next_y = cur.getLo() + next_y;
				}
				/* Mise à jour de la position des decoupes en largeur */
				super.setX(cur.getLa() + super.getX());
				commande.remove(k);

			} else {
				k++;
			}
		}
		return (next_y != super.getY()) ? next_y : 0;
	}

	// Changer de ligne
	public void nextLine(int next_y) {
		super.setY(next_y);
		super.setX(0);
	}

	// Construire la liste des rejets et les inscrire dans le fichier XML
	public void makeRejects(ArrayList<Commande> commandes, Fournisseurs fourni) {
		int k = 0;
		/* 1ère étape : on vire les commandes rejetées de la liste des commandes */
		/* et on remplit la liste des rejets (seul l'id commande est utile) */
		while (k < commandes.size()) {
			if (commandes.get(k).getLa() > fourni.getLa()
					|| commandes.get(k).getLo() > fourni.getLo()) {

				try {
					super.rejet.add(new Commande(0, 0,
							commandes.get(k).getId(), 0));
				} catch (DimException e) {
				} finally {
					commandes.remove(k);
				}

			} else {
				k++;
			}
		}
		/* 2ème étape : on écrit les rejets dans le fichier Xml */
		try {
			super.w.XmlRejets(super.rejet);
		} catch (Exception e) {
		}
	}

}