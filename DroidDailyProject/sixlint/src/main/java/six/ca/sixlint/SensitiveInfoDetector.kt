
package six.ca.sixlint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULiteralExpression
import org.jetbrains.uast.evaluateString

/**
 * @author hellenxu
 * @date 2020-09-01
 * Copyright 2020 Six. All rights reserved.
 */

@Suppress("UnstableApiUsage")
class SensitiveInfoDetector: Detector(), Detector.UastScanner {
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(ULiteralExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object: UElementHandler() {
            override fun visitLiteralExpression(node: ULiteralExpression) {
                val evaluatedString = node.evaluateString() ?: return
                if (evaluatedString.matches(idReg) or evaluatedString.matches(phoneNumReg)) {
                    println("xxl-createUastHandler: $evaluatedString")
                    context.report(ISSUE, node, context.getLocation(node), hintMessage)
                }
            }
        }
    }

    companion object {
        private val phoneNumReg = Regex(".*(\\d{3}[-.]?){2}\\d{4}.*")
        private val idReg = Regex(".*\\d{8,9}.*")

        @JvmField
        val ISSUE = Issue.create(
            id = "id00",
            briefDescription = "Hardcoded id",
            explanation = "replace with dump string",
            category = Category.SECURITY,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(SensitiveInfoDetector::class.java, Scope.ALL)
        )

        private const val hintMessage = "found out sensitive info: id"
    }
}