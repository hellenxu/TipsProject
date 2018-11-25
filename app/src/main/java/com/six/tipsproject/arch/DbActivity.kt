package com.six.tipsproject.arch

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.view.View
import com.six.tipsproject.R
import kotlinx.android.synthetic.main.act_db.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
class DbActivity: Activity(), View.OnClickListener {
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_db)

        val db = Room.databaseBuilder(applicationContext, AppDb::class.java, "helloRoom")
                .build()
        userDao = db.getUserDao()

        btnQuery.setOnClickListener(this)
        btnInsert.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
    }

    //TODO
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnQuery -> {}

            R.id.btnInsert -> {}

            R.id.btnDelete -> {}

            R.id.btnUpdate -> {}

        }
    }

}