fun main() {
    val exampleLines = readInput("Day03_Example")
    val lines = readInput("Day03")

    println("Part 1")
    println(part1(exampleLines))
    println(part1(lines))
    println()

    println("Part 2")
    println(part2(exampleLines))
    println(part2(lines))
}

private fun part1(lines: List<String>): Int {
    val numBits = lines.stream().findAny().map { line -> line.length }.orElse(0)
    var gamma = ""
    var epsilon = ""

    for (i in 0 until numBits) {
        gamma += getMostOrLeastCommonBitAtPosition(lines, i, CRITERIA.MAX)
        epsilon += getMostOrLeastCommonBitAtPosition(lines, i, CRITERIA.MIN)
    }

    return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
}

private fun getMostOrLeastCommonBitAtPosition(lines: List<String>, i: Int, criteria: CRITERIA): Char? {
    val grouped = lines.map { line -> line[i] }.groupBy { digit -> digit }

    if(grouped['0']?.size == grouped['1']?.size) {
        return when (criteria) {
            CRITERIA.MAX -> '1'
            CRITERIA.MIN -> '0'
        }
    }
    
    return when (criteria) {
        CRITERIA.MAX -> grouped.maxByOrNull { entry -> entry.value.size }?.key
        CRITERIA.MIN -> grouped.minByOrNull { entry -> entry.value.size }?.key
    }
}

private fun part2(lines: List<String>): Int {
    val oxygenRating = getRatingByCriteria(lines, CRITERIA.MAX)
    val co2Rating = getRatingByCriteria(lines, CRITERIA.MIN)
    return oxygenRating * co2Rating
}

private fun getRatingByCriteria(lines: List<String>, criteria: CRITERIA): Int {
    val numBits = lines.stream().findAny().map { line -> line.length }.orElse(0)

    var currentLines = lines
    for (i in 0 until numBits) {
        if (currentLines.size > 1) {
            val matchingBit = getMostOrLeastCommonBitAtPosition(currentLines, i, criteria)
            currentLines = currentLines.filter { line -> line[i] == matchingBit }.toList()
        }
    }

    return if (currentLines.size != 1) 0 else Integer.parseInt(currentLines[0], 2)
}

enum class CRITERIA {
    MAX, MIN
}


