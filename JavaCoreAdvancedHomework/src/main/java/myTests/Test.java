package myTests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Метод помеченный данной аннотацией исполняется во время тестирования
 * в порядке установленном в элементе аннотации priority
 * @author skhanov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	
	/**
	 * Приоритет исполнения тестового метода. Чем ниже значение, тем быстрее исполнится метод
	 */
	int priority() default 1;
	
}
