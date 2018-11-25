package com.six.tipsproject.arch

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.view.View
import com.six.tipsproject.R
import com.six.tipsproject.rx.disposedBy
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_db.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
class DbActivity : Activity(), View.OnClickListener {
    private lateinit var userDao: UserDao
    private val compositeDisposable = CompositeDisposable()

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
        when (v?.id) {
            R.id.btnQuery -> getUserList()

            R.id.btnInsert -> {
                insertUser()
            }

            R.id.btnDelete -> {
            }

            R.id.btnUpdate -> {
            }

        }
    }

    private fun getUserList() {
        Maybe.create<List<User>> {
            it.onSuccess(userDao.getUserList())
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("xxl-onsucess: ${it.size}")
                    val sb = StringBuilder("user list: \n")
                    it.forEach { user ->
                        println("xxl-user: $user")
                        sb.append(user)
                    }
                    tvResult.text = sb.toString()
                }, { println("xxl-user-list-error:$it") })
                .disposedBy(compositeDisposable)
    }

    private fun insertUser() {

        Maybe.create<Long> {
            val id = System.currentTimeMillis()
            val user = User(id, "test$id", "six")
            it.onSuccess(userDao.insertUser(user))
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val sb = StringBuilder("inserted user index: $it")
                    tvResult.text = sb.toString()
                }, { println("xxl-insert-user-error:$it") })
                .disposedBy(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}