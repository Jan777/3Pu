
MENSAJES ENVIADOS PARA LOGEAR UN PERSONAJE
DEL CLIENTE AL SERVIDOR:
	mensaje: ingresar a mergame
		user = nombre del usuario
		password = contraseña del usuario
		mundo = mundo seleccionado del check box jugar.

	{"user":"this.nombre","password":this.passw,"mundo": "this.mundo"}
	

DEL SERVIDOR AL CLIENTE
	mensaje: usuario logeado
		posicion x : coordenada de x donde comienza un determinado personaje
		posicion y : coordenada de y donde comienza un determinado personaje
		Lista de personajes: todos los personajes pertenecientes a ese usuario, si no posee por defecto es null.

	{"posX":"X","posY":"Y","personajes": [{"nombrePersonaje":"nombrePersonaje"}]}
******************************************************************************************************************
MENSAJES ENVIADOS PARA COMENZAR A SALVAR A CLAUDIA
DEL CLIENTE AL SERVIDOR:
	mensaje : salva a claudia
		nombrePersonaje: nombre del personaje seleccionado de la lista de personajes ya creados de ese usuario.
		posicion x: coordenada x dentro del mapa
		posicion y: coordenada y dentro del mapa
	{"nombrePersonaje":"this.nombrePersonaje","posX":"this.posX","posY": "this.posY"}

******************************************************************************************************************


******************************************************************************************************************
MENSAJES ENVIADOS PARA REALIZAR UNA BATALLA
DEL CLIENTE AL SERVIDOR:	
	mensaje: atacar a individuo()
		nombrePersonaje : nombre del personaje que ataca.
		nombrePersonajeContricante : nombre del individuo seleccionado con el click en el mapa.
		daño = metodo atacar() del personaje atacante.
		estaVivo = metodo estoyVivo() del personaje atacante

	{"nombrePersonaje":"this.nombrePersonaje","nombrePersonajeContricante":"nombrePersonajeAtacado","daño": "this.atacar()","estaVivo": "this.estoyVivo()"}


DEL SERVIDOR AL CLIENTE:
	mensaje: ser dañado()
		nombrePersonaje : nombre del personaje que ataca.
		daño = metodo atacar() del personaje atacante.

	{"nombrePersonaje":"personajeAtacante.nombrePersonaje","daño":"personajeAtacante.daño"}

DEL SERVIDOR AL CLIENTE:
	mensaje: Ser experianzado
		experiencia = experiencia correspondida con el nivel del individuo y mi propio nivel.
		nombrePersonaje = nombre del personaje ganador de la batalla
	{"nombrePersonaje":"personajeGanador.nombrePersonaje","experiencia":"experiencia"}

**************************************************************************************************************

MENSAJES ENVIADOS PARA REALIZAR UN MOVIMIENTO A ALGUNA DIRECCION
DEL CLIENTE AL SERVIDOR
	mensaje: mover Personaje
		nombrePersonaje : nombre del personaje que se mueve.
	        e.keyCode =  tecla que pulsa(arriba, abajo, derecha, izquierda).
	        posX: pos x del jugador que ha mandado el mensaje.
	        posY: pos y del jugador que ha mandado el mensaje.

	{"nombrePersonaje":"this.nombrePersonaje","e.keyCode":"this.teclado","posX":"this.posX","posY":"this.posY"}

DEL SERVIDOR AL CLIENTE
	mensaje: actulizar ubicacion
	
		Posicion x = Posicion actualizada.
		Posicion y = Posicion actualizad.

		{"posX":"posXactualizada","posY":"posYactualizada"}


