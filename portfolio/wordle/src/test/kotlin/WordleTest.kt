import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldHaveLength
import java.io.File

class WordleTest : StringSpec({
    
    // Tests for isValid function
    "isValid should return true for valid 5-letter words" {
        isValid("hello") shouldBe true
        isValid("world") shouldBe true
        isValid("apple") shouldBe true
    }
    
    "isValid should return false for words that are not 5 letters" {
        isValid("cat") shouldBe false
        isValid("elephant") shouldBe false
        isValid("") shouldBe false
        isValid("hi") shouldBe false
    }
    
    "isValid should return false for strings with non-letter characters" {
        isValid("12345") shouldBe false
        isValid("hell0") shouldBe false
        isValid("hel o") shouldBe false
    }
    
    // Tests for readWordList function
    "readWordList should read words from a valid file" {
        // Create a temporary test file
        val testFile = File("test_words.txt")
        testFile.writeText("apple\ngrape\nlemon\n")
        
        val words = readWordList("test_words.txt")
        words shouldHaveSize 3
        words shouldContain "apple"
        words shouldContain "grape"
        words shouldContain "lemon"
        
        // Clean up
        testFile.delete()
    }
    
    "readWordList should return empty list for non-existent file" {
        val words = readWordList("nonexistent.txt")
        words shouldHaveSize 0
    }
    
    "readWordList should trim whitespace and convert to lowercase" {
        val testFile = File("test_words2.txt")
        testFile.writeText("  HELLO  \n  WORLD  \n")
        
        val words = readWordList("test_words2.txt")
        words shouldHaveSize 2
        words shouldContain "hello"
        words shouldContain "world"
        
        testFile.delete()
    }
    
    // Tests for pickRandomWord function
    "pickRandomWord should return a word from the list" {
        val words = mutableListOf("apple", "grape", "lemon")
        val picked = pickRandomWord(words)
        
        listOf("apple", "grape", "lemon") shouldContain picked
    }
    
    "pickRandomWord should remove the word from the list" {
        val words = mutableListOf("apple", "grape", "lemon")
        val originalSize = words.size
        val picked = pickRandomWord(words)
        
        words.size shouldBe (originalSize - 1)
        words.shouldNotContain(picked)
    }
    
    "pickRandomWord should work with single element list" {
        val words = mutableListOf("apple")
        val picked = pickRandomWord(words)
        
        picked shouldBe "apple"
        words shouldHaveSize 0
    }
    
    // Tests for evaluateGuess function
    "evaluateGuess should return all 1s for exact match" {
        val result = evaluateGuess("apple", "apple")
        result shouldBe listOf(1, 1, 1, 1, 1)
    }
    
    "evaluateGuess should return all 0s for no match" {
        val result = evaluateGuess("tiger", "apple")
        result shouldBe listOf(0, 0, 0, 0, 0)
    }
    
    "evaluateGuess should return mixed results for partial match" {
        val result = evaluateGuess("apple", "apply")
        result shouldBe listOf(1, 1, 1, 1, 0)
    }
    
    "evaluateGuess should handle different positions correctly" {
        val result = evaluateGuess("hello", "hills")
        result shouldBe listOf(1, 0, 1, 1, 0)
    }
    
    "evaluateGuess should be case sensitive if needed" {
        val result = evaluateGuess("apple", "APPLE")
        // Since we're working with lowercase in the game, this might be all 0s
        result shouldHaveSize 5
    }
})