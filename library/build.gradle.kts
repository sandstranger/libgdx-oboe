plugins {
    id("kotlin-android")
    id("maven-publish")
    id("com.android.library")
}

dependencies {
    implementation ("com.badlogicgames.gdx:gdx-backend-android:1.13.0")
    implementation  ("com.badlogicgames.gdx:gdx:1.13.0")
    implementation  ("com.badlogicgames.gdx:gdx-box2d:1.13.0")
    implementation  ("com.badlogicgames.gdx:gdx-freetype:1.13.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
}

android {
    namespace = "oboe"
    buildToolsVersion = "34.0.0"
    compileSdkVersion = "android-34"
    ndkVersion = "25.1.8937393"

    kotlinOptions {
        jvmTarget = "11"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.srcDir("src/kotlin")
        }
    }

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        ndk {
            abiFilters.addAll(listOf("x86", "x86_64", "armeabi-v7a", "arm64-v8a"))

            externalNativeBuild {
                cmake {
                    cppFlags("-std=c++17", "-O3", "-DCMAKE_BUILD_TYPE=Release", "-DNDEBUG")
                }
            }

            consumerProguardFile("proguard-rules.pro")
        }
    }

    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
}