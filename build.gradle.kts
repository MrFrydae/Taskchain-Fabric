plugins {
    id("java")
    id("java-library")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("fabric-loom") version "1.4-SNAPSHOT"
}

group = "dev.frydae"
version = "${property("fabric_version")!!}-SNAPSHOT"

apply(from = uri("https://files.frydae.dev/gradle/common.gradle"))
apply(from = uri("https://files.frydae.dev/gradle/publishing.gradle"))

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    api(group = "co.aikar", name = "taskchain-core", version = "3.7.2")
}

repositories {
    maven("https://ci.emc.gs/nexus/content/groups/aikar/")
}

tasks {
    shadowJar {
        dependencies {
            include(dependency("co.aikar:taskchain-core:3.7.2"))
        }
    }
}