1) Caracter�sticas:
Como jugador quiero crear mi personaje.
Para poder editar sus caracter�sticas principales (como raza y casta).
Para poder verme reflejado en �l.

Criterios de aceptaci�n:
? Dado un usuario cuando �ste selecciona �crear personaje� entonces se le despliega el men� de creaci�n de personaje.
? Dado un personaje cuando el usuario selecciona �elegir raza� entonces se despliega una lista de razas para elegir(Orco, Humano, Elfo y Enano).
? Dado un personaje con una raza seleccionada cuando el usuario selecciona �elegir casta� entonces se despliega una lista de castas a elegir(Mago, Guerrero, Arquero).
? Dado un usuario en instancia de creaci�n de personaje cuando selecciona �agregar nombre� entonces se le agrega un nombre al personaje.
? Dado un personaje cuando se le agrega un nombre ya existente entonces se muestra mensaje de error: �Este nombre ya existe�.
? Dado un personaje cuando el usuario confirma la creaci�n entonces el personaje debe quedar guardado para futuras partidas.
? Dado uno o m�s personajes ya creados por el usuario, cuando este se loguee en el juego entonces se le despliega una lista de sus personajes para poder elegir o crear uno nuevo.
? Dado un usuario en la instancia de creaci�n de personaje cuando selecciona �elegir avatar� entonces se despliegan varias im�genes para seleccionar.

2) Caracter�sticas:
Como jugador quiero ingresar a un mundo.
Para poder participar en �l.
Para poder adquirir experiencia, �tems y habilidades nuevas.

Criterios de aceptaci�n:
? Dado un personaje en instancia de juego cuando este selecciona �Mundos� entonces se despliega una lista de mundos posibles a ingresar.(Mundo Orco, Mundo Humano, Mundo Elfo, Mundo Enano).
? Dado un personaje en instancia de juego cuando quiere ingresar a un mundo entonces se verifica 	que este cumpla con el nivel requerido.
? Dado un personaje y una criatura cuando el personaje sale vencedor entonces adquiere la posibilidad de elegir o no un item aleatorio.
? Dado un personaje cuando alcanza determinado nivel entonces adquiere una habilidad nueva. 
? Dado un personaje en instancia de juego cuando se desplaza dentro del mapa entonces no puede atravesar los obst�culos presentes en �ste.
? Dado un personaje en instancia de juego cuando encuentra un �tem(Casco, Espada, Arco, etc) dentro del mapa entonces el personaje puede aceptarlo o no.
? Dado un personaje y un enemigo cuando �ste �ltimo es clickeado entonces se despliega la interfaz de lucha.
? Dado un personaje y un enemigo en instancia de lucha cuando el enemigo termina una acci�n (lanzar habilidad, atacar) entonces finaliza su turno brind�ndoselo al siguiente.
? Dado un personaje y un enemigo en instancia de lucha cuando el personaje termina una acci�n (lanzar habilidad, atacar) entonces finaliza su turno brind�ndoselo al siguiente.
? Dado un personaje en instancia de juego cuando se desplaza sobre el mapa entonces debe moverse s�lo en 8 direcciones posibles.



3) Caracter�sticas: 
Como jugador quiero eliminar enemigos.
Para poder aumentar mi experiencia.
Para poder acumularla.

Criterios de aceptaci�n:
? Dado dos personajes cuando uno vence a otro entonces el ganador adquiere puntos de experiencia.
? Dado un personaje y una criatura cuando el personaje sale vencedor entonces adquiere experiencia.


4) Caracter�sticas:
Como jugador quiero acumular experiencia.
Para poder subir de nivel.
Para poder llegar a la experiencia m�xima de mi nivel actual.

Criterios de aceptaci�n:
? Dado un personaje cuando consigue una determinada cantidad de puntos de experiencia (100%) entonces sube de nivel.

5) Caracter�sticas: 
Como jugador quiero subir de nivel.
Para poder asignar puntos adicionales a mis habilidades.
Para poder aumentar el nivel de mis habilidades.

