package test;

import org.junit.Assert;
import org.junit.Test;

import main.java.mergame.casta.impl.Guerrero;
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
import main.java.mergame.items_con_decorator.impl.ConAnilloDraupnir;
import main.java.mergame.items_con_decorator.impl.ConEscudoSvalinn;
import main.java.mergame.items_con_decorator.impl.ConEspadaSkofnung;
import main.java.mergame.skill.Habilidad;

public class TestMergamePruebasVarias {

	/*
	 * Especificacion de Items ~~~~~~~~~~~~~~~~~~~~~~~ ConEspadaSkofnung: Esta
	 * espada aumenta en 5 pts el ataque ConEscudoSvalinn: Este escudo otorga 10
	 * pts de defensa ConAnilloDraupnir: Este anillo multiplica el ataque x2
	 */

	@Test
	public void quePuedoAgregarItemDeAtaque() {

		Personaje sigmund = new Humano();
		Assert.assertEquals(10, sigmund.getPuntosDeAtaqueFisico());

		// agrego item de ataque 
		sigmund = new ConEspadaSkofnung(sigmund);
		Assert.assertEquals(10 + 10, sigmund.getPuntosDeAtaqueFisico());
	}

	/*---PRUEBAS DEL DECORATOR---*/

	/*
	 * @Test public void quePuedoAgregarAmbosItems() {
	 * 
	 * Personaje sigmund = new Humano(); Assert.assertEquals(1,
	 * sigmund.obtenerPuntosDeAtaque());
	 * 
	 * // agrego item de ataque sigmund = new ConEspadaSkofnung(sigmund);
	 * Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
	 * 
	 * Assert.assertEquals(0, sigmund.obtenerPuntosDeDefensa()); sigmund = new
	 * ConEscudoSvalinn(sigmund); // agrego defensa Assert.assertEquals(10 + 0,
	 * sigmund.obtenerPuntosDeDefensa()); // y no pierdo ataque
	 * Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque()); }
	 * 
	 * @Test public void quePuedoAgregarAmbosItemsInverso() {
	 * 
	 * Personaje sigmund = new Humano(); Assert.assertEquals(1,
	 * sigmund.obtenerPuntosDeAtaque());
	 * 
	 * // agrego item de ataque Assert.assertEquals(0,
	 * sigmund.obtenerPuntosDeDefensa()); sigmund = new
	 * ConEscudoSvalinn(sigmund); // agrego defensa Assert.assertEquals(10 + 0,
	 * sigmund.obtenerPuntosDeDefensa());
	 * 
	 * sigmund = new ConEspadaSkofnung(sigmund); Assert.assertEquals(5 + 1,
	 * sigmund.obtenerPuntosDeAtaque());
	 * 
	 * Assert.assertEquals(10 + 0, sigmund.obtenerPuntosDeDefensa()); }
	 * 
	 * 
	 * @Test public void quePuedoAgregarDosTiposDeItem() { Personaje sigmund =
	 * new Humano(); Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
	 * 
	 * // agrego item de ataque sigmund = new ConEspadaSkofnung(sigmund);
	 * Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
	 * 
	 * // agrego anillo multiplicador (x2) sigmund = new
	 * ConAnilloDraupnir(sigmund); Assert.assertEquals((5 + 1) * 2,
	 * sigmund.obtenerPuntosDeAtaque()); }
	 * 
	 * @Test public void quePuedoAgregarDosTiposDeItem2() { Personaje sigmund =
	 * new Humano(); Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
	 * 
	 * // agrego item de ataque sigmund = new ConEscudoSvalinn(sigmund); //
	 * agrego defensa Assert.assertEquals(10 + 0,
	 * sigmund.obtenerPuntosDeDefensa());
	 * 
	 * // agrego anillo multiplicador (x2) sigmund = new
	 * ConAnilloDraupnir(sigmund); Assert.assertEquals((10 + 0) * 2,
	 * sigmund.obtenerPuntosDeDefensa()); }
	 * 
	 * /*---FIN PRUEBAS DEL DECORATOR---
	 */

	/*
	 * @Test public void hechizo(){ Personaje elPibe = new Humano();
	 * 
	 * Criatura criatura = new Criatura();
	 * 
	 * criatura.lanzarSkill(elPibe, Habilidad.FUEGO);
	 * 
	 * //elPibe curado con 50 de vitalidad Assert.assertEquals((100 - 3),
	 * elPibe.getSalud()); }
	 * 
	 
	 * 
	 * @Test public void mago(){ Personaje elPibe = new Humano();
	 * elPibe.setCasta(new Mago());
	 * 
	 * Personaje ferra = new Orco(); System.out.println(ferra.getSalud());
	 * 
	 * elPibe.lanzarSkill(ferra, Habilidad.CURAR);
	 * System.out.println(ferra.getSalud());
	 * 
	 * elPibe.lanzarSkill(ferra, Habilidad.FUEGO);
	 * System.out.println(ferra.getSalud()); //Assert.assertEquals(elPibe,
	 * usuario.getPersonaje("fede")); }
	 */
	/*
	 * @Test public void poderDobleOrco(){ Orco brian = new Orco();
	 * brian.setCasta(new Mago());
	 * 
	 * Enano maxi = new Enano(); maxi.setCasta(new Mago());
	 * 
	 * System.out.println(brian.getPuntosDeAtaqueMagico());
	 * 
	 * System.out.println(maxi.getSalud()); for (int i=0; i<10;i++){
	 * brian.lanzarSkill(maxi, Habilidad.FUEGO); maxi.lanzarSkill(brian,
	 * Habilidad.FUEGO); System.out.println(maxi.getSalud()); } }
	 */

