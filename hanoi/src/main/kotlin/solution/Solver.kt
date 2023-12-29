package solution

import state.Board
import state.BoardOperations
import state.MovesLog

class Solver(private val board: Board) {

    fun solve(): MovesLog {
        solveForBoardProjection(board)

        return board.movesLog
    }

    private fun solveForBoardProjection(boardProjection: BoardOperations) {
        if (isSolved(boardProjection)) {
            return
        }


        val largestDiscByRod = boardProjection.rods.associateWith { rod ->
            rod.contents.firstOrNull()
        }
            .filter { it.value != null }
            .maxBy { it.value!!.size }

        val largestDisc = largestDiscByRod.value

        val startRod = largestDiscByRod.key
        val goalRod = boardProjection.goalRod
        val intermediateRod = boardProjection.rods.find { it.name != startRod.name && it.name != goalRod.name }!!

        solveForBoardProjection(
            VirtualBoard(
                underlyingBoard = board,
                discSizeLimit = largestDisc!!.size - 1,
                goalRodName = intermediateRod.name))

        boardProjection.moveDisc(startRod.name, goalRod.name)

        solveForBoardProjection(
            VirtualBoard(
                underlyingBoard = board,
                discSizeLimit = largestDisc.size - 1,
                goalRodName = goalRod.name))

        check(isSolved(boardProjection)) { "Board projection for discs of size ${largestDisc.size} and under " +
                "with the goal of moving all discs to ${goalRod.name} is not solved" }
    }

    private fun isSolved(board: BoardOperations): Boolean =
        board.rods.all {
            it.isGoal || it.contents.isEmpty()
        }

}
