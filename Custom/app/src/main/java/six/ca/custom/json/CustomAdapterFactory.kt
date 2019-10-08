package six.ca.custom.json

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

object CustomAdapterFactory {
    private val CUSTOM_CLASSES = arrayListOf<Class<*>>(FavResponse::class.java)

    fun needCustomAdapter(clzz: Class<*>): Boolean {
        return CUSTOM_CLASSES.contains(clzz)
    }

    fun createAdapter(clzz: Class<*>, moshi: Moshi): JsonAdapter<Any> {
        return when(clzz) {
            FavResponse::class.java -> FavAdapter(moshi)
            else -> moshi.adapter<Any>(clzz)
        }
    }
}
