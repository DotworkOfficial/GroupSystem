import resourcegenerator.bukkit.excludedRuntimeDependencies

plugins {
    id("cccgh.shared")
    id("cccgh.shadow")
    id("cccgh.resource-generator")
}

bukkitResourceGenerator {
    main = "kr.dotmer.group.GroupSystemMain"
    name = "GroupSystem"
    apiVersion = "1.19"
    libraries = excludedRuntimeDependencies()
    depend = listOf("HQFramework")
    softDepend = listOf("DotworkLibrary")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(framework.core)
    runtimeOnly(project(":modules:core"))
    runtimeOnly(project(":modules:api"))
}
