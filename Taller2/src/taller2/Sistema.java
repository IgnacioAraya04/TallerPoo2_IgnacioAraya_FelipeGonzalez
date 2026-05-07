package taller2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
				for (int i = 2; i < tr.getCantidadPoke(); i++) {
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
			System.out.println("No existe una partida guardada\n");
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
					System.out.println((i + 1) + ") " + jugador.getPokemonAtrapados().get(i).getReview());
				}
				System.out.println("\n");
			} else {
				for (int i = 0; i < 5; i++) {
					System.out.println((i + 1) + ") " + jugador.getPokemonAtrapados().get(i).getReview());
				}
				System.out.println("\n");
			}
			break;
		case 2:
			System.out.println("Habitats: ");
			while (true) {
				Scanner scan = new Scanner(System.in);
				for (int i = 0; i < listaHab.size(); i++) {
					System.out.println((i + 1) + ") " + listaHab.get(i).getNombre());
				}
				try {
					Integer elecHabitat = Integer.valueOf(scan.nextLine());
					Pokemon encuentroAleatorio = listaHab.get(elecHabitat - 1).getEncounter();
					System.out.println("Un " + encuentroAleatorio.getNombre() + " salvaje ha aparecido!!\n");
					System.out.println("Que quieres hacer?\n" + "1) Capturar.\n" + "2) Huir.\n");
					Integer elecCaptura = Integer.valueOf(scan.nextLine());
					// pequeño cambio (TEMPORAL - En revision)
					if (elecCaptura == 1) {
						Boolean existePokemon = false;
						for (Pokemon pokemon : jugador.getPokemonAtrapados()) {
							if (pokemon.getNombre().equalsIgnoreCase(encuentroAleatorio.getNombre())) {
								existePokemon = true;
								break;
							}
						}
						if (!existePokemon) {
							String copia = encuentroAleatorio.crearCopia();
							String[] pCopia = copia.split(";");
							Pokemon pokeCopia = new Pokemon(pCopia[0], pCopia[1], Float.valueOf(pCopia[2]),
									Integer.valueOf(pCopia[3]), Integer.valueOf(pCopia[4]), Integer.valueOf(pCopia[5]),
									Integer.valueOf(pCopia[6]), Integer.valueOf(pCopia[7]), Integer.valueOf(pCopia[8]),
									pCopia[9]);
							jugador.atraparPokemon(pokeCopia);
						} else {
							System.out.println("No puedes capturar un Pokemon que ya posees.\n");
						}

					} else if (elecCaptura == 2) {
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
			// PC
			ArrayList<Pokemon> pokemonsEntrenadorList = jugador.getPokemonAtrapados();
			Scanner scanPC = new Scanner(System.in);
			System.out.println("------ Gestion de Equipo y PC ------\n");

			for (int i = 0; i < pokemonsEntrenadorList.size(); i++) {
				// esto de aqui es la etiqueta, es mas que nada para ubicarme rapidamente en que
				// categorizar el size(), osea que pokemons estan en el team o no
				String etiqueta;
				if (i < 6) {
					etiqueta = "[EQUIPO]";
				} else {
					etiqueta = "[PC]";
				}
				System.out.println(i + 1 + ") " + etiqueta + " - " + pokemonsEntrenadorList.get(i).getNombre());
			}
			// Esto de aqui es para SI SOLO SI hay mas de 6 pokemones (TEMPORAL)
			if (pokemonsEntrenadorList.size() > 6) {
				try {
					System.out
							.println("Seleccione un Pokemon del [PC] (ID valido desde el 7 o más) que quieras subir: ");
					int desdeID = Integer.parseInt(scanPC.nextLine()) - 1;

					System.out
							.println("Seleccione un Pokemon del [EQUIPO] que sera remplazado (ID valido desde 1 al 6");
					int haciaID = Integer.parseInt(scanPC.nextLine()) - 1;

					if (haciaID < 6 && desdeID >= 6 && desdeID < pokemonsEntrenadorList.size()) {
						Pokemon temporalPokemon = pokemonsEntrenadorList.get(haciaID);
						pokemonsEntrenadorList.set(haciaID, pokemonsEntrenadorList.get(desdeID));
						pokemonsEntrenadorList.set(desdeID, temporalPokemon);
						System.out.println("el cambio se hizo con exito!");
					} else {
						System.out.println("Movimiento invalido. Pruebe de nuevo.");
					}

				} catch (NumberFormatException e) {
					System.out.println(e + " - ERROR: ingrese numeros Validos.");
				}
			} else {
				System.out.println("No tienes pokemons en PC para realizar cambios.\n");
			}

			break;
		case 4:
			System.out.println("--- Gimnasios de la Region ---\n");
			for (int i = 0; i < 8; i++) {
				System.out.println((i + 1) + ") Líder " + trainers.get(i).getLider());
			}
			System.out.println("\n");
			try {
				Scanner scannerGym = new Scanner(System.in);
				System.out.println("\n Selecione el Gimnasio a desafiar: ");
				
				int eleccionGym = Integer.parseInt(scannerGym.nextLine()) - 1;
				
				if (eleccionGym >= 0 && eleccionGym < 8) {
					Trainer rival = trainers.get(eleccionGym);
					if (jugador.getMedallasArrayList().contains(rival.getEstado())) {
						System.out.println("Ya derrotaste a este lider y tienes su medalla.\n");

					} else if (jugador.getMedallasArrayList().size() >= eleccionGym) {
						// METODO NUEVO
						ejecutarBatalla(rival);
					} else {
						System.out.println("Aun no puedes retar a este líder. Vence al lider anterior primero.\n");
					}

				} else {
					System.out.println("Gimnasio inválido.\n");
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("Utilizar un numero.\n");
			}
			break;
		case 5:

			break;
		case 6:
			// Curar Pokemons
			for (Pokemon pokemon : jugador.getPokemonAtrapados()) {
				pokemon.setEstado("Vivo");
			}
			System.out.println("todos tus Pokemones han sido curados y listos para la batalla!\n");
			break;
		case 7:

			break;
		default:
			break;
		}
	}

	public void ejecutarBatalla(Trainer rival) {
		System.out.println("\n--- BATALLA CONTRA LÍDER " + rival.getLider().toUpperCase() + " ---");
		ArrayList<Pokemon> equipoJugador = new ArrayList<Pokemon>();

		for (int i = 0; i < jugador.getPokemonAtrapados().size() && i < 6; i++) {
			if (jugador.getPokemonAtrapados().get(i).getEstado().equalsIgnoreCase("Vivo")) {
				equipoJugador.add(jugador.getPokemonAtrapados().get(i));
			}
		}
		if (equipoJugador.isEmpty()) {
			System.out.println(
					"No tienes Pokémon aptos para pelear. Ve a curarlos al PC (Opción 6), o ve a capturar un Pokemon \n");
			return;
		}

		ArrayList<Pokemon> equipoRival = rival.getEquipo();
		int indexJugador = 0;
		int indexRival = 0;

		while (indexJugador < equipoJugador.size() && indexRival < equipoRival.size()) {
			Pokemon pJugador = equipoJugador.get(indexJugador);
			Pokemon pRival = equipoRival.get(indexRival);

			System.out.println("\n El líder " + rival.getLider() + " envía a " + pRival.getNombre());
			System.out.println("¡Adelante " + pJugador.getNombre() + "!");
			// esta cosa es para la tabla de tipos
			double efectividadJugador = tabla.getEfectividad(pJugador.getNumTipo(), pRival.getNumTipo());
			double efectividadRival = tabla.getEfectividad(pRival.getNumTipo(), pJugador.getNumTipo());

			double poderDeJugador = pJugador.getStatsTotal() * efectividadJugador;
			double poderDeRival = pRival.getStatsTotal() * efectividadRival;

			System.out.println(pJugador.getNombre() + " ataca con " + poderDeJugador + " de poder (Efectividad x"
					+ efectividadJugador + ")");
			System.out.println(pRival.getNombre() + " ataca con " + poderDeRival + " de poder (Efectividad x"
					+ efectividadRival + ")");

			if (poderDeJugador > poderDeRival) {
				System.out.println(pRival.getNombre() + " del rival se ha debilitado!");
				indexRival++;
			} else if (poderDeRival > poderDeJugador) {
				System.out.println(pJugador.getNombre() + " se ha debilitado!");
				pJugador.setEstado("Debilitado");
				indexJugador++;
			} else {
				System.out.println("¡Ambos Pokémon cayeron en un empate de poder!");
				pJugador.setEstado("Debilitado");
				indexJugador++;
				indexRival++;
			}
		}
		// RESULTADO - con suerte funcionara
		if (indexRival >= equipoRival.size()) {
			System.out.println("\n Felicidades has derrotado al lider" + rival.getLider());
			System.out.println("Has obtenido la medalla!");
		} else {
			System.out.println("\n Has perdido el combate. Todos tus Pokémon se debilitaron. Ve a curarlos.\n");
		}

	}

}
