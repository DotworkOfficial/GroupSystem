@file:Suppress("UnstableApiUsage")

rootProject.name = getProperty("projectName")

dependencyResolutionManagement {
    repositories {
        maven("https://maven.hqservice.kr/repository/maven-private")
        maven("https://maven.hqservice.kr/repository/maven-public")
        maven("https://repo.papermc.io/repository/maven-public/")
        mavenLocal()
    }

    versionCatalogs {
        create("libs") {
            library("spigot-api", "org.spigotmc:spigot-api:${getProperty("spigotVersion")}")
            library("paper-api", "io.papermc.paper:paper-api:${getProperty("spigotVersion")}")
            library("eim", "kr.elroy.itemmanager:elroy-item-manager:${getProperty("eimVersion")}")
            library("elroylib-yaml", "kr.elroy:elroylib-yaml:${getProperty("elroyLibVersion")}")
        }

        create("api") {
            library("hqeconomy", "kr.hqservice:hqeconomy:1.0.0")
        }

        create("framework") {
            library("core", "kr.hqservice:hqframework-bukkit-core:${getProperty("hqFrameworkVersion")}")
            library("command", "kr.hqservice:hqframework-bukkit-command:${getProperty("hqFrameworkVersion")}")
            library("nms", "kr.hqservice:hqframework-bukkit-nms:${getProperty("hqFrameworkVersion")}")
            library("inventory", "kr.hqservice:hqframework-bukkit-inventory:${getProperty("hqFrameworkVersion")}")
            library("database", "kr.hqservice:hqframework-bukkit-database:${getProperty("hqFrameworkVersion")}")
        }

        create("dotwork") {
            library("file", "kr.dotmer:dotworklibrary-file:${getProperty("dotworkLibraryVersion")}")
        }
    }
}

includeBuild("build-logic")
includeAll("modules")

fun includeAll(modulesDir: String) {
    file("${rootProject.projectDir.path}/${modulesDir.replace(":", "/")}/").listFiles()?.forEach { modulePath ->
        include("${modulesDir.replace("/", ":")}:${modulePath.name}")
    }
}

fun getProperty(key: String): String {
    return extra[key]?.toString() ?: throw IllegalArgumentException("property with $key not found")
}
