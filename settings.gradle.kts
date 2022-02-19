enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.version.toml"))
        }
    }
}

include(":app")
rootProject.name = "AppStudy"
include(":feature")
include(":feature:bmi")
include(":feature:todo")
include(":domain")
include(":data")
include(":feature:base")
include(":library")
include(":library:common")
