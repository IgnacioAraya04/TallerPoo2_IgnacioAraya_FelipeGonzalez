Este proyecto se trata de una simulacion simplificada de un juego de pokemon, donde el objetivo
es derrotar los lideres de gimnacio para enfrentarse al Alto Mando, para ello el jugador debera 
explorar distintos habitats para capturar nuevos pokemon.

Integrantes:
Integrante 1: Ignacio Araya Munizaga - 21.824.045-3 - IgnacioAraya04  
Integrante 2: Felipe Gonzáles Zuleta - 21.776.516-1 - SrSummerSmouk  

Estructura del Proyecto:
Este proyecto tiene 7 clases principales, estas son:

Main
Quien se encarga de ser el punto de entrada del programa.
Su funcion es la de manejar el menu principal (continuar o crear nueva partida) y el menu del juego 
principal.

Sistema
Quien se encarga de la lógica del juego y el almacenamiento de variables importantes.
Mantiene una lista de los entrenadores a enfrentar, el catalogo completo de pokemon y los distintos 
habitats que explorar, a su vez, se encarga de almacenar la clase de jugador y la tabla de tipos.
Sus funciones claves son:
Carga de archivos.
Gestion de partidas (Guardar/Cargar).
Sistema de batalla por turnos.
Gestion del equipo.

Player
Representa al jugador y su progreso.
En sus atributos se almacenan el nombre, las medallas, los pokemons atrapados, el estado del equipo
para los combates y que tan lejos esta actualmente en el desafio del alto mando.
Sus funciones son la gestion de medallas, el almacenamiento y gestion de pokemon capturados y 
preparar los datos para su guardado.

Pokemon
Representa a un pokemon individual.
Sus atributos almacenan las estadisticas base (vida, ataque, defense, ataque esp, defensa esp, velocidad)
como tambien la suma de estas, como tambien el tipo del pokemon, su habitat de origen y su estado
actual (Vivo/Debilitado).
Sus funciones principales son el caluclo de las estadisticas totales, preparar los datos de su tipo
para determinarlo facilmente en los calculos de combate y creear copias de la clase para el uso
de los entrenadores / jugador.

Habitat
Representa las distintas áreas donde se pueden capturar los pokemon
Sus atributos son el nombre del hábitat, la pool de pokemon disponibles en ese hábitat y el generador
aleatorio para encuentros.
Sus funciones son la obtencion de un encuentro aleatorio y el agregar pokemon a su respectivo hábitat.

Trainer
Representa a los lideres de gimnacio y miembros del Alto mando.
Tiene por atributos el numero identificador único de cada entrenador, su nombre propio, estado de derrota,
cantidad de pokemon en su equipo y su equipo pokemon.
Sus funciones son la gestion de su propio equipo y la verificacion de su estado de derrota.

TablaTipos
Se encarga de calcular al efectividad entre tipos de pokemon.
Su único atributo es una matriz estática 18x18 con multiplicadores de daño.
Su Funcion es comparar los tipos de los pokemon combatientes y retornar el multiplicador de daño
correspondiente de cada uno.

Instrucciones de ejecucion:
Al ejecutar el programa, se ejecutara un menu para cargar o inciar una partida nueva que tendra
las siguientes opciones:
1) Continuar partida guardada
2) Nueva partida (solicita nombre)
3) Salir
Se debera ingresar un valor entero (1-3) para seleccionar alguna de las opciones, en caso de 
no haber una partida guardada se le notificara de ello.
Luego de continuar/inicaiar nueva partida, se le dirigira a un menu que contiene el resto de
acciones que el usuario podra realizar, estas siendo:

1)Revisar equipo
2)Capturar Pokémon en hábitats
3)Gestionar PC/intercambiar equipo
4)Desafiar gimnasios
5)Desafiar Elite Four
6)Curar Pokémon
7)Guardar partida
8)Guardar y salir
Se debera ingresar un valor entero (1-8) para seleccionar alguna de las opciones.
Al revisar equipo se le mostrara los 6 pokemon que utilizara para combatir.
Al capturar pokemon, se le preguntara que habitat desea ir y se le proporcionara un pokemon
de la pool del habitat seleccionado, ahi podra elegir si capturarlo o huir.
Al gestionar pc, podra observar todos los pokemon capturados y tambien si tiene mas de 6, puede
intercambiar los pokemon en el pc por alguno de los activos.
Al desafiar a los gimancios se le pedira elegir a quien enfrentar y si gana se le proporcionara
con la medalla correspondiente.
Al desafiar al Alto Mando/Elite Four, se le pedira elegir a quien desea combatir, si logra ganar,
el estado del entrenador será cambiado a "derrotado" y podra elegir al siguiente a enfrentar.
Al curar pokemon se actualizara el estado de todos los pokemon (incluidos aquellos en el PC).
Al guardar partida, se creara un txt llamado "Registros" que almacenara los datos del jugador.
Al seleccionar la última opcion se ejecutara el guardado, además de cerrar la sesión y mandarlo
al menu anterior, donde decidira si continuar o salir.
