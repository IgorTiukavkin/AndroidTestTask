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
        ":ui:media"
)
// Presentation
include(
        ":presentation:common",
        ":presentation:favorites"
)
// Features
include(
        ":features:common",
        ":features:favorites"
)