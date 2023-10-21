import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8
import java.net.URL
import kotlinx.serialization.*
import kotlinx.serialization.json.*

fun digestMessage(message: String): String {
    val bytes = message.toByteArray(UTF_8)
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(bytes)
    return digest.joinToString("") { "%02x".format(it) }
}

@Serializable
data class Language(val isSelected: Boolean, val displayName: String)

fun main() {
    val json = URL("https://usignin-73ry6xcm2q-uc.a.run.app/allLanguages?currentCultureIsoCode=en-US").readText()
    val languages = Json.decodeFromString<Map<String, Language>>(json)

    println("Please select a language:")
    val indexedLanguages = languages.entries.toList()
    for ((index, language) in indexedLanguages.withIndex()) {
        println("${index + 1}. ${language.value.displayName}")
    }

    val choice = readLine()?.toIntOrNull()
    if (choice != null && choice in 1..indexedLanguages.size) {
        val selectedLanguage = indexedLanguages[choice - 1]
        println("You selected ${selectedLanguage.value.displayName}")
    } else {
        println("Invalid selection")
    }
    
    val message = "Hello, World!"
    println(message)
    val hash = digestMessage(message)
    println(hash)
}
