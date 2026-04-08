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
					sistem.inicarPartida(elec, null);
					break;
				case 2:
					System.out.print("Ingresa tu nombre! : ");String nombre = scan.nextLine();
					sistem.inicarPartida(elec, nombre);
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

}
