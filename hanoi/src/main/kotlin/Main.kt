import solution.Solver
import state.Board

fun main(args: Array<String>) {

    val board = Board.new(8)
    val solver = Solver(board)
    val solution =
        try {
            solver.solve()
        } finally {
            println(board.movesLog)
        }
}