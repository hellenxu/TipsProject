package com.six.tipsproject.arch.db.migration

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.view.View
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory
import com.six.tipsproject.R
import com.six.tipsproject.rx.disposedBy
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_db.*
import java.util.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
class DbHelperActivity : Activity(), View.OnClickListener {
    private lateinit var userDao: UserDao
    private val compositeDisposable = CompositeDisposable()
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_db)

        val db = Room.databaseBuilder(applicationContext, AppDb::class.java, "test.db")
                .openHelperFactory(AssetSQLiteOpenHelperFactory())
                .build()
        userDao = db.getUserDao()

        btnQuery.setOnClickListener(this)
        btnInsert.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnQuery -> getUserList()

            R.id.btnInsert -> insertUser()

            R.id.btnDelete -> deleteUser()

            R.id.btnUpdate -> updateUser()

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
            val user = User(id, "test$id", random.nextInt())
            it.onSuccess(userDao.insertUser(user))
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val sb = StringBuilder("inserted user index: $it")
                    tvResult.text = sb.toString()
                }, { println("xxl-insert-user-error:$it") })
                .disposedBy(compositeDisposable)
    }

    private fun deleteUser() {
        Maybe.create<Int> {
            val user = userDao.getUserList()[0]
            it.onSuccess(userDao.deleteUser(user))
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val sb = StringBuilder("delete user index: $it")
                    tvResult.text = sb.toString()
                }, { println("xxl-delete-user-error:$it") })
                .disposedBy(compositeDisposable)
    }

    private fun updateUser() {
        Maybe.create<Int> {
            val user = userDao.getUserList()[0]
            val newUser = User(user.uid, "updated: ${user.name}", user.age)
            it.onSuccess(userDao.updateUser(newUser))
        }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val sb = StringBuilder("updated user index: $it")
                    tvResult.text = sb.toString()
                }, { println("xxl-update-user-error:$it") })
                .disposedBy(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}