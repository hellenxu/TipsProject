package com.six.tipsproject.arch

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Database(entities = [User::class], version = 1)
abstract class AppDb: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}