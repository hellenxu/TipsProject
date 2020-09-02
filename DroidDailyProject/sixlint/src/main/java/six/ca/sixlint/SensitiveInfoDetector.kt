
package six.ca.sixlint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
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
                if (evaluatedString.matches(Regex("\\d{7,9}"))) {
                    context.report(ISSUE, node, context.getLocation(node), hintMessage)
                }
            }
        }
    }

    companion object {
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