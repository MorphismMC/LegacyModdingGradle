plugins {
    `maven-publish`
    `java`
}

group = "com.example.examplemod"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenLocal()
    maven {
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
    }
    maven {
        name = "cursemaven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
}