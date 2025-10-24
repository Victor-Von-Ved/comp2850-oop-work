fun main() {
    println("Welcome to Wordle!")
    println()
    
    val words = try {
        readWordList("data/words.txt")
    } catch (e: Exception) {
        println("Error reading word list: ${e.message}")
        return
    }
    
    if (words.isEmpty()) {
        println("No valid words found in the file.")
        return
    }
    
    val target = pickRandomWord(words)
    val maxAttempts = 10
    var won = false
    
    for (attempt in 1..maxAttempts) {
        val guess = obtainGuess(attempt)
        val matches = evaluateGuess(guess, target)
        displayGuess(guess, matches)
        
        if (guess == target) {
            println()
            println("Congratulations! You guessed the word correctly!")
            won = true
            break
        }
        println()
    }
    
    if (!won) {
        println("Sorry, you've run out of guesses.")
        println("The word was: $target")
    }
}
