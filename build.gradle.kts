plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	id("jacoco")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
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
	// Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Hibernate dependencies
	implementation("org.hibernate.orm:hibernate-core")
	implementation("org.hibernate.orm:hibernate-platform:6.6.0.Final")
	implementation("jakarta.transaction:jakarta.transaction-api")

	// MapStruct dependencies
	implementation("org.mapstruct:mapstruct:1.6.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")

	// Lombok dependencies
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Testing dependencies
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Other dependencies
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
	runtimeOnly("com.h2database:h2")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
}
jacoco {
	toolVersion = "0.8.12"  // Asegúrate de usar una versión reciente de JaCoCo
}
tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy("jacocoTestReport") // Ejecutar Jacoco después de las pruebas
}
tasks.jacocoTestReport {
	dependsOn(tasks.test)  // Ejecutar las pruebas antes de generar el informe

	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
	classDirectories.setFrom(
			fileTree(layout.buildDirectory.dir("classes/java/main")) {
				exclude(
						"**com/example/demo/architecture/infrastructure/mapper**",
						"**com/example/demo/architecture/commons/constants**")
			}
	)
}


