package six.ca.droiddailyproject.arch.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Database(entities = [User::class], version = 2)
abstract class AppDb: RoomDatabase() {
    companion object {
        val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
            }
        }
    }

    abstract fun getUserDao(): UserDao
}