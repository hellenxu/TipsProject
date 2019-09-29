package six.ca.custom.json;

import androidx.core.util.Pair;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
final class Utils {

    /**
     * Checks if {@code annotations} contains {@code jsonQualifier}.
     * Returns a pair containing the subset of {@code annotations} without {@code jsonQualifier}
     * and the {@code jsonQualified} instance, or null if {@code annotations} does not contain
     * {@code jsonQualifier}.
     */
    static <A extends Annotation> Pair<A, Set<Annotation>> nextAnnotations(
            Set<? extends Annotation> annotations, Class<A> jsonQualifier) {
        if (!jsonQualifier.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(jsonQualifier + " is not a JsonQualifier.");
        }
        if (annotations.isEmpty()) {
            return null;
        }
        for (Annotation annotation : annotations) {
            if (jsonQualifier.equals(annotation.annotationType())) {
                Set<? extends Annotation> delegateAnnotations = new LinkedHashSet<>(annotations);
                delegateAnnotations.remove(annotation);
                //noinspection unchecked Protected by the if statment.
                return new Pair<>((A) annotation, Collections.unmodifiableSet(delegateAnnotations));
            }
            A delegate = findDelegatedAnnotation(annotation, jsonQualifier);
            if (delegate != null) {
                Set<? extends Annotation> delegateAnnotations = new LinkedHashSet<>(annotations);
                delegateAnnotations.remove(annotation);
                return new Pair<>(delegate, Collections.unmodifiableSet(delegateAnnotations));
            }
        }
        return null;
    }

    private static <A extends Annotation> A findDelegatedAnnotation(
            Annotation annotation, Class<A> jsonQualifier) {
        for (Annotation delegatedAnnotation : annotation.annotationType().getAnnotations()) {
            if (jsonQualifier.equals(delegatedAnnotation.annotationType())) {
                //noinspection unchecked
                return (A) delegatedAnnotation;
            }
        }
        return null;
    }

    private Utils() {
        throw new AssertionError("No instances.");
    }
}
