package x.app.ood

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OodApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("ood lab started.")
    }
}

fun main(args: Array<String>) {
    runApplication<OodApplication>(*args)
}
