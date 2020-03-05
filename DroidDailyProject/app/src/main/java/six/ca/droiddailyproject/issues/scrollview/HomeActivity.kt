package six.ca.droiddailyproject.issues.scrollview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-01-12.
 */

/**
 * What went wrong: ScrollView hides some part of the layout
 *
 * Structure:
 * + Activity
 * + + Fragment One [layout: TabLayout + ViewPager]
 * + + + Fragment OneTwo [layout: RelativeLayout[ScrollView{width, height: match_parent}(LinearLayout) + LinearLayout(FragmentThree container)]]
 * + + + + Fragment OneTwoThree [layout: ConstraintLayout[include two layouts, and both of them are ScrollView(ConstraintLayout)]]
 */
class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_issue_sv_home)

        val fragment = FragmentOne()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}