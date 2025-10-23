import java.io.File
import kotlin.random.Random

// Function 1: Check if a word is valid (exactly 5 letters)
fun isValid(word: String): Boolean {
    return word.length == 5 && word.all { it.isLetter() }
}

// Function 2: Read word list from file
fun readWordList(filename: String): MutableList<String> {
    val words = mutableListOf<String>()
    val file = File(filename)
    if (file.exists()) {
        file.forEachLine { line ->
            val word = line.trim().lowercase()
            if (word.isNotEmpty()) {
                words.add(word)
            }
        }
    }
    return words
}

// Function 3: Pick a random word from the list and remove it
fun pickRandomWord(words: MutableList<String>): String {
    val index = Random.nextInt(words.size)
    return words.removeAt(index)
}

// Function 4: Obtain a valid guess from the user
fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val input = readLine()?.trim()?.lowercase() ?: ""
        if (isValid(input)) {
            return input
        } else {
            println("Invalid guess. Please enter exactly 5 letters.")
        }
    }
}

// Function 5: Evaluate the guess against the target word
// Returns a list of 5 integers: 0 = no match, 1 = match
fun evaluateGuess(guess: String, target: String): List<Int> {
    val result = mutableListOf<Int>()
    for (i in 0 until 5) {
        if (guess[i] == target[i]) {
            result.add(1)
        } else {
            result.add(0)
        }
    }
    return result
}

// Function 6: Display the guess with matches shown
fun displayGuess(guess: String, matches: List<Int>) {
    val display = StringBuilder()
    for (i in 0 until 5) {
        if (matches[i] == 1) {
            display.append(guess[i])
        } else {
            display.append('?')
        }
    }
    println(display.toString())
}