plugins {
    id("cccgh.shared")
}

dependencies {
    compileOnly(libs.spigot.api)
    
    compileOnly(framework.core)
    compileOnly(framework.database)
    compileOnly(framework.command)

    api(project(":modules:api"))
}