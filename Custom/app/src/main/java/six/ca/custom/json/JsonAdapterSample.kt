package six.ca.custom.json

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import six.ca.custom.R

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
class JsonAdapterSample : AppCompatActivity() {
    private lateinit var loadingTv: TextView
    private lateinit var resultTv: TextView
    private lateinit var vm: JsonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_json)

        loadingTv = findViewById(R.id.loading_txt)
        resultTv = findViewById(R.id.result_txt)

        vm = ViewModelProviders.of(this).get(JsonViewModel::class.java)

        vm.favNumSet.observe(this, Observer {response ->
            loadingTv.visibility = View.GONE
            if (response.isEmpty()) {
                resultTv.text = "no data"
            } else {
                resultTv.text = response.joinToString("\n")
            }
        })

        loadData()
    }

    private fun loadData() {
        loadingTv.text = "Start loading..."
        vm.getFavNums()
    }
}