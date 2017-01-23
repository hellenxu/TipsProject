package com.example;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

/**
 * Created by xiaolin on 23/01/17.
 */

public class CodeGenerator {
    private Map<String, SixAnnotatedClass> annotatedItems = new LinkedHashMap<>();

    private void generateCode(SixAnnotatedClass annotatedClass) throws IOException {
        TypeMirror superClass = annotatedClass.getTypeElement().getSuperclass();
        MethodSpec methodBuilder =
                MethodSpec.methodBuilder("getInstance")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "id")
                        .returns(superClass.getClass())
                        .build();

        TypeSpec helloAnnotation = TypeSpec.classBuilder(annotatedClass.getQualifiedSimpleName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder)
                .build();

        JavaFile javaFile = JavaFile.builder(annotatedClass.getQualifiedClassName(), helloAnnotation)
                .build();

        javaFile.writeTo(System.out);
    }

    private void addAnnotatedClass(SixAnnotatedClass newItem) throws AnnotatedException {
        if(annotatedItems.containsKey(newItem.getId())){
            throw new AnnotatedException("Id conflicts. There is an annotated class has the id %s", newItem.getId());
        }
        annotatedItems.put(newItem.getId(), newItem);
    }
}
