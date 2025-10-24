import java.io.File
import kotlin.random.Random

fun isValidWord(word: String): Boolean {
    return word.length == 5 && word.all { it.isLetter() }
}

fun readWordList(filename: String): MutableList<String> {
    val words = mutableListOf<String>()
    val file = File(filename)
    if (file.exists()) {
        file.forEachLine { line ->
            val word = line.trim().lowercase()
            if (isValidWord(word)) {
                words.add(word)
            }
        }
    }
    return words
}

fun pickRandomWord(words: MutableList<String>): String {
    if (words.isEmpty()) {
        throw IllegalArgumentException("Word list is empty")
    }
    val index = Random.nextInt(words.size)
    return words.removeAt(index)
}

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val input = readLine()?.trim()?.lowercase() ?: ""
        if (isValidWord(input)) {
            return input
        } else {
            println("Invalid word. Please enter exactly 5 letters.")
        }
    }
}

fun evaluateGuess(guess: String, target: String): List<Int> {
    val result = MutableList(5) { 0 }
    val targetChars = target.toCharArray()
    val used = BooleanArray(5) { false }
    
    for (i in 0 until 5) {
        if (guess[i] == target[i]) {
            result[i] = 1
            used[i] = true
        }
    }
    
    return result
}

fun displayGuess(guess: String, matches: List<Int>) {
    val output = StringBuilder()
    for (i in 0 until 5) {
        if (matches[i] == 1) {
            output.append(guess[i])
        } else {
            output.append('?')
        }
    }
    println(output.toString())
}
