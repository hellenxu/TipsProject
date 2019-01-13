package six.ca.droiddailyproject.arch.db.migration

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Database(entities = [User::class], version = 3)
abstract class AppDb: RoomDatabase() {
    companion object {
        val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_2_3 = object: Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }

    abstract fun getUserDao(): UserDao
}