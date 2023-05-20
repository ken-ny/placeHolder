package com.dam.placeholder.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase con diferentes listas de datos para pruebas que devuelve de manera
 * aleatoria con los diferentes métodos.
 * 
 * @author kenni
 *
 */
public class RandomLists {

	private List<String> cardList = Arrays.asList("Caballería aeronauta", "Aerocapellán",
			"Precipitador de la emboscada", "Secuelas de la calamidad", "Contragolpe mortal", "Desencantar",
			"Minero del Gran Desierto", "En la trinchera", "Mandato de Kayla", "Reconstrucción de Kayla",
			"Deponer las armas", "Loran del Tercer Camino", "Loran, discípula de la historia", "Huida de Loran",
			"Producción en cadena", "Excavación meticulosa", "Disciplina militar", "Myrel, Escudo de Argivia",
			"Vanguardia de la falange", "Ingeniero de piedras de poder", "Pena de prisión", "Reutilizar",
			"Oficial de alistamiento", "Reparar y recargar", "Veterana del asedio", "Partición del alma",
			"Red estática", "Superviviente de Korlis", "Arquitecta de tópteros", "Recibimiento de Tocasia",
			"Unión del Tercer Camino", "Élite del señor guerrero", "Médica yotiana", "Ensamblador autónomo",
			"Trilladora de combate", "Distribuidor de pelotones", "Secuaz chatarreño", "Serafín de acero",
			"Pebetero de Tocasia", "Sylex de Urza", "Espada de poder de la veterana", "Yotiano de primera línea",
			"Mariscal aéreo", "Organizar", "Desmontar", "Desincronizar", "Drafna, fundador de Lat-Nam",
			"Arqueóloga fallaji", "Flujo de conocimiento", "Forjar el Ancla", "Hurkyl, maga experta",
			"Última meditación de Hurkyl", "Enfriamiento involuntario", "Guardiana de la cadencia", "Roc de Koilos",
			"Adepto de Lat-Nam", "Máquinas sobre la materia", "Animación de la Piedra de Fortaleza",
			"En armonía con el Multiverso", "Agente de rescate", "Rayo dispersor", "Oficial del tiro celeste",
			"División de la piedra de poder", "Lección severa", "Alzar el vuelo", "Teferi, peregrino temporal",
			"Sabio del Tercer Camino", "Mecánica de tópteros", "Urza, prodigio de las piedras", "Mandato de Urza",
			"Rechazo de Urza", "Subyugación de la Piedra de Debilidad", "Comando alado", "Centinela del céfiro",
			"Agente arcano", "Baluarte costero", "Mensajero de combate", "Coloso cargaprofunda", "Metamorfo corpulento",
			"Tóptero avistador", "Máquina impulsada", "El Ancla Temporal", "Rompementes terisiano",
			"Ashnod, mecánica de la carne", "Intervención de Ashnod", "Carnicero bélico", "Langosta carroñera",
			"Corromper", "Intención diabólica", "Discípulos de Gix", "Desfigurar", "Sueños de acero y aceite",
			"Soldadura de emergencia", "Entrega fatídica", "Gix, magistrado de Yawgmoth", "Caricia de Gix",
			"Mandato de Gix", "Infiltrada de Gix", "Titiritero de Gix", "Desollacráneos de Gix", "Alimaña mordiente",
			"Golpe a la garganta", "Conclusión macabra", "Consagradora gorgoteante", "Negociación hostil",
			"Acróbata de la zona mortífera", "Sombra de la miseria", "Instante de desafío", "Nadie se queda atrás",
			"Remordimiento abrumador", "Dilema doloroso", "Fractura de la piedra de poder", "Megatopo voraz",
			"Vigilancia thran", "Traxodemonio", "Asaltatrincheras", "Segador de Ashnod", "Aparecido de arcilla",
			"Garra excavadora", "Arado de guerra embestidor", "Engullecarnes pirexiano", "Transmutado latigocortante",
			"Iracundo chatarreño", "Altar de transmutación", "Corona del transmutado", "Carrera armamentística",
			"Reencuentro amargo", "Fin de la hermandad", "Reclutas de infantería", "Destino dracónico",
			"Cantor de forja enano", "Estallido en la excavación", "La caída de Kroog", "Danzacadenas fallaji",
			"Feldon, excavador de Rónom", "Fauceniza gigante", "Trasgo insensato", "Buscapiedras astado",
			"Guerra mecanizada", "Mishra, excavador prodigioso", "Mandato de Mishra", "Dominio de Mishra",
			"Arremetida de Mishra", "Lanzaveloz del monasterio", "Rayo aniquilador", "Surgir de las trincheras",
			"Toro fortachón de Penregon", "Ráfaga pírrica", "Asolación total", "Cazadora de rocs",
			"Patrullarriscos de Sardia", "Rivalidad fraternal", "Aunapedazos de Tomakul",
			"Tirano de la Cordillera Kher", "Bombardeo desatado", "Visiones de Pirexia", "Golpe en remolino",
			"Autómata relámpago", "Dragón mecánico fallaji", "Demoledora pesada", "Destructor de Mishra",
			"Mesa de investigación de Mishra", "Mestizo chatarreño", "Batallón de rayoartrópodos",
			"Animista de aleación", "Oportunista argotiana", "Duende argotiana", "Osadía", "Despertar del bosque",
			"Armadura de maderapálida", "Rondador de maderapálida", "Fauceafilada excavadora", "Contraemboscada",
			"Protector de Citanul", "Confrontación épica", "Desaparecer de la historia", "Excavación fallaji",
			"Chamán de la fauna", "Niebla de guerra", "Cazadora de Gaia", "Obsequio de Gaia", "Crecimiento gigante",
			"Portador torcerraíz", "Gwenna, Ojos de Gaia", "Reclusa acaparadora", "Báloth obstinado",
			"Patrulla perimetral", "Buscacero de Sarith", "Disparo abatidor", "Creación de Tawnos",
			"Cría de sierpe endentecida", "Mandato de Titania", "Guardia de honor de Tomakul", "Cosecha ineficiente",
			"Gólem pedrerrama", "Talacunas", "Ácaro desestabilizador", "Triturador de acero",
			"Máscara del artesano del jade", "Behemot perenne", "Amalgama cablerraíz", "Coloso oxidado",
			"Simulacro simiesco", "Ingenieras de arbalestas", "Fabricabaterías", "Ritualista brote lúgubre",
			"Evangelista de síntesis", "Vanguardia fallaji", "Hajar, guardaespaldas leal",
			"Harbin, aviador de vanguardia", "Heroína de las dunas", "Genio de la chatarrería",
			"Legiones hechas cenizas", "Mishra, domador del mak fawa", "Reina Kayla bin-Kroog",
			"Saheeli, maestra de filigrana", "Megasierpe de Sarinth", "Araña pescadora celeste", "Tawnos, el Juguetero",
			"Iconoclasta del Tercer Camino", "Tocasia, mentora de la excavación", "Urza, príncipe de Kroog",
			"Disidente yotiano", "Estratega yotiana", "Serpiente filoespiral", "Campeón de arcilla",
			"Alas de aeronauta", "Vengador argiviano", "Nivelador del paisaje urbano", "Refractor de energía",
			"Bomba trasga", "Estatua flotante", "Liberador, tóptero de guerra de Urza", "Operario de la mina",
			"Portal hacia Pirexia", "Operario de la central de energía", "Tóptero reconstruido",
			"Refinería de piedras residuales", "Centinela espectral", "El Ataúd de Estasis", "Ejemplar de acero",
			"El Cerebro Pétreo", "Unidad de recuperación de piedras", "Guardia de la cueva su-chi",
			"Entrega de suministros", "Draco formaligera", "Matriz de la simetría", "Traje de poder thran",
			"Araña thran", "Operario de la torre", "Forja del campo de batalla", "Zona de explosión", "Matorrales",
			"Campo de demolición", "Terrenos expansivos", "Cabeza de playa fortificada", "Salón de Tagsin",
			"Yermos de Llanowar", "Fundición de Mishra", "Excavación de Tocasia", "Río subterráneo",
			"Dragón mecánico pirexiano", "Titania, Voz de Gaia", "Mishra, poseído por Gix", "Urza, señor protector",
			"Las Piedras de Fortaleza y Debilidad", "Argoth, santuario de la naturaleza");

