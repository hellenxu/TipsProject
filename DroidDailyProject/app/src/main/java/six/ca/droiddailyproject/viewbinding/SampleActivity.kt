package six.ca.droiddailyproject.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.databinding.ActVbBinding

/**
 * @author hellenxu
 * @date 2020-07-28
 * Copyright 2020 Six. All rights reserved.
 */
class SampleActivity: AppCompatActivity() {
    private lateinit var binding: ActVbBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActVbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = getString(R.string.hello_word)
    }

}