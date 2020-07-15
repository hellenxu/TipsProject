package six.ca.droiddailyproject.browser

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsService

object CustomTabsHelper {
    private var packageName: String? = null

    private const val STABLE_PACKAGE = "com.android.chrome"
    private const val BETA_PACKAGE = "com.chrome.beta"
    private const val DEV_PACKAGE = "com.chrome.dev"
    private const val LOCAL_PACKAGE = "com.google.android.apps.chrome"

    fun getPackageNameToUse(context: Context): String? {
        val pm = context.packageManager
        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
        val defaultResolveInfo = pm.resolveActivity(viewIntent, 0)

        var defaultPackageName = ""
        if (defaultResolveInfo != null) {
            defaultPackageName = defaultResolveInfo.activityInfo.packageName
        }

        val resolvedActivityList = pm.queryIntentActivities(viewIntent, 0)
        val supportingCustomTabs = arrayListOf<String>()
        resolvedActivityList.forEach {info ->
            val intent = Intent()
            intent.action = CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
            intent.setPackage(info.activityInfo.packageName)
            if (pm.resolveService(intent, 0) != null) {
                supportingCustomTabs.add(info.activityInfo.packageName)
            }
        }

        when {
            supportingCustomTabs.isEmpty() -> { packageName = null }
            supportingCustomTabs.size == 1 -> { packageName = supportingCustomTabs[0] }
            defaultPackageName.isNotEmpty()
                    && supportingCustomTabs.contains(defaultPackageName)
                    && !hasSpecializedHandlerIntents(context, viewIntent) -> {
                packageName = defaultPackageName
            }
            supportingCustomTabs.contains(STABLE_PACKAGE) -> {
                packageName = STABLE_PACKAGE
            }
            supportingCustomTabs.contains(BETA_PACKAGE) -> {
                packageName = BETA_PACKAGE
            }
            supportingCustomTabs.contains(DEV_PACKAGE) -> {
                packageName = DEV_PACKAGE
            }
            supportingCustomTabs.contains(LOCAL_PACKAGE) -> {
                packageName = LOCAL_PACKAGE
            }
        }

        return packageName
    }

    private fun hasSpecializedHandlerIntents(
        context: Context,
        intent: Intent
    ): Boolean {
        try {
            val pm = context.packageManager
            val handlers = pm.queryIntentActivities(
                intent,
                PackageManager.GET_RESOLVED_FILTER
            )
            if (handlers == null || handlers.size == 0) {
                return false
            }
            for (resolveInfo in handlers) {
                val filter = resolveInfo.filter ?: continue
                if (filter.countDataAuthorities() == 0 || filter.countDataPaths() == 0) continue
                if (resolveInfo.activityInfo == null) continue
                return true
            }
        } catch (e: RuntimeException) {
            Log.e(
                CustomTabsHelper.javaClass.simpleName,
                "Runtime exception while getting specialized handlers"
            )
        }
        return false
    }
}