package six.ca.droiddailyproject.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * @author hellenxu
 * @date 2020-02-13
 * Copyright 2020 Six. All rights reserved.
 */
class NestedSampleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_nested_frag)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LevelOneFragment())
                .commit()
        }
    }

//    override fun onSaveInstanceState(outState: Bundle?) {
//        outState?.putParcelable("info", InfoManager.instance.sharedInfo)
//        super.onSaveInstanceState(outState)
//        println("xxl-act-onSaveInstanceState: $outState")
//    }

    // without setting android:persistableMode, this onSaveInstanceState will not be called
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        println("xxl-act-the-other-onSaveInstanceState")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        println("xxl-act-onResumeFragments")
    }

    override fun onResume() {
        super.onResume()
        println("xxl-act-onResume")
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//        InfoManager.instance.sharedInfo = savedInstanceState?.getParcelable("info") ?: InfoManager.Info("000")
//        println("xxl-act-onRestoreInstanceState")
//    }
}

/**
 * launch -> press home:
 * 2020-02-19 22:31:09.997 32316-32316/six.ca.droiddailyproject I/System.out: xxl-frag-onViewStateRestored: null
 * 2020-02-19 22:31:10.000 32316-32316/six.ca.droiddailyproject I/System.out: xxl-onResume
 * 2020-02-19 22:31:10.001 32316-32316/six.ca.droiddailyproject I/System.out: xxl-act-onResumeFragments
 * 2020-02-19 22:33:30.826 32316-32316/six.ca.droiddailyproject I/System.out: xxl-frag-onSaveInstanceState: Bundle[{}]
 * 2020-02-19 22:33:30.838 32316-32316/six.ca.droiddailyproject I/System.out: xxl-act-onSaveInstanceState: Bundle[{android:viewHierarchyState=Bundle[{android:views={16908290=android.view.AbsSavedState$1@1352a56, 2131230726=android.support.v7.widget.Toolbar$SavedState@b0d6ad7, 2131230728=android.view.AbsSavedState$1@1352a56, 2131230734=android.view.AbsSavedState$1@1352a56, 2131230791=android.view.AbsSavedState$1@1352a56, 2131230827=android.view.AbsSavedState$1@1352a56}}], android:support:fragments=android.support.v4.app.FragmentManagerState@48e2fc4, android:lastAutofillId=1073741823, android:fragments=android.app.FragmentManagerState@8b03ead}]
 *
 */