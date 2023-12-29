package solution

import state.Board
import state.BoardOperations
import state.Disc
import state.RodOperations

class VirtualBoard(val underlyingBoard: Board, val discSizeLimit: Int, val goalRodName: String) : BoardOperations {
    override val goalRod: RodOperations
        get() = rods.find { it.name == goalRodName }!!

    override val rods: Collection<RodOperations>
        get() = underlyingBoard.rods.map { VirtualRod(it, discSizeLimit, goalRodName == it.name) }

    override fun getRod(name: String): RodOperations {
        return VirtualRod(underlyingBoard.getRod(name), discSizeLimit, goalRodName == name)
    }

    override fun moveDisc(from: String, to: String) {
        val fromRod = VirtualRod(underlyingBoard.getRod(from), discSizeLimit, goalRodName == from)
        require(fromRod.contents.isNotEmpty()) {"Cannot move a disc from an empty rod"}

        underlyingBoard.moveDisc(from, to)
    }
}
