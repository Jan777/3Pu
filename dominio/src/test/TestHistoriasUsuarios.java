package test;

import org.junit.Assert;
import org.junit.Test;

import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.Criatura;
import main.java.mergame.individuos.personajes.Personaje;
import main.java.mergame.individuos.personajes.Usuario;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import main.java.mergame.individuos.personajes.impl.PersonajeImpl;
import main.java.mergame.interfaz.Mundo;
import main.java.mergame.items_con_decorator.impl.ConEspadaSkofnung;

public class TestHistoriasUsuarios {

	// DADO UN PERSONAJE Y UNA CRIATURA, CUANDO EL PERSONAJE SALE VENCEDOR
	// ENTONCES ADQUIERE EXPERIENCIA
	@Test
	public void aumentarExperienciaPorAtacarOtroPersonajeTest() {//ANDA
		PersonajeImpl brian = new Orco();
		brian.setCasta(new Mago());

		PersonajeImpl maxi = new Humano();
		maxi.setCasta(new Mago());
		
		Assert.assertEquals(0, brian.getExperiencia());
		while (maxi.estaVivo()) {
			brian.atacar(maxi);
		}
		
		Assert.assertEquals(50, brian.getExperiencia());
		
	}

	// DADO DOS PERSONAJES, CUANDO UNO VENCE A OTRO, EL GANADOR ADQUIERE PUNTOS
	// DE EXPERIENCIA
	@Test
	public void aumentarExperienciaPorAtacarMonstruoTest() {
		PersonajeImpl brian = new Orco();
		brian.setCasta(new Mago());

		Criatura maxi = new Criatura();
		maxi.setNivel(1);

		Assert.assertEquals(0, brian.getExperiencia());
		while (maxi.estaVivo()) {
			brian.atacar(maxi);
			brian.reestablecerEstamina();
		}
		
		Assert.assertEquals(40, brian.getExperiencia());
	}

	// DADO UN PERSONAJE CUANDO CONSIGUE UNA DETERMINANDA CANTIDAD DE PUNTOS DE
	// EXPERIENCIA(100%) ENTONCES SUBE DE NIVEL
	@Test
	public void aumentarNivelTest() {
		PersonajeImpl brian = new Orco();
		brian.setCasta(new Mago());

		PersonajeImpl maxiHumano = new Humano();
		maxiHumano.setCasta(new Mago());
		
		Assert.assertEquals(0, brian.getExperiencia());
		Assert.assertEquals(1, brian.getNivel());

		while (maxiHumano.estaVivo()) {// PRIMER ENEMIGO QUE DA 50EXP
			brian.atacar(maxiHumano);
		}
		brian.reestablecerEstamina();

		PersonajeImpl maxiOrco = new Orco();// SEGUNDO ENEMIGO QUE DA 50EXP
		maxiOrco.setCasta(new Mago());
		while (maxiOrco.estaVivo()) {
			brian.atacar(maxiOrco);
		}
		brian.reestablecerEstamina();

		PersonajeImpl maxiElfo = new Elfo(); // TERCER ENEMIGO QUE DA 50EXP
		maxiElfo.setCasta(new Mago());
		while (maxiElfo.estaVivo()) {
			brian.atacar(maxiElfo);
			brian.reestablecerEstamina();
		}
		brian.reestablecerEstamina();

		PersonajeImpl maxiEnano = new Enano(); // CUARTO ENEMIGO QUE DA 50EXP
		maxiEnano.setCasta(new Mago());
		while (maxiEnano.estaVivo()) {
			brian.atacar(maxiEnano);
			brian.reestablecerEstamina();
		}
		brian.reestablecerEstamina();
		
		Assert.assertEquals(100, brian.getExperiencia());
		Assert.assertEquals(2, brian.getNivel());
	}

	// DADO UN PERSONAJE CUANDO SUBE DE NIVEL ENTONCES ADQUIERE PUNTOS DE
	// ESTADISTICA
	@Test
	public void aumentarEstadisticasAlSubirNivelTest() {
		PersonajeImpl brian = new Orco();
		brian.setCasta(new Mago());

		Personaje maxiHumano = new Humano();
		maxiHumano.setCasta(new Mago());
		
		Assert.assertEquals(1, brian.getNivel());
		Assert.assertEquals(100, brian.getSalud());
		Assert.assertEquals(100, brian.getEstamina());
		Assert.assertEquals(10, brian.getDefensa());
		Assert.assertEquals(1, brian.getPoderFisico());
		Assert.assertEquals(5, brian.getPoderMagico());
		

		while (maxiHumano.estaVivo()) {// PRIMER ENEMIGO QUE DA 50EXP
			brian.atacar(maxiHumano);
		}
		brian.reestablecerEstamina();

		Personaje maxiOrco = new Orco();// SEGUNDO ENEMIGO QUE DA 50EXP y SUBO
										// NIVEL POR ELLO
		maxiOrco.setCasta(new Mago());
		while (maxiOrco.estaVivo()) {
			brian.atacar(maxiOrco);
		}
		
		Assert.assertEquals(2, brian.getNivel());
		Assert.assertEquals(200, brian.getSalud());
		Assert.assertEquals(200, brian.getEstamina());
		Assert.assertEquals(20, brian.getDefensa());
		Assert.assertEquals(21, brian.getPoderFisico());
		Assert.assertEquals(25, brian.getPoderMagico());
		
	}

