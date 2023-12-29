package state

class MovesLog(internal val moves: MutableList<Move> = mutableListOf()) {
    fun logMove(move: Move) {
        moves.add(move)
    }

    val contents: List<Move>
        get() = moves.toList()

    override fun toString(): String {
        if(moves.isEmpty()) {
            return "[empty log]"
        }
        return moves.map { it.toString() }.joinToString("\n", "Log with ${moves.size} moves:\n")
    }
}
