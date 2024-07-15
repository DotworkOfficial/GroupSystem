plugins {
    id("cccgh.shared")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.eim)
    compileOnly(libs.elroylib.yaml)

    compileOnly(files("${rootProject.projectDir}/libs/ChatControl-Red-10.27.7.jar"))

    compileOnly(framework.core)
    compileOnly(framework.database)
    compileOnly(framework.command)
    compileOnly(api.hqeconomy)

    api(project(":modules:api"))
}