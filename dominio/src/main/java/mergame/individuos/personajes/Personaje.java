package main.java.mergame.individuos.personajes;

import main.java.mergame.casta.EsDeCasta;
import main.java.mergame.individuos.Individuo;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import main.java.mergame.interfaz.Mundo;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Elfo.class, name = "Elfo"),
		@JsonSubTypes.Type(value = Enano.class, name = "Enano"),
		@JsonSubTypes.Type(value = Humano.class, name = "Humano"),
		@JsonSubTypes.Type(value = Orco.class, name = "Orco"),
})
public interface Personaje extends Individuo{

	void setCasta(EsDeCasta casta);

	EsDeCasta getCasta();

	int getSalud();

	void consumoElixir(int puntos);

	void lanzarSkill(Individuo atacado, String nombreSkill);

	int getPoderMagico();

	boolean entrarAlMundo(Mundo mundo);

	Personaje desequipar(Class decorado);

	boolean tiene(Class decorado);

	String getNombre();

	void setNombre(String nombre);

}
