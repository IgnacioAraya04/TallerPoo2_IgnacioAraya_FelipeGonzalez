package taller2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	TablaTipos tabla = new TablaTipos();
	ArrayList<Habitat> listaHab = new ArrayList<Habitat>() ;
	ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>() ; 
	
	
	
	
	
	public void cargarArchivos() throws FileNotFoundException {
		cargarHabitat();
		cargarPokemon();
	}
	private void cargarHabitat() throws FileNotFoundException {
		Scanner arch = new Scanner(new File(""));
		Habitat hab;
		while (arch.hasNextLine()) {
			String string = (String) arch.nextLine();
			hab = new Habitat(string);
			listaHab.add(hab);
		}
		arch.close();
	}
	
	private void cargarPokemon() throws FileNotFoundException {
		Scanner arch = new Scanner(new File(""));
		Pokemon poke;
		while (arch.hasNextLine()) {
			String string = (String) arch.nextLine();
			String[] partes = string.split(";");
			poke = new Pokemon(string, string, 0, null, null, null, null, null, null, string);
			pokedex.add(poke);
		}
		arch.close();
	}
}
