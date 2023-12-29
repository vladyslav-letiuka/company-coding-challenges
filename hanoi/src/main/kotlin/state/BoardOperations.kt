package state

interface BoardOperations {
    val goalRod: RodOperations
    val rods: Collection<RodOperations>
    fun getRod(name: String): RodOperations
    fun moveDisc(from: String, to: String)
}
