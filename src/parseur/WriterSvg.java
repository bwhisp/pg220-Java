package parseur;

import java.io.File;
import java.io.FileWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import algo.Algo;
import algo.Algo1;
import algo.Algo2;
import classes.Commande;
import classes.Decoupe;
import classes.Fournisseurs;

public class WriterSvg {

	XMLStreamWriter writer;

	public WriterSvg() {
		super();
	}

	public void SvgInit(Fournisseurs fourni, Algo algo) throws Exception {
		String file = "";
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		/* On construit le nom du fichier... */
		if (algo instanceof Algo1) {
			file = "./data/results.f".concat(Integer.toString(fourni.getId()))
					.concat("m1.svg");
		}
		if (algo instanceof Algo2) {
			file = "./data/results.f".concat(Integer.toString(fourni.getId()))
					.concat("m2.svg");
		}

		/* ...et on crée le curseur qui ous servira à écrire */
		FileWriter output = new FileWriter(new File(file));
		writer = outputFactory.createXMLStreamWriter(output);
		writer.writeStartDocument();
		writer.writeCharacters("\n");

		writer.writeStartElement("svg");
	}

	public void SvgPlanche(Fournisseurs fourni, int num) throws Exception {
		int y;

		/* Rectangle de fond, qui dessine des marges blanches de 10px */
		writer.writeStartElement("rect");
		writer.writeAttribute("x", "0");
		y = (num - 1) * (fourni.getLo() + 20);
		writer.writeAttribute("y", Integer.toString(y));
		writer.writeAttribute("width", Integer.toString(20 + fourni.getLa()));
		writer.writeAttribute("height", Double.toString(20 + fourni.getLo()));
		writer.writeAttribute("style", "fill:WHITE;");
		writer.writeEndElement();
		writer.writeCharacters("\n");

		/* Rectangle représentant la planche */
		writer.writeStartElement("rect");
		writer.writeAttribute("x", "10");
		y = 10 + (num - 1) * (fourni.getLo() + 20);
		writer.writeAttribute("y", Integer.toString(y));
		writer.writeAttribute("width", Integer.toString(fourni.getLa()));
		writer.writeAttribute("height", Double.toString(fourni.getLo()));
		writer.writeAttribute("style", "fill:YELLOW;");
		writer.writeEndElement();
		writer.writeCharacters("\n");
	}

	public void SvgDecoupe(Decoupe dec, Commande com, int numP, int loP)
			throws Exception {
		// On calcule les coordonnées où va être placée la découpe et le numéro
		int x = 10 + dec.getX();
		int y = 10 + (numP - 1) * (loP + 20) + dec.getY();
		int xText = x + (com.getLa() / 2);
		int yText = y + (com.getLo() / 2);

		// On place la découpe sur l'image...
		writer.writeStartElement("rect");
		writer.writeAttribute("x", Integer.toString(x));
		writer.writeAttribute("y", Integer.toString(y));
		writer.writeAttribute("width", Integer.toString(com.getLa()));
		writer.writeAttribute("height", Double.toString(com.getLo()));
		writer.writeAttribute("style", "fill:GREEN;");
		writer.writeEndElement();
		writer.writeCharacters("\n");
		
		// ... et son numéro au milieu de celle ci
		writer.writeStartElement("text");
		writer.writeAttribute("x", Integer.toString(xText));
		writer.writeAttribute("y", Integer.toString(yText));
		writer.writeAttribute("style","fill:WHITE;font-size:4px");
		writer.writeCharacters(Integer.toString(com.getId()));
		writer.writeEndElement();
		writer.writeCharacters("\n");
	}

	public void SvgFinal() throws Exception {
		writer.writeEndElement();
		writer.writeCharacters("\n");

		writer.flush();
		writer.close();
	}
}