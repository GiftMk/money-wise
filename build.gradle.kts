import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("plugin.jpa") version "1.9.24"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("com.diffplug.spotless") version "7.0.0.BETA1"
}

group = "com.moneywise"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

object Versions {
	const val KOTEST = "5.9.0"
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("io.kotest:kotest-runner-junit5:${Versions.KOTEST}")
	testImplementation("io.kotest:kotest-assertions-core:${Versions.KOTEST}")
	testImplementation("io.kotest:kotest-property:${Versions.KOTEST}")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
	kotlin {
		targetExclude("build/**/*")
		ktfmt().kotlinlangStyle()
	}
}

afterEvaluate {
	tasks.findByName("spotlessApply")?.let {
		tasks.withType(KotlinCompile::class).configureEach {
			finalizedBy(it)
		}
	}
}