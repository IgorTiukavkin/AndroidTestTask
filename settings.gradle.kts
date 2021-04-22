pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "AndroidTestTask"

// Modules
include(
    ":app",
    ":ui",
    ":presentation",
    ":features"
)
// UI
include(
    ":ui:common",
    ":ui:main",
    ":ui:favorites",
    ":ui:media",
    ":ui:search"
)
// Presentation
include(
    ":presentation:common",
    ":presentation:favorites",
    ":presentation:search",
    ":presentation:media"
)
// Features
include(
    ":features:common",
    ":features:favorites",
    ":features:search"
)