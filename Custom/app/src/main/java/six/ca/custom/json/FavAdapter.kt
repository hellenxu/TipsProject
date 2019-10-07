package six.ca.custom.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.JsonDataException
import java.lang.reflect.Array
import java.lang.reflect.Array.getLength
import kotlin.collections.ArrayList

/**
 * @author hellenxu
 * @date 2019-10-07
 * Copyright 2019 Six. All rights reserved.
 */
class FavAdapter constructor(private val moshi: Moshi) {

    @FromJson
    fun fromJson(reader: JsonReader): FavResponse {
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
        val list = ArrayList<PhoneInfo>()
        when(reader.peek()) {
            JsonReader.Token.BEGIN_ARRAY -> {
                list.addAll(readArray(reader))
            }

            JsonReader.Token.BEGIN_OBJECT -> {
                readObject(reader)?.let {
                    list.add(it)
                }
            }

            JsonReader.Token.NULL -> {
                list.addAll(emptyList())
            }

            else -> throw JsonDataException()
        }

        return list
    }

    private fun readArray(reader: JsonReader): List<PhoneInfo> {
        val list = ArrayList<PhoneInfo>()
        reader.beginArray()
        while (reader.hasNext()) {
            readObject(reader)?.let {
                list.add(it)
            }
        }
        reader.endArray()

        return list
    }

    private fun readObject(reader: JsonReader): PhoneInfo? {
        return moshi.adapter<PhoneInfo>(PhoneInfo::class.java).fromJson(reader)
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Any): String {
        val result = StringBuilder()
        val adapter = moshi.adapter<FavResponse>(FavResponse::class.java)
        writer.beginArray()
        val size = getLength(value)
        for(i in 0 until size) {
            result.append(adapter.toJson(writer, Array.get(value, i) as FavResponse))
        }
        writer.endArray()
        return result.toString()
    }
}