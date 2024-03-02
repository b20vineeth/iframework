package com.web.framework.cache;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheableAspect {
/*
    @AfterReturning(
        pointcut = "@annotation(org.springframework.cache.annotation.Cacheable)",
        returning = "result"
    )
	
    @Async 
   public void afterCacheable(JoinPoint joinPoint, Object result) {
		// Perform actions after @Cacheable method returns a value from cache
		// Add your additional logic here
		// Extracting method signature and cacheable annotation
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Cacheable cacheableAnnotation = methodSignature.getMethod().getAnnotation(Cacheable.class);

		// Retrieving key value and cache name
		String[] cacheNames = cacheableAnnotation.value();
		Object obj = joinPoint.getArgs()[0]; // Assuming the key is the first method argument
		try {
			String keyValue = evaluateExpression(obj, cacheableAnnotation.key().toString());
			System.out.println("Cache name: " + cacheNames[0]); // Assuming only one cache name is used
			System.out.println("Key value: " + keyValue);
		
		} catch (NoSuchFieldException |IllegalAccessException e) {
		 
		}  

		

	}*/
	
	 
//	 @Async 
//	 @AfterReturning(
//			    pointcut = "@annotation(org.springframework.cache.annotation.Cacheable)",
//			    returning = "result"
//			)
	 public void cacheLoadusername(JoinPoint joinPoint, Object result) throws Throwable {
			  		
		 MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			Cacheable cacheableAnnotation = methodSignature.getMethod().getAnnotation(Cacheable.class);

			String[] cacheNames = cacheableAnnotation.value();
			Object obj = joinPoint.getArgs()[0]; // Assuming the key is the first method argument
			try {
				String keyValue = evaluateExpression(obj, cacheableAnnotation.key().toString());
				System.out.println("Cache name: " + cacheNames[0]); // Assuming only one cache name is used
				System.out.println("Key value: " + keyValue.replace("'", ""));
			
			} catch (NoSuchFieldException |IllegalAccessException e) {
			 
			}   
	 
	 }
	     
    

    public  String evaluateExpression(Object obj, String expression) throws NoSuchFieldException, IllegalAccessException {
        // Split the expression based on '+' operator
        String[] parts = expression.split("\\+");
        StringBuilder resultBuilder = new StringBuilder();

        for (String part : parts) {
            // Trim the part and remove leading and trailing whitespaces
            part = part.trim();
            
            if (part.startsWith("#")) {
                // Extract property name
                String propertyName = part.substring(part.lastIndexOf('.') + 1);
                
                // Get the value of the property using Reflection
                Field field = obj.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                Object value = field.get(obj);
                
                // Append the value to the result
                resultBuilder.append(value);
            } else {
                // Directly append the string part
                resultBuilder.append(part);
            }
        }

        return resultBuilder.toString();
    
}
}
