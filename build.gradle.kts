plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.morphismmc"
version = "1.0.0"
base {
    archivesName.set("legacymodding-gradle")
}

repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Fabric"
                url = uri("https://maven.fabricmc.net/")
            }
        }
        filter {
            includeModule("net.fabricmc", "fabric-loom-native")
        }
    }
    maven {
        name = "NeoForged"
        url = uri("https://maven.neoforged.net/releases")
        content {
            includeGroup("net.neoforged")
        }
    }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(gradleApi())
    compileOnly("com.intellij:annotations:9.0.4")
    testCompileOnly("com.intellij:annotations:9.0.4")
    implementation("gradle.plugin.org.jetbrains.gradle.plugin.idea-ext:gradle-idea-ext:1.2")

    testImplementation(enforcedPlatform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.assertj:assertj-core:3.26.0")
    testImplementation(gradleTestKit())
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}


gradlePlugin {
    website.set("https://github.com/MorphismMC/LegacyModdingGradle")
    vcsUrl.set("https://github.com/MorphismMC/LegacyModdingGradle.git")

    plugins {
        create("legacymodding") {
            id = "io.github.morphismmc.legacymodding"
            implementationClass = "io.github.morphismmc.legacymodding.LegacyModdingPlugin"
            displayName = "Legacy Mod Development Plugin"
            description = "This plugin helps you create Minecraft mods using the Forge platform"
            tags.set(listOf("minecraft", "forge", "java", "mod"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
