package six.ca.droiddailyproject.arch.db.migration

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-28.
 */
@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "age") val age: Int = 0)