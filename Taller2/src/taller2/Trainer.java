package taller2;

import java.util.ArrayList;

public class Trainer {
	private Integer num;
	private String lider;
	private String estado;
	private Integer cantPokemon;
	private ArrayList<Pokemon> equipo;

	public Trainer(Integer num, String lider, String estado, Integer cantPokemon) {
		super();
		this.num = num;
		this.lider = lider;
		this.estado = estado;
		this.cantPokemon = cantPokemon;
		equipo = new ArrayList<Pokemon>();
	}

	public void addPoke(Pokemon poke) {
		equipo.add(poke);
	}

	public String getLider() {
		return lider;
	}

	public Integer getCantidadPoke() {
		return cantPokemon;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	public boolean comprobarEstado() {
		if (estado.equalsIgnoreCase("no derrotado")) {
			return false;
		} else {
			return true;
		}

	}

}
