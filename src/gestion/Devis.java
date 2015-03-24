package gestion;

import java.util.*;

import classes.*;
import parseur.*;

public class Devis {

	public static void main(String[] args) {

		Reader r = new Reader();
		ArrayList<Commande> c = new ArrayList<Commande>();
		ArrayList<Fournisseurs> f = new ArrayList<Fournisseurs>();

		try {
			c = r.readCommande(args[0]);
		} catch (Exception e) {

		}

		try {
			f = r.readFourni(args[1]);
		} catch (Exception e) {

		}

		new Simulation(f, c);
	
	}
	
}
