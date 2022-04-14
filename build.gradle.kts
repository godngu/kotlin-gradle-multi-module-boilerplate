import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"

    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jmailen.kotlinter") version "3.9.0"
    id("org.springframework.boot") version "2.5.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

object Version {
    const val kotest = "4.4.3"
}

// 하위 모듈에 공통으로 적용할 값들을 설정
allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> { useJUnitPlatform() }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    group = "com.godngu.boilerplate"
    version = "1.0-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        testImplementation("io.kotest:kotest-runner-junit5:${Version.kotest}")
        testImplementation("io.kotest:kotest-assertions-core:${Version.kotest}")
        testImplementation("io.kotest:kotest-extensions-spring-jvm:${Version.kotest}")
    }
}

project(":api") {
    group = "com.godngu.boilerplate.api"
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = true
    jar.enabled = false

    dependencies {
        implementation(project(":application"))
        implementation("org.springframework.boot:spring-boot-starter-web")
    }

    springBoot {
        buildInfo()
    }
}

project(":application") {
    group = "com.godngu.boilerplate.application"
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true

    dependencies {
        implementation(project(":domain"))
        implementation(project(":infrastructure"))
        implementation("javax.transaction:javax.transaction-api")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "mockito-core")
        }
    }
}

project(":domain") {
    group = "com.godngu.boilerplate.domain"
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true

    apply(plugin = "kotlin-jpa")

    dependencies {
        compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")

        testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
        testImplementation(project(":infrastructure"))
//        testRuntimeOnly("com.h2database:h2")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "mockito-core")
        }
    }

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    noArg {
        annotation("javax.persistence.Entity")
    }
}

project(":infrastructure") {
    group = "com.godngu.boilerplate.infrastructure"
    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true

    dependencies {
        implementation(project(":domain"))
        api("org.springframework.boot:spring-boot-starter-data-jpa")
        // Hibernate5Module이 지연로딩 되는 객체의 프로퍼티 직렬화를 가능하게 해준다. (jackson ObjectMapper support)
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.9.8")
        runtimeOnly("com.h2database:h2")
    }
}
