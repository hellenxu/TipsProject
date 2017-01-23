package com.example;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

/**
 * Created by xiaolin on 23/01/17.
 */

public class CodeGenerator {
    private Map<String, SixAnnotatedClass> annotatedItems = new LinkedHashMap<>();

    //TODO 1) PackageName; 2) Import AnnotatedException; 3) return instance of Dinner.
    public void generateCode(SixAnnotatedClass annotatedClass, Filer filer) throws IOException {
        System.out.println("xxl-generate00");
        TypeMirror superClass = annotatedClass.getTypeElement().getSuperclass();
        MethodSpec.Builder methodBuilder =
                MethodSpec.methodBuilder("getInstance")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "id")
                        .returns(superClass.getClass())
                        .beginControlFlow("if(id == null)")
                        .addStatement("throw new AnnotatedException(\"id should not be null\")")
                        .endControlFlow();

        for(SixAnnotatedClass item : annotatedItems.values()){
            System.out.println("xxl-condition00");
            methodBuilder.beginControlFlow("if ($S.equals(id))", item.getId())
                    .addStatement("return new $T()", item.getTypeElement().getClass())
                    .endControlFlow();
        }

        methodBuilder.addStatement("throw new AnnotatedException(\"Unknow Id: $S \")", annotatedClass.getId());

        TypeSpec helloAnnotation = TypeSpec.classBuilder(annotatedClass.getQualifiedSimpleName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();

        System.out.println("xxl-pkg: " + annotatedClass.getQualifiedClassName());
        JavaFile javaFile = JavaFile.builder(annotatedClass.getQualifiedClassName(), helloAnnotation)
                .build();

        javaFile.writeTo(filer);
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
