package six.ca.droiddailyproject.extension

import android.content.SharedPreferences

/**
 * @author hellenxu
 * @date 2020-06-10
 * Copyright 2020 Six. All rights reserved.
 */

fun SharedPreferences.edit(
    commit: Boolean = false,
    action: SharedPreferences.Editor.() -> Boolean
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}