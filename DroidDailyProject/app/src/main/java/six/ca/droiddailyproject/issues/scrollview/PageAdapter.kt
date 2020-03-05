package six.ca.droiddailyproject.issues.scrollview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.util.SparseArray

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-01-12.
 */
class PageAdapter(val fm: androidx.fragment.app.FragmentManager?, val num: Int): androidx.fragment.app.FragmentPagerAdapter(fm) {
    private val fragmentArray = SparseArray<androidx.fragment.app.Fragment>()


    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        return createFragment(pos = p0)
    }

    private fun createFragment(pos: Int): androidx.fragment.app.Fragment {
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