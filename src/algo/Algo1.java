package algo;

import java.util.*;
import classes.*;

public class Algo1 extends Algo {

	public Algo1() {
		super();
	}

	public ArrayList<Decoupe> decouper(ArrayList<Commande> commande,
			Fournisseurs fourni) {

		super.init(fourni);

		int x_max = fourni.getLa();
		int y_max = fourni.getLo();
		int k = 0;
		Decoupe dec = new Decoupe();
		ArrayList<Decoupe> decoupes = new ArrayList<Decoupe>();

		super.setX(0);
		super.setY(0);

		super.nextPlanche(fourni);

		while (k < commande.size()) {
			/*	La découpe peut être placée. On la place.	*/
			if (commande.get(k).getLa() < x_max
					&& super.getY() + commande.get(k).getLo() < y_max) {
				dec = new Decoupe();
				dec.setX(super.getX());
				dec.setY(super.getY());
				dec.setId(commande.get(k).getId());
				decoupes.add(dec);
				super.setY(super.getY() + commande.get(k).getLo());

				try {
					super.w.XmlDecoupe(dec);
				} catch (Exception e) {
					System.out.println("Erreur du parseur xml :" + e.getMessage());
				}
				try {
					super.ws.SvgDecoupe(dec, commande.get(k),
							super.getNum_planche(), y_max);
				} catch (Exception e) {
					System.out.println("Erreur du parseur svg :" + e.getMessage());
				}
				k++;
			/*	Rejet 	*/
			} else if (commande.get(k).getLa() > x_max
					|| commande.get(k).getLo() > y_max) {
				super.rejectUp(commande.get(k));
				k++;
			/*	Changement de planche	*/
			} else {
				super.nextPlanche(fourni);
			}
		}
		/*	On place les rejets dans le Xml sans oublier de fermer la balise de planche*/
		super.w.endElement();
		try {
			super.w.XmlRejets(super.rejet);
		} catch (Exception e) {
		}
		
		super.finalise();

		return decoupes;
	}

}
