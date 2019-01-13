package six.ca.droiddailyproject.arch.db.migration

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.view.View
import six.ca.droiddailyproject.arch.db.migration.AppDb.Companion.MIGRATION_2_3
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.act_db.*
import six.ca.droiddailyproject.R
import six.ca.droiddailyproject.rx.disposedBy
import java.io.*
import java.util.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
class DbHelperActivity : Activity(), View.OnClickListener {
    private lateinit var userDao: UserDao
    private val compositeDisposable = CompositeDisposable()
    private val random = Random()
    private val dbName = "test.db"
    private var isInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.act_db)

        initDb()

        btnQuery.setOnClickListener(this)
        btnInsert.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
    }

    private fun initDb() {

        Maybe.create<UserDao> {
            copyDb()

            val db = Room.databaseBuilder(applicationContext, AppDb::class.java, dbName)
//                    .openHelperFactory(AssetSQLiteOpenHelperFactory())
//                    .addMigrations(MIGRATION_2_3)
                    .build()
            it.onSuccess(db.getUserDao())

        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    userDao = it
                    isInitialized = true
                }, { println("xxl-db-error: $it")})
                .disposedBy(compositeDisposable)

    }

    private fun copyDb() {
        val dbPath = applicationContext.getDatabasePath(dbName)

        val target = File(dbPath.path)
        val inputStream = applicationContext.assets.open("databases/$dbName")
        val outputStream = FileOutputStream(target)
        val buffer = ByteArray(8192)
        var length = inputStream.read(buffer, 0, 8192)
        while(length > 0) {
            outputStream.write(buffer, 0, length)
            length = inputStream.read(buffer, 0, 8192)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    override fun onClick(v: View?) {
        if(!isInitialized) {
            return
        }

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
            val user = User(id.toInt(), "test$id", random.nextInt())
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