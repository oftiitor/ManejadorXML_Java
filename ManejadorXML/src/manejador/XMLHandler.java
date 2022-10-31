package manejador;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import listaCanciones.Cancion;
import listaCanciones.Lista;

public class XMLHandler extends DefaultHandler {

	private ArrayList<Lista> listas;
	private Lista listaCanciones;
	private String texto;
	private Cancion cancion;

	public XMLHandler(ArrayList<Lista> listas) {
		this.listas = listas;
	}

	@Override
	public void characters(char[] ch, int start, int length) {

		texto = new String(ch, start, length);

	}

	@Override
	public void startElement(String nameSpaceUri, String localName, String qName, Attributes atts) {

		if (qName.equalsIgnoreCase("lista")) {
			listaCanciones = new Lista();
		}

		if (qName.equalsIgnoreCase("cancion")) {
			cancion = new Cancion();
		}

	}

	@Override
	public void endElement(String nameSpaceUri, String localName, String qName) {

		if (qName.equalsIgnoreCase("nombre")) {
			listaCanciones.setNombre(texto);
		} else if (qName.equalsIgnoreCase("numeroCanciones")) {
			listaCanciones.setNumeroCanciones(Integer.parseInt(texto));
		} else if (qName.equalsIgnoreCase("cancion")) {
			listaCanciones.getCancion().add(cancion);
		} else if (qName.equalsIgnoreCase("titulo")) {
			cancion.setTitulo(texto);
		} else if (qName.equalsIgnoreCase("artista")) {
			cancion.setArtista(texto);
		} else if (qName.equalsIgnoreCase("duracion")) {
			cancion.setDuracion(Integer.parseInt(texto));
		} else if (qName.equalsIgnoreCase("lista")) {
			listas.add(listaCanciones);
		}

	}

}