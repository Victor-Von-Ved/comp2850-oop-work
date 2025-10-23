fun main() {
    println("Welcome to Wordle!")
    println("==================")
    println()
    
    // Read the word list from data/words.txt
    val words = readWordList("data/words.txt")
    
    if (words.isEmpty()) {
        println("Error: Could not load word list from data/words.txt")
        return
    }
    
    println("Loaded ${words.size} words.")
    
    // Pick a random word as the target
    val target = pickRandomWord(words)
    
    // Allow up to 10 attempts
    val maxAttempts = 10
    var won = false
    
    for (attempt in 1..maxAttempts) {
        // Get a valid guess from the user
        val guess = obtainGuess(attempt)
        
        // Evaluate the guess
        val matches = evaluateGuess(guess, target)
        
        // Display the result
        displayGuess(guess, matches)
        
        // Check if the player won
        if (matches.all { it == 1 }) {
            println("\nCongratulations! You guessed the word '$target' in $attempt attempts!")
            won = true
            break
        }
    }
    
    if (!won) {
        println("\nGame Over! You've used all $maxAttempts attempts.")
        println("The word was: $target")
    }
}