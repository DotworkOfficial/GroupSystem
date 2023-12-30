plugins {
    id("cccgh.shared")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.eim)

    compileOnly(framework.core)
    compileOnly(framework.database)
    compileOnly(framework.command)
    compileOnly(api.hqeconomy)

    api(project(":modules:api"))
}