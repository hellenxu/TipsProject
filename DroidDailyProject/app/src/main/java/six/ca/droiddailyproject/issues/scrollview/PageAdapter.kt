package six.ca.droiddailyproject.issues.scrollview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-01-12.
 */
class PageAdapter(val fm: FragmentManager?, val num: Int): FragmentPagerAdapter(fm) {
    private val fragmentArray = SparseArray<Fragment>()


    override fun getItem(p0: Int): Fragment {
        return createFragment(pos = p0)
    }

    private fun createFragment(pos: Int): Fragment {
        var fragment = fragmentArray.get(pos)
        if(fragment == null) {
            when(pos) {
                0 -> fragment = FragmentOneOne()
                1 -> fragment = FragmentOneTwo()
                2 -> fragment = FragmentOneThree()
            }

            fragmentArray.put(pos, fragment)
        }

        return fragment
    }

    override fun getCount(): Int {
        return num
    }
}