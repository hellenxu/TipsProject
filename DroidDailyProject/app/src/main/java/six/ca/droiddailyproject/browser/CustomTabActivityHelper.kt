package six.ca.droiddailyproject.browser

import android.app.Activity
import android.content.ComponentName
import android.net.Uri
import androidx.browser.customtabs.*

/**
 * This is a helper class to manage the connection to the Custom Tabs Service.
 */
object CustomTabActivityHelper {
    private var customTabsSession: CustomTabsSession? = null
    private var client: CustomTabsClient? = null
    private var connection: CustomTabsServiceConnection? = null
    private var connectionCallback: ConnectionCallback? = null

    @JvmStatic
    fun openCustomTab(
        activity: Activity,
        customTabsIntent: CustomTabsIntent,
        uri: Uri,
        fallback: CustomTabFallback?
    ) {
        val packageName = CustomTabsHelper.getPackageNameToUse(activity)
        if (packageName == null) {
            fallback?.openUri(activity, uri)
        } else {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(activity, uri)
        }

    }

    fun bindCustomTabsService(activity: Activity) {
        if (client != null) return

        val packageName = CustomTabsHelper.getPackageNameToUse(activity) ?: return
        val newConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                name: ComponentName,
                newClinet: CustomTabsClient
            ) {
                client = newClinet
                client?.warmup(0L)
                connectionCallback?.onCustomTabsConnected()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                client = null
                customTabsSession = null
                connectionCallback?.onCustomTabsDisconnected()
            }

        }

        connection = newConnection
        CustomTabsClient.bindCustomTabsService(activity, packageName, newConnection)
    }

    fun unbindCustomTabsService(activity: Activity) {
        connection?.let {
            activity.unbindService(it)
            client = null
            customTabsSession = null
            connection = null

        }
    }
}

interface ConnectionCallback {
    fun onCustomTabsConnected()

    fun onCustomTabsDisconnected()
}

interface CustomTabFallback {
    fun openUri(activity: Activity, uri: Uri)
}