plugins {
    java
    id("com.github.johnrengelman.shadow")
}

tasks.shadowJar {
    if (project.rootProject.hasProperty("debugOutputDir")) {
        destinationDirectory.set(file("${project.rootProject.properties["debugOutputDir"]}"))
    } else {
        destinationDirectory.set(file(rootProject.projectDir.path + "/build_outputs"))
    }

    archiveBaseName.set(project.rootProject.name)
    archiveVersion.set("")
    archiveClassifier.set("")
    destinationDirectory.set(file(rootProject.projectDir.path + "/build_outputs"))
}

configurations.runtimeClasspath.get().apply {
    exclude("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
}