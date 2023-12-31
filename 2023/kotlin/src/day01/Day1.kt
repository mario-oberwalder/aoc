

/*
--- Day 1: Trebuchet?! ---

Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.

You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").

As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?
*/

fun main() {
    println("Day1")

    val inputA = readFileAsLinesUsingUseLines("2023/kotlin/day01/resources/inputA.txt")
    val inputB = readFileAsLinesUsingUseLines("2023/kotlin/day01/resources/InputA.txt")

    day1partA(inputA)
    day1partB(inputB)
}

fun day1partA(input: List<String>) {
    println(input)

    val numbers = input
        .map { characters -> characters.filter { it.isDigit() } }
        .filter { string -> string.isNotEmpty() }
    println(numbers)

    val calibrationValues = numbers.map { number -> numberToCalibration(number) }
    println(calibrationValues)

    val result = calibrationValues.sum()
    println(result)
}

fun day1partB(input: List<String>) {
    println(input)

    var lines = input
    println(lines)

    lines = lines
        .map { line -> literalToDigit(line) }
    println(lines)

    val numbers = lines
        .map { characters -> characters.filter { it.isDigit() } }
        .filter { string -> string.isNotEmpty() }
    println(numbers)

    val calibrationValues = numbers.map { number -> numberToCalibration(number) }
    println(calibrationValues)

    val result = calibrationValues.sum()
    println(result)
}

enum class NumberToReplace(val numericRepresentation: String) {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9")
}

fun literalToDigit(line: String): String {
    var modifiedLine = line
    val literals: Set<String> = NumberToReplace.entries.map { entry -> entry.name.lowercase() }.toSet()

    do {
        val result = modifiedLine.findAnyOf(literals)
        result?.second ?: return modifiedLine
        val replacementRange: IntRange =
            result.first..<result.first + result.second.length - 1 //conserve the last character of a literal so overlapping literals stay intact twone -> 21
        modifiedLine = modifiedLine.replaceRange(
            replacementRange,
            NumberToReplace.valueOf(result.second.uppercase()).numericRepresentation
        )
    } while (true)
}

fun numberToCalibration(number: String): Int {
    return (number.first().toString() + number.last().toString()).toInt()
}
