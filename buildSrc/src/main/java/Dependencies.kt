package dependencies

object Versions {
    const val kotlin = "1.4.21"
    const val coreKtx = "1.3.2"
    const val coroutine = "1.4.0"
    const val materialDesign = "1.2.1"
    const val recyclerView = "1.1.0"
    const val appcompat = "1.2.0"
    const val constrainLayout = "2.0.4"
    const val koin = "2.0.1"
    const val multidex = "2.0.1"
    const val navigation = "2.3.2"

    const val junit = "4.13.1"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"
    const val androidXTest = "1.1.0"
    const val mockk = "1.10.0"
    const val assertJ = "3.2.0"
    const val coreTest = "2.1.0"

}

object Modules {
    const val auth = ":features:login"
    const val navigation = ":navigation"
    const val commons = ":commons"
}

object Config {
    const val versionCode = 1
    const val versionName = "1.0"

    const val minSDK = 21
    const val compiledSDKVersion = 30
    const val buildTools = "30.0.0"
    const val appId = "com.apelgigit.miniappsstockbit"
    const val targetSDK = 30
}

object Libs {
    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    }

    object Koin {
        const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
        const val koinVM = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
        const val koinExt = "org.koin:koin-androidx-ext:${Versions.koin}"
    }

    object Android {
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constrainLayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainLayout}"
        const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

        // NAVIGATION
        const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigationArgs =  "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    }

    object UnitTest {
        const val junit = "junit:junit:${Versions.junit}"
        const val testJunitExt = "androidx.test.ext:${Versions.junitExt}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
        const val rules = "androidx.test:rules:${Versions.androidXTest}"
        const val testRunner = "androidx.test:runner:${Versions.androidXTest}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
        const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
        const val testCore = "android.arch.core:core-testing:${Versions.coreTest}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
    }

}