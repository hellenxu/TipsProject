package six.ca.droiddailyproject.drawable

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-06-11
 * Copyright 2020 Six. All rights reserved.
 */
class ResourceDrawableActivity: AppCompatActivity(R.layout.act_res_drawable) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spinner = findViewById<AppCompatSpinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, getData())

    }

    private fun getData(): List<String> {
        val data = mutableListOf<String>()

        for (i in 0..10) {
            data.add("Item No.$i")
        }

        return data
    }

}