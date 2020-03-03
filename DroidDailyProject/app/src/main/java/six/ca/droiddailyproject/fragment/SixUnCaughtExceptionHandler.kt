package six.ca.droiddailyproject.fragment

import android.content.Context
import android.content.Intent
import kotlin.system.exitProcess

/**
 * @author hellenxu
 * @date 2020-03-02
 * Copyright 2020 Six. All rights reserved.
 */
class SixUnCaughtExceptionHandler constructor(private val ctx: Context): Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        println("xxl-uncaught: $e")
        val intent =
            Intent(ctx, WelcomeActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

        ctx.startActivity(intent)
        exitProcess(0)
    }
}