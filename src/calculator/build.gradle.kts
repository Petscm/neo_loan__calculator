import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    `maven-publish`
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.14.0"
}

group = "petscm.neo_loan"
version = "0.0.1-SNAPSHOT"
description = "calculator"

val overridedBuildDir = layout.buildDirectory.dir("generated").get().asFile.path

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

configurations {
    all{
        exclude(group="ch.qos.logback", module="logback-classic")
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.8.13")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.6")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.38")
    implementation("org.openapitools:openapi-generator-gradle-plugin:7.14.0")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("org.openapitools:jackson-databind-nullable:0.2.7")
    implementation("org.mapstruct:mapstruct:1.6.3")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDir("$overridedBuildDir/generated" )
        }
    }
}

tasks.register("GenerateOpenapiServer", GenerateTask::class) {
    generatorName = "spring"
    remoteInputSpec = "https://raw.githubusercontent.com/Petscm/neo_loan__api_schemas/refs/heads/main/calculator-service/1_0_0v/api.yaml"
    outputDir = "$overridedBuildDir/generated"
    ignoreFileOverride = ".openapi-generator-ignore"
    configOptions.set(mapOf(
        "generator" to "spring-boot",
        "useJakartaEe" to "true",
        "invokerPackage" to "petscm.neo_loan.api_schemas.server",
        "apiPackage" to "petscm.neo_loan.api_schemas.server.api",
        "modelPackage" to "petscm.neo_loan.api_schemas.server.model",
        "configPackage" to "petscm.neo_loan.api_schemas.server.config",
        "basePackage" to "petscm.neo_loan.api_schemas.server.base",
        "useOptional" to "true",
        "openApiNullabel" to "false",
        "interfaceOnly" to "true",
        "sourceFolder" to "",
        "additionalModelTypeAnnotations" to "@lombok.Builder\n@lombok.NoArgsConstructor\n@lombok.AllArgsConstructor",
        "generatedConstructorWithRequiredArgs" to "false",
        "useTags" to "true",
    ))
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/petscm/neo_loan__calculator")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}
