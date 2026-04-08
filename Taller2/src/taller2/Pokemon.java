package taller2;

public class Pokemon {
	private String nombre;
	private String habitat;
	private float porcentajeAparicion;
	private Integer vida;
	private Integer ataque;
	private Integer defensa;
	private Integer ataqueEsp;
	private Integer defensaEsp;
	private Integer velocidad;
	private String tipo;
	private Integer numTipo;
	private Integer statsTotal;
	private String estado;

	public Pokemon(String nombre, String habitat, float porcentajeAparicion, Integer vida, Integer ataque,
			Integer defensa, Integer ataqueEsp, Integer defensaEsp, Integer velocidad, String tipo) {
		super();
		this.nombre = nombre;
		this.habitat = habitat;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEsp = ataqueEsp;
		this.defensaEsp = defensaEsp;
		this.velocidad = velocidad;
		this.tipo = tipo;
		calcularStatsTotales();
		obtenerNumTipo();
		setEstado("Vivo");
	}

	public String getNombre() {
		return nombre;
	}

	public String getHabitat() {
		return habitat;
	}

	public float getPorcentajeAparicion() {
		return porcentajeAparicion;
	}

	public Integer getNumTipo() {
		return numTipo;
	}

	public Integer getStatsTotal() {
		return statsTotal;
	}

	public void setEstado(String t) {
		estado = t;
	}

	public String getEstado() {
		return estado;
	}
	
	public String guardar() {
		return nombre+";"+ estado;
	}

	private void calcularStatsTotales() {
		statsTotal = vida + ataque + defensa + ataqueEsp + defensaEsp + velocidad;
	}

	private void obtenerNumTipo() {
		String[] tipos = { "Normal", "Fuego", "Agua", "Planta", "Electrico", "Hielo", "Lucha", "Veneno", "Tierra",
				"Volador", "Psiquico", "Bicho", "Roca", "Fantasma", "Dragon", "Acero", "Siniestro", "Hada" };
		for (int i = 0; i < tipos.length; i++) {
			if (tipos[i].equalsIgnoreCase(tipo)) {
				numTipo = i;
			}
		}
	}

}
