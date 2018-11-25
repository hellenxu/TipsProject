package com.six.tipsproject.arch

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-11-21.
 */
@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Long,
                @ColumnInfo(name = "first_name") val firstName: String,
                @ColumnInfo(name = "last_name") val lastName: String)