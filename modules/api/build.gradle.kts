plugins {
    id("cccgh.shared")
    id("elroy.publish")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(framework.core)
}