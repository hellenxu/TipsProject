package six.ca.droiddailyproject.issues.scrollview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_issue_sv_onetwo.*
import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-01-12.
 */
class FragmentOneTwo: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_issue_sv_onetwo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = FragmentOneTwoThree()
        childFragmentManager.beginTransaction().replace(R.id.anotherContainer, fragment).commit()
    }
}