package decorator;
 
import org.junit.Assert;
import org.junit.Test;
 
public class TestDecorator {
   
    /*
     * Especificación de Items
     * ~~~~~~~~~~~~~~~~~~~~~~~
     * ConEspadaSkofnung: Esta espada aumenta en 5 pts el ataque
     * ConEscudoSvalinn: Este escudo otorga 10 pts de defensa
     * ConAnilloDraupnir: Este anillo multiplica el ataque x2
     */
   
    @Test
    public void quePuedoAgregarItemDeAtaque() {
 
        Personaje sigmund = new Humano();
        Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego item de ataque
        sigmund = new ConEspadaSkofnung(sigmund);
        Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
    }
 
    
   @Test
    public void quePuedoAgregarAmbosItems() {
 
        Personaje sigmund = new Humano();
        Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego item de ataque
        sigmund = new ConEspadaSkofnung(sigmund);
        Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
 
        Assert.assertEquals(0, sigmund.obtenerPuntosDeDefensa());
        sigmund = new ConEscudoSvalinn(sigmund);
        // agrego defensa
        Assert.assertEquals(10 + 0, sigmund.obtenerPuntosDeDefensa());
        // y no pierdo ataque
        Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
    }
    
    @Test
    public void quePuedoAgregarAmbosItemsInverso() {
 
        Personaje sigmund = new Humano();
        Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego item de ataque
        Assert.assertEquals(0, sigmund.obtenerPuntosDeDefensa());
        sigmund = new ConEscudoSvalinn(sigmund);
        // agrego defensa
        Assert.assertEquals(10 + 0, sigmund.obtenerPuntosDeDefensa());
        
        sigmund = new ConEspadaSkofnung(sigmund);
        Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());

        Assert.assertEquals(10 + 0, sigmund.obtenerPuntosDeDefensa());
    }
 
    
    @Test
    public void quePuedoAgregarDosTiposDeItem() {
        Personaje sigmund = new Humano();
        Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego item de ataque
        sigmund = new ConEspadaSkofnung(sigmund);
        Assert.assertEquals(5 + 1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego anillo multiplicador (x2)
        sigmund = new ConAnilloDraupnir(sigmund);
        Assert.assertEquals((5 + 1) * 2, sigmund.obtenerPuntosDeAtaque());
    }
    
    @Test
    public void quePuedoAgregarDosTiposDeItem2() {
        Personaje sigmund = new Humano();
        Assert.assertEquals(1, sigmund.obtenerPuntosDeAtaque());
 
        // agrego item de ataque
        sigmund = new ConEscudoSvalinn(sigmund);
        // agrego defensa
        Assert.assertEquals(10 + 0, sigmund.obtenerPuntosDeDefensa());
 
        // agrego anillo multiplicador (x2)
        sigmund = new ConAnilloDraupnir(sigmund);
        Assert.assertEquals((10 + 0) * 2, sigmund.obtenerPuntosDeDefensa());
    }
    
    @Test //suponiendo que consumiendo un elixir incrementa los puntos de ataques netos del humano.
    public void conElixir(){
    	Personaje elPibe = new Humano();
    	elPibe = new ConEspadaSkofnung(elPibe);
    	Assert.assertEquals((5 + 1), elPibe.obtenerPuntosDeAtaque());
    	
    	elPibe.consumoElixir(3);
    	
    	Assert.assertEquals((5 + (1 + 3)), elPibe.obtenerPuntosDeAtaque());
    	
    	
    }
    
    @Test
    public void hechizo(){
    	Personaje elPibe = new Humano();
    	
    	Criatura criatura = new Criatura();
    	
    	criatura.lanzarHechizo("Curar", elPibe);
    	
    	//elPibe curado con 50 de vitalidad
    	Assert.assertEquals((100 + 50), elPibe.salud);
    }
}
