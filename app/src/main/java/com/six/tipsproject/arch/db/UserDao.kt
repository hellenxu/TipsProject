package com.six.tipsproject.arch.db

import android.arch.persistence.room.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserList(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(newUser: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int
}