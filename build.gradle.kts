import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val springBootVersion = "3.0.2"
	val kotlinVersion = "1.8.0"

	java
	id("org.springframework.boot").version(springBootVersion)
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm").version(kotlinVersion)
	kotlin("plugin.spring").version(kotlinVersion)
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

apply(plugin = "io.spring.dependency-management")

group = "com.example"

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")

	// JWT authentication
	implementation("org.springframework.boot:spring-boot-starter-security")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}
