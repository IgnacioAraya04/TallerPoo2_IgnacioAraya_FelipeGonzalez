package taller2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class Sistema {
	TablaTipos tabla = new TablaTipos();
	Player jugador;
	ArrayList<Habitat> listaHab = new ArrayList<Habitat>();
	ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	ArrayList<Trainer> trainers = new ArrayList<Trainer>();

	public void cargarArchivos() throws FileNotFoundException {
		cargarHabitat();
		cargarPokemon();
		cargarEntrenadores(false);
		cargarEntrenadores(true);
	}

	private void cargarHabitat() throws FileNotFoundException {
		Scanner arch = new Scanner(new File("Habitats.txt"));
		Habitat hab;
		while (arch.hasNextLine()) {
			String string = (String) arch.nextLine();
			hab = new Habitat(string);
			listaHab.add(hab);
		}
		arch.close();
	}

	private void cargarPokemon() throws FileNotFoundException {
		Scanner arch = new Scanner(new File("Pokedex.txt"));
		Pokemon poke;
		while (arch.hasNextLine()) {
			String string = (String) arch.nextLine();
			String[] partes = string.split(";");
			poke = new Pokemon(partes[0], partes[1], Float.valueOf(partes[2]), Integer.valueOf(partes[3]),
					Integer.valueOf(partes[4]), Integer.valueOf(partes[5]), Integer.valueOf(partes[6]),
					Integer.valueOf(partes[7]), Integer.valueOf(partes[8]), partes[9]);
			pokedex.add(poke);
			rellenarHabitats(poke);
		}
		arch.close();
	}

	private void rellenarHabitats(Pokemon poke) {
		for (Habitat habitat : listaHab) {
			if (habitat.getNombre().equalsIgnoreCase(poke.getHabitat())) {
				habitat.addPokemon(poke);
				break;
			}
		}
	}

	private void cargarEntrenadores(boolean eliteSeven) throws FileNotFoundException {
		if (!eliteSeven) {
			Scanner arch = new Scanner(new File("Gimnasios.txt"));
			Trainer tr;
			while (arch.hasNextLine()) {
				String string = (String) arch.nextLine();
				String[] partes = string.split(";");
				tr = new Trainer(Integer.valueOf(partes[0]), partes[1], partes[2], Integer.valueOf(partes[3]));
				for (int i = 4; i < tr.getCantidadPoke() + 4; i++) {
					for (Pokemon pokemon : pokedex) {
						if (pokemon.getNombre().equalsIgnoreCase(partes[i])) {
							tr.addPoke(pokemon);
						}
					}
				}
				trainers.add(tr);
			}
			arch.close();
		} else {
			Scanner arch = new Scanner(new File("Alto Mando.txt"));
			Trainer tr;
			while (arch.hasNextLine()) {
				String string = (String) arch.nextLine();
				String[] partes = string.split(";");
				tr = new Trainer(Integer.valueOf(partes[0]), partes[1], partes[2], 6);
				for (int i = 2; i < tr.getCantidadPoke() ; i++) {
					for (Pokemon pokemon : pokedex) {
						if (pokemon.getNombre().equalsIgnoreCase(partes[i])) {
							tr.addPoke(pokemon);
						}
					}

				}
				trainers.add(tr);
			}
			arch.close();
		}
	}

	public Integer inicarPartida(Integer elec, String nombre) throws FileNotFoundException {
		if (elec == 1) {
			return cargarPartida();
		} else {
			crearPartida(nombre);
			return 0;
		}
	}

	private void crearPartida(String nombre) {
		jugador = new Player(nombre);
	}

	private Integer cargarPartida() throws FileNotFoundException {
		File file = new File("Registros.txt");
		if (!file.exists()) {
			System.out.println("No existe una partida guardada");
			return 1;
		} else {
			Scanner registros = new Scanner(file);
			String usuario = registros.nextLine();
			String[] partesUsuario = usuario.split(";");
			jugador = new Player(partesUsuario[0]);
			if (partesUsuario[1] == "") {
			} else {
				jugador.añadirMedalla(partesUsuario[1]);
			}
			registros.nextLine();
			while (registros.hasNextLine()) {
				String string = (String) registros.nextLine();
				String[] partes = string.split(";");
				for (Pokemon pokemon : pokedex) {
					if (pokemon.getNombre().equalsIgnoreCase(partes[0])) {
						String copia = pokemon.crearCopia();
						String[] pCopia = copia.split(";");
						Pokemon pokeCopia = new Pokemon(pCopia[0], pCopia[1], Float.valueOf(pCopia[2]),
								Integer.valueOf(pCopia[3]), Integer.valueOf(pCopia[4]), Integer.valueOf(pCopia[5]),
								Integer.valueOf(pCopia[6]), Integer.valueOf(pCopia[7]), Integer.valueOf(pCopia[8]),
								pCopia[9]);
						pokeCopia.setEstado(partes[1]);
						jugador.atraparPokemon(pokeCopia);
					}
				}

			}
			registros.close();
			return 0;
		}

	}

	public void guardarPartida() throws IOException {
		File arch = new File("Registros.txt");
		BufferedWriter escritor;
		if (arch.exists()) {
			arch.delete();
		}
		escritor = new BufferedWriter(new FileWriter("Registros.txt"));
		escritor.write(jugador.guardar());
		escritor.newLine();
		for (Pokemon pokemon : jugador.getPokemonAtrapados()) {
			escritor.write(pokemon.guardar());
			escritor.newLine();
		}

		escritor.close();
	}

	public void menu(Integer elec) {
		switch (elec) {
		case 1:
			System.out.println("Equipo: ");
			if (jugador.getPokemonAtrapados().size() < 6) {
				for (int i = 0; i < jugador.getPokemonAtrapados().size(); i++) {
					System.out.println((i+1) + ") " + jugador.getPokemonAtrapados().get(i).getReview());
				}
			} else {
				for (int i = 0; i < 5; i++) {
					System.out.println((i+1) + ") " + jugador.getPokemonAtrapados().get(i).getReview());
				}
			}
			break;
		case 2:
			System.out.println("Habitats: ");
		while (true) {
			Scanner scan = new Scanner(System.in);
			for (int i = 0; i <listaHab.size(); i++) {
				System.out.println((i+1)+") "+ listaHab.get(i).getNombre());
			}
			try {
				Integer elecHabitat = Integer.valueOf(scan.nextLine());
				Pokemon encuentroAleatorio = listaHab.get(elecHabitat-1).getEncounter();
				System.out.println("Un " + encuentroAleatorio.getNombre() + " salvaje ha aparecido!!");
				System.out.println("Que quieres hacer?\n" + "1) Capturar.\n" + "2) Huir");
				Integer elecCaptura = Integer.valueOf(scan.nextLine());
				if (elecCaptura == 1) {
					String copia = encuentroAleatorio.crearCopia();
					String[] pCopia = copia.split(";");
					Pokemon pokeCopia = new Pokemon(pCopia[0], pCopia[1], Float.valueOf(pCopia[2]),
							Integer.valueOf(pCopia[3]), Integer.valueOf(pCopia[4]), Integer.valueOf(pCopia[5]),
							Integer.valueOf(pCopia[6]), Integer.valueOf(pCopia[7]), Integer.valueOf(pCopia[8]),
							pCopia[9]);
					jugador.atraparPokemon(pokeCopia);
				}else if (elecCaptura == 2) {
					break;
				} else {
					System.out.println("seleccionar una de las opciones");
				}
			} catch (Exception e) {
				System.out.println("Utilizar un número");
			}
			
			break;
		}
			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		default:
			break;
		}
	}
}
