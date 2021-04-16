pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "AndroidTestTask"

include(":app", ":ui")

// Ui
include(
        ":ui:common",
        ":ui:main"
)