Criterios de aceptaci�n:
? Dado un personaje cuando sube de nivel entonces adquiere puntos de habilidad consumibles.
? Dado un personaje cuando sube de nivel entonces adquiere puntos de estad�stica.
? Dado un personaje con puntos de habilidad cuando el usuario ingrese en las opciones de habilidades entonces puede incrementar los puntos de una habilidad espec�fica.
? Dado un personaje con puntos de estad�stica cuando el usuario ingrese en las opciones de estad�sticas entonces puede incrementar los puntos de una estad�stica espec�fica.

6) Caracter�sticas:
Como jugador quiero aumentar mis habilidades. 
Para poder manipular �tems de manera m�s eficiente.
Para poder aumentar los efectos que los �tems me otorgan.

Criterios de aceptaci�n:
? Dado un personaje cuando se le aumentan los puntos en una estad�stica entonces mejora los efectos de sus items.


7) Caracter�sticas:
Como jugador quiero equipar �tems.
Para poder potenciar mis habilidades.
Para poder tener mayor efecto sobre ataques, curaciones o buffeos que realice.

Criterios de aceptaci�n:
? Dado un personaje cuando es equipado con un item entonces recibe efectos sobre sus habilidades.

8) Caracter�sticas: 
Como jugador quiero disponer de habilidades de destreza, fuerza e inteligencia.
Para poder afectar a mis puntos de ataque, magia y defensa.

Criterios de aceptaci�n:
? Dado un personaje cuando este aumenta sus puntos de estad�stica entonces va a poder atacar o defenderse con mayor potencia.



9) Caracter�sticas:
Como jugador quiero encontrarme con otros jugadores en el mismo mundo.
Para poder aliarme a ellos o combatir contra ellos.
Para poder obtener un beneficio dependiendo de la acci�n que elija.

Criterios de aceptaci�n:
? Dado dos o m�s personajes cuando el usuario clickea sobre un personaje diferente al suyo entonces se despliega la opci�n de crear una alianza
? Dado dos o m�s personajes en alianza cuando el usuario clickea un personaje ajeno a su alianza entonces puede iniciar el combate.
? Dado dos personajes cuando el usuario clickea sobre otro personaje entonces puede elegir entre combatir o aliarse.

10) Caracter�sticas:
Como jugador quiero aliarme con otro jugador.
Para poder combatir junto a �l y aumentar la experiencia que recolectemos en ese tiempo.
Para poder obtener puntos de experiencia multiplicado por el n�mero de aliados combatientes.

Criterios de aceptaci�n:
? Dado personajes aliados cuando estos combaten contra criaturas u otros personajes entonces obtienen puntos de experiencia que se reparten entre los integrantes.
? Dado personajes aliados cuando estos combaten contra criaturas u otros personajes entonces obtienen mayor cantidad de puntos de experiencia que si estuvieran combatiendo individualmente.

11) Caracter�sticas:
Como jugador quiero combatir contra otros jugadores.
Para poder obtener sus �tems al derrotarlos.
Para poder equiparmelo o dejarlo.

Criterios de aceptaci�n:
? Dado dos personajes cuando uno vence a otro entonces el vencedor puede o no adquirir el �ltimo arma conseguida por el personaje derrotado.

12) Caracter�sticas:
Como jugador quiero cambiar las alianzas establecidas cada cierta cantidad de tiempo.
Para poder traicionar a mis aliados.
Para poder derrotarlos.

Criterios de aceptaci�n:
? Dado un personaje en una alianza cuando finalizan los 5 minutos disponibles dentro de la misma, entonces se habilita la opci�n �Salir de alianza�.
? Dado un personaje en una alianza cuando finalizan los 5 minutos disponibles dentro de la misma y hay otra alianza disponible entonces se habilita la opci�n �Cambiar de alianza�.

13) Caracter�stica:
Como jugador quiero que los items adquiridos se fusionen con todos los items obtenidos previamente del mismo tipo.

Criterios de aceptaci�n:
? Dado un personaje en instancia de juego cuando adquiere un item nuevo entonces se fusiona con el/los items adquiridos previamente.

