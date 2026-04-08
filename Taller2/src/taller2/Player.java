package taller2;

import java.util.ArrayList;

public class Player {
	private String nombre;
	private ArrayList<String> medallas;
	private ArrayList<Pokemon> PokemonAtrapados;

	public Player(String nombre) {
		super();
		this.nombre = nombre;
		medallas = new ArrayList<String>();
		PokemonAtrapados = new ArrayList<Pokemon>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Pokemon> getPokemonAtrapados() {
		return PokemonAtrapados;
	}

	public void añadirMedalla(String s) {
		medallas.add(s);
	}

	public void atraparPokemon(Pokemon p) {
		PokemonAtrapados.add(p);
	}

	public String guardar() {
		String guardarPj = nombre;
		for (int i = 0; i < medallas.size(); i++) {
			guardarPj += ";" + medallas.get(i);
		}
		return guardarPj;
	}

}
