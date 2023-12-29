package solution

import state.Disc
import state.Rod
import state.RodOperations

class VirtualRod(private val underlyingRod: Rod,
                 private val discSizeLimit: Int,
                 override val isGoal: Boolean) : RodOperations {
    override val name: String
        get() = underlyingRod.name

    override val contents: List<Disc>
        get() = underlyingRod.discs.filter { it.size <= discSizeLimit }


    override fun addDisc(disc: Disc) {
        require(disc.size <= discSizeLimit) {
            "Virtual rod with size limit $discSizeLimit " +
                    "does not support adding a disc of size ${disc.size}"
        }
        underlyingRod.addDisc(disc)
    }

    override fun removeDisc(): Disc {
        require(contents.isNotEmpty()) { "Cannot remove a disc from an empty rod" }

        return underlyingRod.removeDisc()
    }
}