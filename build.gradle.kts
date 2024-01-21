import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
}



val group = "com.kotlin"
val version = "0.0.1-SNAPSHOT"
// kotest version
val kotestVersion = "5.8.0"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}


configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// spring-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// spring-security
//	implementation("org.springframework.boot:spring-boot-starter-security")
	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// spring-web
	implementation("org.springframework.boot:spring-boot-starter-web")
	// jackson-kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// kotlin-reflection
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// kotlin-faker
	implementation("io.github.serpro69:kotlin-faker:1.15.0")
	// https://mvnrepository.com/artifact/com.github.f4b6a3/ulid-creator
	implementation("com.github.f4b6a3:ulid-creator:5.2.3")
	// h2
	runtimeOnly("com.h2database:h2")
	// postgresql
	runtimeOnly("org.postgresql:postgresql")
	// springboot-configuration-processor
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	// kotest
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-property:$kotestVersion")
	// kotest-extension-spring
	implementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}

// -plain.jar 생성 방지
tasks.named<Jar>("jar") {
	enabled = false
}