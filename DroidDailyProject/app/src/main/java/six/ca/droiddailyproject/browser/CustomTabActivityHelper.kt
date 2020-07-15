package six.ca.droiddailyproject.browser

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
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
                // CustomTabsSession#mayLaunchUrl() may come with battery and network cost
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
        println("xxl-bindCustomTabsService")
    }

    fun unbindCustomTabsService(activity: Activity) {
        connection?.let {
            activity.unbindService(it)
            client = null
            customTabsSession = null
            connection = null
            println("xxl-unbindCustomTabsService")
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

internal class CustomTabActivityLifecycleCallbacks: Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity?) = Unit

    override fun onActivityResumed(activity: Activity?) = Unit

    override fun onActivityDestroyed(activity: Activity?) = Unit

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity?) {
        activity?.let {
            CustomTabActivityHelper.bindCustomTabsService(it)
        }
    }

    override fun onActivityStopped(activity: Activity?) {
        activity?.let {
            CustomTabActivityHelper.unbindCustomTabsService(it)
        }
    }
}