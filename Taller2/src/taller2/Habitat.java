package taller2;

import java.util.ArrayList;
import java.util.Random;

public class Habitat {
	private String nombre;
	private ArrayList<Pokemon> pokePool = new ArrayList<Pokemon>();
	private Random rand;
	
	public Habitat(String nombre) {
		super();
		this.nombre = nombre;
		rand = new Random();
	}
	public String getNombre() {
		return nombre;
	}

	public void addPokemon(Pokemon poke) {
		pokePool.add(poke);
	}
	
	public Pokemon getEncounter() {
		Pokemon randomPokemon = pokePool.get(rand.nextInt(pokePool.size()));
		return randomPokemon;
	}
}
