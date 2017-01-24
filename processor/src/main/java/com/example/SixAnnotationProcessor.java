package com.example;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import ca.six.example.SixAnnotation;


/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-01-23.
 */

@AutoService(Processor.class)
public class SixAnnotationProcessor extends AbstractProcessor{
    private Types types;
    private Elements elements;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment proEnv) {
        super.init(proEnv);
        System.out.println("xxl-init00");
        types = proEnv.getTypeUtils();
        elements = proEnv.getElementUtils();
        filer = proEnv.getFiler();
        messager = proEnv.getMessager();
    }

    private void onProcessFailed(Element element, Exception e){
        messager.printMessage(Diagnostic.Kind.ERROR, "Processing Failed " + e.toString(), element);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        System.out.println("xxl-process00");
        Set<? extends Element> sixAnnotatedClass = roundEnv.getElementsAnnotatedWith(SixAnnotation.class);
        CodeGenerator generator = new CodeGenerator(elements, "");
        for(Element element : sixAnnotatedClass){
            if(element instanceof TypeElement){
                try {
                    TypeElement typeElement = (TypeElement) element;
                    SixAnnotatedClass annotatedClass = new SixAnnotatedClass(typeElement);
                    if (checkValidClass(annotatedClass)){
                        generator.setSuperQualifiedName(annotatedClass.getQualifiedClassName());
                        generator.addAnnotatedClass(annotatedClass);
                    } else {
                        return true;
                    }
                }catch (AnnotatedException e){
                    onProcessFailed(element, e);
                }
            }
        }

        try {
            generator.generateCode(filer);
        } catch (IOException e) {
            onProcessFailed(null, e);
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(SixAnnotation.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // 0) must have public modifier; 1) no abstract class; 2)no interface;
    // 3) must have an empty constructor; 4) type in annotation must be the same of superclass
    private boolean checkValidClass(SixAnnotatedClass item) throws AnnotatedException {
        System.out.println("xxl-start check valid class");
        TypeElement itemElement = item.getTypeElement();

        // check public
        if(!itemElement.getModifiers().contains(Modifier.PUBLIC)){
            throw new AnnotatedException("Annotated class %s must have modifier Public.",
                    itemElement.getQualifiedName().toString());
        }

        // check abstract
        if(itemElement.getModifiers().contains(Modifier.ABSTRACT)){
            throw new AnnotatedException("Annoated class %s cannot be an abstract class.",
                    itemElement.getQualifiedName().toString());
        }

        TypeElement superClassElement = elements.getTypeElement(item.getQualifiedClassName());
        if(superClassElement.getKind() == ElementKind.INTERFACE){ // check no interface
            if(!itemElement.getInterfaces().contains(superClassElement.asType())){
                throw new AnnotatedException("Class %s must implement methods of %s.",
                        itemElement.getQualifiedName().toString(),
                        superClassElement.getQualifiedName().toString());
            }
        }
//        else {
//            // check empty constructor
//            TypeElement currentClass = itemElement;
//            while (true) {
//                TypeMirror superClassType = currentClass.getSuperclass();
//
//                if (superClassType.getKind() == TypeKind.NONE) {
//                    // Basis class (java.lang.Object) reached, so exit
//                    throw new AnnotatedException("The class %s annotated with @%s must inherit from %s",
//                            itemElement.getQualifiedName().toString(), SixAnnoation.class.getSimpleName(),
//                            item.getQualifiedClassName());
//                }
//
//                if (superClassType.toString().equals(item.getQualifiedClassName())) {
//                    // Required super class found
//                    break;
//                }
//
//                // Moving up in inheritance tree
//                currentClass = (TypeElement) types.asElement(superClassType);
//            }

        List<? extends Element> enclosedElements = itemElement.getEnclosedElements();
        for(Element enclosedElement : enclosedElements){
            if(enclosedElement.getKind() == ElementKind.CONSTRUCTOR){
                ExecutableElement construcElement = (ExecutableElement) enclosedElement;
                if(construcElement.getParameters().size() == 0
                        && construcElement.getModifiers().contains(Modifier.PUBLIC)) {
                    return true;
                }
            }
        }

        return false;
    }
}
