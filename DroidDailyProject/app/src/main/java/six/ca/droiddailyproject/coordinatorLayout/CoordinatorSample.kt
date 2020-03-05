package six.ca.droiddailyproject.coordinatorLayout

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import six.ca.droiddailyproject.R

/**
 * CoordinatorLayout Hello World.
 * Created by Xiaolin on 2016-07-11.
 */

class CoordinatorSample : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_coordinator_nested)

        val colBar = findViewById(R.id.colToolbar) as CollapsingToolbarLayout
        colBar.title = getString(R.string.app_name)
        colBar.setExpandedTitleColor(Color.WHITE)
        colBar.setCollapsedTitleTextColor(Color.WHITE)

//        val data = ArrayList<String>()
//        for(i in 0..30){
//            data.add("title: " + i)
//        }

//        val recylerView = findViewById(R.id.rvDetails) as RecyclerView
//        recylerView.layoutManager = LinearLayoutManager(this)
//        recylerView.adapter = RVSampleAdapter(this, data)
    }
}