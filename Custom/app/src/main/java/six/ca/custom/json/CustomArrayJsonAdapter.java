package six.ca.custom.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
public class CustomArrayJsonAdapter<T> extends JsonAdapter<T[]> {
    private Class<T> clazz;
    private JsonAdapter<T> adapter;

    public CustomArrayJsonAdapter(JsonAdapter<T> adapter, Class<T> clazz) {
        this.clazz = clazz;
        this.adapter = adapter;
    }

    @Override
    public T[] fromJson(@NotNull JsonReader reader) throws IOException {
        switch (reader.peek()) {
            case BEGIN_ARRAY:
                return readArray(reader);
            case BEGIN_OBJECT:
                return readObject(reader);
            case NULL:
                return (T[])Array.newInstance(clazz, 0);
            default:
                throw new JsonDataException();
        }
    }

    private T[] readArray(JsonReader reader) throws IOException {
        List<T> list = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            T item = (T) adapter.fromJson(reader);
            list.add(item);
        }
        reader.endArray();

        return list.toArray((T[])Array.newInstance(clazz, list.size()));
    }

    private T[] readObject(JsonReader reader) throws IOException {
        List<T> list = new ArrayList<>();
        T item = adapter.fromJson(reader);
        list.add(item);
        return list.toArray((T[])Array.newInstance(clazz, list.size()));
    }

    @Override
    public void toJson(@NotNull JsonWriter writer, T[] value) throws IOException {
        writer.beginArray();
        for (int i = 0, size = Array.getLength(value); i < size; i ++) {
            adapter.toJson(writer, (T) Array.get(value, i));
        }
        writer.endArray();
    }
}
