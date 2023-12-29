package state

class Rod(override val name: String, override val isGoal: Boolean) : RodOperations {
    internal val discs = mutableListOf<Disc>()

    override val contents: List<Disc>
        get() = discs.toList()

    override fun addDisc(disc: Disc) {
        val topDisc = discs.lastOrNull()
        if (topDisc != null && topDisc.size < disc.size) {
            throw IllegalMoveException(
                "Cannot add a disc of size ${disc.size} " +
                        "to a rod with a top disc of size ${topDisc.size}"
            )
        }
        discs.add(disc)
    }

    override fun removeDisc(): Disc {
        require(discs.isNotEmpty()) { "Cannot remove a disc from an empty rod" }

        return discs.removeAt(discs.size - 1)
    }
}
