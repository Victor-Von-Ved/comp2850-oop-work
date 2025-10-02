fun greetingFor(target: String): String {
    val greeting = setOf("hello", "hi", "G'day").random()
    return "$greeting $target!"
}
