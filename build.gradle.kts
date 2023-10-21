plugins {
    // Apply the application plugin
    application
    kotlin("jvm") version "1.5.21"
}

application {
    // Set the mainClassName based on your package (or leave as "MainKt" for default package)
    mainClassName = "MainKt"
}

dependencies {
    // Add your project dependencies here
}

repositories {
    mavenCentral() // Add this line to include the Maven Central repository
}
