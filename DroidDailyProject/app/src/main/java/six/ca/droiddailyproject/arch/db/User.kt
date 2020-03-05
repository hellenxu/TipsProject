package six.ca.droiddailyproject.arch.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Long,
                @ColumnInfo(name = "first_name") val firstName: String,
                @ColumnInfo(name = "last_name") val lastName: String,
                @ColumnInfo(name = "age") val age: Int = 0)