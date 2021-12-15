open class ArmaDeFuego(
    private val nombre: String,
    var municion: Int,
    protected var municionARestar: Int = 1,
    private val tipoDeMunicion: String,
    private val danio: Int,
    private val radio: String
) {
    init {
        require(radio == "Pequeño" || radio == "Amplio" || radio == "pequeño" || radio == "amplio") { "El radio debe ser Pequeño o Amplio" }
        require(municion >= 0) { "Te has quedado sin municion" }
    }

    override fun toString(): String = "Al arma $nombre le quedan $municion balas y dispara con un daño de $danio"
    open fun dispara(): Int = if (municion >= municionARestar) municion - municionARestar else municion
    open fun recagar(r: Int): Int {
        municion += r
        return municion
    }
}

class Pistola(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: String) :
    ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {
    override fun dispara(): Int {
        val resta = municionARestar * 1
        if (municion >= resta) municion -= resta else municion
        return municion
    }
}

class Rifle(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: String) :
    ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {
    override fun dispara(): Int {
        val resta = municionARestar * 2
        if (municion >= resta) municion -= resta else municion
        return municion
    }

}

class Bazooka(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: String) :
    ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio) {
    override fun dispara(): Int {
        val resta = municionARestar * 3
        if (municion >= resta) municion -= resta else municion
        return municion
    }
}

fun main() {
    val bazooka = Bazooka("B123", 4, 1, "B1", 20, "amplio")
    val pistola = Pistola("P123", 15, 1, "P1", 4, "pequeño")
    val rifle = Rifle("R123", 30, 3, "R1", 9, "amplio")
    val lista = listOf(bazooka, pistola, rifle)
    val disparos = mutableMapOf<String, ArmaDeFuego>()
    for (i in 0 until 6) {
        disparos["$i"] = lista.random()
    }
    disparos.mapValues { it.value.dispara(); println(it.value) }
}