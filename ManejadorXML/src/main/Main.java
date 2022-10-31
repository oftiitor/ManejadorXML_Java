package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.SAXException;

import listaCanciones.Cancion;
import listaCanciones.Lista;
import manejador.XMLHandler;

public class Main {

	private static ArrayList<Lista> listasRepro = new ArrayList<Lista>();
	private static Lista listaCanciones = new Lista();

	public static void main(String[] args) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		XMLHandler handler;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xsw;
		boolean salir = true;
		int opcion = 0;

		do {
			
			System.out.println("\nSeleccione una de las siguientes opciones:");
			System.out.println("1. Leer archivo.");
			System.out.println("2. Introduce una nueva lista de canciones.");
			System.out.println("3. Exportar archivo.");
			System.out.println("4. Salir.\n");

			do {

				try {
					opcion = Integer.parseInt(br.readLine());
					salir = false;
				} catch (IOException e1) {
					System.out.println("Introduzca un valor numérico.");
					salir = true;
				}

			} while (salir == true);

			switch (opcion) {
			case 1:

				System.out.println("Introduce el nombre del archivo que desea leer:");

				try {
					parser = factory.newSAXParser();
					handler = new XMLHandler(listasRepro);

					do {

						String nombreArchivo = br.readLine();

						if (nombreArchivo.equalsIgnoreCase("listas.xml")) {
							parser.parse(new File(nombreArchivo), handler);
							salir = false;
						} else {
							System.out.println("El archivo indicado no existe. Vuelva a intentarlo.");
							salir = true;
						}

					} while (salir == true);

					System.out.println(listasRepro);
					System.out.println("========================================");

				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				}

				break;

			case 2:

				System.out.println("\n\n¿Cuantas listas desea crear?");
				int numListas = 0;

				try {
					numListas = Integer.parseInt(br.readLine());
				} catch (NumberFormatException | IOException e) {
					System.out.println("Introduzca un valor numérico.");
					e.printStackTrace();
				}

				for (int i = 0; i < numListas; i++) {

					listaCanciones = new Lista();

					try {
						System.out.println("Inserte el nombre de la nueva lista:");
						listaCanciones.setNombre(br.readLine());

						System.out.println("Inserte el numero de canciones de la nueva lista:");
						int numeroCanciones = Integer.parseInt(br.readLine());
						listaCanciones.setNumeroCanciones(numeroCanciones);

						listasRepro.add(listaCanciones);

						for (int j = 0; j < numeroCanciones; j++) {

							Cancion cancion = new Cancion();

							System.out.println("Inserte el titulo de la cancion:");
							cancion.setTitulo(br.readLine());

							System.out.println("Inserte el artista de la cancion:");
							cancion.setArtista(br.readLine());

							System.out.println("Inserte la duracion en segundos de la cancion:");
							cancion.setDuracion(Integer.parseInt(br.readLine()));

							listaCanciones.getCancion().add(cancion);

						}

						System.out.println(listasRepro);
						System.out.println("==========================================");

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}

				break;

			case 3:
				
				try {
					
					do {
						
						System.out.println("Introduce el nombre del archivo al que desea exportar los datos introducidos:");
						String nuevoXML = br.readLine();
					
						if (nuevoXML.matches("[A-Za-z0-9]+\\.xml")) {
							
							xsw = xof.createXMLStreamWriter(new FileWriter(nuevoXML));
							
							// Inicio del documento con prólogo (sin salto de línea)
							xsw.writeStartDocument();
							
							// Salto de línea entre el prólogo y el elemento raíz <listas>
							xsw.writeCharacters("\n");
							
							// Etiqueta de inicio de <listas>
							xsw.writeStartElement("listas");
							
							for (Lista l : listasRepro) {
								
								// Salto de línea y tabulación para mantener la jerarquía del árbol
								xsw.writeCharacters("\n\t");
								
								// Etiqueta de inicio de <lista>
								xsw.writeStartElement("lista");
								
								// Nombre
								xsw.writeCharacters("\n\t\t");
								xsw.writeStartElement("nombre");
								xsw.writeCharacters(l.getNombre());
								xsw.writeEndElement();
								
								// Numero Canciones
								xsw.writeCharacters("\n\t\t");
								xsw.writeStartElement("numeroCanciones");
								xsw.writeCharacters("" + l.getNumeroCanciones());
								xsw.writeEndElement();
								
								xsw.writeCharacters("\n\t\t");
								xsw.writeStartElement("canciones");
								
								for (Cancion c : l.getCancion()) {
									
									// Salto de línea y tabulación para mantener la jerarquía del árbol
									xsw.writeCharacters("\n\t\t\t");
									
									// Etiqueta de inicio de <usuario>
									xsw.writeStartElement("cancion");
									
									// Titulo
									xsw.writeCharacters("\n\t\t\t\t");
									xsw.writeStartElement("titulo");
									xsw.writeCharacters(c.getTitulo());
									xsw.writeEndElement();
									
									// Artista
									xsw.writeCharacters("\n\t\t\t\t");
									xsw.writeStartElement("titulo");
									xsw.writeCharacters(c.getArtista());
									xsw.writeEndElement();
									
									// Duracion
									xsw.writeCharacters("\n\t\t\t\t");
									xsw.writeStartElement("titulo");
									xsw.writeCharacters("" + c.getDuracion());
									xsw.writeEndElement();
									
									// Cierre de <cancion> con salto de línea y tabulación previa 
									xsw.writeCharacters("\n\t\t\t");
									xsw.writeEndElement();
									
								}
								
								// Cierre de <canciones>
								xsw.writeCharacters("\n\t\t");
								xsw.writeEndElement();
								
								// Cierre de <lista> con salto de línea y tabulación previa 
								xsw.writeCharacters("\n\t");
								xsw.writeEndElement();
								
							}
							
							xsw.writeCharacters("\n");
							
							// Cierre de <listas>
							xsw.writeEndElement();
	
							// Cierre del documento
							xsw.writeEndDocument();
							
							// Le obligamos a que escriba la información almacenada en el documento y que no lo siga guardando en la "memoria"
							xsw.flush();
							
							xsw.close();
							
							System.out.println("\nDocumento exportado correctamente.\nEl nuevo nombre adoptado es " + nuevoXML);
							
							salir = true;
							
						} else {
							
							System.out.println("\nEl archivo introducido debe cumplir el siguiente patrón:\n*****.xml\n");
							
							salir = false;
							
						}
					
					} while (salir == false);
					
				} catch (XMLStreamException | IOException e) {
					System.out.println("No fue posible exportar los datos a un nuevo documento XML.");
					e.printStackTrace();
				}
				
				break;

			case 4:
				System.out.println("Gracias por utilizar nuestros servicios.");
				break;

			default:
				System.out.println("Seleccione una opción válida.");
				break;
			}

		} while (opcion != 4);

	}

}