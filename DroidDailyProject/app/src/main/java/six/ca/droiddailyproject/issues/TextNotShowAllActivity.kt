package six.ca.droiddailyproject.issues

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import six.ca.droiddailyproject.databinding.ActTextIncompleteBinding

/**
 * @author hellenxu
 * @date 2020-10-28
 * Copyright 2020 Six. All rights reserved.
 */
class TextNotShowAllActivity: AppCompatActivity() {
    private lateinit var binding: ActTextIncompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActTextIncompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(3000)

            binding.config.setConfig(ConfigDetail("", "short: abc", "long description: available until 3 weeks from now on"))
            binding.optionalMessage.visibility = View.GONE
        }
    }

}