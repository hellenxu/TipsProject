package six.ca.sixlint

import org.jetbrains.uast.UClass
import org.jetbrains.uast.visitor.AbstractUastVisitor

/**
 * @author hellenxu
 * @date 2020-09-09
 * Copyright 2020 Six. All rights reserved.
 */
class WhiteListVisitor(private val whiteList: List<String>): AbstractUastVisitor() {

    override fun visitClass(node: UClass): Boolean {
        println("xxl-in-white-list00: ${node.name}; ${node.qualifiedName}")

        // check other conditions
        if (node.name !in whiteList) {
            println("xxl-not-in-white-list")
            // report issue
            return true
        }
        return super.visitClass(node)
    }
}