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

    public void generateCode(Filer filer) throws IOException {
        System.out.println("xxl-generate: " + annotatedItems.size());
        if(annotatedItems.size() > 0) {
            TypeElement superClassName = elementsUtils.getTypeElement(superQualifiedName);
            final String factoryClassName = superClassName.getSimpleName() + SUFFIX;
            PackageElement pkgElement = elementsUtils.getPackageOf(superClassName);
            final String pkg = pkgElement.getQualifiedName().toString();

            MethodSpec.Builder methodBuilder =
                    MethodSpec.methodBuilder("getInstance")
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .addParameter(String.class, "id")
                            .returns(TypeName.get(superClassName.asType()))
                            .beginControlFlow("if(id == null)")
                            .addStatement("throw new IllegalArgumentException(\"id should not be null\")")
                            .endControlFlow();

            for (SixAnnotatedClass item : annotatedItems.values()) {
                System.out.println("xxl-return: " + item.getTypeElement().getQualifiedName().toString());
                methodBuilder.beginControlFlow("if ($S.equals(id))", item.getId())
                        .addStatement("return new $L()", item.getTypeElement().getQualifiedName().toString())
                        .endControlFlow();
            }

            methodBuilder.addStatement("throw new IllegalArgumentException($S + id)", "Unknown id = ");

            TypeSpec helloAnnotation = TypeSpec.classBuilder(factoryClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodBuilder.build())
                    .build();

            JavaFile javaFile = JavaFile.builder(pkg, helloAnnotation)
                    .build();

            javaFile.writeTo(filer);
        }
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
        for(String key : annotatedItems.keySet()){
            System.out.println("xxl-item: " + annotatedItems.get(key));
        }
    }

    public void clearItemList(){
        annotatedItems.clear();
    }

    public void setSuperQualifiedName(String superQualifiedName){
        this.superQualifiedName = superQualifiedName;
    }
}
