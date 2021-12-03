fun main() {

    fun part1(input: List<String>): Int {
        return countNumberOfIncreases(getInputAsInts(input))
    }

    fun part2(input: List<String>): Int {
        val slidingWindowSums = getInputAsInts(input).windowed(3).map { list -> list.sum() }
        return countNumberOfIncreases(slidingWindowSums)
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun getInputAsInts(input: List<String>) = input.map { s -> s.toInt() }

fun countNumberOfIncreases(list: List<Int>) = list
    .windowed(2)
    .count { (previous, current) -> current > previous }
