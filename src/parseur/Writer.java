package parseur;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import algo.*;
import classes.*;

public class Writer {

	XMLStreamWriter writer;

	public Writer() {
		super();
	}

	public void XmlInit(Fournisseurs fourni, Algo algo) throws Exception {
		String file = "";
		int algorithme = 0;
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		/* On construit le nom du fichier... */
		if (algo instanceof Algo1) {
			file = "./data/results.f".concat(Integer.toString(fourni.getId()))
					.concat("m1.xml");
			algorithme = 1;
		}
		if (algo instanceof Algo2) {
			file = "./data/results.f".concat(Integer.toString(fourni.getId()))
					.concat("m2.xml");
			algorithme = 2;
		}

		/* ...et on crée le curseur qui ous servira à écrire */
		FileWriter output = new FileWriter(new File(file));
		writer = outputFactory.createXMLStreamWriter(output);
		writer.writeStartDocument();
		writer.writeCharacters("\n\n");

		/* enfin, on entre les premières informations sur le Xml */
		writer.writeStartElement("simulation");
		writer.writeAttribute("fournisseur", Integer.toString(fourni.getId()));
		writer.writeAttribute("prix", Double.toString(fourni.getPrix()));
		writer.writeAttribute("rejets", Integer.toString(algo.getReject()));
		writer.writeAttribute("algorithme", Integer.toString(algorithme));
		writer.writeCharacters("\n");
	}

	public void XmlPlanche(int num) throws Exception {
		if (num > 1) { // Ferme la dernière balise planche si ce n'est pas la première qu'on place
			writer.writeCharacters("\t\t");
			writer.writeEndElement();
		} else { // Ouvre la balise planches si c'est la première planche
			writer.writeCharacters("\n");
			writer.writeCharacters("\t");
			writer.writeStartElement("planches");
		}

		writer.writeCharacters("\n");
		writer.writeCharacters("\t\t");
		writer.writeStartElement("planche");
		writer.writeAttribute("numéro de planche", Integer.toString(num));
		writer.writeCharacters("\n");
	}

	public void XmlDecoupe(Decoupe decoupe) throws Exception {
		writer.writeCharacters("\t\t\t");
		writer.writeEmptyElement("decoupe");
		writer.writeAttribute("commande", Integer.toString(decoupe.getId()));
		writer.writeAttribute("x", Integer.toString(decoupe.getX()));
		writer.writeAttribute("y", Integer.toString(decoupe.getY()));
		writer.writeCharacters("\n");
	}

	public void XmlRejets(ArrayList<Commande> rejets) throws Exception {
		Commande cur = new Commande();
		int k;
		/* On ouvre la balise rejets... */
		writer.writeCharacters("\n");
		writer.writeCharacters("\t");
		writer.writeStartElement("rejets");
		writer.writeCharacters("\n");

		/* ...et on remplit avec les commandes rejetées */
		for (k = 0; k < rejets.size(); k++) {
			cur = rejets.get(k);
			writer.writeCharacters("\t\t");
			writer.writeEmptyElement("rejet");
			writer.writeAttribute("commande", Integer.toString(cur.getId()));
			writer.writeCharacters("\n");
		}

		writer.writeCharacters("\t");
		writer.writeEndElement();
		writer.writeCharacters("\n");
	}

	/*	Pour fermer la dernière balise planche et la balise planches	*/
	public void endElement() {
		try {
			writer.writeCharacters("\t\t");
			writer.writeEndElement();
			writer.writeCharacters("\n");
			writer.writeCharacters("\t");
			writer.writeEndElement();
			writer.writeCharacters("\n");
		} catch (Exception e) {
		}
	}

	public void XmlFinal() throws Exception {
		/*	On ferme la dernière balise encore ouverte (simulation)	*/
		writer.writeCharacters("\n");
		writer.writeEndElement();
		writer.writeCharacters("\n");

		/*	On ferme le fichier proprement	*/
		writer.flush();
		writer.close();
	}

}
