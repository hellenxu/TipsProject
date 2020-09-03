package six.ca.droiddailyproject.lint

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * @author hellenxu
 * @date 2020-09-02
 * Copyright 2020 Six. All rights reserved.
 */
class CustomLintActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "12345678"
        println("xxl-id: 098765432")
    }
}