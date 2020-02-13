package six.ca.droiddailyproject.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-02-13
 * Copyright 2020 Six. All rights reserved.
 */
class LevelOneFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.frag_level_one, container, false)
        rootView.findViewById<TextView>(R.id.label_frag_one).setOnClickListener(this)
        rootView.findViewById<TextView>(R.id.label_frag_two).setOnClickListener(this)
        rootView.findViewById<TextView>(R.id.label_frag_three).setOnClickListener(this)
        if (savedInstanceState == null) {
            val tag = "tag1"
            childFragmentManager.beginTransaction()
                .replace(R.id.flay_content, FragmentOne(), tag)
                .addToBackStack(tag)
                .commit()
        }

        return rootView
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.label_frag_one -> {
                val tag = "tag1"
                childFragmentManager.beginTransaction()
                    .replace(R.id.flay_content, FragmentOne(), tag)
                    .addToBackStack(tag)
                    .commit()
            }

            R.id.label_frag_two -> {
                val tag = "tag2"
                childFragmentManager.beginTransaction()
                    .replace(R.id.flay_content, FragmentTwo(), tag)
                    .addToBackStack(tag)
                    .commit()
            }

            R.id.label_frag_three -> {
                val tag = "tag3"
                childFragmentManager.beginTransaction()
                    .replace(R.id.flay_content, FragmentThree(), tag)
                    .addToBackStack(tag)
                    .commit()
            }
        }
    }
}