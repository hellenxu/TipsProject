package com.example;


import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-01-23.
 */

public class CodeGenerator {
    private Map<String, SixAnnotatedClass> annotatedItems = new LinkedHashMap<>();
    private Elements elementsUtils;
    private String superQualifiedName;

    private static final String SUFFIX = "Factory";

    public CodeGenerator(Elements elementsUtils, String superQualifiedName) {
        this.elementsUtils = elementsUtils;
        this.superQualifiedName = superQualifiedName;
    }

    //TODO return interface
    public void generateCode(SixAnnotatedClass annotatedClass, Filer filer) throws IOException {
        final String factoryClassName = annotatedClass.getQualifiedSimpleName() + SUFFIX;
        TypeElement superClassName = elementsUtils.getTypeElement(superQualifiedName);
        PackageElement pkgElement = elementsUtils.getPackageOf(superClassName);
        final String pkg = pkgElement.getQualifiedName().toString();

        System.out.println("xxl-generate00: " + superClassName.getQualifiedName().toString());
        MethodSpec.Builder methodBuilder =
                MethodSpec.methodBuilder("getInstance")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "id")
                        .returns(TypeName.get(superClassName.asType()))
                        .beginControlFlow("if(id == null)")
                        .addStatement("throw new IllegalArgumentException(\"id should not be null\")")
                        .endControlFlow();

        for(SixAnnotatedClass item : annotatedItems.values()){
            System.out.println("xxl-size: " + annotatedItems.size());
            System.out.println("xxl-condition00: " + item.getTypeElement().getQualifiedName().toString());
            methodBuilder.beginControlFlow("if ($S.equals(id))", item.getId())
                    .addStatement("return new $L()", item.getTypeElement().getQualifiedName().toString())
                    .endControlFlow();
        }

        methodBuilder.addStatement("throw new IllegalArgumentException($S + id)", "Unknown id = ");

        TypeSpec helloAnnotation = TypeSpec.classBuilder(factoryClassName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();

        System.out.println("xxl-pkg: " + annotatedClass.getQualifiedClassName());
        JavaFile javaFile = JavaFile.builder(pkg, helloAnnotation)
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
