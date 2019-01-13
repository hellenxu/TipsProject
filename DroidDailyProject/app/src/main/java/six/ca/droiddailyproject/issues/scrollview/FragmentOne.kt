package six.ca.droiddailyproject.issues.scrollview

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_issue_sv_one.*
import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-01-12.
 */
class FragmentOne: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_issue_sv_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabs.addTab(tabs.newTab().setText("OneOne"))
        tabs.addTab(tabs.newTab().setText("OneTwo"))
        tabs.addTab(tabs.newTab().setText("OneThree"))

        vp.adapter = PageAdapter(fragmentManager, tabs.tabCount)
        vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab) {
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                vp.currentItem = p0.position
            }

        })
    }
}