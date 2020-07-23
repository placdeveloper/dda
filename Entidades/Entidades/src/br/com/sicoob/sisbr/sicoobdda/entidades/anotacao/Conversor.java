package br.com.sicoob.sisbr.sicoobdda.entidades.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kaio.rocha
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Conversor {

    String vo() default "";

    String entidade() default "";

    String dto() default "";

    String DTO() default "";

}
