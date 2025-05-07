plugins {
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    group = "com.rk.batbudget"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}