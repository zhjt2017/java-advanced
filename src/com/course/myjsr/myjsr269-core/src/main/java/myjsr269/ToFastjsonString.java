package myjsr269;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * generate json string (using fastjson) for model
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-21 11:25:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ToFastjsonString {
}
