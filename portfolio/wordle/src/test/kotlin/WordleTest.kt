import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import java.io.File

class WordleTest : StringSpec({
    
    "isValidWord should return true for valid 5-letter words" {
        isValidWord("hello") shouldBe true
        isValidWord("world") shouldBe true
        isValidWord("TESTS") shouldBe true
    }
    
    "isValidWord should return false for invalid words" {
        isValidWord("test") shouldBe false
        isValidWord("testing") shouldBe false
        isValidWord("12345") shouldBe false
        isValidWord("test!") shouldBe false
        isValidWord("") shouldBe false
    }
    
    "readWordList should read valid words from file" {
        val testFile = File("test_words.txt")
        testFile.writeText("apple\nbanana\npeach\ngrape\nmelon\n")
        
        val words = readWordList("test_words.txt")
        
        words shouldContain "apple"
        words shouldContain "peach"
        words shouldContain "grape"
        words shouldContain "melon"
        words.size shouldBe 4
        
        testFile.delete()
    }
    
    "readWordList should filter out invalid words" {
        val testFile = File("test_words2.txt")
        testFile.writeText("apple\ndog\ntesting\npeach\n12345\n")
        
        val words = readWordList("test_words2.txt")
        
        words shouldContain "apple"
        words shouldContain "peach"
        words shouldNotContain "dog"
        words shouldNotContain "testing"
        words shouldNotContain "12345"
        words.size shouldBe 2
        
        testFile.delete()
    }
    
    "pickRandomWord should return a word from the list" {
        val words = mutableListOf("apple", "peach", "grape")
        val picked = pickRandomWord(words)
        
        listOf("apple", "peach", "grape") shouldContain picked
        words.size shouldBe 2
        words shouldNotContain picked
    }
    
    "pickRandomWord should remove the word from the list" {
        val words = mutableListOf("hello")
        val picked = pickRandomWord(words)
        
        picked shouldBe "hello"
        words.size shouldBe 0
    }
    
    "evaluateGuess should return all 1s for perfect match" {
        val result = evaluateGuess("hello", "hello")
        result shouldBe listOf(1, 1, 1, 1, 1)
    }
    
    "evaluateGuess should return all 0s for no match" {
        val result = evaluateGuess("abcde", "fghij")
        result shouldBe listOf(0, 0, 0, 0, 0)
    }
    
    "evaluateGuess should correctly identify partial matches" {
        val result = evaluateGuess("hello", "helps")
        result shouldBe listOf(1, 1, 1, 0, 0)
    }
    
    "evaluateGuess should handle mixed correct and incorrect positions" {
        val result = evaluateGuess("world", "would")
        result shouldBe listOf(1, 1, 0, 1, 1)
    }
    
})
