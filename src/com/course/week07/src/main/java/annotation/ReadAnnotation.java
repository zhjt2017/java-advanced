package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ReadAnnotation
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-19 09:55:04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadAnnotation {
}