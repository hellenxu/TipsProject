package six.ca.sixlint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

/**
 * @author hellenxu
 * @date 2020-09-01
 * Copyright 2020 Six. All rights reserved.
 */
@Suppress("UnstableApiUsage")
class SixIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(SensitiveInfoDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API
}