	@Test
	public void personajeEquipadoLanzandoSkill() {
		Personaje brian = new Orco();
		brian.setCasta(new Mago());

		Personaje maxi = new Enano();
		maxi.setCasta(new Mago());

		System.out.println("puntos magia brian: " + brian.getPuntosDeAtaqueMagico());
		brian = new ConEspadaSkofnung(brian);
		brian = new ConEspadaSkofnung(brian);
		System.out.println("magia brian mas 2 espadas: " + brian.getPuntosDeAtaqueMagico());

		System.out.println("Salud maxi: " + maxi.getSalud());
		for (int i = 0; i < 10; i++) {
			// deberia lanzar el skill con los poderes magicos todos sumados
			brian.lanzarSkill(maxi, Habilidad.FUEGO);
			// maxi.lanzarSkill(brian, Habilidad.FUEGO);
			System.out.println("Salud despues de ser atacado maxi: " + maxi.getSalud());
		}
	}

	@Test
	public void quePuedoAgregarDosTiposDeItem() {
		Personaje sigmund = new Humano();
		Assert.assertEquals(10, sigmund.getPuntosDeAtaqueFisico());

		// agrego item de ataque
		sigmund = new ConEspadaSkofnung(sigmund);
		Assert.assertEquals(10 * 2, sigmund.getPuntosDeAtaqueFisico());

		// agrego anillo multiplicador (x2)
		sigmund = new ConAnilloDraupnir(sigmund);
		Assert.assertEquals((10 * 2) * 2, sigmund.getPuntosDeAtaqueFisico());
	}

	@Test
	public void quePuedoAgregarDosTiposDeItem2() {
		Personaje sigmund = new Humano();
		Assert.assertEquals(10, sigmund.getPuntosDeAtaqueFisico());

		// agrego item de ataque
		sigmund = new ConEscudoSvalinn(sigmund);
		// agrego defensa
		Assert.assertEquals(10 * 2, sigmund.getPuntosDeAtaqueFisico());

		// agrego anillo multiplicador (x2)
		sigmund = new ConAnilloDraupnir(sigmund);
		Assert.assertEquals((10 * 2) * 2, sigmund.getPuntosDeAtaqueFisico());
	}

	/*---FIN PRUEBAS DEL DECORATOR---*/

	/*
	 * @Test public void hechizo(){ Personaje elPibe = new Humano();
	 * 
	 * Criatura criatura = new Criatura();
	 * 
	 * criatura.lanzarSkill(elPibe, Habilidad.FUEGO);
	 * 
	 * //elPibe curado con 50 de vitalidad Assert.assertEquals((100 - 3),
	 * elPibe.getSalud()); }
	 * 
	 * //? Dado un usuario cuando este selecciona "crear personaje" entonces el
	 * personaje es creado.
	 * 
	 * @Test public void creacionPersonaje(){ Usuario usuario = new
	 * Usuario("ferra", "pass1234"); Personaje elPibe = new Humano();
	 * elPibe.setNombre("fede"); usuario.agregarPesonaje(elPibe);
	 * 
	 * Assert.assertEquals(elPibe, usuario.getPersonaje("fede")); }
	 * 
	 
	 * 
	 * @Test public void mago(){ Personaje elPibe = new Humano();
	 * elPibe.setCasta(new Mago());
	 * 
	 * Personaje ferra = new Orco(); System.out.println(ferra.getSalud());
	 * 
	 * elPibe.lanzarSkill(ferra, Habilidad.CURAR);
	 * System.out.println(ferra.getSalud());
	 * 
	 * elPibe.lanzarSkill(ferra, Habilidad.FUEGO);
	 * System.out.println(ferra.getSalud()); //Assert.assertEquals(elPibe,
	 * usuario.getPersonaje("fede")); }
	 */
	@Test
	public void poderDobleOrco() {
		Orco brian = new Orco();
		brian.setCasta(new Mago());

		Humano maxi = new Humano();
		maxi.setCasta(new Mago());

		System.out.println(brian.getPuntosDeAtaqueMagico());

		System.out.println(maxi.getSalud());
		for (int i = 0; i < 150; i++) {
			brian.atacar(maxi);
			maxi.lanzarSkill(brian, Habilidad.FUEGO);
			System.out.println(maxi.getSalud());

		}

		/*
		 * @Test public void personajeEquipadoLanzandoSkill(){ Personaje brian =
		 * new Orco(); brian.setCasta(new Mago());
		 * 
		 * Personaje maxi = new Enano(); maxi.setCasta(new Mago());
		 * 
		 * System.out.println(brian.getPuntosDeAtaqueMagico());
		 * System.out.println("poder magico con espada"); brian = new
		 * ConEspadaSkofnung(brian); brian = new ConEspadaSkofnung(brian); brian
		 * = new ConEspadaSkofnung(brian);
		 * System.out.println(brian.getPuntosDeAtaqueMagico());
		 * 
		 * System.out.println(maxi.getSalud()); for (int i=0; i<10;i++){
		 * //deberia lanzar el skill con los poderes magigos todos sumados
		 * brian.lanzarSkill(maxi, Habilidad.FUEGO); maxi.lanzarSkill(brian,
		 * Habilidad.FUEGO); System.out.println(maxi.getSalud()); } }
		 */
	}
	
	
}