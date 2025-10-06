import kotlin.system.exitProcess

fun main(args: Array<String>) {
	if (args.size < 2) {
		exitProcess(1)
	}
	println(args[0])
	println(args[1])
}
// Task 3.1: command line arguments
