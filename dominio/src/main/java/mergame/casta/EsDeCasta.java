package main.java.mergame.casta;

import java.util.Map;

import main.java.mergame.casta.impl.Guerrero;
import main.java.mergame.casta.impl.Mago;
import main.java.mergame.individuos.personajes.impl.Elfo;
import main.java.mergame.individuos.personajes.impl.Enano;
import main.java.mergame.individuos.personajes.impl.Humano;
import main.java.mergame.individuos.personajes.impl.Orco;
import main.java.mergame.skill.Habilidad;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Guerrero.class, name = "Guerrero"),
		@JsonSubTypes.Type(value = Mago.class, name = "Mago")
})
public abstract class EsDeCasta {

	protected Map <String, Habilidad> habilidades;
	protected int poderMagicoBase;
	protected int poderFisicoBase;

	public abstract Habilidad getSkill(String skill);

	public int getPoderMagicoBase() {
		return poderMagicoBase;
	}

	public int getPoderFisicoBase() {
		return poderFisicoBase;
	}

}