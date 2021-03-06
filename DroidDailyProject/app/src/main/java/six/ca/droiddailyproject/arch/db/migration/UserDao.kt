package six.ca.droiddailyproject.arch.db.migration

import androidx.room.*
import six.ca.droiddailyproject.arch.db.migration.User

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