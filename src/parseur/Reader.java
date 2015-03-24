package parseur;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import classes.Commande;
import classes.Fournisseurs;

public class Reader {
	public Reader() {
		super();

	}

	/*****************************************************************************/
	/** ************ Créér la liste des commandes à partir du xml ************* **/
	/*****************************************************************************/
	
	public ArrayList<Commande> readCommande(String fichier) throws Exception {
		File file = new File(fichier);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(
				file));
		ArrayList<Commande> listCom = new ArrayList<Commande>();

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamReader.START_ELEMENT:
				if (reader.getLocalName().equals("commande")) {
					int id = Integer.parseInt(reader.getAttributeValue(null,
							"id"));
					int largeur = Integer.parseInt(reader.getAttributeValue(
							null, "largeur"));
					int longueur = Integer.parseInt(reader.getAttributeValue(
							null, "longueur"));
					int quantite = Integer.parseInt(reader.getAttributeValue(
							null, "quantite"));
					listCom.add(new Commande(largeur, longueur, id, quantite));
				}
				break;
			default:
			}
		}
		return listCom;
	}

	/*****************************************************************************/
	/** *********** Créér la liste des fournisseurs à partir du xml *********** **/
	/*****************************************************************************/
	
	public ArrayList<Fournisseurs> readFourni(String fichier) throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(
				fichier));
		ArrayList<Fournisseurs> listFourni = new ArrayList<Fournisseurs>();
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamReader.START_ELEMENT:
				if (reader.getLocalName().equals("fournisseur")) {
					int id = Integer.parseInt(reader.getAttributeValue(null,
							"id"));
					int largeur = Integer.parseInt(reader.getAttributeValue(
							null, "largeur"));
					int longueur = Integer.parseInt(reader.getAttributeValue(
							null, "longueur"));
					double prix = Double.parseDouble(reader.getAttributeValue(
							null, "prix"));
					listFourni
							.add(new Fournisseurs(largeur, longueur, id, prix));
				}
				break;
			default:
			}
		}
		return listFourni;
	}

}
