package six.ca.droiddailyproject.issues.constraintlayout

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-06-16
 * Copyright 2020 Six. All rights reserved.
 */
class SampleActivity: AppCompatActivity() {

    private lateinit var loaderContainer: LinearLayout
    private lateinit var contentContainer: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_child_view_visibility)

        loaderContainer = findViewById(R.id.loader)
        contentContainer = findViewById(R.id.content_container)

    }

    override fun onResume() {
        super.onResume()

        showLoader()
        lifecycleScope.launch {
            getData()
            hideLoader()
        }
    }

    private fun showLoader() {
        loaderContainer.visibility = View.VISIBLE
        contentContainer.visibility = View.GONE
    }

    private fun hideLoader() {
        loaderContainer.visibility = View.GONE
        contentContainer.visibility = View.VISIBLE
    }

    private suspend fun getData() = withContext(Dispatchers.IO) {
        delay(3000)
        println("fetched data")
    }
}