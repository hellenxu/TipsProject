package six.ca.droiddailyproject.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.SixApplication

/**
 * @author hellenxu
 * @date 2020-02-13
 * Copyright 2020 Six. All rights reserved.
 */
class LevelOneFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("xxl-frag-saved: ${savedInstanceState?.getParcelable<InfoManager.Info>("info")}")
//        println("xxl-frag-sharedInt: ${InfoManager.instance.sharedInt}")
//        println("xxl-frag-sharedInfo: ${InfoManager.instance.sharedInfo}")

//        println("xxl-frag-static-sharedInt: ${StaticInfo.sharedInt}")
//        println("xxl-frag-static-sharedInfo: ${StaticInfo.sharedInfo}")
//
//        println("xxl-frag-sharedInfo: ${ if (InfoManager.instance.propertyInitialized()) InfoManager.instance.sharedInfo else savedInstanceState?.getParcelable<InfoManager.Info>("info")}")

        println("xxl-frag-bundle-Int: ${arguments?.getInt("fromActInt", 0)}")
        println("xxl-frag-bundle-Obj: ${arguments?.getParcelable<InfoManager.Info>("fromActObj")}")

        println("xxl-frag-application-Int: ${SixApplication.sharedInt}")
        println("xxl-frag-application-Obj: ${SixApplication.sharedInfo}")

    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("info", InfoManager.instance.sharedInfo)
        super.onSaveInstanceState(outState)
        println("xxl-frag-onSaveInstanceState: $outState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("xxl-frag-restored: ${savedInstanceState?.getParcelable<InfoManager.Info>("info")}")
        println("xxl-frag-onViewStateRestored: $savedInstanceState")
    }

    override fun onResume() {
        super.onResume()
        println("xxl-frag-onResume")
    }
}