package six.ca.droiddailyproject.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.databinding.FragVbOneBinding

/**
 * @author hellenxu
 * @date 2020-07-28
 * Copyright 2020 Six. All rights reserved.
 */
class OneFragment: Fragment() {
    private lateinit var binding: FragVbOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragVbOneBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvHello.text = "${getString(R.string.hello_word)} from ${this.javaClass.simpleName}"
    }
}