	private List<String> expansionList = Arrays.asList("D Booster Set 08: Minerva Rising", "La Guerra de los Hermanos",
			"Dominaria", "Gremios de Ravnica", "La lealtad de Ravnica", "La Guerra de la Chispa",
			"El trono de Eldraine", "Theros: Más alla de la muerte", "Ikoria: Mundo de behemots",
			"D Booster Set 09: Dragontree Invasion", "D Booster Set 10: Dragon Masquerade",
			"D Booster Set 11: Clash of the Heroes");

	private List<String> rarityList = Arrays.asList("C", "R", "RR", "RRR", "SP", "Common", "Rare", "Uncommon",
			"Mythic");

	private List<String> conditionList = Arrays.asList("B", "P", "G", "M", "NM");

	private List<String> shippingState = Arrays.asList("bought", "paid", "shipped", "arrived", "evaluated");

	/**
	 * Devuelve el nombre de una carta aleatoria entre todas las disponibles
	 * 
	 * @return nombre de carta aleatoria
	 */
	public String getRandomCard() {
		return this.cardList.get(randomPosition(this.cardList.size()));
	}

	/**
	 * Devuelve el nombre de una expansión aleatoria entre todas las disponibles
	 * 
	 * @return
	 */
	public String getRandomExpansion() {
		return this.expansionList.get(randomPosition(this.expansionList.size()));
	}

	/**
	 * 
	 * @return
	 */
	public String getRandomRarity() {
		return this.rarityList.get(randomPosition(this.rarityList.size()));
	}

	/**
	 * Devuelve una condición aleatoria entre todas las disponibles
	 * 
	 * @return
	 */
	public String getRandomCondition() {
		return this.conditionList.get(randomPosition(this.conditionList.size()));
	}

	/**
	 * Devuelve un estado aleatorio entre todos los disponibles
	 * 
	 * @return
	 */
	public String getRandomState() {
		return this.shippingState.get(randomPosition(this.shippingState.size()));
	}

	/**
	 * Devuelve una posición aleatoria para cada lista
	 * 
	 * @param size
	 * @return
	 */
	private int randomPosition(int size) {
		return ThreadLocalRandom.current().nextInt(size);
	}
}
