package com.example;

import com.google.auto.service.AutoService;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import ca.six.example.SixAnnoation;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Element;


@AutoService(Processor.class)
public class SixAnnotationProcessor extends AbstractProcessor{
    private Types types;
    private Elements elements;
    private Filer filer;
    private Messager messager;
    private Map<String, SixAnnotatedClass> annotatedItems = new LinkedHashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment proEnv) {
        super.init(proEnv);
        types = proEnv.getTypeUtils();
        elements = proEnv.getElementUtils();
        filer = proEnv.getFiler();
        messager = proEnv.getMessager();
    }

    private void onProcessFailed(Element element){
        messager.printMessage(Diagnostic.Kind.ERROR, "", element);
    }

    //TODO
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(SixAnnoation.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //TODO
    private boolean checkValidClass(){
        return false;
    }

    //TODO
    private void addAnnotatedClass(SixAnnotatedClass newItem){

    }

    //TODO
    private void generateCode(){

    }
}
