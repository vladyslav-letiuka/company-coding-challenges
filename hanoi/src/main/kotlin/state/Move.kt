package state

data class Move(val from: Rod, val to: Rod, val disc: Disc) {
    override fun toString(): String {
        return "[ Moving: ${from.name} -> ${to.name}. Disc size: ${disc.size}]"
    }
}
