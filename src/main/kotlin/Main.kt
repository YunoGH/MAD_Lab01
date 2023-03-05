//made together with Ishak Yueksel (05.03.2023)

fun main() {
    val generatedNumber = generateNumber()
    println("Guess the 4-digit number! (Digits can't repeat)")
    //print("$generatedNumber") - for checking the results early
    var guess: String
    var nm: Pair<Int, Int>

    do {
        guess = readLine()!!
        if (hasRepeatingDigits(guess)) {
            println("Error: guess contains repeating digits")
        } else {
            val nm = checkGuess(generatedNumber, guess)
            println("${nm.first}:${nm.second}")
        }
        nm = checkGuess(generatedNumber, guess)
    } while (nm.second != 4)
    println("Congratulations! You guessed the number $generatedNumber")
}

//here we generate a sudo random number by shuffling a lift of one-digit numbers and then return the first 4 values as a String
fun generateNumber(): String {
    val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    digits.shuffle()

    return digits.subList(0, 4).joinToString("")
}

//in checkGuess we go through the input given and compare its 4 digits to the generated one,
//if the correct number is in the right position we add 1 to m as well as mark it as "used" so that it won't get compared again
//otherwise if the guess equals any given number from the generated one, we add 1 to n
fun checkGuess(number: String, guess: String): Pair<Int, Int> {
    var n = 0
    var m = 0
    val used = mutableSetOf<Int>()

    //n = numbers guessed (not in correct position)
    for (i in 0 until 4) {
        if (i !in used && guess[i] in number) {
            n++
        }
    }

    //m = correct number in correct position
    for (i in 0 until 4) {
        if (guess[i] == number[i]) {
            m++
            used.add(i)
        }
    }


    return Pair(n, m)
}

// create a set of the digits in the input string, which removes any duplicates -> if set size is less length,
// then there is at least one duplicate -> returns true otherwise false.
fun hasRepeatingDigits(str: String): Boolean {
    return str.toSet().size < str.length
}
