package six.ca.droiddailyproject.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_one.*
import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-10-02.
 */
class LifecycleFragment : androidx.fragment.app.Fragment() {

    init {
        retainInstance = true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        println("xxl-onAttach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_label.setText(R.string.lifecycle)
        btn_load.setText(R.string.lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("xxl-onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("xxl-onCreateView")
        return inflater.inflate(R.layout.frag_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("xxl-onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("xxl-onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        println("xxl-onStart")
    }

    override fun onResume() {
        super.onResume()
        println("xxl-onResume")
    }

    override fun onPause() {
        super.onPause()
        println("xxl-onPause")
    }

    override fun onStop() {
        super.onStop()
        println("xxl-onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("xxl-onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("xxl-onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        println("xxl-onDetach")
    }
}