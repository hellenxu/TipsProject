package six.ca.custom.json

import com.squareup.moshi.*
import java.lang.reflect.Array
import java.lang.reflect.Array.getLength
import kotlin.collections.ArrayList

/**
 * @author hellenxu
 * @date 2019-10-07
 * Copyright 2019 Six. All rights reserved.
 */
class FavAdapter constructor(private val moshi: Moshi) : JsonAdapter<Any>() {

    override fun fromJson(reader: JsonReader): kotlin.Array<FavResponse> {
        val list = arrayListOf<FavResponse>()
        when (reader.peek()) {
            JsonReader.Token.BEGIN_OBJECT -> {
                val obj = readObject(reader, FavResponse::class.java) as FavResponse
                list.add(obj)
            }
            JsonReader.Token.BEGIN_ARRAY -> {
                list.addAll(readArray(reader, FavResponse::class.java))
            }
            else -> {
            }
        }

        return list.toTypedArray()
    }

    private fun <T> readArray(reader: JsonReader, clzz: Class<*>): List<T> {
        val list = ArrayList<T>()
        reader.beginArray()
        while (reader.hasNext()) {
            readObject(reader, clzz)?.let {
                list.add(it as T)
            }
        }
        reader.endArray()

        return list
    }

    private fun <T> readObject(reader: JsonReader, clzz: Class<T>): T? {
        return when (clzz) {
            PhoneInfo::class.java -> moshi.adapter<T>(clzz).fromJson(reader)
            else -> {
                readFavNumList(reader) as T
            }
        }
    }

    private fun readFavNumList(reader: JsonReader): FavResponse {
        reader.beginObject()
        var serviceCd = ""
        var featureCd = ""
        var setSizeNum = ""
        var effectiveDt = ""
        var lastUpdateDt = ""
        val currentList = ArrayList<PhoneInfo>()
        val futureList = ArrayList<PhoneInfo>()

        while (reader.hasNext()) {
            val name = reader.nextName()

            if ("serviceCd".equals(name, true)) {
                serviceCd = reader.nextString()
            }
            if ("featureCd".equals(name, true)) {
                featureCd = reader.nextString()
            }
            if ("setSizeNum".equals(name, true)) {
                setSizeNum = reader.nextString()
            }
            if ("effectiveDt".equals(name, true)) {
                effectiveDt = reader.nextString()
            }
            if ("lastUpdateDt".equals(name, true)) {
                lastUpdateDt = reader.nextString()
            }
            if ("currentList".equals(name, true)) {
                currentList.addAll(readList(reader))
            }
            if ("futureList".equals(name, true)) {
                futureList.addAll(readList(reader))
            }

        }
        reader.endObject()

        return FavResponse(
            serviceCd,
            featureCd,
            setSizeNum,
            effectiveDt,
            lastUpdateDt,
            currentList,
            futureList
        )
    }

    private fun readList(reader: JsonReader): List<PhoneInfo> {
        val list = arrayListOf<PhoneInfo>()
        when (reader.peek()) {
            JsonReader.Token.BEGIN_OBJECT -> {
                val obj = readObject(reader, PhoneInfo::class.java) as PhoneInfo
                list.add(obj)
            }
            JsonReader.Token.BEGIN_ARRAY -> {
                list.addAll(readArray(reader, PhoneInfo::class.java))
            }
            else -> {
            }
        }
        return list
    }

    override fun toJson(writer: JsonWriter, value: Any?) {
        val result = StringBuilder()
        val adapter = moshi.adapter<FavResponse>(FavResponse::class.java)
        writer.beginArray()
        val size = getLength(value)
        for (i in 0 until size) {
            result.append(adapter.toJson(writer, Array.get(value, i) as FavResponse))
        }
        writer.endArray()
    }
}