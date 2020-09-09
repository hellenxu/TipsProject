package six.ca.sixlint

import com.android.tools.lint.detector.api.Context
import com.squareup.moshi.Json
import org.jetbrains.kotlin.konan.file.File

/**
 * @author hellenxu
 * @date 2020-09-04
 * Copyright 2020 Six. All rights reserved.
 */

@Suppress("UnstableApiUsage")
class LintConfigurationReader(context: Context) {

    init {
        val path = context.mainProject.dir.path
        println("xxl-main-project-path: $path")
        val configFile = File(path, "lint_config.json")
        configFile.forEachLine {
            println("xxl-content: $it")
        }
    }
}

data class LintConfig(
    @Json(name = "java_white_list")
    val codeWhiteList: List<String> = emptyList(),
    @Json(name = "res_white_list")
    val resWhiteList: List<String> = emptyList()
)