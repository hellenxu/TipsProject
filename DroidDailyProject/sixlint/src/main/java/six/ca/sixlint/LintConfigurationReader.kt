package six.ca.sixlint

import com.android.tools.lint.detector.api.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.jetbrains.kotlin.konan.file.File

/**
 * @author hellenxu
 * @date 2020-09-04
 * Copyright 2020 Six. All rights reserved.
 */

@Suppress("UnstableApiUsage")
class LintConfigurationReader(context: Context) {
    private val config: LintConfig

    init {
        val path = context.mainProject.dir.path
        val configFile = File(path, CONFIG_FILE)
        val content = StringBuilder()
        configFile.forEachLine {
            content.append(it)
        }

        // 20200909 couldn't use Moshi as lintAPi isn't friendly enough to Moshi, and exception NoClassDefFoundError will be thrown
        config = Gson().fromJson(content.toString(), LintConfig::class.java)
        println("xxl-config: $config")
    }

    val javaWhiteList = config.codeWhiteList.filter { it.isNotBlank() }
    val resWhiteList = config.resWhiteList.filter { it.isNotBlank() }

    companion object {
        private const val CONFIG_FILE = "lint_config.json"
    }
}

data class LintConfig(
    @SerializedName("java_white_list")
    val codeWhiteList: List<String> = emptyList(),
    @SerializedName("res_white_list")
    val resWhiteList: List<String> = emptyList()
)