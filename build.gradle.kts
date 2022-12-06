import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

// 相关插件要现在这里进行声明
// java / java-library插件不能进行 apply false
// 其余插件需要添加apply false 让其不在当前项目使用
// 有版本号的插件再此处声明后,在subprojects中才能使用apply(plugin = xxx)
// 不然会找不到插件
plugins {
    java
    id("java-library")
    id("org.springframework.boot") version "3.0.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
}


// 全局项目配置
allprojects {

    group = "io.github.yangxj96"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }

}

// 所有子项目的共同配置(不包含根项目)
subprojects {
    // 此处的插件由于在最外层的plugins中声明了. 且声明了版本号,此处能直接使用apply()
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    // java源码和目标文件版本
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // 指定中文编码
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    // 定义的变量,可以再全局使用
    extra["springCloudVersion"] = "2022.0.0-RC2"
    extra["mybatisVersion"] = "3.0.0"

    // 等同于dependencyManagement {}
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    // 所有子项目都需要使用的依赖
    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    // 使其支持junit
    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