	@Test
	public void quePuedoQuitarUnItem() { // NO LO BORREN QUE ES EL DE DESEQUIPAR
		Personaje sigmund = new Humano();
		
		Assert.assertEquals(10, sigmund.getPuntosDeAtaqueFisico());
		
		sigmund = new ConEspadaSkofnung(sigmund);
		sigmund = new ConEspadaSkofnung(sigmund);
		
		//con dos espadas
		Assert.assertEquals(30, sigmund.getPuntosDeAtaqueFisico());
		
		sigmund = sigmund.desequipar(ConEspadaSkofnung.class);
		
		//con una espada menos
		Assert.assertEquals(20, sigmund.getPuntosDeAtaqueFisico());


	}

	// DADO UN USUARIO CUANDO ESTE SELECCIONA "CREAR PERSONAJE" ENTONCES EL
	// PERSONAJE ES CREADO
	@Test
	public void creacionPersonaje() {
		Usuario usuario = new Usuario("ferra", "pass1234");
		PersonajeImpl elPibe = new Humano();
		elPibe.setNombre("fede");
		usuario.agregarPesonaje(elPibe);

		Assert.assertEquals(elPibe, usuario.getPersonaje("fede"));
	}

	// DADO UN USUARIO CON 3 PERSONAJES CREADOS CUANDO CREA UN CUARTO PERSONA
	// ENTONCES ESTE NO SE CREA
	@Test
	public void cantidadPersonajes() {
		Usuario usuario = new Usuario("ferra", "pass1234");
		PersonajeImpl elPibe = new Humano();
		PersonajeImpl elPibe2 = new Orco();
		PersonajeImpl elPibe3 = new Enano();
		PersonajeImpl elPibe4 = new Humano();
		elPibe.setNombre("ferra");
		usuario.agregarPesonaje(elPibe);
		elPibe2.setNombre("fede");
		usuario.agregarPesonaje(elPibe2);
		elPibe3.setNombre("doni");
		usuario.agregarPesonaje(elPibe3);
		elPibe4.setNombre("bri");
		usuario.agregarPesonaje(elPibe4);

		Assert.assertNotEquals(elPibe4, usuario.getPersonaje("bri"));
	}

	// Dado un personaje en instancia de juego cuando quiere ingresar a un mundo
	// entonces se verifica que este cumpla con el nivel requerido.
	@Test
	public void nivelRequeridoParaEntrarAlMundo() {
		Mundo mundo = new Mundo("Los grosos", 40);
		Personaje juan = new Elfo(3, 15);
		Personaje javier = new Humano(40, 0);
		Personaje clara = new Orco(65, 56);

		Assert.assertEquals(false, juan.entrarAlMundo(mundo));
		Assert.assertEquals(true, javier.entrarAlMundo(mundo));
		Assert.assertEquals(true, clara.entrarAlMundo(mundo));

	}

	// Dado un personaje y una criatura cuando el personaje sale vencedor
	// entonces adquiere la posibilidad de elegir o no un item aleatorio.
	@Test
	public void meEquipoONoElDrop() {
		Criatura bicho = new Criatura(1);
		Personaje eduardo = new Enano();

		// sin haberse equipado el drop de la criatura
		Assert.assertEquals(10, eduardo.getPuntosDeAtaqueFisico());

		eduardo.atacar(bicho);
		// criatura muerta
		if (!bicho.estaVivo()){
			eduardo = bicho.droppeo(eduardo);
		}
		// luego de haberse equipado el drop
		Assert.assertEquals(20, eduardo.getPuntosDeAtaqueFisico());
	}
	
	@Test
	public void decoratorLanzandoSkilll() {
		Personaje decorado = new Humano();
		decorado.setCasta(new Mago());
		
		Assert.assertEquals(5, decorado.getPuntosDeAtaqueMagico());
		
		decorado = new ConEspadaSkofnung(decorado);
		Assert.assertEquals(15, decorado.getPuntosDeAtaqueMagico());
	}
}
