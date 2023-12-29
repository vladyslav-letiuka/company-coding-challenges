package state

interface RodOperations {
    val name: String
    val contents: List<Disc>
    val isGoal: Boolean
    fun addDisc(disc: Disc)
    fun removeDisc() : Disc
}