package myTests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Метод помеченный аннотацией исполняется во время тестирования
 * в порядке установленном в элементе аннотации priority.
 * Метод на который применяется аннотация должен быть публичным, возвращать void
 * и не должен иметь параметров
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
