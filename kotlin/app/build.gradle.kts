plugins {
    alias(libs.plugins.jvm)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    testImplementation("com.squareup.okhttp3:mockwebserver")
    implementation(platform("io.arrow-kt:arrow-stack:1.2.1"))
    implementation("io.arrow-kt:arrow-core")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.AppKt"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
