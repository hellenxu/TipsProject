package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import ca.six.example.Bind;

/**
 * Created by xiaolin on 24/01/17.
 */

public class BindProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;
    private Map<String, String> parameters = new LinkedHashMap<>();

    private static final String METHOD_INJECT = "inject";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(Bind.class);
        for(Element element : annotatedElements){
            if(element instanceof VariableElement){
                Bind bindAnnotation = element.getAnnotation(Bind.class);
                int viewId = bindAnnotation.value();
                VariableElement varElement = (VariableElement) element;
                String varName = varElement.getSimpleName().toString();
                String varType = varElement.asType().toString();
                System.out.println("xxl-id: " + viewId + "; name: " + varName + "; type: " + varType);
            }
        }
        return false;
    }

    private void generateCode(String packageName, String className, Map<String, String> paramList) throws IOException{
        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_INJECT)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Void.class);
        for(String simpleName : paramList.keySet()){
            builder.addParameter(ClassName.get(packageName, simpleName), paramList.get(simpleName));
        }

        builder.addStatement("activity.tv = (TextView)activity.findViewById()");

        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("ClassName")
                .addMethod(builder.build());

        JavaFile javaFile = JavaFile.builder("pkg", typeSpecBuilder.build())
                .build();
        javaFile.writeTo(filer);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Bind.class.getCanonicalName());
        return annotations;
    }
}
