package com.lizhi.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Classification {

    String DES() default "no description";

    TYPE BUSTYPE() default TYPE.NORMAL;

    enum TYPE {WX, DX, PRZIE, QUESTION,NORMAL};
}


