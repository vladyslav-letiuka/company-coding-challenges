package state

class Board private constructor(override val rods: List<Rod>) : BoardOperations {
    override val goalRod: Rod
    val movesLog = MovesLog()

    init {
        require(rods.size == 3) { "Board must have 3 rods" }

        var goalRod : Rod? = null
        rods.forEach {
            if(it.isGoal) {
                require(goalRod == null) { "Board can only have one goal rod" }
                goalRod = it
            }
        }
        require(goalRod != null) { "Board must have one goal rod" }

        this.goalRod = goalRod!!
    }

    override fun getRod(name: String): Rod {
        return rods.find { it.name == name } ?: throw IllegalArgumentException("Rod $name not found")
    }

    override fun moveDisc(from: String, to: String) {
        val fromRod = getRod(from)
        val toRod = getRod(to)

        val disc = fromRod.removeDisc()
        toRod.addDisc(disc)

        movesLog.logMove(Move(fromRod, toRod, disc))
    }

    companion object {
        fun new(size: Int) : Board {
            val startingRod = Rod("#1", false)
            val intermediateRod = Rod("#2", false)
            val goalRod = Rod("#3", true)

            for(i in (1..size).reversed()) {
                startingRod.addDisc(Disc(i))
            }

            return Board(listOf(startingRod, intermediateRod, goalRod))
        }
    }
}