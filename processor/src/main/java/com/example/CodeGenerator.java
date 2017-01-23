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

    public void generateCode(SixAnnotatedClass annotatedClass) throws IOException {
        TypeMirror superClass = annotatedClass.getTypeElement().getSuperclass();
        MethodSpec.Builder methodBuilder =
                MethodSpec.methodBuilder("getInstance")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "id")
                        .returns(superClass.getClass())
                        .beginControlFlow("if(id == null || id.length == 0)")
                        .addStatement("throw new AnnotatedException('id should not be empty')")
                        .endControlFlow();

        for(SixAnnotatedClass item : annotatedItems.values()){
            methodBuilder.beginControlFlow("if(id == $S)", item.getId())
                    .addStatement("return new $T()", item.getTypeElement().getClass())
                    .endControlFlow();
        }

        methodBuilder.beginControlFlow("throw new AnnotatedException('Unknown Id $S')")
                .endControlFlow();

        TypeSpec helloAnnotation = TypeSpec.classBuilder(annotatedClass.getQualifiedSimpleName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();

        JavaFile javaFile = JavaFile.builder(annotatedClass.getQualifiedClassName(), helloAnnotation)
                .build();

        javaFile.writeTo(System.out);
    }

    public void addAnnotatedClass(SixAnnotatedClass newItem) throws AnnotatedException {
        final String id = newItem.getId();
        if(newItem == null || id == null || id.length() == 0){
            throw new AnnotatedException("Invalid AnnotatedClass");
        }

        if(annotatedItems.containsKey(newItem.getId())){
            throw new AnnotatedException("Id conflicts. There is an annotated class has the id %s", newItem.getId());
        }
        annotatedItems.put(newItem.getId(), newItem);
    }

    public void clearItemList(){
        annotatedItems.clear();
    }
}
