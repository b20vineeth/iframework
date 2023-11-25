package com.web.framework.util;
 
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
  
// create a custom Annotation
@Retention(RetentionPolicy.RUNTIME)
public
@interface Action {
   
    public String name() default ""; 
}