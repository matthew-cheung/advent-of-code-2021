fun main() {

    fun part1(): Position {
        val position = Position(0,0)

        val lines = readInput("Day02")
        for (line in lines) {
            val parts = line.split("\\s+".toRegex())
            val increment = parts[1].toInt()
            when (parts[0]) {
                "forward" -> {
                    position.horizontal += increment
                }
                "down" -> {
                    position.depth += increment
                }
                "up" -> {
                    position.depth -= increment
                }
                else -> {
                    throw IllegalStateException("bad input: $line")
                }
            }
        }
        return position
    }

    fun part2(): Position {
        val position = Position(0,0)

        val lines = readInput("Day02")

        var aim = 0
        for (line in lines) {
            val parts = line.split("\\s+".toRegex())
            val increment = parts[1].toInt()
            when (parts[0]) {
                "forward" -> {
                    position.horizontal += increment
                    position.depth += increment * aim
                }
                "down" -> {
                    aim += increment
                }
                "up" -> {
                    aim -= increment
                }
                else -> {
                    throw IllegalStateException("bad input: $line")
                }
            }
        }
        return position
    }

    println(part1().toAnswer())
    println(part2().toAnswer())
}

class Position(var horizontal: Int, var depth: Int) {
    override fun toString(): String {
        return "Position(horizontal=$horizontal, depth=$depth)"
    }

    fun toAnswer() = horizontal * depth
}
