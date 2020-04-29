package msk.pobazar.wcquiz.domain.model

data class GameResult(
    val results: List<Result>,
    val time: Long
) {

    val winStrick: Int
        get() = calculateWinStrick()

    val countRight: Int
        get() = results.count { it.isRight }

    val countAll: Int
        get() = results.size

    class Result(
        val question: String,
        val answerRight: String,
        val image: String,
        private val answer: String
    ) {
        val isRight: Boolean
            get() = answer == answerRight
    }

    private fun calculateWinStrick(): Int {
        var maxWin = 0
        var currWin = 0
        for (i in results.indices) {
            if (results[i].isRight)
                currWin++
            else {
                if (currWin > maxWin) {
                    maxWin = currWin
                }
                currWin = 0
            }
        }
        if (currWin > maxWin) {
            maxWin = currWin
        }
        return maxWin
    }
}