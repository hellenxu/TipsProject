package six.ca.custom.json;

import androidx.core.util.Pair;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.Types;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
@JsonQualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE
})
public @interface Wrap {

    String[] path();

    boolean failOnNotFound() default true;

    JsonAdapter.Factory ADAPTER_FACTORY = (type, annotations, moshi) -> {
        Pair<Wrap, Set<Annotation>> nextAnnotations = Utils.nextAnnotations(annotations,
                Wrap.class);
        if (nextAnnotations == null) {
            return null;
        }
        JsonAdapter<Object> adapter;

        if (type instanceof GenericArrayType) {
            Class clazz = Types.getRawType(type).getComponentType();
            adapter = new CustomArrayJsonAdapter(moshi, clazz);
        } else {
            adapter = moshi.adapter(type, nextAnnotations.second);
        }

        Wrap wrapped = nextAnnotations.first;
        return new WrapJsonAdapter<>(adapter, wrapped.path(), wrapped.failOnNotFound());
    };

    final class Factory {

        public static Wrap create(final String... path) {
            return create(true, path);
        }

        public static Wrap create(final boolean failOnNotFound, final String... path) {
            return new Wrap() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return Wrap.class;
                }

                @Override
                public String[] path() {
                    return path;
                }

                @Override
                public boolean failOnNotFound() {
                    return failOnNotFound;
                }

                @Override
                public int hashCode() {
                    int result = Arrays.hashCode(path);
                    result = 43 * result + (failOnNotFound ? 1 : 0);
                    return result;
                }

                @Override
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }

                    Wrap wrapped = (Wrap) obj;
                    return Arrays.equals(path, wrapped.path())
                            && failOnNotFound == wrapped.failOnNotFound();
                }

                @NotNull
                @Override
                public String toString() {
                    return "Wrapped("
                            + "path=" + Arrays.asList(path)
                            + ", failOnNotFound=" + failOnNotFound
                            + ")";
                }
            };
        }

        private Factory() {
            throw new AssertionError("No instances.");
        }
    }
}
