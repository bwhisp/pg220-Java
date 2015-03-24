package classes;

import algo.*;

import java.util.*;

import exceptions.DimException;

public class Simulation extends Algo {

	ArrayList<Decoupe> decoupe = new ArrayList<Decoupe>();

	public Simulation() {
	}

	public Simulation(ArrayList<Fournisseurs> fourni,
			ArrayList<Commande> commande) {
		super();
		Fournisseurs fournisseur;
		ArrayList<Commande> temp;
		int k = 0;

		dupCom(commande);

		while (k < fourni.size()) {
			fournisseur = fourni.get(k);
			temp = dupList(commande);

			triLo(temp);
			Algo1 algo1 = new Algo1();
			decoupe = algo1.decouper(temp, fournisseur);

			triLa(temp);
			fournisseur = fourni.get(k);
			Algo2 algo2 = new Algo2();
			decoupe = algo2.decouper(temp, fournisseur);

			k++;
		}

	}

	/*****************************************************************************/
	/** ****** Méthodes servant à mettre en forme la liste des commandes ****** **/
	/** ************* avant execution des algorithmes implémentés ************* **/
	/*****************************************************************************/

	/* 	Tri dans l'ordre decroissant des longueurs (algo1) 	*/
	public void triLo(ArrayList<Commande> commande) {

		int ind_max = 0;
		Commande temp;

		for (int i = 0; i < commande.size() - 1; i++) {
			ind_max = i;
			for (int j = i + 1; j < commande.size(); j++) {
				if (commande.get(j).compareLo(commande.get(ind_max)) == 1) {
					ind_max = j;
				}
			}
			if (i != ind_max) {
				temp = commande.get(i);
				commande.remove(i);
				commande.add(i, commande.get(ind_max - 1));
				commande.remove(ind_max);
				commande.add(ind_max, temp);
			}
		}
	}

	/* 	Tri par largeurs decroissantes (algo2)	 */
	public void triLa(ArrayList<Commande> commande) {
		int ind_max;
		Commande temp;

		for (int i = 0; i < commande.size() - 1; i++) {
			ind_max = i;
			for (int j = i + 1; j < commande.size(); j++) {
				if (commande.get(j).compareLa(commande.get(ind_max)) == 1) {
					ind_max = j;
				}
			}
			if (i != ind_max) {
				temp = commande.get(i);
				commande.remove(i);
				commande.add(i, commande.get(ind_max - 1));
				commande.remove(ind_max);
				commande.add(ind_max, temp);
			}
		}
	}

	/*	On duplique les commandes avec une quantité supérieure à 1	*/
	public void dupCom(ArrayList<Commande> commande) {
		int pos = 0;
		int k;
		Commande cur;

		while (pos < commande.size()) {
			cur = commande.get(pos);
			for (k = 1; k < cur.getQte(); k++) {
				try {
					commande.add(0,
							new Commande(cur.getLa(), cur.getLo(), cur.getId(),
									cur.getQte()));
				} catch (DimException e) {
				} finally {
					pos++;
				}
			}
			pos++;
		}
	}

	/*	On crée une liste temporaire qui sera utilisée pour les algos et réinitialisée	*/
	/*	à chaque changement de fournisseur car algo2 détruit la liste des commandes	*/
	public ArrayList<Commande> dupList(ArrayList<Commande> commande) {
		int k;
		ArrayList<Commande> newList = new ArrayList<Commande>();
		Commande cur;

		for (k = 0; k < commande.size(); k++) {
			cur = commande.get(k);
			try {
				newList.add(new Commande(cur.getLa(), cur.getLo(), cur.getId(),
						cur.getQte()));
			} catch (DimException e) {
			}
		}

		return newList;
	}

}
