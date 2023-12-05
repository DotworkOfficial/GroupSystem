plugins {
    id("cccgh.shared")
    `maven-publish`
}

file(rootProject.gradle.rootProject.projectDir.path + "/credentials.gradle.kts").let {
    if (it.exists()) {
        apply(it.path)
    }
}

publishing {
    publications {
        create<MavenPublication>("nexus") {
            groupId = extra["projectGroup"].toString()
            artifactId = extra["artifactId"].toString().lowercase()
            version = extra["projectVersion"].toString()
            from(components["java"])

            pom {
                name.set(extra["projectName"].toString())
                url.set(extra["projectUrl"].toString())
            }
        }
    }
}