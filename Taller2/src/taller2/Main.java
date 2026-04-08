package taller2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Sistema sistem = new Sistema();
		sistem.cargarArchivos();
		Scanner scan = new Scanner(System.in);
		Integer elec = 0;
		while (elec != 3) {
			try {
				System.out.println("1) Continuar.\n" + "2) Nueva partida.\n" + "3) Salir.");
				elec = Integer.valueOf(scan.nextLine());
				switch (elec) {
				case 1:
					if(sistem.inicarPartida(elec, null) == 0) {
						menu(sistem);
					}
					break;
				case 2:
					System.out.print("Ingresa tu nombre! : ");
					String nombre = scan.nextLine();
					sistem.inicarPartida(elec, nombre);
					menu(sistem);
					break;

				case 3:
					break;

				default:
					System.out.println("Ingrese una de las opciones");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Ingrese un número");
			}

		}
		scan.close();

	}

	public static void menu(Sistema sistem) {
		Integer eleccion = 0;
		Scanner scan = new Scanner(System.in);
		while (eleccion != 8) {
			try {
				System.out.println("1) Revisar equipo.\n" + "2) Salir a capturar.\n"
						+ "3) Acceso al PC. (cambiar Pokemon del equipo)\n" + "4) Retar a un gimnasio.\n"
						+ "5) Desafio del alto Mando.\n" + "6) Curar Pokemon.\n" + "7) Guardar.\n"
						+ "8) Guardar y salir\n");
				eleccion = Integer.valueOf(scan.nextLine());
				if (eleccion >= 1 && eleccion <= 8) {
					sistem.menu(eleccion);
				}
				
			} catch (Exception e) {
				
			}

		}
		scan.close();

	}
}
