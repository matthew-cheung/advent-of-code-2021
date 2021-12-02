import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        var previous = Integer.MAX_VALUE

        for (line in input) {
            val value = line.toInt()
            if(value > previous) {
                count++
            }
            previous = value
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val window = LinkedList<Int>()

        var count = 0
        var previous = Int.MAX_VALUE
        for (line in input) {
            val newValue = line.toInt()
            window.addLast(newValue)
            if (window.size >= 3) {
                val newSum = window.stream().reduce { a: Int, b: Int -> Integer.sum(a, b) }.orElse(0)
                if (newSum > previous) {
                    count++
                }
                previous = newSum
                window.removeFirst()
            }
        }

        return count
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
