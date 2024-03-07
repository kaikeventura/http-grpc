import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id ("com.google.protobuf") version "0.8.18"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

group = "com.kaikeventura"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.grpc:grpc-netty:1.44.1")
	implementation("io.grpc:grpc-protobuf:1.44.1")
	implementation("io.grpc:grpc-kotlin-stub:1.2.1")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	generatedFilesBaseDir = "$buildDir/generated/sources/proto"
	protoc {
		artifact = "com.google.protobuf:protoc:3.12.2"
	}

	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.30.0"
		}
	}
	generateProtoTasks.all().forEach {
		it.plugins {
			id("grpc"){
				outputSubDir = "grpc"
			}
		}
	}
}

sourceSets{
	main{
		proto{
			srcDir("$buildDir/generated/sources/proto/main/grpc")
		}
	}
}
