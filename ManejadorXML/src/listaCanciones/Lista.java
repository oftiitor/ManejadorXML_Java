package listaCanciones;

import java.util.ArrayList;

public class Lista {

	private String nombre;
	private int numeroCanciones;
	private ArrayList<Cancion> cancion;

	public Lista() {
		cancion = new ArrayList<Cancion>();
	}

	public Lista(String nombre, int numeroCanciones, ArrayList<Cancion> cancion) {
		super();
		this.nombre = nombre;
		this.numeroCanciones = numeroCanciones;
		this.cancion = cancion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroCanciones() {
		return numeroCanciones;
	}

	public void setNumeroCanciones(int numeroCanciones) {
		this.numeroCanciones = numeroCanciones;
	}

	public ArrayList<Cancion> getCancion() {
		return cancion;
	}

	public void setCancion(ArrayList<Cancion> cancion) {
		this.cancion = cancion;
	}

	@Override
	public String toString() {
		return "Lista:\n - Nombre de la lista: " + nombre + ".\n - Numero de canciones: " + numeroCanciones
				+ ".\n - Canciones: " + cancion + ".";
	}

}