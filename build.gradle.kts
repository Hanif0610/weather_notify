import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
    kotlin("plugin.jpa") version "1.4.31"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "alone.project"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("com.google.guava:guava:20.0")

    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    implementation("com.googlecode.json-simple:json-simple:1.1")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")

    compile("org.apache.poi:poi:3.12")
    compile("org.apache.poi:poi:3.12")
    compile("org.apache.poi:poi-ooxml:3.12")
    compile("org.apache.poi:poi-scratchpad:3.12")
    compile("org.apache.poi:poi-ooxml-schemas:3.12")
    compile("org.apache.poi:ooxml-schemas:1.1")
    compile("org.apache.poi:poi-excelant:3.12")
    compile("org.apache.poi:poi-contrib:3.6")
    compile("org.apache.poi:ooxml-security:1.0")
    compile("commons-io:commons-io:2.4")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
