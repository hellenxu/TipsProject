package six.ca.custom.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
public class CustomArrayJsonAdapter<T> extends JsonAdapter<T[]> {
    private Class<T> clazz;
    private JsonAdapter<T> adapter;
    private Moshi moshi;

    public CustomArrayJsonAdapter(Moshi moshi, Class<T> clazz) {
        this.clazz = clazz;
        this.adapter = moshi.adapter(clazz);
        this.moshi = moshi;
    }

    @Override
    public T[] fromJson(@NotNull JsonReader reader) throws IOException {
        switch (reader.peek()) {
            case BEGIN_ARRAY:
                return (T[]) readArray(reader, clazz);
            case BEGIN_OBJECT:
                return (T[]) readObject(reader, clazz);
            case NULL:
                return (T[]) Array.newInstance(clazz, 0);
            default:
                throw new JsonDataException();
        }
    }

    private Object[] readArray(JsonReader reader, Class clazz) throws IOException {
        List<Object> list = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            Object[] objectResult = readObject(reader, clazz);
            list.addAll(Arrays.asList(objectResult));
        }
        reader.endArray();

        Object[] array = (Object[]) Array.newInstance(clazz, list.size());
        for (int i = 0, size = list.size(); i < size; i++) {
            Array.set(array, i, list.get(i));
        }

        return array;
    }

    private Object[] readObject(JsonReader reader, Class clzz) {
        if (clzz == FavResponse.class ) {
            return new FavAdapter(moshi).fromJson(reader);
        }
        return new Object[]{};
    }

    @Override
    public void toJson(@NotNull JsonWriter writer, T[] value) throws IOException {
        writer.beginArray();
        for (int i = 0, size = Array.getLength(value); i < size; i++) {
            adapter.toJson(writer, (T) Array.get(value, i));
        }
        writer.endArray();
    }
}
