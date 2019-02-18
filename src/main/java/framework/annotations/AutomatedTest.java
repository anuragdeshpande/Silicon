package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutomatedTest {
    String Author();
    String Team();
    String[] Centers();
    String FeatureNumber() default "";
    String StoryOrDefectNumber();
    String[] Themes() default "";
    String PI();
    String Iteration();
}



