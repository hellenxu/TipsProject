package com.example;


import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

import ca.six.example.SixAnnoation;

/**
 * Created by xiaolin on 23/01/17.
 */

public class SixAnnotatedClass {
    private String id;
    private String qualifiedClassName;
    private String qualifiedSimpleName;
    private TypeElement typeElement;

    public SixAnnotatedClass(TypeElement typeElement) throws AnnotatedException {
        SixAnnoation annoation = typeElement.getAnnotation(SixAnnoation.class);
        id = annoation.id();

        if(id == null || id.length() == 0){
            throw new AnnotatedException("Annotated Class should not have a empty id");
        }

        this.typeElement = typeElement;
        try {
            qualifiedClassName = typeElement.getQualifiedName().toString();
            qualifiedSimpleName = typeElement.getSimpleName().toString();
        } catch (MirroredTypeException mte){
            DeclaredType declaredType = (DeclaredType) mte.getTypeMirror();
            TypeElement annotatedClass = (TypeElement) declaredType.asElement();
            qualifiedClassName = annotatedClass.getQualifiedName().toString();
            qualifiedSimpleName = annotatedClass.getSimpleName().toString();
        }
    }

    public String getId() {
        return id;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public String getQualifiedSimpleName() {
        return qualifiedSimpleName;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }
}
