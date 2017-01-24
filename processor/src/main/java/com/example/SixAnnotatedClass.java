package com.example;


import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

import ca.six.example.SixAnnotation;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-09-22.
 */

public class SixAnnotatedClass {
    private String id;
    private String qualifiedClassName;
    private String qualifiedSimpleName;
    private TypeElement typeElement;

    public SixAnnotatedClass(TypeElement typeElement) throws AnnotatedException {
        SixAnnotation annoation = typeElement.getAnnotation(SixAnnotation.class);
        id = annoation.id();

        if(id == null || id.length() == 0){
            throw new AnnotatedException("Annotated Class should not have a empty id");
        }

        this.typeElement = typeElement;
        try {
            Class <?> clazz = annoation.type();
            qualifiedClassName = clazz.getCanonicalName();
            qualifiedSimpleName = clazz.getSimpleName();
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
