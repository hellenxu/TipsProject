
package six.ca.sixlint

import com.android.resources.ResourceFolderType
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.*
import org.w3c.dom.Attr
import java.util.*

/**
 * @author hellenxu
 * @date 2020-09-01
 * Copyright 2020 Six. All rights reserved.
 */

@Suppress("UnstableApiUsage")
class SensitiveInfoDetector:
    Detector(),
    Detector.UastScanner,
    Detector.XmlScanner,
    Detector.ResourceFolderScanner
{
    private lateinit var lintConfigReader: LintConfigurationReader
    private lateinit var whiteListVisitor: WhiteListVisitor

    override fun beforeCheckEachProject(context: Context) {
        super.beforeCheckEachProject(context)
        if (!this::lintConfigReader.isInitialized) {
            lintConfigReader = LintConfigurationReader(context)
            whiteListVisitor = WhiteListVisitor(lintConfigReader.javaWhiteList)
        }
    }

    // need to return ULiteralExpression as visitLiteralExpression() will be override in method createUastHandler
    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UClass::class.java, ULiteralExpression::class.java)
    }

    override fun getApplicableFiles(): EnumSet<Scope> {
        return EnumSet.of(Scope.JAVA_FILE)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object: UElementHandler() {
            override fun visitLiteralExpression(node: ULiteralExpression) {
                val evaluatedString = node.evaluateString() ?: return
                if (evaluatedString.matches(idReg) or evaluatedString.matches(phoneNumReg)) {
                    println("xxl-createUastHandler: $evaluatedString")
                    context.report(JAVA_FILE_ISSUE, node, context.getLocation(node), hintMessage)
                }
            }

            override fun visitClass(node: UClass) {
                println("xxl-handler-visitClass")
                node.accept(WhiteListVisitor(lintConfigReader.javaWhiteList))
            }
        }
    }


    override fun getApplicableAttributes(): Collection<String>? {
        return XmlScannerConstants.ALL
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val attrName = attribute.name
        val attrValue = attribute.value

        if (attrValue.matches(idReg) or attrValue.matches(phoneNumReg)) {
            println("xxl-attr: $attrName; $attrValue")
            context.report(RESOURCE_FILE_ISSUE, context.getValueLocation(attribute), hintMessage)
        }
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        val whiteList = listOf(ResourceFolderType.VALUES, ResourceFolderType.DRAWABLE, ResourceFolderType.MIPMAP)
        return folderType !in whiteList
    }

    companion object {
        private val phoneNumReg = Regex(".*(\\d{3}[-.]?){2}\\d{4}.*")
        private val idReg = Regex(".*\\d{8,9}.*")

        @JvmField
        val JAVA_FILE_ISSUE = Issue.create(
            id = "id00",
            briefDescription = "Hardcoded id",
            explanation = "replace with dump string",
            category = Category.SECURITY,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(SensitiveInfoDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        @JvmField
        val RESOURCE_FILE_ISSUE = Issue.create(
            id = "id00",
            briefDescription = "Hardcoded id",
            explanation = "replace with dump string",
            category = Category.SECURITY,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(SensitiveInfoDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )

        private const val hintMessage = "found out sensitive info: id"
    }
}